package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ConfirmationWindowController {

    @FXML Button conYes,conNo;

    public void conYesonAction(ActionEvent event){
        CancelReservation cr = new CancelReservation();

        if(MainWindowController.process==1){
            cr.CancelRoomReservation();
            MainWindowController.process=0;
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }else if(MainWindowController.process==2){
            cr.CancelEventReservation();
            MainWindowController.process=0;
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }


    }

    public void conNoonAction(ActionEvent event){
        MainWindowController.process=0;
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
