package sample;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckinProcess {

    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    private String name,id,add,vehino,tp,neTime,nDate,cudate;
    private int room, guest=0;
    private String type;


    CheckinProcess(String name,String id,String add,String vehino,String tp,String neTime,String nDate,int room,String cuDate,int guest){
        this.name=name;
        this.id=id;
        this.add= add;
        this.vehino=vehino;
        this.tp =tp;
        this.nDate=nDate;
        this.neTime=neTime;
        this.room=room;
        this.cudate=cuDate;
        this.guest = guest;
    }

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private String validateInputs(){
        if (name.equals("") || id.equals("") || add.equals("") || tp.equals("") || guest == 0) {
            return "one or more fields are empty";
        } else {
            if(room != 0){
                String regex = "\\d{10}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tp);
                if(matcher.matches()){
                    if(id.length() > 9){

                    }else{
                        return "invalid id";
                    }
                }else{
                    return "invalid Phone number";
                }
            }else{
                return "Select a room";
            }
        }
        return CheckIn();
    }

    private String CheckIn(){
        int flag=0,cuid=0,resid=0,bilid=0;
        String myStatement;
        myStatement = "select * from customer where NIC = '"+id+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);
            while (rs.next()) {
                flag=1;
                cuid=rs.getInt("CustomerID");
            }

            if(flag==0){
                myStatement = "INSERT INTO customer ( Name,Address,VehicleNum,TP,NIC) VALUES('"+name+"','"+add+"','"+vehino+"','"+tp+"','"+id+"')";

                stmt = con.createStatement();
                PreparedStatement psn = con.prepareStatement(myStatement);
                psn.execute();

                myStatement="select MAX(CustomerID) AS id from customer";
                stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery(myStatement);
                while (rs2.next()) {
                    cuid=rs2.getInt("id");
                    myStatement="update customer set VehicleNum='"+vehino+"',TP='"+tp+"' where CustomerID='"+cuid+"'";
                    PreparedStatement pstn = con.prepareStatement(myStatement);
                    pstn.executeUpdate();
                }

            }
            if (room <= 3) {
                type = "AC/DoubleBed";
            } else if (room <= 6) {
                type = "NonAC/SingleBed";
            } else if (room <= 10) {
                type = "NonAC/DoubleBed";
            }

            myStatement="INSERT INTO reservation ( Type,RoomNo,NumofGuests,CustomerID,Check_in_date,Check_in_time) VALUES('"+type+"','"+room+"','"+guest+"','"+cuid+"','"+nDate+"','"+neTime+"')";
            stmt = con.createStatement();
            PreparedStatement psn = con.prepareStatement(myStatement);
            psn.execute();

            myStatement="select MAX(ReservationID) AS id from reservation";
            stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery(myStatement);
            while (rs2.next()) {
                resid=rs2.getInt("id");
            }

            myStatement="INSERT INTO bill( Date,CustomerID) VALUES('"+cudate+"','"+cuid+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();

            myStatement="select MAX(BillID) AS id from bill";
            stmt = con.createStatement();
            rs2 = stmt.executeQuery(myStatement);
            while (rs2.next()) {
                bilid=rs2.getInt("id");
            }

            myStatement="INSERT INTO bill_reservation( Bill_id,Reservation_id ) VALUES('"+bilid+"','"+resid+"')";
            stmt = con.createStatement();
            psn = con.prepareStatement(myStatement);
            psn.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "success";
    }
}
