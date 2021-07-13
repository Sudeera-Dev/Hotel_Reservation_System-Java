package sample;

import javafx.beans.property.SimpleStringProperty;

public class Reservation {
    private SimpleStringProperty seReID;
    private SimpleStringProperty seReType;
    private SimpleStringProperty seReRm;
    private SimpleStringProperty seReNg;
    private SimpleStringProperty seRecID;
    private SimpleStringProperty seRecName;
    private SimpleStringProperty seReciDate;
    private SimpleStringProperty seReciTime;
    private SimpleStringProperty seRecoDate;
    private SimpleStringProperty seRecoTime;

    Reservation(String seReID,String seReType,String seReRm,String seReNg,String seRecID,String seRecName,String seReciDate,String seReciTime,String seRecoDate,String seRecoTime){
        this.seReID= new SimpleStringProperty(seReID);
        this.seReType= new SimpleStringProperty(seReType);
        this.seReRm= new SimpleStringProperty(seReRm);
        this.seReNg= new SimpleStringProperty(seReNg);
        this.seRecID= new SimpleStringProperty(seRecID);
        this.seRecName= new SimpleStringProperty(seRecName);
        this.seReciDate= new SimpleStringProperty(seReciDate);
        this.seReciTime= new SimpleStringProperty(seReciTime);
        this.seRecoDate= new SimpleStringProperty(seRecoDate);
        this.seRecoTime= new SimpleStringProperty(seRecoTime);
    }

    public String getSeRecID() {
        return seRecID.get();
    }

    public String getSeReciDate() {
        return seReciDate.get();
    }

    public String getSeRecName() {
        return seRecName.get();
    }

    public String getSeReID() {
        return seReID.get();
    }

    public String getSeReNg() {
        return seReNg.get();
    }

    public String getSeReRm() {
        return seReRm.get();
    }

    public String getSeReType() {
        return seReType.get();
    }

    public String getSeReciTime() {
        return seReciTime.get();
    }

    public String getSeRecoDate() {
        return seRecoDate.get();
    }

    public String getSeRecoTime() {
        return seRecoTime.get();
    }
}
