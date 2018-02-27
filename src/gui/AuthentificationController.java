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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceUser;
import utile.Config;
import static utile.Utils.patissier;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Button BtnInscrire;
    @FXML
    private TextField log;
    @FXML
    private TextField pass;
    @FXML
    private Button BtnConnecter;
    private ServiceUser serviceUser = new ServiceUser();
    private User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscrire(ActionEvent event) throws IOException {

        Stage stage = (Stage) BtnInscrire.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InscriptionUser.fxml")));
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void connecter(ActionEvent event) throws IOException {

        try {
            boolean test = false;
            String message = "";

            if (pass.getText().length() == 0) {
                test = true;
                message = "Champs password obligatoire";

            } else if (log.getText().length() == 0) {
                test = true;
                message = "Champs login obligatoire";

            }

            if (test) {
                showMessage(message);
            } else {
                if (!serviceUser.afficherUserExist(log.getText(), pass.getText()).isEmpty()) {
                    List<User> users;
                    users = new ArrayList<>();
                    users = serviceUser.afficherUserExist(log.getText(), pass.getText());
                    for (User u : users) {
                        Config.id_user=u.getId_user();
                       
                        if (u.getType().equals("client")) {
                            Stage stage = (Stage) BtnInscrire.getScene().getWindow();
                            stage.close();

                            Stage window = new Stage();


                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuClient.fxml")));
                            window.setScene(scene);
                            window.show();
                           
                        }else if (u.getType().equals("Patissier")) {
                           patissier=u;
                            Stage stage = (Stage) BtnInscrire.getScene().getWindow();
                            stage.close();

                            Stage window = new Stage();


                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuPatissier.fxml")));
                            window.setScene(scene);
                            window.show();
                           
                        }
                        else if (u.getType().equals("Admin")) {
                           patissier=u;
                            Stage stage = (Stage) BtnInscrire.getScene().getWindow();
                            stage.close();

                            Stage window = new Stage();


                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuAdmin.fxml")));
                            window.setScene(scene);
                            window.show();
                           
                        }
                    }

                }
                clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    private void clear() {

        log.setText("");
        pass.setText("");
    }

}
