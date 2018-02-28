/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Ingredient;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceIngredient;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class NouvelIngredientFXMLController implements Initializable {

    @FXML
    private TextField nomIng;
    @FXML
    private TextField prixIng;
    @FXML
    private Button ajouter;
      private ServiceIngredient serviceIngredient = new ServiceIngredient();
    private Ingredient ingredient = new Ingredient();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout_ing(ActionEvent event) {
         boolean test = false;
        String message = "";

        if (nomIng.getText().length() == 0) {
            test = true;
            message = "Champs nom obligatoire";
        }  else if (!testPrix(prixIng.getText())) {
            test = true;
            message = " prix invalid";
        }
       
        if (test) {
            showMessage(message);
        } else {
           

            Ingredient i = new Ingredient(nomIng.getText(),
                    Double.parseDouble(prixIng.getText()) );
            try {
                serviceIngredient.ajouterIngredient(i);
                clear();
                
            } catch (SQLException ex) {
                Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    private boolean testPrix(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    private void clear() {
        
        nomIng.setText(""); 
      
        prixIng.setText("");
        
       
    }
    
}
