package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class EventWindowController  implements Initializable {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    @FXML Label evName,evId,evAdd,evTp,evPkg,evNop,evTitle,evTotal,evPaid,evTbp,evTotal1;
    @FXML TextField evPay;
    @FXML Button evClear,evCheck;
    @FXML Pane totPaidPanel,notPaidPanel;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notPaidPanel.toFront();
        getDetails();
    }

    private void getDetails(){
        String myStatement="Select * from customer,event,bill_event,bill where Bill_id=bill.BillID and Event_id=event.EventID and bill.CustomerID = customer.CustomerID and Event_id='"+MainWindowController.eventID+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);
            while (rs.next()) {
                MainWindowController.billId=rs.getInt("Bill_id");
                evTitle.setText(rs.getString("event.date")+" / "+rs.getString("event.Time"));
                evName.setText(rs.getString("Name"));
                evId.setText(rs.getString("NIC"));
                evAdd.setText(rs.getString("Address"));
                evTp.setText(rs.getString("TP"));
                int pkg=rs.getInt("event.MenuType");
                if (pkg == 1) {
                    evPkg.setText("Package 1 - Rs. 990 per Plate");
                } else if (pkg == 2) {
                    evPkg.setText("Package 2 - Rs. 790 per Plate");
                } else if (pkg == 3) {
                    evPkg.setText("Package 3 - Rs. 690 per Plate");
                } else {

                }
                evNop.setText(rs.getString("number_of_plates"));
                evTotal.setText("Rs. "+decimalFormat.format(rs.getFloat("Amount")));
                String my="select * from ledger,bill where ledger.BillID=bill.BillID and ledger.BillID='"+rs.getString("bill.BillID")+"'";
                float total=0,tbp=0;
                try {
                    stmt = con.createStatement();
                    ResultSet rs2 = stmt.executeQuery(my);
                    while (rs2.next()) {
                        total=total+ rs2.getFloat("paid");
                    }
                    tbp=rs.getFloat("Amount")-total;
                    if(total >= rs.getFloat("Amount")){
                        totPaidPanel.toFront();
                        evTotal1.setText("Rs. "+decimalFormat.format(rs.getFloat("Amount")));
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                evPaid.setText("Rs. "+decimalFormat.format(total));
                evTbp.setText("Rs. "+decimalFormat.format(tbp));
                evPay.setText(decimalFormat.format(tbp));



            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void evClearOnAction(ActionEvent event){
        evPay.setText("");
    }

    public void reCancelOnAction(ActionEvent event) throws IOException {
        MainWindowController.process = 2;
        Parent root3 = FXMLLoader.load(getClass().getResource("ConfirmationWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Confirm");
        stage.setScene(new Scene(root3));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
