/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Ingredient;
import entite.Recette;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.sql.rowset.serial.SerialBlob;
import service.ServiceIngredient;
import service.ServiceRecette;
import utile.Config;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class CrudRecetteFXMLController implements Initializable {
    private ServiceRecette servicerecette = new ServiceRecette();
    private ServiceIngredient serviceIngredient = new ServiceIngredient();
    private ObservableList<String> list_ing = FXCollections.observableArrayList();
    private Recette recette = new Recette();
    private List<Ingredient> all_ing = null;

    @FXML
    private VBox vbox_ing;
    @FXML
    private TextField nomR;
    @FXML
    private TextField descR;
    @FXML
    private TextField prixR;
    @FXML
    private Button ajouterRecette;
    @FXML
    private TableColumn<Recette, String> nomRecette = new TableColumn<Recette, String>();
    @FXML
    private TableColumn<Recette, String> descRecette = new TableColumn<Recette, String>();
    @FXML
    private TableColumn<Recette, Double> prixRecette = new TableColumn<Recette, Double>();
    @FXML
    private TableColumn<Recette, Integer> idRecette;
    @FXML
    private TextField imgR;
    @FXML
    private TableView<Recette> tabRecette;
    @FXML
    private Button modifierRecette;
    @FXML
    private Button plusIngrediant;
    @FXML
    private Button btnImage;
    @FXML
    private Button filtre;
    @FXML
    private Button nouv_ing;
    @FXML
    private Button retourclient;

    
     private List<Recette> findRecette() throws SQLException {
        return servicerecette.afficherRecette();
   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ajouterRecette.setVisible(true);
            modifierRecette.setVisible(false);
            nomRecette.setCellValueFactory(new PropertyValueFactory<Recette,String>("nom_recette"));
            prixRecette.setCellValueFactory(new PropertyValueFactory<Recette,Double>("prix_recette"));
            descRecette.setCellValueFactory(new PropertyValueFactory<Recette,String>("info_recette"));
            idRecette.setCellValueFactory(new PropertyValueFactory<Recette,Integer>("id_recette"));
           
            ObservableList<Recette> items = FXCollections.observableArrayList(findRecette());
            tabRecette.setItems(items);
            tabRecette.setRowFactory(new Callback<TableView<Recette>, TableRow<Recette>>() {
                @Override
                public TableRow<Recette> call(TableView<Recette> tableView) {
                    final TableRow<Recette> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem showMenuItem = new MenuItem("Voir details");
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                    final MenuItem editMenuItem = new MenuItem("Modifier");
                    showMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            AffichageRecetteFXMLController.id_recette = row.getItem().getId_recette();
                            Parent pane = null;
                            try {
                                pane = FXMLLoader.load(
                                        getClass().getResource("affichageRecetteFXML.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Stage stage = (Stage) (tabRecette).getScene().getWindow();
                            stage.getScene().setRoot(pane);
                        }

                    });
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                   supprimerRecette(row.getItem().getId_recette());
                        }

                    });
                    editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                           // System.out.println("id"+row.getItem().getId_ingredient());
                           recette.setId_recette(row.getItem().getId_recette());
                            
                            nomR.setText(row.getItem().getNom_recette());
                            descR.setText(row.getItem().getInfo_recette());
                            prixR.setText(String.valueOf(row.getItem().getPrix_recette()));
                             

                                ajouterRecette.setVisible(false);
                            modifierRecette.setVisible(true);

                        }

                    });
                    contextMenu.getItems().add(showMenuItem);
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
            all_ing = serviceIngredient.afficher();
    }    catch (SQLException ex) {
            Logger.getLogger(CrudRecetteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }


        for (Ingredient ingr : all_ing) {
            list_ing.add(ingr.getNom_ingredient());
        }

        HBox hBox = new HBox();
        ComboBox ing = new ComboBox();

        TextField qte = new TextField();
        int size = vbox_ing.getChildren().size()-1 ;

        ing.setId("ingrediant"+size);
        ing.setPromptText("selectioner ingrediant");
        ing.setPrefWidth(150);
        ing.setItems(list_ing);
        ing.setOnAction(event -> {
            calcule_somme();
        });

        qte.setId("qte"+size);
        qte.setPromptText("Qte");
        qte.setPrefWidth(52);
        qte.setOnKeyReleased(event -> {
            calcule_somme();
        });

        hBox.getChildren().add(ing);
        hBox.getChildren().add(qte);
        HBox.setMargin(ing,new Insets(0, 0, 0, 10));
        HBox.setMargin(qte,new Insets(0, 0, 0, 10));
        HBox.setMargin(hBox,new Insets(0, 0, 5, 0));
        vbox_ing.getChildren().add(hBox);

        calcule_somme();
    }


    @FXML
    private void plus_ingrediant(ActionEvent event) {
        HBox hBox = new HBox();
        ComboBox ing = new ComboBox();
        TextField qte = new TextField();
        int size = vbox_ing.getChildren().size()-1 ;

        ing.setId("ingrediant"+size);
        ing.setPromptText("selectioner ingrediant");
        ing.setPrefWidth(150);
        ing.setItems(list_ing);
        ing.setOnAction(evt -> {
            calcule_somme();
        });

        qte.setId("qte"+size);
        qte.setPromptText("Qte");
        qte.setPrefWidth(52);
        qte.setOnKeyReleased(evt -> {
            calcule_somme();
        });

        hBox.getChildren().add(ing);
        hBox.getChildren().add(qte);
        HBox.setMargin(ing,new Insets(0, 0, 0, 10));
        HBox.setMargin(qte,new Insets(0, 0, 0, 10));
        HBox.setMargin(hBox,new Insets(0, 0, 5, 0));
        vbox_ing.getChildren().add(hBox);

    }

    private double calcule_somme (){
        int size = vbox_ing.getChildren().size()-1 ;
        double total = 0 ;
        for (int i=0 ; i < size ; i++ ){
            ComboBox ing = (ComboBox) vbox_ing.lookup("#ingrediant"+i);
            TextField qte = (TextField) vbox_ing.lookup("#qte"+i);


            for ( Ingredient ingr:all_ing ) {
                if(ingr.getNom_ingredient().equals(ing.getValue())){
                    double qte_ ;
                    try{
                        qte_ = Double.parseDouble(qte.getText().replace(',','.')) ;
                    }catch(Exception e){
                        qte_=0;
                    }
                    double prix_ = ingr.getPrix_ingredient();
                    total +=  qte_ * prix_ ;
                }
            }
        }

        DecimalFormat df = new DecimalFormat(".##");
        total = Double.valueOf(df.format(total).replace(',','.'));

        prixR.setText(total+"");

        return total ;
    }
public static byte[] convertFileCententToBlob(File file) throws IOException{
        byte[] fileCentent = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
           inputStream.read(fileCentent);
            
        } catch (IOException e){
            throw new IOException("unable te convert file."+ e.getMessage());
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        return fileCentent;
        }
}
    @FXML
    private void ajouter_recette(ActionEvent event) throws IOException, SQLException {
        Blob blob = null;
         boolean test = false;
        String message = "";

        if (nomR.getText().length() == 0) {
            test = true;
            message = "Champs nom obligatoire";
        }  else if(descRecette.getText().length() == 0) {
            test = true;
            message = "Champs description obligatoire";
        } else if(imgR.getText().length() == 0) {
            test = true;
            message = "vous devez entrer une image";
        } 
       
        if (test) {
            showMessage(message);
        } else {

            int size = vbox_ing.getChildren().size()-1 ;
            ArrayList<Ingredient> ingrs = new ArrayList<>();
            for (int i=0 ; i < size ; i++ ){
                ComboBox ing = (ComboBox) vbox_ing.lookup("#ingrediant"+i);
                TextField qte = (TextField) vbox_ing.lookup("#qte"+i);

                for ( Ingredient ingr:all_ing ) {
                    if(ingr.getNom_ingredient().equals(ing.getValue())){
                        ingrs.add(new Ingredient(ingr.getId_ingredient(),Double.parseDouble(qte.getText())) );
                    }
                }


            }
         blob = new SerialBlob(convertFileCententToBlob(file));

          Recette r = new Recette(nomR.getText(),descR.getText(),
                  calcule_somme(),blob,Config.id_user);
            try {
               
                servicerecette.ajouterRecette(r,ingrs);

                ObservableList<Recette> items2 = FXCollections.observableArrayList(findRecette());
                tabRecette.setItems(items2);
                clear();
                 
                
            } catch (SQLException ex) {
                Logger.getLogger(CrudRecetteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        nomR.setText(""); 
      descR.setText("");
        prixR.setText("");
        
       
    }

    @FXML
    private void modifier_recette(ActionEvent event) {
         try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de modifier!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

               
               recette = new Recette(recette.getId_recette(), nomR.getText(),descR.getText(),Double.parseDouble(prixR.getText()));
                        
               
               //  System.out.println("id"+ingredient.getId_ingredient());
                servicerecette.modifierRecette(recette);

                ObservableList<Recette> items2 = FXCollections.observableArrayList(findRecette());
                tabRecette.setItems(items2);
                clear();
                ajouterRecette.setVisible(true);
                           modifierRecette.setVisible(false);

            }

        } catch (Exception ex) {
            Logger.getLogger(CrudRecetteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void supprimerRecette(int id) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de supprimer!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                servicerecette.supprimerR(id);
                ObservableList<Recette> items2 = FXCollections.observableArrayList(findRecette());
                tabRecette.setItems(items2);
            }

        } catch (Exception ex) {
            Logger.getLogger(CrudIngredientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    final FileChooser fileChooser = new FileChooser();
    File file;
    
    @FXML
    private void ParcourirImage(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imgR.setText(file.getPath());
        }
    }

    @FXML
    private void trierRecette(ActionEvent event) throws SQLException {
         ObservableList<Recette> items = FXCollections.observableArrayList(servicerecette.TrieRecette());
            tabRecette.setItems(items);
    }

    @FXML
    private void nouvel_ing(ActionEvent event) throws IOException {
        Stage stage = (Stage) nomR.getScene().getWindow();
      
       Stage window  = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("nouvelIngredientFXML.fxml")));
window.setScene(scene);
window.show();
        
    }

    @FXML
    private void retour_client(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
        
    }
    
    
}