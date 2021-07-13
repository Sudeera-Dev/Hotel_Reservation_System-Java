package sample;

import javafx.beans.property.SimpleStringProperty;

public class Event {
    private SimpleStringProperty seEvID;
    private SimpleStringProperty seEvDate;
    private SimpleStringProperty seEvTime;
    private SimpleStringProperty seEvPkg;
    private SimpleStringProperty seEvcID;
    private SimpleStringProperty seEvcName;
    private SimpleStringProperty seEvTotal;
    private SimpleStringProperty seEvTbp;

    Event(String seEvID,String seEvDate,String seEvTime,String seEvPkg,String seEvcID,String seEvcName,String seEvTotal,String seEvTbp){
        this.seEvID= new SimpleStringProperty(seEvID);
        this.seEvDate= new SimpleStringProperty(seEvDate);
        this.seEvTime= new SimpleStringProperty(seEvTime);
        this.seEvPkg= new SimpleStringProperty(seEvPkg);
        this.seEvcID= new SimpleStringProperty(seEvcID);
        this.seEvcName= new SimpleStringProperty(seEvcName);
        this.seEvTotal= new SimpleStringProperty(seEvTotal);
        this.seEvTbp= new SimpleStringProperty(seEvTbp);
    }

    public String getSeEvcID() {
        return seEvcID.get();
    }

    public String getSeEvDate() {
        return seEvDate.get();
    }

    public String getSeEvcName() {
        return seEvcName.get();
    }

    public String getSeEvID() {
        return seEvID.get();
    }

    public String getSeEvPkg() {
        return seEvPkg.get();
    }

    public String getSeEvTime() {
        return seEvTime.get();
    }

    public String getSeEvTbp() {
        return seEvTbp.get();
    }

    public String getSeEvTotal() {
        return seEvTotal.get();
    }
}
