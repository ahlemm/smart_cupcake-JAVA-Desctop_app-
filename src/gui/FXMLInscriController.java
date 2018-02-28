/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entite.Formation;
import java.util.*;
import entite.Inscription;
import java.io.IOException;
import service.ServiceFormation;
import service.ServiceInscription;
import java.net.URL;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arbia
 */
public class FXMLInscriController implements Initializable {

  
    @FXML
    private TextField login;
    @FXML
    private Button inscrire;
    
    
    private Formation selectedFormation;

    /**
     * Initializes the controller class.
     */ 
  
    @FXML
    private TableColumn<Formation, Date> date_debut=new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Date> date_fin = new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Integer> nbr_participant= new TableColumn<Formation, Integer>();
    @FXML
    private TableColumn<Formation, String> info_formation = new TableColumn<Formation, String>();
    @FXML
    private TableColumn<Formation, Date> date_fin_inscription= new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Integer> nbr_heures = new TableColumn<Formation, Integer>();
    @FXML
    private TableColumn<Formation, Integer> Id_formation = new TableColumn<Formation, Integer>();;
    
    
    
    @FXML
    private TableView<Formation> tabview;
    
    
    @FXML
    private ComboBox<Formation> info= new ComboBox<Formation>();
    
    @FXML
    private TextField nbrP; 
    
     
    @FXML
    private TextField debud;
    @FXML
    private TextField fin;
    @FXML
    private TextField fin_inscription;
    @FXML
    private TextField nbrh;
    ServiceFormation ServiceFormation = new ServiceFormation();
    ServiceInscription serviceinscription = new ServiceInscription();
    @FXML
    private TextField form;
    @FXML
    private Button retourinscri2;
    
    private List<Formation> findFormation() throws SQLException {
        return ServiceFormation.afficher();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
       
      try {
            date_debut.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
            nbr_participant.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbr_participant"));
            info_formation.setCellValueFactory(new PropertyValueFactory<Formation, String>("info_formation"));
            date_fin_inscription.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin_inscri"));
            nbr_heures.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbr_h"));
 
            Id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));
            ObservableList<Formation> items = FXCollections.observableArrayList(findFormation());
            tabview.setItems(items); 
            ObservableList<Formation> tabview = FXCollections.observableArrayList(ServiceFormation.afficher());
        
             
                 
      
            info.getItems().addAll(tabview);
            info.setCellFactory(new Callback<ListView<Formation>, ListCell<Formation>>() {

                @Override
                public ListCell<Formation> call(ListView<Formation> f) {

                    final ListCell<Formation> cell;
                    cell = new ListCell<Formation>() {
                        
                        @Override
                        protected void updateItem(Formation f, boolean bln) {
                            super.updateItem(f, bln);

                            if (f != null) { 
                                
                              setText(String.valueOf(f.getInfo_formation())); 
                               
        
                            } else {

                                setText(null);
                            } 
                              
                        }

                        

                    };

                    return cell;
                }
            }); 
            } catch (SQLException ex) { 
            Logger.getLogger(FXMLInscriController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
                    
            
info.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Formation>() {
          @Override
          public void changed(ObservableValue<? extends Formation> observable, Formation oldValue, Formation newValue) {
              System.out.println("Value is: "+newValue);
              selectedFormation=newValue; 
             
              //Pour remplir les nouveaux Champs !  
                    
             
              nbrP.setText(String.valueOf(newValue.getNbr_participant())); 
debud.setText(String.valueOf(newValue.getDate_debut())); 
 nbrh.setText(String.valueOf(newValue.getNbr_h())); 
  fin.setText(String.valueOf(newValue.getDate_fin())); 
  fin_inscription.setText(String.valueOf(newValue.getDate_fin_inscri()));
  form.setText(String.valueOf(newValue.getId_formation()));
              
          }
      });        
        // TODO
   }   
       

    @FXML
    private void inscrire(ActionEvent event) {
        
  
  
           
    

            Inscription i;
           i = new Inscription( login.getText(),Integer.parseInt(form.getText()));
           
    try{
             serviceinscription.ajouterInscription(i); 
             
              

            } 
           catch (SQLException ex) {
                Logger.getLogger(FXMLInscriController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void retourinscri2(ActionEvent event) throws IOException {  FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }
}
        
     
        
         
    

    
    

    
      
   
    

     
    

