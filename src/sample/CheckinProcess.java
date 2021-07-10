package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckinProcess {

    private String name,id,add,vehino,tp,neTime,nDate;
    private int room;

    CheckinProcess(String name,String id,String add,String vehino,String tp,String neTime,String nDate,int room){
        this.name=name;
        this.id=id;
        this.add= add;
        this.vehino=vehino;
        this.tp =tp;
        this.nDate=nDate;
        this.neTime=neTime;
        this.room=room;
    }

    public String mainProcess(){
        return validateInputs();
    }

    private String validateInputs(){
        if (name.equals("") || id.equals("") || add.equals("") || tp.equals("")) {
            return "one or more fields are empty";
        } else {
            if(room != 0){
                String regex = "\\d{10}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tp);
                if(matcher.matches()){
                    if(id.length() > 9){
                        return "success";
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
    }
}
