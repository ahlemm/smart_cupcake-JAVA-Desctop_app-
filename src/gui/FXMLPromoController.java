/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import utile.Config;
import entite.Formation;
import entite.Produit;
import entite.Promotion;
import java.io.IOException;

import service.ServiceInscription;
import service.ServiceProduit;

import service.ServicePromotion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arbia
 */
public class FXMLPromoController implements Initializable {

    @FXML
    private TableColumn<Produit, Integer> id_produit=new TableColumn<Produit, Integer>();
    @FXML
    private TableColumn<Produit, Double> prix_produit=new TableColumn<Produit, Double>();
    @FXML
    private TableColumn<Produit, String> nom_produit=new TableColumn<Produit, String>();
    /**
     * Initializes the controller class.
     */ 
    
    @FXML
    private TableView<Produit> tab;
    
    @FXML
    private TableColumn<Promotion, Double> prixP=new TableColumn<Promotion, Double>();
    @FXML
    private TableColumn<Promotion, Integer> pourcentage=new TableColumn<Promotion, Integer>();
    

    @FXML
    private TableView<Promotion> tab1;
    @FXML
    private TableColumn<Promotion, String> nom=new TableColumn<Promotion, String>();
    @FXML
    private TableColumn<Promotion, Double> nv_prix=new TableColumn<Promotion, Double>();
     
    
       
    @FXML
    private TableColumn<Promotion, Integer> id=new TableColumn<Promotion, Integer>();
     ServiceProduit ServiceProduit = new ServiceProduit();
     private List<Promotion> listPromotion = new ArrayList<Promotion>();
     ServicePromotion ServicePromotion = new ServicePromotion();
     
     private double nouveauP = 0;
    @FXML
    private Pane pane;
    @FXML
    private Label nouveau_prix;
    @FXML
    private Button retour7;
    
    private List<Produit> findProduit() throws SQLException {
        return ServiceProduit.afficher();
    } 
     private List<Promotion> findPromotion() throws SQLException {
        return ServicePromotion.afficher();
    } 
     
//    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
         try{ 
               if (tab1.getItems().isEmpty()) {
                tab1.setVisible(true);
                pane.setVisible(false);
            } else {
                tab1.setVisible(true);
                pane.setVisible(true);
            }
              id_produit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
            prix_produit.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
 
            nom_produit.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            ObservableList<Produit> items = FXCollections.observableArrayList(findProduit());
            tab.setItems(items); 
            ObservableList<Produit> tab = FXCollections.observableArrayList(ServiceProduit.afficher());
         /////////////////////////
         
         } catch (SQLException ex) {
            Logger.getLogger(FXMLPromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         tab.setRowFactory(new Callback<TableView<Produit>, TableRow<Produit>>() {
                @Override
                public TableRow<Produit> call(TableView<Produit> tableView) {
                    final TableRow<Produit> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem addMenuItem = new MenuItem("ajouter une promotion");

                    addMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            openDialog(row.getItem().getNom(), row.getItem().
                                    getPrix(), row.getItem().getId());
                        }

                       
                    
                    });

                    contextMenu.getItems().add(addMenuItem);

                    // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu)
                    );
                    return row;
                }
            });  
         try{
                  nom.setCellValueFactory(new PropertyValueFactory<Promotion, String>("nom_produit"));
            prixP.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("prix_produit"));
            pourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("pourcentage"));
            nv_prix.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("nv_prix"));
            id.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("id_produit"));  
            
             ObservableList<Promotion> items = FXCollections.observableArrayList(findPromotion());
            tab1.setItems(items); 
            
            tab1.setRowFactory(new Callback<TableView<Promotion>, TableRow<Promotion>>() {
                @Override //manich fehma belgde hedha
                public TableRow<Promotion> call(TableView<Promotion> TableView) {
                    final TableRow<Promotion> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                  
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                    supprimer(row.getItem().getId_promotion());

                        } 

                    }); 
             contextMenu.getItems().add(removeMenuItem);
                    
                      row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu)
                    );
                    return row; 
                } 
            
            }); } 
         catch (SQLException ex) {
            Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
         
       
         
    } 
     private boolean testPrix(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

private void openDialog(String productName, double prix_produit, int id_produit) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Choix de pourcentage");
        dialog.setHeaderText(productName);
        dialog.setContentText("pourcentage:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            boolean test = true;
            String message = "";
            if (!testPrix(result.get())) {
                test = false;
                message = "pourcentage invalide";
            }
            if (test) {

                int pour = Integer.parseInt(result.get());
                double nv = (prix_produit)-((pour * (prix_produit))/100);

                Promotion promo = new Promotion();
                promo.setNom_produit(productName);
                promo.setPourcentage(pour);
                promo.setPrix_produit(prix_produit);
                promo.setNv_prix(nv);
                promo.setId_produit(id_produit);

               nouveauP = nv;
                nouveau_prix.setText(nouveauP + "TND");
                listPromotion.add(promo); 
                

                ObservableList<Promotion> items = FXCollections.
                        observableArrayList(listPromotion);
                        
                tab1.setItems(items);    
                        
                        
                
                
                
                 

            try {
                
                for (Promotion prom : tab1.getItems()) {
                    
                    Promotion pr = new Promotion(prom.getPourcentage(),prom.getPrix_produit(),prom.getNv_prix(),prom.getNom_produit(),prom.getId_produit(),Config.id_user);
                     
                ServicePromotion.ajouterPromotion(pr);
                }
} catch (SQLException ex) {
                Logger.getLogger(FXMLPromoController.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (tab1.getItems().isEmpty()) {
                    tab1.setVisible(false);
                    pane.setVisible(false);
                } else {
                    tab1.setVisible(true);
                    pane.setVisible(true);
                }  
        }
             else {
           
                showMessage(message);
            }
            } 
        } 
private void supprimer(int id_promotion) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de supprimer!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServicePromotion.supprimer(id_promotion);
                ObservableList<Promotion> items = FXCollections.observableArrayList(findPromotion());
                tab1.setItems(items);
            }

        } catch (Exception ex) {
            Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour7(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClient.fxml"));
            Parent root = loader.load();
           Scene homePageScene=new Scene(root); 
           Stage appStage =(Stage) ((Node) event.getSource()).getScene().getWindow(); 
           appStage.setScene(homePageScene); 
           appStage.show();
    }
 }

    


    
   
    
     
    

