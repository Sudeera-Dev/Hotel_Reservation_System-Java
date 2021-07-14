package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CheckOutWindowController implements Initializable{
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    @FXML Label rmNo,coName,coAdd,coTime,coId,coVehino,coSelroom,coTp,ciTotal,coRes,ciTotal1,coTime1;
    @FXML TextField cooTime;
    @FXML DatePicker cooDate;
    @FXML Button coCal,ciCancel,coPay;
    @FXML Pane rmReservePanel,coPan1,coPan2;

    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();

    String conTime,conDate;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public void initialize(URL location, ResourceBundle resources) {
        coPan2.setVisible(false);
        coPan1.setVisible(true);
        rmReservePanel.toBack();
        loadDetails();
        checkProcess();
        conTime = cooTime.getText();
        conDate = date.format(cooDate.getValue());
        CheckOutProcess co = new CheckOutProcess(conDate,conTime);
        ciTotal.setText(co.mainProcess());


    }

    private void checkProcess(){
        String myStatement="select * from reservation,customer,bill_reservation,bill where Bill_id=BillID and Reservation_id='"+MainWindowController.reservationID+"' and reservation.CustomerID=customer.CustomerID  and ReservationID = '"+MainWindowController.reservationID+"'";


        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);

            while(rs.next()){
                int i=0;
                String cod=rs.getString("Check_out_date");
                if (!(cod == null)){
                    i=1;
                }
                System.out.println(i);
                if(i == 1) {
                    rmReservePanel.toFront();
                    ciTotal1.setText(rs.getString("Amount"));
                    coTime1.setText(rs.getString("Check_out_date"));
                    coPan2.setVisible(true);
                    coPan1.setVisible(false);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(myStatement);

    }

    private void loadDetails(){
        conTime=time.format(now);
        conDate=date.format(now);
        cooDate.setValue(LocalDate.now());
        cooTime.setText(time.format(now).toString());
        LocalDateTime now = LocalDateTime.now();

        String myStatement;


        myStatement="select * from reservation,customer,bill_reservation,bill where Bill_id=BillID and Reservation_id='"+MainWindowController.reservationID+"' and reservation.CustomerID=customer.CustomerID  and ReservationID = '"+MainWindowController.reservationID+"'";


        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);

            while(rs.next()){
                MainWindowController.billId=rs.getInt("BillID");
                System.out.println(MainWindowController.billId);
                rmNo.setText("Room "+rs.getString("RoomNo"));
                coName.setText(rs.getString("Name"));
                coAdd.setText(rs.getString("Address"));
                coId.setText(rs.getString("NIC"));
                coVehino.setText(rs.getString("VehicleNum"));
                coTp.setText(rs.getString("TP"));
                coTime.setText(rs.getString("Check_in_date"));
                if(rs.getInt("RoomNo") == 1){
                    coSelroom.setText("Room 1 - AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 2){
                    coSelroom.setText("Room 2 - AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 3){
                    coSelroom.setText("Room 3 - AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 4){
                    coSelroom.setText("Room 4 - AC - Single Bed");
                }else if(rs.getInt("RoomNo") == 5){
                    coSelroom.setText("Room 5 - AC - Single Bed");
                }else if(rs.getInt("RoomNo") == 6){
                    coSelroom.setText("Room 6 - AC - Single Bed");
                }else if(rs.getInt("RoomNo") == 7){
                    coSelroom.setText("Room 7 - non/AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 8){
                    coSelroom.setText("Room 8 - non/AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 9){
                    coSelroom.setText("Room 9 - non/AC - Double Bed");
                }else if(rs.getInt("RoomNo") == 10){
                    coSelroom.setText("Room 10 - non/AC - Double Bed");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(myStatement);

    }

    public void coCalOnAction(ActionEvent event){
        conTime = cooTime.getText();
        conDate = date.format(cooDate.getValue());
        CheckOutProcess co = new CheckOutProcess(conDate,conTime);
        ciTotal.setText(co.mainProcess());

    }

    public void coCancelOnAction(ActionEvent event) throws IOException {
        MainWindowController.process = 1;
        Parent root3 = FXMLLoader.load(getClass().getResource("ConfirmationWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Confirm");
        stage.setScene(new Scene(root3));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }

    public void coPayOnAction(ActionEvent event){
        conTime = cooTime.getText();
        conDate = date.format(cooDate.getValue());
        CheckOutProcess co = new CheckOutProcess(conDate,conTime);
        ciTotal.setText(co.mainProcess());
        coRes.setText( co.checkout());


    }
}
