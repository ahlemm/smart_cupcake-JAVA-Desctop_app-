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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.ServiceUser;
import utile.Config;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class MotDePasseController implements Initializable {

    @FXML
    private PasswordField actuel;
    @FXML
    private PasswordField nouveau;
    @FXML
    private PasswordField confirmer;
    @FXML
    private Button BtnMDP;
    private ServiceUser serviceUser = new ServiceUser();
    private User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        List<User> users;
//        users = new ArrayList<>();
//        try {
//
//            users = serviceUser.afficherUserById(Config.id_user);
//            for (User u : users) {
//                actuel.setText(u.getPassword());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    // TODO

    @FXML
    private void modifierMDP(ActionEvent event) throws SQLException {
        try {

            boolean test = false;
            String message = "";

            if (nouveau.getText().length() == 0) {
                test = true;
                message = " remplire le nouveau mot de passe";
            } else if (!nouveau.getText().equals(confirmer.getText())) {
                test = true;
                message = " les mots  de passe sont differents";
            }else if (!actuel.getText().equals(serviceUser.afficherUserById(Config.id_user).get(0).getPassword())) {
                test = true;
                message = " MDP erroné";
            }
            if (test) {
                showMessage(message);
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setHeaderText("vous etes suur de modifier!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    List<User> users;
                    users = new ArrayList<>();

                    users = serviceUser.afficherUserById(Config.id_user);
                    for (User u : users) {
                        if (actuel.getText().equals(u.getPassword())) {
                            User ur = new User(Config.id_user, nouveau.getText());

                            serviceUser.modifierMDP(ur);
                            clear();
                        }

                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }
     private void clear() {
      
        actuel.setText("");
        nouveau.setText("");
         confirmer.setText("");
       
    }

    @FXML
    private void retourMDP(ActionEvent event) throws IOException {
      Stage stage = (Stage) actuel.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Compte.fxml")));
        window.setScene(scene);
        window.show();
    }
}
