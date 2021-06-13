package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    ObservableList<String> ciTimeList = FXCollections.observableArrayList("AM","PM");
    ObservableList<String> coTimeList = FXCollections.observableArrayList("AM","PM");
    ObservableList<String> pkgsList = FXCollections.observableArrayList("Package 1","Package 2","Package 3");
    ObservableList<String> timeSlotList = FXCollections.observableArrayList("Day","Night");

    @FXML Button navBtn1,navBtn2,navBtn3,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    @FXML Pane rsPane,rrPane,sPane;
    @FXML ChoiceBox ciTime,coTime,pkgs,timeSlot;

    private int ro1=0,ro2=0,ro3=0,ro4=0,ro5=0,ro6=0,ro7=0,ro8=0,ro9=0,ro10=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        ciTime.setItems(ciTimeList);
        coTime.setItems(coTimeList);
        pkgs.setItems(pkgsList);
        timeSlot.setItems(timeSlotList);
        rsPane.toFront();
    }

    public void NavBtn1OnAction(ActionEvent event){
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        rsPane.toFront();

    }

    public void NavBtn2OnAction(ActionEvent event){
        navBtn2.setStyle("-fx-background-color: #a3b5d9");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        rrPane.toFront();
    }

    public void NavBtn3OnAction(ActionEvent event){
        navBtn3.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        sPane.toFront();

    }

    public void r1OnAction(ActionEvent event){
        if(ro1 != 0){
            r1.setStyle("-fx-background-color: green");
            ro1 =0;
        }else if(ro1 == 0){
            r1.setStyle("-fx-background-color: #dede01");
            ro1 =1;
        }
    }
}
