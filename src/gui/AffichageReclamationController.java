/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reclamation;
import gui.AffichageController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceReclamation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichageReclamationController implements Initializable {

    @FXML
    private TableColumn<?, ?> info;
    @FXML
    private TableColumn<?, ?> tel;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableView<Reclamation> tableID;
    @FXML
    private Button ajouter;
    @FXML
    private Button Emailing;
    @FXML
    private Button retour6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         info.setCellValueFactory(new PropertyValueFactory<>("info"));
         tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
          
         date.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         ServiceReclamation m= new ServiceReclamation();
         //System.out.println("hhh");
        try {
            tableID.setItems(m.afficher());
            
            // TODO
            // tableMoniteur.setItems(mservice.getAllMoniteurs());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
        // TODO

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
            
    }

    @FXML
    private void Emailing(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXMLEMAIL.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
        
        
    }

    @FXML
    private void retour6(ActionEvent event) throws IOException {
        
             FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }
    }    
    

