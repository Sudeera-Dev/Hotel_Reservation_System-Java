package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CancelReservation {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;
    String myStatement;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public void CancelRoomReservation(){
        myStatement="DELETE FROM bill where BillID='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM reservation where ReservationID='"+ MainWindowController.reservationID+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM bill_reservation where Bill_id='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM ledger where BillID='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void CancelEventReservation(){
        myStatement="DELETE FROM event where EventID='"+ MainWindowController.eventID+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM bill where BillID='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM bill_event where Bill_id='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        myStatement="DELETE FROM ledger where BillID='"+ MainWindowController.billId+"'";
        try {
            stmt = con.createStatement();
            stmt.execute(myStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
