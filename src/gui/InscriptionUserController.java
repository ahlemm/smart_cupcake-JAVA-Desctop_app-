/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceUser;
import utile.Config;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class InscriptionUserController implements Initializable {

    @FXML
    private TextField nomIn;
    @FXML
    private TextField prenomIn;
    @FXML
    private DatePicker dateIn;
    @FXML
    private TextField emailIn;
    @FXML
    private TextField numIn;
    @FXML
    private TextField loginIn;

    @FXML
    private Button BtnInscri;
    @FXML
    private TextField adresseIn;
    @FXML
    private ComboBox<String> typeIn = new ComboBox<String>();
    private ServiceUser serviceUser = new ServiceUser();
    private User user = new User();
    @FXML
    private PasswordField pwd1;
    @FXML
    private PasswordField pwd2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        typeIn.getItems().addAll(
                "Client",
                "Patisserie"
        );

    }

    @FXML
    private void inscrire(ActionEvent event) {
        try {
            boolean test = false;
            String message = "";

            if (nomIn.getText().length() == 0) {
                test = true;
                message = "Champs Nom obligatoire";
           
            } else if (prenomIn.getText().length() == 0) {
                test = true;
                message = "Champs prenom obligatoire";
           
            }else if (emailIn.getText().length() == 0) {
                test = true;
                message = "Champs email obligatoire";
           
            }
            else if (prenomIn.getText().length() == 0) {
                test = true;
                message = "Champs prenom obligatoire";
           
            }else if (dateIn.getValue() == null) {

                test = true;
                message = "Champs Date obligatoire";
            }else if (numIn.getText().length() == 0) {
                test = true;
                message = "Champs numero telephone obligatoire";
           
            } else if (adresseIn.getText().length() == 0) {
                test = true;
                message = "Champs numero telephone obligatoire";
           
            } else if (!serviceUser.afficherUserLogin(loginIn.getText()).isEmpty()) {
                test = true;
                message = " login existe deja!!";
            }else if (!serviceUser.afficherUserEmail(emailIn.getText()).isEmpty()) {
                test = true;
                message = " Email existe deja!!";
            }
            else if (pwd1.getText().length() == 0) {
                test = true;
                message = "Champs mot de passe obligatoire";
               
            }
            else if (!pwd1.getText().equals( pwd2.getText())) {
                test = true;
                message = "Les mots de passe ne sont pas identiques";
               
            }
            if (test) {
                showMessage(message);
            } else {
                Date date = Date.from(dateIn.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                User u = new User(nomIn.getText(), prenomIn.getText(), typeIn.getValue(), (java.sql.Date) date, emailIn.getText(), adresseIn.getText(), Integer.parseInt(numIn.getText()),
                        loginIn.getText(), pwd1.getText());

                serviceUser.ajouterUser(u);
                clear();

            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean testDate(LocalDate localDate) {
        try {
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    private void clear() {
        nomIn.setText("");
        prenomIn.setText("");
        typeIn.setValue(null);
        dateIn.setValue(null);
        emailIn.setText("");
        adresseIn.setText("");
        numIn.setText("");
        loginIn.setText("");
        pwd1.setText("");
        pwd2.setText("");
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          Stage stage = (Stage) nomIn.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Authentification.fxml")));
        window.setScene(scene);
        window.show();
    }

}
