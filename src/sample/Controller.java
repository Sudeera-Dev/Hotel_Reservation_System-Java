package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML private Button btnlogin,btnClear;
    @FXML private PasswordField txtPwd ;
    @FXML private TextField txtUid;

    public void btnloginOnAction(ActionEvent event){
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Hotel Reservation System");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=(Stage) btnlogin.getScene().getWindow();
        stage.close();
    }

    public void BtnClearOnAction(ActionEvent event){
        Stage stage=(Stage) btnClear.getScene().getWindow();

        txtPwd.setText("");
        txtUid.setText("");
    }
}
