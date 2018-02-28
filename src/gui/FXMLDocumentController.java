/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Materiels;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceMateriels;

/**
 *
 * @author Firassov
 */
public class FXMLDocumentController implements Initializable {

      @FXML
    private TextField type;

    private TextField info;
    private TextField prixMateriels;

    @FXML
    private TextField etat;
    @FXML
    private Label label;
    @FXML
    private TextField nfo;
    @FXML
    private TextField prix;
    @FXML
    private Button btn;

    void savePersonne(ActionEvent event) throws IOException, SQLException {
        float id1=Integer.parseInt(prixMateriels.getText());
        String nom1=type.getText();
        String nom2=info.getText();
       // float nom3=prixMateriels.getText();
     /*   String nom4=etat.getText();
         ServiceMateriels ps=new ServiceMateriels();
         Materiels p =new Materiels(nom1, nom2,id1,nom4);
        ps.ajouterMateriels(p);
        FXMLLoader l=new FXMLLoader(getClass().getResource("FXMLafficher.fxml"));
        Parent pr= l.load();*/
        //FXMLafficherController personeaff=l.getController();
      //  personeaff.setId(id.getText());
       // personeaff.setNom(nom1);
       //// Scene s =nom.getScene();
        //s.setRoot(pr);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterMateriel(ActionEvent event) throws SQLException, IOException {
        boolean test = false;
        String message = "";
   // String typeM=type.getText();
    //String infoM=nfo.getText();
    //float prixM=Float.parseFloat(prix.getText());
    //String etatM=etat.getText();
    /* if (prix.getText().length() == 0) {
            test = true;
            message = "Champs  obligatoire";
        } else if (!testprix(prix.getText())) {
            test = true;
            message = " prix invalid";
        }   */
    if (type.getText().length() == 0) {
            test = true;
            message = "Champs Type obligatoire";
        } 
    else if (nfo.getText().length() == 0) {
            test = true;
            message = "Champs info obligatoire";
        } else if (etat.getText().length() == 0) {
            test = true;
            message = "Champs prix obligatoire";
        } else if (prix.getText().length() == 0) {
            test = true;
            message = "Champs prix obligatoire";
        }else if (!testPrix(prix.getText())) {
            test = true;
            message = " prix invalid";
        }
    if (test) {
            showMessage(message);
        }else
    {ServiceMateriels service= new ServiceMateriels();
     String typeM=type.getText();
    String infoM=nfo.getText();
    float prixM=Float.parseFloat(prix.getText());
   String etatM=etat.getText();
    Materiels Mat=new Materiels(typeM, infoM, prixM,etatM);
    service.ajouterMateriels(Mat);}
    
    
     
    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AffichageMateriels.fxml"));
            
      Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
          stage.initModality(Modality.APPLICATION_MODAL);
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
           stage.show();
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
    } 
      /* private boolean testprix(String h) {
        try {
            Float.parseFloat(h);
            return true;
        } catch (NumberFormatException ex) {
            return false;
    }  }*/

