/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entite.Produit;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceUser;
import utile.Config;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class CompteController implements Initializable {

    @FXML
    private TextField nomC;
    @FXML
    private TextField prenomC;
    @FXML
    private TextField emailC;
    @FXML
    private TextField adresseC;
    @FXML
    private TextField numC;
    @FXML
    private TextField loginC;
    @FXML
    private DatePicker dateC;
    
    @FXML
    private Button BtnModC;

    private ServiceUser serviceUser = new ServiceUser();
    private User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<User> users;
        users = new ArrayList<>();
        try {
          
            users = serviceUser.afficherUserById(Config.id_user);
            for (User u : users) {
                nomC.setText(u.getNom());
                prenomC.setText(u.getPrenom());
                adresseC.setText(u.getAdresse());
                emailC.setText(u.getEmail());
                loginC.setText(u.getLogin());
           
                numC.setText(String.valueOf(u.getNum_tel()));

                Calendar cal = Calendar.getInstance();
                cal.setTime(u.getDate_naissance());

                dateC.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifierCompte(ActionEvent event) {
        try {

               boolean test = false;
            String message = "";

            if (nomC.getText().length() == 0) {
                test = true;
                message = "Champs Nom obligatoire";
           
            } else if (prenomC.getText().length() == 0) {
                test = true;
                message = "Champs prenom obligatoire";
           
            }else if (emailC.getText().length() == 0) {
                test = true;
                message = "Champs email obligatoire";
           
            }
           else if (dateC.getValue() == null) {

                test = true;
                message = "Champs Date obligatoire";
            }else if (numC.getText().length() == 0) {
                test = true;
                message = "Champs numero telephone obligatoire";
           
            } else if (adresseC.getText().length() == 0) {
                test = true;
                message = "Champs numero telephone obligatoire";
           
            } else if (!serviceUser.afficherUserLogin(loginC.getText()).isEmpty()) {
                test = true;
                message = " login existe deja!!";
            }else if (!serviceUser.afficherUserEmail(emailC.getText()).isEmpty()) {
                test = true;
                message = " Email existe deja!!";
            }
          
            if (test) {
                showMessage(message);
            } else {
                
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de modifier!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

               
               
                

            
                Date date = Date.from(dateC.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                User u = new User(Config.id_user,nomC.getText(), prenomC.getText(),  date, emailC.getText(), adresseC.getText(), Integer.parseInt(numC.getText()),
                        loginC.getText());

                serviceUser.modifierCompte(u);
              
            }
            }
            
            
            
         

        } catch (Exception ex) {
            Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimerC(ActionEvent event) {
        
        try {
            serviceUser.supprimer(Config.id_user);
        } catch(MySQLIntegrityConstraintViolationException ex){
            System.out.println("*****normalment yod5el lel bloc hedha mte3 catch*****");
            showMessage("Vous ne pouvez pas supprimer votre compte");
        }
        catch (SQLException ex) {
                     Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    @FXML
    private void modifierMotDePasse(ActionEvent event) throws IOException {
           Stage stage = (Stage) loginC.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MotDePasse.fxml")));
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
         Stage stage = (Stage) loginC.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Authentification.fxml")));
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void retourCompte(ActionEvent event) throws IOException {
          Stage stage = (Stage) loginC.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuClient.fxml")));
        window.setScene(scene);
        window.show();
    }
}
