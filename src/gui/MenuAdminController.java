/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mdin Ahlem
 */
public class MenuAdminController implements Initializable {

    @FXML
    private Button patisserie_client;
    @FXML
    private Button ingredient1;
    @FXML
    private Button retour_1;
    @FXML
    private Button consulterMonCompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void accéderàpatisseries(ActionEvent event) throws IOException {
        
        
        
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichage.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();

        
        
    }


    @FXML
    private void consulterMonCompte(ActionEvent event)  throws IOException 
    {FXMLLoader loader = new FXMLLoader(getClass().getResource("Compte.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }

    @FXML
    private void patisserie_client(ActionEvent event) throws IOException {FXMLLoader loader = new FXMLLoader(getClass().getResource("affichage.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }

    @FXML
    private void gestion_ingr(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("crudIngredientFXML.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }

    @FXML
    private void retour_1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }

    
}
