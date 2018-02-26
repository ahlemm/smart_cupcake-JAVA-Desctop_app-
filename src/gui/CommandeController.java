/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Commande;
import entite.Panier;
import entite.Produit;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
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
import service.ServiceCommande;
import service.ServicePanier;
import service.ServiceProduit;
import service.ServiceUser;
import utile.Config;
import vo.PanierVo;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class CommandeController implements Initializable {

    private List<PanierVo> listPanier = new ArrayList<PanierVo>();
    private ServiceCommande serviceCommande = new ServiceCommande();
    private ServicePanier servicePanier = new ServicePanier();
    private double somme = 0;
    @FXML
    private TextField Pmin;
    @FXML
    private TextField Pmax;
    @FXML
    private ComboBox<String> TypeP = new ComboBox<String>();
    @FXML
    private ComboBox<Produit> NomPr = new ComboBox<Produit>();
    @FXML
    private ComboBox<User> NomPa = new ComboBox<User>();
    @FXML
    private TableView<Produit> tabView1;
    @FXML
    private TableColumn<Produit, String> type = new TableColumn<Produit, String>();
    @FXML
    private TableColumn<Produit, String> nom = new TableColumn<Produit, String>();

    @FXML
    private TableColumn<Produit, Double> prix = new TableColumn<Produit, Double>();
    @FXML
    private TableColumn<Produit, Date> date = new TableColumn<Produit, Date>();

    @FXML
    private TableColumn<Produit, Integer> Id;
    private ServiceProduit serviceProduit = new ServiceProduit();
    private ServiceUser serviceUser = new ServiceUser();
    @FXML
    private Button BtnFiltrer;
    @FXML
    private Button BtnInit;
    @FXML
    private TableView<PanierVo> tabView2;
    @FXML
    private TableColumn<PanierVo, String> nomP;
    @FXML
    private TableColumn<PanierVo, Double> prixP;
    @FXML
    private TableColumn<PanierVo, Integer> quantiteP;
    @FXML
    private TableColumn<PanierVo, Double> prixTotaleP;
    @FXML
    private TableColumn<PanierVo, Integer> idProd;
    @FXML
    private Label prixTotal;
    @FXML
    private DatePicker dateCmd;
    @FXML
    private Button btnCmd;
    @FXML
    private Pane panel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            if (tabView2.getItems().isEmpty()) {
                tabView2.setVisible(false);
                panel.setVisible(false);
            } else {
                tabView2.setVisible(true);
                panel.setVisible(true);
            }

            type.setCellValueFactory(new PropertyValueFactory<Produit, String>("type"));
            nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            prix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
            date.setCellValueFactory(new PropertyValueFactory<Produit, Date>("delai_expiration"));
            Id.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
            ObservableList<Produit> items = FXCollections.observableArrayList(serviceProduit.afficher());
            tabView1.setItems(items);

            ObservableList<Produit> productList = FXCollections.observableArrayList(serviceProduit.afficher());

            NomPr.getItems().addAll(productList);

            NomPr.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

                @Override
                public ListCell<Produit> call(ListView<Produit> p) {

                    final ListCell<Produit> cell = new ListCell<Produit>() {

                        @Override
                        protected void updateItem(Produit t, boolean bln) {
                            super.updateItem(t, bln);

                            if (t != null) {

                                setText(t.getNom());
                            } else {

                                setText(null);
                            }
                        }

                    };

                    return cell;
                }
            });

            ObservableList<User> userList = FXCollections.observableArrayList(serviceUser.findAllPatisserie());

            NomPa.getItems().addAll(userList);

            NomPa.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

                @Override
                public ListCell<User> call(ListView<User> p) {

                    final ListCell<User> cell = new ListCell<User>() {

                        @Override
                        protected void updateItem(User u, boolean bln) {
                            super.updateItem(u, bln);

                            if (u != null) {

                                setText(u.getNom());
                            } else {

                                setText(null);
                            }
                        }

                    };

                    return cell;
                }
            });

            TypeP.getItems().addAll(
                    "Option 4",
                    "Option 5",
                    "Option 6"
            );

            /////////////////////////
            tabView1.setRowFactory(new Callback<TableView<Produit>, TableRow<Produit>>() {
                @Override
                public TableRow<Produit> call(TableView<Produit> tableView) {
                    final TableRow<Produit> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem addMenuItem = new MenuItem("Ajouter au panier");

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
// intialiaze tabview 2

            nomP.setCellValueFactory(new PropertyValueFactory<PanierVo, String>("nom_produit"));
            prixP.setCellValueFactory(new PropertyValueFactory<PanierVo, Double>("prix_unitaire"));
            quantiteP.setCellValueFactory(new PropertyValueFactory<PanierVo, Integer>("quantite"));
            prixTotaleP.setCellValueFactory(new PropertyValueFactory<PanierVo, Double>("prix_totale_produit"));
            idProd.setCellValueFactory(new PropertyValueFactory<PanierVo, Integer>("id_produit"));
            ///////
            tabView2.setRowFactory(new Callback<TableView<PanierVo>, TableRow<PanierVo>>() {

                public TableRow<PanierVo> call(TableView<PanierVo> tableView) {
                    final TableRow<PanierVo> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Annuler");

                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //     row.getItem()

                            somme -= row.getItem().getPrix_totale_produit();
                            prixTotal.setText(somme + "TND");
                            tabView2.getItems().remove(row.getItem());

                            if (tabView2.getItems().isEmpty()) {
                                tabView2.setVisible(false);
                                panel.setVisible(false);
                            } else {
                                tabView2.setVisible(true);
                                panel.setVisible(true);
                            }
                        }

                    });

                    contextMenu.getItems().add(removeMenuItem);

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
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void filtrer(ActionEvent event) {

        try {
            boolean test = true;
            String message = "";
            if (!testPrix(Pmin.getText()) && Pmin.getText().length() != 0) {
                test = false;
                message = "Prix min invalid";
            } else if (!testPrix(Pmax.getText()) && Pmax.getText().length() != 0) {
                test = false;
                message = "Prix max invalid";
            }

            if (test) {
                double min = 0;
                double max = 0;
                String nomProd, nomPat, typeProd;
                if (Pmin.getText().length() != 0) {
                    min = Double.parseDouble(Pmin.getText());
                }
                if (Pmax.getText().length() != 0) {
                    max = Double.parseDouble(Pmax.getText());
                }
                if (NomPr.getValue() == null) {
                    nomProd = "";
                } else {
                    nomProd = NomPr.getValue().toString();
                }
                if (NomPa.getValue() == null) {
                    nomPat = "";
                } else {
                    nomPat = NomPa.getValue().toString();
                }
                if (TypeP.getValue() == null) {
                    typeProd = "";
                } else {
                    typeProd = TypeP.getValue().toString();
                }
                List<Produit> listProd = serviceProduit.filtrerProduit(nomProd, nomPat, typeProd, min, max);

                ObservableList<Produit> items = FXCollections.observableArrayList(listProd);
                tabView1.setItems(items);
            } else {
                showMessage(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void initialiserLaPage(ActionEvent event) {

        NomPa.setValue(null);
        NomPr.setValue(null);
        TypeP.setValue(null);
        Pmin.setText("");
        Pmax.setText("");

        try {
            ObservableList<Produit> items = FXCollections.observableArrayList(serviceProduit.afficher());
            tabView1.setItems(items);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void openDialog(String productName, double prixU, int idProduct) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Choix de quantité");
        dialog.setHeaderText(productName);
        dialog.setContentText("Quantité:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            boolean test = true;
            String message = "";
            if (!testPrix(result.get())) {
                test = false;
                message = "Quantité invalide";
            }
            if (test) {

                int qte = Integer.parseInt(result.get());
                double total = qte * prixU;

                PanierVo panierVo = new PanierVo();
                panierVo.setNom_produit(productName);
                panierVo.setQuantite(qte);
                panierVo.setPrix_unitaire(prixU);
                panierVo.setPrix_totale_produit(total);
                panierVo.setId_produit(idProduct);

                somme += total;
                prixTotal.setText(somme + "TND");
                listPanier.add(panierVo);

                ObservableList<PanierVo> items = FXCollections.
                        observableArrayList(listPanier);
                tabView2.setItems(items);

                if (tabView2.getItems().isEmpty()) {
                    tabView2.setVisible(false);
                    panel.setVisible(false);
                } else {
                    tabView2.setVisible(true);
                    panel.setVisible(true);
                }
            } else {
                showMessage(message);
            }
        }
    }

    @FXML
    private void PasserUneCommande(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setHeaderText("vous etes sur de passer cette commande?!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Date date = Date.from(dateCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Commande commande = new Commande(Config.id_user, new Date(), date, somme);

            try {
                int idCmd = serviceCommande.ajouterCommande(commande);
                for (PanierVo panierVo : tabView2.getItems()) {
                    Panier panier = new Panier(panierVo.getId_produit(), idCmd, panierVo.getQuantite(), panierVo.getPrix_totale_produit());
                    servicePanier.ajouter(panier);
                    Stage stage = (Stage) Pmax.getScene().getWindow();
                    stage.close();

                    Stage window = new Stage();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListCommande.fxml")));
                    window.setScene(scene);
                    window.show();

                }
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void retourCMD(ActionEvent event) throws IOException {
        
        
          Stage stage = (Stage) Pmax.getScene().getWindow();
        stage.close();

        Stage window = new Stage();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MenuClient.fxml")));
        window.setScene(scene);
        window.show();
    }
}
