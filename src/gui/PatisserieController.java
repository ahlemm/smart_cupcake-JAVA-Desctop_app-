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
public class PatisserieController implements Initializable {
        ObservableList listPat = FXCollections.observableArrayList();


    @FXML
    private Button patisserie;
    @FXML
    private TextField nom2;
    @FXML
    private TextField adresse2;
    @FXML
    private Button rechercher2;
    @FXML
    private TableColumn<?, ?> id_patisserie;
    @FXML
    private TableColumn<?, ?> nom_patisserie;
    @FXML
    private TableColumn<?, ?> info_patisserie;
    @FXML
    private TableColumn<?, ?> adresse_patisserie;
    @FXML
    private TableColumn<?, ?> patissier;
    @FXML
    private TableView<?> listPatisserie;
    private Object activite;
    @FXML
    private TextField activite22;
    @FXML
    private TableColumn<?, ?> spetialite;
    @FXML
    private Label label5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getAllPatisseries(ActionEvent event) {
        id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		spetialite.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                
                     ServicePatisserie sp = new ServicePatisserie();
 listPat= sp. getAllPatisseries();
       listPatisserie.setItems(listPat);
       label5.setText("");
    }
//
//    private void searchPatisserie(ActionEvent event) {
//        ServicePatisserie sp = new ServicePatisserie();
//       String x; String y;  String z;
//       listPatisserie.setEditable(true);
//        
//          id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
//		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
//		spetialite.setCellValueFactory(new PropertyValueFactory<>("activite"));
//		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
//		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
//                  patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));
//
//            
//
//  listPat= (ObservableList) sp.searchPatisserie(nom2.getText(), activite22.getText(), adresse2.getText() );
//       listPatisserie.setItems(listPat);
//        
//    }

    @FXML
    private void searchPatisserie(ActionEvent event) {
          ServicePatisserie sp = new ServicePatisserie();
       listPatisserie.setEditable(true);
        
          id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
		nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
		spetialite.setCellValueFactory(new PropertyValueFactory<>("activite"));
		info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
		adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
                  patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));

                          if (nom2.getText().equals("") && activite22.getText().equals("")&& adresse2.getText().equals(""))
                          { label5.setText("veuillez remplir au minimum un champ de recherche");}
            

                          else { listPat= sp.searchPatisserie(nom2.getText(), activite22.getText(), adresse2.getText() );
       listPatisserie.setItems(listPat);}
        
    }
    }



