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

    @FXML Label rmNo;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public void initialize(URL location, ResourceBundle resources) {
        loadDetails();
    }

    private void loadDetails(){
        LocalDateTime now = LocalDateTime.now();

        String myStatement;


        myStatement="select * from bill,bill_reservation,reservation where BillID = Bill_id and Reservation_id = ReservationID and BillId = '"+MainWindowController.billID+ "' and ReservationID = '"+MainWindowController.reservationID+"'";


        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);

            while(rs.next()){

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(myStatement);

    }
}
