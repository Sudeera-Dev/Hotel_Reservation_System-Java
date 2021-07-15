package sample;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventReservation {

    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    private String name,add,nop,Id,tp;
    private double discount,total,payment;
    private int pkg;

    EventReservation(String Id,double payment){
        this.Id=Id;
        this.payment=payment;
    }

    EventReservation(String name,String add,String nop,String Id,String tp,double discount,double total,double payment,int pkg){
        this.name=name;
        this.add=add;
        this.nop=nop;
        this.Id=Id;
        this.tp=tp;
        this.discount=discount;
        this.total=total;
        this.payment=payment;
        this.pkg=pkg;
    }

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private String validateInputs(){
        if (name.equals("") || Id.equals("") || add.equals("") || tp.equals("") || payment == 0) {
            return "one or more fields are empty";
        } else {
            if(total != 0){
                String regex = "\\d{10}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tp);
                if(matcher.matches()){
                    if(Id.length() > 9){

                    }else{
                        return "invalid id";
                    }
                }else{
                    return "invalid Phone number";
                }
            }else{
                return "Please calculate before checking out";
            }
        }
        return resreveDate();
    }

    private String resreveDate(){
        int flag=0,cuid=0,resid=0,bilid=0,eid=0;
        String myStatement;

        myStatement = "select * from customer where NIC = '"+Id+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);
            while (rs.next()) {
                flag=1;
                cuid=rs.getInt("CustomerID");
            }

            if(flag==0){
                myStatement = "INSERT INTO customer ( Name,Address,TP,NIC) VALUES('"+name+"','"+add+"','"+tp+"','"+Id+"')";

                stmt = con.createStatement();
                PreparedStatement psn = con.prepareStatement(myStatement);
                psn.execute();

                myStatement="select MAX(CustomerID) AS id from customer";
                stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery(myStatement);
                while (rs2.next()) {
                    cuid=rs2.getInt("id");
                    myStatement="update customer set TP='"+tp+"' where CustomerID='"+cuid+"'";
                    PreparedStatement pstn = con.prepareStatement(myStatement);
                    pstn.executeUpdate();
                }

            }


            myStatement="INSERT INTO Customer ( Name,Address,TP,NIC) VALUES('"+name+"','"+add+"','"+tp+"','"+Id+"')";
            stmt = con.createStatement();
            PreparedStatement psn = con.prepareStatement(myStatement);
            psn.execute();

                myStatement="select MAX(CustomerID) AS id from customer";
                stmt = con.createStatement();
                ResultSet rs3 = stmt.executeQuery(myStatement);
                while (rs3.next()) {
                    cuid=rs3.getInt("id");
                }

            String date = MainWindowController.ercDate;
            String daynight = MainWindowController.ercTime;
            myStatement="INSERT INTO Event(MenuType,total,Discount,CustomerID,number_of_plates,time,date) VALUES('"+pkg+"','"+total+"','"+discount+"','"+cuid+"','"+nop+"','"+daynight+"','"+date+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();

                myStatement="select MAX(EventID) AS id from Event";
                stmt = con.createStatement();
                ResultSet rs4 = stmt.executeQuery(myStatement);
                while (rs4.next()) {
                    eid=rs4.getInt("id");
                }



            String bdate = MainWindowController.cuDate;
            myStatement="INSERT INTO bill ( Date,Amount,CustomerID) VALUES('"+bdate+"','"+total+"','"+cuid+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();

                myStatement="select MAX(BillID) AS id from bill";
                stmt = con.createStatement();
                ResultSet rs5 = stmt.executeQuery(myStatement);
                while (rs5.next()) {
                    bilid=rs5.getInt("id");
                }

            myStatement="INSERT INTO bill_event ( Bill_id,Event_id) VALUES('"+bilid+"','"+eid+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();

            myStatement="INSERT INTO ledger(Description,paid,Date,CustomerID,BillID ) VALUES('Event Reservation','"+payment+"','"+bdate+"','"+cuid+"','"+bilid+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }





        return "success";

    }

    public void Payment(){

    }

}
