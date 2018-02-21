/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package gui;

import entite.Patisserie;
import entite.User;
import service.ServicePatisserie;
import service.ServiceUser; 
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static utile.Utils.patissier;
/**
 * FXML Controller class
 *
 * @author Mdin Ahlem
 */
public class AjoutPatController implements Initializable {
    ObservableList listPat1 = FXCollections.observableArrayList();


    @FXML
    private TableColumn<?, ?>  nom_patisserie;
    @FXML
    private TableColumn<?, ?>  activite;
    private TextField longitude;
    @FXML
    private TableColumn<?, ?>  info_patisserie;
    private TextField latitude;
    @FXML
    private Button valider;
    @FXML
    private TableColumn <?, ?> adresse_patisserie;
    @FXML
    private AnchorPane updatePatisserie;
    @FXML
    private Button modifier;
    private TableView<Patisserie> listPatisserie;
    @FXML
    private TableColumn<?, ?>  id_patisserie;
    @FXML
    private Button mes_patisserie;
    @FXML
    private TextField nom_pat;
    @FXML
    private TextField activite_pat;
    @FXML
    private TextField longitude_pat;
    @FXML
    private TextField info_pat;
    @FXML
    private TextField latitude_pat;
    @FXML
    private TextField adresse_pat;
    @FXML
    private TableColumn<?, ?>  id_patissier;
    @FXML
    private TableView<Patisserie> listPatisserie1;
    @FXML
    private Text Nom;
    @FXML
    private Button supprimer;
    @FXML
    private Label label;
    @FXML
    private Label label1;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//</editor-fold>
        // TODO
    }    

   

    @FXML
    private void updatePatisserie1(ActionEvent event) {
        ServicePatisserie sp = new ServicePatisserie();
        int x ;
       listPatisserie1.setEditable(true);
       int selectedIndex=listPatisserie1.getSelectionModel().getSelectedIndex();
     
       Patisserie p = (Patisserie) listPatisserie1.getSelectionModel().getSelectedItem();
  //x=p.getId_patisserie();
 
      
  if(selectedIndex >=0) {
        
                  //   sp.ModifierPatisserie(p);
 
            sp.updatePatisserie1(p.getId_patisserie(),nom_pat.getText(),activite_pat.getText(),info_pat.getText(),Double.parseDouble(longitude_pat.getText()),Double.parseDouble(latitude_pat.getText()),adresse_pat.getText());
               label.setText("modification avec succées" )  ;       
  }
       else{
           Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Pas de Selection");
           alert.setHeaderText("Vous n'avez pas choisi une patisserie");
           alert.setContentText("Veuillez selectionnner une patisserie ");
           alert.showAndWait();
         
       }
        
        
        
        
    }


    @FXML
    private void ajouterPatisserie(ActionEvent event) throws SQLException {
         ServicePatisserie sp = new ServicePatisserie();
     // ServiceUser su = new ServiceUser ();
      //FXMLLoader loader = new FXMLLoader(getClass().getResource("affichage.fxml"));

                 
        Patisserie p = new Patisserie( nom_pat.getText(),activite_pat.getText(), info_pat.getText(),Double.parseDouble(longitude_pat.getText()),Double.parseDouble(latitude_pat.getText()),adresse_pat.getText(),patissier);
        if (nom_pat.getText().equals("") || activite_pat.getText().equals("")|| info_pat.getText().equals("")|| (Double.parseDouble(longitude_pat.getText())==0D ))/*||(Double.parseDouble(longitude_pat.getText())==0D)|| adresse_pat.getText().equals("") )*/
        {  Alert alert= new Alert(AlertType.WARNING);
        alert.setTitle("Pas de Selection");
           alert.setHeaderText("Vous n'avez pas choisi une patisserie");
           alert.setContentText("Veuillez selectionnner une patisserie ");
           alert.showAndWait();
         
       }
        else 
        {  sp.ajouterPatisserie(p); 
          label.setText("ajout avec succées" );        }

        
    }

    @FXML
    private void getPatisseriesByidPatissier() {
                ServicePatisserie sp = new ServicePatisserie();
        int x =patissier.getId_user();
       listPatisserie1.setEditable(true);
       
                id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                  id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

        listPat1= sp.getPatisseriesByidPatissier(x);
        listPatisserie1.setItems(listPat1);
     label.setText("pas d'ajout vérifiez vos parametres" ) ;
          label1.setText("" ) ;

    }

    @FXML
    private void supprimer1(ActionEvent event)throws SQLException  {
        ServicePatisserie sp = new ServicePatisserie();
        int x ;
       listPatisserie1.setEditable(true);
       int selectedIndex=listPatisserie1.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie1.getSelectionModel().getSelectedItem();
      
   //x=p.getId_patisserie();
      
  if(selectedIndex >=0) {
           listPatisserie1.getItems().remove(selectedIndex);
              sp.supprimer(p.getId_patisserie());
                 label1.setText("patisserie supprimée" )  ; 
            }
       else{
           Alert alert= new Alert(AlertType.WARNING);
        alert.setTitle("Pas de Selection");
           alert.setHeaderText("Vous n'avez pas choisi une patisserie");
           alert.setContentText("Veuillez selectionnner une patisserie ");
           alert.showAndWait();
         
       }
   
    
    }}
//
//    }


   /* @FXML
//    private void updatePatisserie(ActionEvent event) {
        
        ServicePatisserie sp = new ServicePatisserie();
        int x ;
       listPatisserie.setEditable(true);
       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
  x=p.getId_patisserie();
 
      
  if(selectedIndex >=0) {
        
                  //   sp.ModifierPatisserie(p);
 
            sp.updatePatisserie(x,nom_pat.getText(),activite_pat.getText(),info_pat.getText(),adresse_pat.getText());
            
		getAllPatisseries();
            }
       else{
           Alert alert= new Alert(Alert.AlertType.WARNING);
           alert.setTitle("No Selection");
           alert.setHeaderText("No Person Selected");
           alert.setContentText("Please select a person in the table");
           alert.showAndWait();
         
       }
        
        
        
        
        
        
    }*/

//    private void getPatisseriesByidPatissier(ActionEvent event) {
//        
//        
//           
//        ServicePatisserie sp = new ServicePatisserie();
//        int x =patissier.getId_user();
//       listPatisserie1.setEditable(true);
//       
//                id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
//		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
//		activite.setCellValueFactory(new PropertyValueFactory<>("activite"));
//		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
//		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
//                  id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));
//
//        listPat1= sp.getPatisseriesByidPatissier(x);
//        listPatisserie1.setItems(listPat1);
//        
//    }

//    @FXML
//    private void updatePatisserie(ActionEvent event) {
//    }
//
//
//    @FXML
//    private void mes_patisseries(ActionEvent event) {
//    }

 
        
        
        
        
        
        
        
        
        
    