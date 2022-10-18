/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DB_Connetor.LogInConnector;
import ModelPegawai.ModelPegawai;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cornelius
 */
public class SignUpController implements Initializable {
    private boolean Data_Edit=false;
    
    
    @FXML
    private TextField AlamatPegawaiText;
    @FXML
    private TextField NamaPegawaiText;
    @FXML
    private TextField NIPText;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SignupButton;
    @FXML
    private AnchorPane SignUpWindows;
    @FXML
    private TextField NoTeleponPegawaiText;
    @FXML
    private TextField PasswordText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    @FXML
    private void CancelButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("About Me");
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
            CancelButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    @FXML
    private void SignupButtonClick(ActionEvent event) {
    if (!NIPText.getText().trim().isEmpty() && !NamaPegawaiText.getText().trim().isEmpty() && !NoTeleponPegawaiText.getText().trim().isEmpty() && !AlamatPegawaiText.getText().trim().isEmpty() && !PasswordText.getText().trim().isEmpty()) {
            LogInConnector.signuppegawai(event, NIPText.getText(), NamaPegawaiText.getText(), NoTeleponPegawaiText.getText(), AlamatPegawaiText.getText(), PasswordText.getText());
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Fill Data For Sign Up!!!");
            a.show();
        }
    }
}
    


