package sample;

import javafx.beans.property.SimpleStringProperty;

public class customer {
    private SimpleStringProperty seCuId;
    private SimpleStringProperty seCuName;
    private SimpleStringProperty seCuAdd;
    private SimpleStringProperty seCuEmail;
    private SimpleStringProperty seCuTp;
    private SimpleStringProperty seCuVehino;
    private SimpleStringProperty seCuNic;

    customer(String cuID,String seCuName,String seCuAdd,String seCuEmail,String seCuTp,String seCuVehino,String seCuNic){
        this.seCuId= new SimpleStringProperty(cuID);
        this.seCuName= new SimpleStringProperty(seCuName);
        this.seCuAdd= new SimpleStringProperty(seCuAdd);
        this.seCuEmail= new SimpleStringProperty(seCuEmail);
        this.seCuTp= new SimpleStringProperty(seCuTp);
        this.seCuVehino= new SimpleStringProperty(seCuVehino);
        this.seCuNic= new SimpleStringProperty(seCuNic);
    }

    public String getSeCuAdd() {
        return seCuAdd.get();
    }

    public String getSeCuEmail() {
        return seCuEmail.get();
    }

    public String getSeCuId() {
        return seCuId.get();
    }

    public String getSeCuName() {
        return seCuName.get();
    }

    public String getSeCuNic() {
        return seCuNic.get();
    }

    public String getSeCuTp() {
        return seCuTp.get();
    }

    public String getSeCuVehino() {
        return seCuVehino.get();
    }
}
