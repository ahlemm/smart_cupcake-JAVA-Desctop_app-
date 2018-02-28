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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierController implements Initializable {

    @FXML
    private Label modif;
    @FXML
    private TextField type;
    @FXML
    private TextField info;
    @FXML
    private TextField prix;
    @FXML
    private TextField etat;
    @FXML
    private Button modi;
    Materiels Materiel;

    /**
     * Initializes the controller class.
     */
   
@FXML
     public void SetText(Materiels m)  {
    type.setText(m.getType());
    info.setText(m.getInfo());
    prix.setText(Float.toString(m.getPrixMateriel()));
    etat.setText(m.getEtat());
     }
    @FXML
    private void M(ActionEvent event) throws IOException {
        //type.setText(tableID.getSelectionModel().getSelectedItem().getIdMateriels());
         boolean test = false;
        String message = "";
    System.out.println(Materiel.getIdMateriels());
    //type.setText(value);
     if (type.getText().length() == 0) {
            test = true;
            message = "Champs Type obligatoire";
        } 
    else if (info.getText().length() == 0) {
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
    String infoM=info.getText();
    float prixM=Float.parseFloat(prix.getText());
    String etatM=etat.getText();
    
    
    Materiels Mat=new Materiels(Materiel.getIdMateriels(),typeM, infoM, prixM, etatM);
    
   
    service.modifier(Mat);
    }
    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AffichageMateriels.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
    
               
    }
public void setMateriel(Materiels M){
     Materiel= M;
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
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