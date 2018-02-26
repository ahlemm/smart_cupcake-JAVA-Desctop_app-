/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Commande;
import entite.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceCommande;
import utile.Config;
import vo.PanierVo;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ListCommandeController implements Initializable {

    @FXML
    private TableView<Commande> tabView3;
    @FXML
    private TableColumn<Commande, Date> dateCommande;
    @FXML
    private TableColumn<Commande, Date> dateLivraison;
    @FXML
    private TableColumn<Commande, Double> prixTotale;
    @FXML
    private TableColumn<Commande, String> etat;

    @FXML
    private TableColumn<Commande, Integer> idP;
    private ServiceCommande serviceCommande = new ServiceCommande();

    private List<Commande> findCommande() throws SQLException {
        return serviceCommande.afficherByIdUser(Config.id_user);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dateCommande.setCellValueFactory(new PropertyValueFactory<Commande, Date>("date_commande"));
            dateLivraison.setCellValueFactory(new PropertyValueFactory<Commande, Date>("date_livraison"));
            prixTotale.setCellValueFactory(new PropertyValueFactory<Commande, Double>("prix_totale"));
            etat.setCellValueFactory(new PropertyValueFactory<Commande, String>("etat_commande"));
            idP.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id_commande"));
            ObservableList<Commande> items = FXCollections.observableArrayList(findCommande());
            tabView3.setItems(items);
            tabView3.setRowFactory(new Callback<TableView<Commande>, TableRow<Commande>>() {

                public TableRow<Commande> call(TableView<Commande> tableView) {
                    final TableRow<Commande> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Annuler");
                    final MenuItem editMenuItem = new MenuItem("Details");
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
   String etat = row.getItem().getEtat_commande();
                         
                    if (etat.equalsIgnoreCase("En attente") ) {
                       try {
                    Commande cmd = row.getItem();
                    cmd.setEtat_commande("Annuler");
       
           //methode update service
           serviceCommande.modifierCommande(cmd);
            ObservableList<Commande> items = FXCollections.observableArrayList(findCommande());
            tabView3.setItems(items);
       } catch (SQLException ex) {
           Logger.getLogger(ListCommandeController.class.getName()).log(Level.SEVERE, null, ex);
       }
                    }else{
                        showMessage("vous n'avez pas le droit d'annuler cette commande!");
                    }
                        }

                    });
                    editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    @FXML
    private void retourList(ActionEvent event) throws IOException {
        
        
          Stage stage = (Stage) tabView3.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuClient.fxml")));
        window.setScene(scene);
        window.show();
    }
}
