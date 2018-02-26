/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.ServiceProduit;
import utile.Config;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ProduitGUIController implements Initializable {

    private ServiceProduit serviceProduit = new ServiceProduit();
    private Produit produit = new Produit();
    

    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker delai;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private ComboBox<String> type = new ComboBox<String>();
    @FXML
    private TableView<Produit> tabView;
    @FXML
    private TableColumn<Produit, String> colType = new TableColumn<Produit, String>();
    @FXML
    private TableColumn<Produit, String> colNom = new TableColumn<Produit, String>();
    ;
    @FXML
    private TableColumn<Produit, Double> colPrix = new TableColumn<Produit, Double>();
    @FXML
    private TableColumn<Produit, Date> colDate = new TableColumn<Produit, Date>();
    @FXML
    private TableColumn<Produit, Integer> colId;

    /**
     * Initializes the controller class.
     */
    private List<Produit> findProduct() throws SQLException {
        return serviceProduit.afficherProduitParPatisserie(Config.id_user);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            btnAdd.setVisible(true);
            btnEdit.setVisible(false);
            colType.setCellValueFactory(new PropertyValueFactory<Produit, String>("type"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
            colDate.setCellValueFactory(new PropertyValueFactory<Produit, Date>("delai_expiration"));
            colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
            ObservableList<Produit> items = FXCollections.observableArrayList(findProduct());
            tabView.setItems(items);

            type.getItems().addAll(
                    "Option 4",
                    "Option 5",
                    "Option 6"
            );

            tabView.setRowFactory(new Callback<TableView<Produit>, TableRow<Produit>>() {
                @Override //manich fehma belgde hedha
                public TableRow<Produit> call(TableView<Produit> tableView) {
                    final TableRow<Produit> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                    final MenuItem editMenuItem = new MenuItem("Editer");
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                    supprimer(row.getItem().getId());

                        }

                    });
                    editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("id"+row.getItem().getId());
                            produit.setId(row.getItem().getId());
                            type.setValue(row.getItem().getType());
                            nom.setText(row.getItem().getNom());
                            prix.setText(String.valueOf(row.getItem().getPrix()));

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(row.getItem().getDelai_expiration());

                            delai.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
                            btnAdd.setVisible(false);
                            btnEdit.setVisible(true);

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
            Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouterProduit(ActionEvent event) {
        boolean test = false;
        String message = "";

        if (type.getValue() == null) {
            test = true;
            message = "Champs Type obligatoire";
        } else if (nom.getText().length() == 0) {
            test = true;
            message = "Champs nom obligatoire";
        } else if (prix.getText().length() == 0) {
            test = true;
            message = "Champs prix obligatoire";
        } else if (!testPrix(prix.getText())) {
            test = true;
            message = " prix invalid";
        }
       else  if (delai.getValue() == null) {

            test = true;
            message = "Champs Delai éxpiration obligatoire";
        }
        if (test) {
            showMessage(message);
        } else {
            Date date = Date.from(delai.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Produit p = new Produit(type.getValue(), nom.getText(),
                    Double.parseDouble(prix.getText()), date, Config.id_user);
            try {
                serviceProduit.ajouterProduit(p);

                ObservableList<Produit> items = FXCollections.observableArrayList(findProduct());
                tabView.setItems(items);
                clear();
            } catch (SQLException ex) {
                Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean testDate(LocalDate localDate) {
        try {
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @FXML
    private void modifierProduit(ActionEvent event) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de modifier!");
            Optional<ButtonType> result = alert.showAndWait();//hedhi mafhemt.hech
            if (result.get() == ButtonType.OK) {

                System.out.println("**" + delai.getValue());

                Date date = Date.from(delai.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                produit = new Produit(produit.getId(),type.getValue(), nom.getText(),
                        Double.parseDouble(prix.getText()), date, Config.id_user);
                System.out.println("**" + date);
                 System.out.println("id"+produit.getId());
                serviceProduit.modifier(produit);

                ObservableList<Produit> items = FXCollections.observableArrayList(findProduct());
                tabView.setItems(items);
                clear();

            }

        } catch (Exception ex) {
            Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        type.setValue(null);
        nom.setText("");
        prix.setText("");
        delai.setValue(null);
        btnAdd.setVisible(true);
        btnEdit.setVisible(false);
    }

    @FXML
    private void annuler(ActionEvent event) {

        clear();
    }
    
    
    
       private void supprimer(int id) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de supprimer!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                serviceProduit.supprimer(id);
                ObservableList<Produit> items = FXCollections.observableArrayList(findProduct());
                tabView.setItems(items);
            }

        } catch (Exception ex) {
            Logger.getLogger(ProduitGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
