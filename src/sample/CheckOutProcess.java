package sample;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CheckOutProcess {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;


    private String coDate,coTime,ciDate,CusName;
    private long days=0, total=0;

    CheckOutProcess(String coDate,String coTime){
        this.coDate =coDate;
        this.coTime=coTime;
    }


    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private int selectPrice(int no){
        if(no<=3){
            return 3000;
        }else if(no<=6){
            return 1000;
        }else{
            return 1500;
        }
    }

    private String validateInputs(){
        String myStatement;

        if(!(coDate.equals("") || coTime.equals(""))){
            myStatement="select * from reservation where ReservationID="+MainWindowController.reservationID;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(myStatement);
                while (rs.next()) {
                    ciDate = rs.getString("Check_in_date");
                    char ch;
                    for(int i=0;i<10;i++){
                        ch = coDate.charAt(i);
                        if(ch == '/'){
                            coDate = coDate.replace('/','-');
                        }
                    }

                    LocalDate localDate1 = LocalDate.parse(ciDate);
                    LocalDate localDate2 = LocalDate.parse(coDate);

                    days = Period.between(localDate1, localDate2).getDays();
                    total = days * selectPrice(rs.getInt("RoomNo"));
                    return "Rs. "+ total;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }

        return null;

    }

    public String checkout(){
        int Rid = MainWindowController.reservationID;

        String query = "update reservation set Check_out_date = '"+coDate+"', Check_out_time = '"+coTime+"' where ReservationID='"+Rid+"'";
        try {
            PreparedStatement pstn = con.prepareStatement(query);
//            pstn.setDate(1, Date.valueOf(coDate));
//            pstn.setTime(2, Time.valueOf(coTime));
            pstn.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int Bill = MainWindowController.billId;
        String queryy = "update bill set Amount ='"+total+"' ";

        try {
            PreparedStatement pstn = con.prepareStatement(queryy);
            pstn.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String myStatement = "select * from bill where BillID= '"+Bill+"'";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);
            while (rs.next()){
                CusName = rs.getString("CustomerID");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        String queryinsret = "insert into ledger (Description,paid,Date,CustomerID,BillID) values ('Reservation','"+total+"','"+coDate+"','"+CusName+"','"+Bill+"')";
        try {
            PreparedStatement pstn = con.prepareStatement(queryinsret);

            pstn.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "success";
    }

}
