/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import static com.sun.deploy.config.JREInfo.clear;
import entite.Materiels;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceMateriels;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Csv;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichageMaterielsController implements Initializable {

    @FXML
    private TableView<Materiels> tableID;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> info;
    @FXML
    private TableColumn<?, ?> prixMateriel;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    
 
      
  ObservableList<Materiels> ls = FXCollections.observableArrayList();
  
 
  
    @FXML
    private TextField rech;
    @FXML
    private Button ex;
   
    /**
     * Initializes the controller class.
     */
    @FXML
    public void ajouter() throws IOException
        { 
       FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
        
        }
    @FXML
    public void modifier() throws IOException 
        { 
       FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("modifier.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
       ModifierController p ;
       ModifierController controller = fxmlLoader.<ModifierController>getController();
       Materiels m= tableID.getSelectionModel().getSelectedItem();
       controller.setMateriel(m);
       controller.SetText(m);
       
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
        
        }
    @FXML
    private void onDeleteClicked(ActionEvent event) throws SQLException {
        ServiceMateriels mservice= new ServiceMateriels();
        Materiels m= tableID.getSelectionModel().getSelectedItem();
        mservice.supprimer(tableID.getSelectionModel().getSelectedItem().getIdMateriels());
        tableID.setItems(mservice.afficher());
      //  clear();
        
    }
    private void onUpdateClicked(ActionEvent event) throws IOException {
      /* ServiceMateriels mservice= new ServiceMateriels();
        int IdMateriels= tableID.getSelectionModel().getSelectedItem().getIdMateriels();
        Materiels m= tableID.getSelectionModel().getSelectedItem();
      // m.setIdMateriels(IdMateriels);
        //mservice.modifier(m);
        //t1ableID.setItems(mservice.afficher());
        clear();
        */
       FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            
       Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            stage.show();
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
         info.setCellValueFactory(new PropertyValueFactory<>("info"));
         prixMateriel.setCellValueFactory(new PropertyValueFactory<>("prixMateriel"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         ServiceMateriels m= new ServiceMateriels();
         System.out.println("hhh");
        try {
            tableID.setItems(m.afficher());
   
            
            // TODO
            // tableMoniteur.setItems(mservice.getAllMoniteurs());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        searcher();
    }    

    @FXML
    private void searcher() {
         
           FilteredList<Materiels> filterData;
        filterData = new FilteredList<>(ls, H -> true);
        
        rech.setOnKeyReleased( e -> {
         rech.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate((Predicate<? super Materiels>)mat-> {
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (mat.getType().toLowerCase().contains(typedText)) {
                    return true;
                }
                return false;
            });
        
        });
       
            SortedList<Materiels> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableID.comparatorProperty());
            tableID.setItems(sortedList);
        });
       
      
    }

    @FXML
    private void excel(ActionEvent event) throws SQLException {
        ServiceMateriels mservice= new ServiceMateriels();
         Csv csv=new Csv(mservice.afficher());

        
    }

   

  
}
