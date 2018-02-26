/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.animation.PauseTransition;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mdin Ahlem
 */
public class AffichageController implements Initializable {
ObservableList<Patisserie> listPat = FXCollections.observableArrayList();
    @FXML
    
    private TableView<Patisserie> listPatisserie;
    @FXML
    private TableColumn<?, ?> id_patisserie;
    @FXML
    private TableColumn<?, ?> nom_patisserie;
    private TableColumn<?, ?> activite;
    @FXML
    private TableColumn<?, ?> info_patisserie;
    @FXML
    private TableColumn<?, ?> adresse_patisserie;
    @FXML
    private Button afficher;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField info_pat;
    @FXML
    private TextField activite_pat;
    @FXML
    private TextField nom_pat;
    @FXML
    private TextField adresse_pat;
    @FXML
    private Button approuves;
    @FXML
    private Button nnaprouvé;
    @FXML
    private AnchorPane etudié;
    @FXML
    private Button approuver;
    @FXML
    private Button etudier;
    @FXML
    private Button etud;
    @FXML
    private TableColumn<?, ?> id_patissier;
    @FXML
    private TextField rechercher;
    @FXML
    private Button getby_id_user;
    @FXML
    private Button getby_id_pat;
    @FXML
    private Label label3;
    @FXML
    private Button page_précédente;
    @FXML
    private TableColumn<?, ?> activite1;
    @FXML
    private ImageView pp;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
		
		getAllPatisseries();
                getAllPatisserieApprouved();
               getAllPatisserieNonApprouved();
               getAllPatisserieàEtudier();
       //       approuver();
                
               
//loadPatisserieList();
                //initChamps();
	}
   
   
   
   
   
   
	
@FXML
	private void getAllPatisseries(){
	 
		 
		id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                
   ServicePatisserie sp = new ServicePatisserie();
 listPat= sp. getAllPatisseries();
       listPatisserie.setItems(listPat);
       label3.setText("" ) ;
	}
//	
       	
@FXML 
        private void getAllPatisserieApprouved(){
	 
		 
		id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
               id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                
                     ServicePatisserie sp = new ServicePatisserie();
  listPat= sp. getAllPatisserieApprouved();
       listPatisserie.setItems(listPat);
        label3.setText("" ) ;
	}
    @FXML
         private void getAllPatisserieNonApprouved() {
             
             id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
               id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                
                     ServicePatisserie sp = new ServicePatisserie();
  listPat= sp. getAllPatisserieNonApprouved();
       listPatisserie.setItems(listPat);
              label3.setText("" ) ;
             
    }
        
    @FXML
            private void getAllPatisserieàEtudier() {
             
             id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                  id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                
                     ServicePatisserie sp = new ServicePatisserie();
  listPat= sp. getAllPatisserieàEtudier();
       listPatisserie.setItems(listPat);
              label3.setText("" ) ;
             
    }
@FXML
    private void supprimer(ActionEvent event) throws SQLException {
        ServicePatisserie sp = new ServicePatisserie();
      
       listPatisserie.setEditable(true);
       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
    
 
  if(selectedIndex >=0) {
           listPatisserie.getItems().remove(selectedIndex);
              sp.supprimer(p.getId_patisserie());
               label3.setText("patisserie supprimée" ) ;
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                 pauseTransition.setOnFinished(i -> label3.setText(""));
            }
       else{
           Alert alert= new Alert(AlertType.WARNING);
           alert.setTitle("No Selection");
           alert.setHeaderText("No Patisserie Selected");
           alert.setContentText("Please select a patisserie in the table");
           alert.showAndWait();
         
       }
   
    }
   @FXML
    private void updatePatisserie(ActionEvent event) {
     
        
        ServicePatisserie sp = new ServicePatisserie();
       
       listPatisserie.setEditable(true);
       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
      
  if(selectedIndex >=0 ) {
        
                  //   sp.ModifierPatisserie(p);
           if (nom_pat.getText().equals("")|| activite_pat.getText().equals("")|| info_pat.getText().equals("")||adresse_pat.getText().equals("") )
         {                 label3.setText("veuillez remplir tous les champs" ) ;}

         else { sp.updatePatisserie(p.getId_patisserie(),nom_pat.getText(),activite_pat.getText(),info_pat.getText(),adresse_pat.getText());
            
		getAllPatisseries();
                 label3.setText("modification avec succées" ) ;
                 PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
 pauseTransition.setOnFinished(x -> label3.setText(""));
 pauseTransition.play();
               nom_pat.setText("");
               activite_pat.setText("");
               info_pat.setText("");
               adresse_pat.setText("");

               
            }}
           else{
           Alert alert= new Alert(AlertType.WARNING);
              alert.setTitle("Pas de Selection");
           alert.setHeaderText("vous n'avez pas sélectionner une Patisserie !");
           alert.setContentText("veuillez sélectionner une patisserie dans la table");
           alert.showAndWait(); }
         
       
    
         
    }
// @FXML
//    private void approuver(ActionEvent event) {
// ServicePatisserie sp = new ServicePatisserie();
//        int x ;
//       listPatisserie.setEditable(true);
//       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
//     
//       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
//  x=p.getId_patisserie();
//      
//  if(selectedIndex >=0) {
//           listPatisserie.getItems().remove(selectedIndex);
//            
//		
//              sp.approuver(x);
//              getAllPatisseries();
//            }
//       else{
//           Alert alert= new Alert(AlertType.WARNING);
//           alert.setTitle("No Selection");
//           alert.setHeaderText("No Patisserie Selected");
//           alert.setContentText("Please select a patisserie in the table");
//           alert.showAndWait();
//         
//       }
//   
//    }

    @FXML
    private void approuver(ActionEvent event) {
        
        
    
 ServicePatisserie sp = new ServicePatisserie();
      //  int x ;
       listPatisserie.setEditable(true);
       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
  //x=p.getId_patisserie();
      
  if(selectedIndex >=0) {
           listPatisserie.getItems().remove(selectedIndex);
            
		
              sp.approuver(p.getId_patisserie());
               label3.setText("" ) ;
                getAllPatisserieApprouved();
            }
       else{
           Alert alert= new Alert(AlertType.WARNING);
               alert.setTitle("Pas de Selection");
           alert.setHeaderText("vous n'avez pas sélectionner une Patisserie !");
           alert.setContentText("veuillez sélectionner une patisserie dans la table");
           alert.showAndWait();
         
       }
   
    }

    @FXML
    private void etudier(ActionEvent event) {
        
        ServicePatisserie sp = new ServicePatisserie();
        //int x ;
       listPatisserie.setEditable(true);
       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
     
       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
  //x=p.getId_patisserie();
      
  if(selectedIndex >=0) {
           listPatisserie.getItems().remove(selectedIndex);
            
		
              sp.etudier(p.getId_patisserie());
                getAllPatisserieàEtudier(); 
                label3.setText("" ) ;
            }
       else{
           Alert alert= new Alert(AlertType.WARNING);
           alert.setTitle("Pas de Selection");
           alert.setHeaderText("vous n'avez pas sélectionner une Patisserie !");
           alert.setContentText("veuillez sélectionner une patisserie dans la table");
           alert.showAndWait();
         
       }
        
        
        
    }

    @FXML
    private void getPatisseriesByidPatissier(ActionEvent event) {
        
        ServicePatisserie sp = new ServicePatisserie();
        // int x ;
       listPatisserie.setEditable(true);
       
        id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                  id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

  // x=Integer.parseInt(rechercher.getText())  ;      
   if(rechercher.getText().equals("")) {label3.setText("veuillez selectionnez id(patisserie/patissier)");}
  else 
   { listPat= sp.getPatisseriesByidPatissier(Integer.parseInt(rechercher.getText()));
       listPatisserie.setItems(listPat);
        label3.setText("" ) ;}
//       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
//     
//    //   Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
//x=Integer.parseInt(rechercher.getText())  ;
////x=p.getId_patisserie();
// 
//      
// 
//        
//                  //   sp.ModifierPatisserie(p);
// 
//            sp.getPatisseriesByidPatissier(x);
//            
//		// getAllPatisseries();
            
       
        
    }

    

    @FXML
    private void page_précédente(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
        
    }

    @FXML
    private void search_nom(ActionEvent event) {
        
        
           ServicePatisserie sp = new ServicePatisserie();
        int x ;
       listPatisserie.setEditable(true);
        
          id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		activite1.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                  id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

  // x=Integer.parseInt(rechercher.getText())  ;             
    if(rechercher.getText().equals("")) {label3.setText("veuillez selectionnez id(patisserie/patissier)");}

    else {listPat= sp.search_nom(rechercher.getText() );
       listPatisserie.setItems(listPat);
        label3.setText("" ) ;
    }
}
}  
        
        
        
        
        
        
        
        
   

   
            
        




//    private void approuver() {
//   ServicePatisserie sp = new ServicePatisserie();
//        int x ;
//       listPatisserie.setEditable(true);
//       int selectedIndex=listPatisserie.getSelectionModel().getSelectedIndex();
//     
//       Patisserie p = listPatisserie.getSelectionModel().getSelectedItem();
//  x=p.getId_patisserie();
// 
//      
//  if(selectedIndex >=0) {
//        
//                     sp.approuver(x);
// 
//                        sp.approuver(Integer.parseInt(id_patisserie.getText()));
//
//		getAllPatisseries();
//            }
//       else{
//           Alert alert= new Alert(AlertType.WARNING);
//           alert.setTitle("No Selection");
//           alert.setHeaderText("No Person Selected");
//           alert.setContentText("Please select a person in the table");
//           alert.showAndWait();
//         
//       }
        

   


    
 












    