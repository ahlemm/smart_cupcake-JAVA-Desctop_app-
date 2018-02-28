/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField info;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private DatePicker date;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterReclamation(ActionEvent event) throws SQLException, IOException {
        boolean test = false;
        String message = "";
        if (info.getText().length() == 0) {
            test = true;
            message = "Champs Info obligatoire";
        } 
   else if (email.getText().length() == 0) {
            test = true;
            message = "Champs Email obligatoire";
        } 
         else  if (date.getValue() == null) {

            test = true;
            message = "Champs date obligatoire";
        }
        else if (!testNbr(tel.getText())) {
            test = true;
            message = " tel invalid";
        }
    if (test) {
            showMessage(message);
        }else
    {
        String infoM=info.getText();
        int telM=Integer.parseInt(tel.getText());
   //Calendar cal = Calendar.getInstance();
  // Date sqldate = new Date()
     // cal.setTime(ls.getItem().getDateReclamation());
// Date dateReclamation =Date.form(dateReclamation.getValue().atStartOfDay(ZoneID.systemDefault()).toInstant())
    
//date.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
    String emailM=email.getText();
   // Date dateM =java.sql.Date.valueOf(date.getValue());
    java.sql.Date dateM =java.sql.Date.valueOf(date.getValue());
    ServiceReclamation service= new ServiceReclamation();
        System.out.println(dateM);
    Reclamation Mat=new Reclamation(infoM, telM, dateM, emailM);
        System.out.println("test");
    service.ajouterReclamation(Mat);
    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AffichageReclamation.fxml"));
            
      Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
          stage.initModality(Modality.APPLICATION_MODAL);
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
           stage.show();
    }
    }
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }
    
        
   private boolean testDate(LocalDate localDate) {
        try {
            Date date = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }  
private boolean testNbr(String nh) {
        try {
            Integer.parseInt(nh);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    
}
}
