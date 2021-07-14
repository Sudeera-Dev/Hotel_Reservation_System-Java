package sample;

import javafx.beans.property.SimpleStringProperty;

public class Ledger {
    private SimpleStringProperty repID;
    private SimpleStringProperty repDesc;
    private SimpleStringProperty repCuID;
    private SimpleStringProperty repCuName;
    private SimpleStringProperty repDate;
    private SimpleStringProperty repPaid;


    Ledger(String repID,String repDesc,String repCuID,String repCuName,String repDate,String repPaid){
        this.repID= new SimpleStringProperty(repID);
        this.repDesc= new SimpleStringProperty(repDesc);
        this.repCuID= new SimpleStringProperty(repCuID);
        this.repCuName= new SimpleStringProperty(repCuName);
        this.repDate= new SimpleStringProperty(repDate);
        this.repPaid= new SimpleStringProperty(repPaid);

    }

    public String getRepCuID() {
        return repCuID.get();
    }

    public String getRepCuName() {
        return repCuName.get();
    }

    public String getRepDate() {
        return repDate.get();
    }

    public String getRepDesc() {
        return repDesc.get();
    }

    public String getRepID() {
        return repID.get();
    }

    public String getRepPaid() {
        return repPaid.get();
    }
}
