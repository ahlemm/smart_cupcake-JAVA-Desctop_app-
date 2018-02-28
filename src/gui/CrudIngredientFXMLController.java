/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import service.ServiceIngredient;
import entite.Ingredient;
import entite.Recette;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import utile.Config;
import service.ServiceRecette;


/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class CrudIngredientFXMLController implements Initializable {
      private ServiceIngredient serviceIngredient = new ServiceIngredient();
    private Ingredient ingredient = new Ingredient();
    

    @FXML
    private Button ajouter_ingredient;
    @FXML
    private TextField prix_ingredient;
 
    @FXML
    private TextField nom_ingredient;
    @FXML
    private TableView<Ingredient> tabView;
    @FXML
    private TableColumn<Ingredient,String> colNom = new TableColumn<Ingredient, String>();
     @FXML
    private TableColumn<Ingredient,Double> colPrix  = new TableColumn<Ingredient, Double>();
    @FXML
    private TableColumn<Ingredient,Integer> colID ;  
   
    @FXML
    private Button modifier_ingredient;
    @FXML
    private Button annuler;
    @FXML
    private Button menuretour;
 

    /**
     * Initializes the controller class.
   */
   private List<Ingredient> findIngredient() throws SQLException {
        return serviceIngredient.afficher();
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            
             ajouter_ingredient.setVisible(true);
                            modifier_ingredient.setVisible(false);
//                             ajouterRecette.setVisible(true);
//                            modifierRecette.setVisible(false);
            
              colNom.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("nom_ingredient"));
               colPrix.setCellValueFactory(new PropertyValueFactory<Ingredient,Double>("prix_ingredient"));
                  colID.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("id_ingredient"));
              ObservableList<Ingredient> items = FXCollections.observableArrayList(findIngredient());
             tabView.setItems(items);
           
            
             
             
             tabView.setRowFactory(new Callback<TableView<Ingredient>, TableRow<Ingredient>>() {
                @Override //manich fehma belgde hedha
                public TableRow<Ingredient> call(TableView<Ingredient> tableView) {
                    final TableRow<Ingredient> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                    final MenuItem editMenuItem = new MenuItem("Modifier");
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                   supprimer(row.getItem().getId_ingredient());

                        }

                    });
                    editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                           // System.out.println("id"+row.getItem().getId_ingredient());
                            ingredient.setId_ingredient(row.getItem().getId_ingredient());
                            
                            nom_ingredient.setText(row.getItem().getNom_ingredient());
                            prix_ingredient.setText(String.valueOf(row.getItem().getPrix_ingredient()));
                             

                                ajouter_ingredient.setVisible(false);
                            modifier_ingredient.setVisible(true);

                        }

                    });
                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(editMenuItem);
                    // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu)
                    );
                    return row;
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

           
                 
           
           
    }
          
      

    @FXML
    private void ajouter_ingredient(ActionEvent event) {
         boolean test = false;
        String message = "";

        if (nom_ingredient.getText().length() == 0) {
            test = true;
            message = "Champs nom obligatoire";
        }  else if (!testPrix(prix_ingredient.getText())) {
            test = true;
            message = " prix invalid";
        }
       
        if (test) {
            showMessage(message);
        } else {
           

            Ingredient i = new Ingredient(nom_ingredient.getText(),
                    Double.parseDouble(prix_ingredient.getText()) );
            try {
                serviceIngredient.ajouterIngredient(i);

                ObservableList<Ingredient> items = FXCollections.observableArrayList(findIngredient());
                tabView.setItems(items);
                clear();
            } catch (SQLException ex) {
                Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    private void clear() {
        
        nom_ingredient.setText(""); 
      
        prix_ingredient.setText("");
        
       
    }

    @FXML
    private void modifier_ingredient(ActionEvent event) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de modifier!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

               
               ingredient = new Ingredient(ingredient.getId_ingredient(), nom_ingredient.getText(),Double.parseDouble(prix_ingredient.getText()));
                        
               
               //  System.out.println("id"+ingredient.getId_ingredient());
                serviceIngredient.modifier(ingredient);

                ObservableList<Ingredient> items = FXCollections.observableArrayList(findIngredient());
                tabView.setItems(items);
                clear();
                ajouter_ingredient.setVisible(true);
                            modifier_ingredient.setVisible(false);

            }

        } catch (Exception ex) {
            Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
       private void supprimer(int id) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de supprimer!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                serviceIngredient.supprimer(id);
                ObservableList<Ingredient> items = FXCollections.observableArrayList(findIngredient());
                tabView.setItems(items);
            }

        } catch (Exception ex) {
            Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void annuler_ajout(ActionEvent event) {
        clear();
    }

    @FXML
    private void retour_admin1(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
        
    }

    

    
    }
    
    
    
  

