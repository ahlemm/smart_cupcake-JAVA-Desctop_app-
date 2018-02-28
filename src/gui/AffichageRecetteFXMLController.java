/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entite.Ingredient;
import entite.Recette;
import java.io.ByteArrayInputStream;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceRecette;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class AffichageRecetteFXMLController implements Initializable {

    public static int id_recette = 0 ;
    private ServiceRecette servicerecette = new ServiceRecette();
    private Recette recette = null;

    @FXML
    TextField nom ;
    @FXML
    TextField prix ;
    @FXML
    TextArea description ;
    @FXML
    ImageView image ;
    @FXML
    TableView ingredients ;
    @FXML
    private TableColumn<Ingredient, String> ing_nom = new TableColumn<Ingredient, String>();
    @FXML
    private TableColumn<Ingredient, Double> ing_qte = new TableColumn<Ingredient, Double>();

private Image img;

    /**
     * Initializes the controller class.
     */

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            recette = servicerecette.detailsRecette(id_recette);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nom.setText(recette.getNom_recette());
        prix.setText(recette.getPrix_recette()+"");
        description.setText(recette.getInfo_recette());
        // image view
        byte byteImage[] = new byte[0];
        try{
            byteImage = recette.getImage().getBytes(1, (int)recette.getImage().length());
        
        } catch (SQLException ex) {
            Logger.getLogger(AffichageRecetteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image img = new Image(new ByteArrayInputStream(byteImage));
        
        ing_nom.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("nom_ingredient"));
        ing_qte.setCellValueFactory(new PropertyValueFactory<Ingredient,Double>("quantite"));
        image.setImage(img);
        
        ObservableList<Ingredient> items = FXCollections.observableArrayList(recette.getIngredients());
        ingredients.setItems(items);
        ingredients.setRowFactory(new Callback<TableView<Ingredient>, TableRow<Ingredient>>() {
            @Override
            public TableRow<Ingredient> call(TableView<Ingredient> tableView) {
                final TableRow<Ingredient> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();

                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );
                return row;
            }
        });




    }    

    @FXML
    private void retourRecette(ActionEvent event) {
        Parent pane = null;
        try {
            pane = FXMLLoader.load(
                    getClass().getResource("crudRecetteFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(pane);
        
    }

    @FXML
    private void showYoutube(ActionEvent event) throws IOException {
        YoutubeFXMLController.nom_recette = nom.getText();
          Stage stage = (Stage) nom.getScene().getWindow();
      
       Stage window  = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("youtubeFXML.fxml")));
window.setScene(scene);
window.show();
        
    }
    }
    

    
    

