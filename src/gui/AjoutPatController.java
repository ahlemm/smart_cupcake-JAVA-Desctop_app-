/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package gui;

import entite.Patisserie;
import entite.User;
import java.io.File;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.P;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static utile.Utils.patissier;

/**
 * FXML Controller class
 *
 * @author Mdin Ahlem
 */
public class AjoutPatController implements Initializable {

    ObservableList listPat1 = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> nom_patisserie;
    @FXML
    private TableColumn<?, ?> activite;
    private TextField longitude;
    @FXML
    private TableColumn<?, ?> info_patisserie;
    private TextField latitude;
    @FXML
    private Button valider;
    @FXML
    private TableColumn<?, ?> adresse_patisserie;
    @FXML
    private AnchorPane updatePatisserie;
    @FXML
    private Button modifier;
    private TableView<Patisserie> listPatisserie;
    @FXML
    private TableColumn<?, ?> id_patisserie;
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
    private TableColumn<?, ?> id_patissier;
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
    @FXML
    private Button browse;
    @FXML
    private ImageView im1;
    @FXML
    private ListView view;
    @FXML
    private TextField picture;
    private TableColumn<?, ?> photo;
    @FXML
    private Button page_précédente1;
    @FXML
    private ImageView retour33;

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
        int x;
        listPatisserie1.setEditable(true);
        int selectedIndex = listPatisserie1.getSelectionModel().getSelectedIndex();

        Patisserie p = (Patisserie) listPatisserie1.getSelectionModel().getSelectedItem();
        //x=p.getId_patisserie();

        if (selectedIndex >= 0) {
            String l = p.getLongitude().toString();
            String n = p.getLatitude().toString();
            System.out.println(l);
            nom_pat.setText(p.getNom_patisserie());
            activite_pat.setText(p.getAcitivite());
            info_pat.setText(p.getInfo_patisserie());
//           longitude.setText(l);
//            latitude.setText(n);
            adresse_pat.setText(p.getAdresse_patisserie());

            //   sp.ModifierPatisserie(p);
            sp.updatePatisserie1(p.getId_patisserie(), nom_pat.getText(), activite_pat.getText(), info_pat.getText(), Double.parseDouble(longitude_pat.getText()), Double.parseDouble(latitude_pat.getText()), adresse_pat.getText());
            label.setText("modification avec succées");
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished(y -> label.setText(""));
            pauseTransition.play();
            nom_pat.setText("");
            activite_pat.setText("");
            info_pat.setText("");
            longitude.setText("0");
            latitude.setText("0");
            adresse_pat.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
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

        Patisserie p = new Patisserie(nom_pat.getText(), activite_pat.getText(), info_pat.getText(), Double.parseDouble(longitude_pat.getText()), Double.parseDouble(latitude_pat.getText()), adresse_pat.getText(), patissier, picture.getText());
        if (nom_pat.getText().equals("") || activite_pat.getText().equals("") || info_pat.getText().equals("") || (Double.parseDouble(longitude_pat.getText()) == 0D))/*||(Double.parseDouble(longitude_pat.getText())==0D)|| adresse_pat.getText().equals("") )*/ {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Pas de Selection");
            alert.setHeaderText("Vous n'avez pas choisi une patisserie");
            alert.setContentText("Veuillez selectionnner une patisserie ");
            alert.showAndWait();

        } else {
            sp.ajouterPatisserie(p);

            label.setText("ajout avec succées");
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(z -> label.setText(""));
            pauseTransition.play();
            nom_pat.setText("");
            activite_pat.setText("");
            info_pat.setText("");
            longitude.setText("0");
            latitude.setText("0");
            adresse_pat.setText("");
            picture.setText("");
            ;
        }
    }

    @FXML
    private void getPatisseriesByidPatissier() {
        ServicePatisserie sp = new ServicePatisserie();
        int x = patissier.getId_user();

        listPatisserie1.setEditable(true);

        id_patisserie.setCellValueFactory(new PropertyValueFactory<>("id_patisserie"));
        nom_patisserie.setCellValueFactory(new PropertyValueFactory<>("nom_patisserie"));
        activite.setCellValueFactory(new PropertyValueFactory<>("activite"));
        info_patisserie.setCellValueFactory(new PropertyValueFactory<>("info_patisserie"));
        adresse_patisserie.setCellValueFactory(new PropertyValueFactory<>("adresse_patisserie"));
        id_patissier.setCellValueFactory(new PropertyValueFactory<>("patissier"));
        //     photo.setCellValueFactory(new PropertyValueFactory<>("photo_patisserie"));
        listPat1 = sp.getPatisseriesByidPatissier(x);
        listPatisserie1.setItems(listPat1);
        label.setText("pas d'ajout vérifiez vos parametres");
        label1.setText("");

    }

    @FXML
    private void supprimer1(ActionEvent event) throws SQLException {
        ServicePatisserie sp = new ServicePatisserie();
        //   int x ;
        listPatisserie1.setEditable(true);
        int selectedIndex = listPatisserie1.getSelectionModel().getSelectedIndex();

        Patisserie p = listPatisserie1.getSelectionModel().getSelectedItem();

        //x=p.getId_patisserie();
        if (selectedIndex >= 0) {
            listPatisserie1.getItems().remove(selectedIndex);
            sp.supprimer(p.getId_patisserie());
            label1.setText("patisserie supprimée");
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(o -> label1.setText(""));
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Pas de Selection");
            alert.setHeaderText("Vous n'avez pas choisi une patisserie");
            alert.setContentText("Veuillez selectionnner une patisserie ");
            alert.showAndWait();

        }

    }

    @FXML
    private void addpicture(ActionEvent event) {

        FileChooser fc = new FileChooser();
        File selectedFile1 = fc.showOpenDialog(null);
        if (selectedFile1 != null) {

            view.getItems().add(selectedFile1.getName());
            picture.setText(selectedFile1.getName());

            Image image = new Image(selectedFile1.toURI().toString());
            im1.setImage(image);
        } else {
            System.out.println("fichier non valide");
        }

    }

    @FXML
    private void page_précédente2(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPatissier.fxml"));
        Parent root = loader.load();
        Scene homePageScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

}
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

