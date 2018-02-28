/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Formation;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.ServiceFormation;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory; 
import utile.Config;
//import com.restfb.DefaultFacebookClient;
//import com.restfb.FacebookClient;
//import com.restfb.Parameter;
//import com.restfb.types.FacebookType;
import java.util.Calendar;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.util.Callback;
import static utile.Utils.patissier;

/**
 * FXML Controller class
 *
 * @author Arbia
 */
public class FXMLCrudController implements Initializable { 
//    String accessToken = "EAACEdEose0cBAPem6VVUDXfCKM8anIlCdWFsX3oInU8Nx0AADvHuZBNVQsMJDNhhxcyVeQRSi5KdvZCzMRmZBoSPrgQ0AD9HUodKrQNJCXPIfFozHG02XdR08KfCpAis7VQ7BhA8PNsdOY2XAQGWPOCInErdzhONGGWawVzSECBXeAk8a1OpIGorX7NRwW6Jc7idGh6wwZDZD";
//       FacebookClient fbClient = new DefaultFacebookClient(accessToken);

    private ServiceFormation ServiceFormation = new ServiceFormation();
    private Formation formation = new Formation();

    @FXML
    private TextField nbr_participant;
    @FXML
    private TextField nbr_heures;
    @FXML
    private TextField info_formation;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private DatePicker date_fin_inscription;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Formation> tableview;
    @FXML
    private TableColumn<Formation, Date> date = new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Date> datee = new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Integer> Nbr_participant = new TableColumn<Formation, Integer>();
    @FXML
    private TableColumn<Formation, String> Info_formation = new TableColumn<Formation, String>();
    @FXML
    private TableColumn<Formation, Date> dateee = new TableColumn<Formation, Date>();
    @FXML
    private TableColumn<Formation, Integer> Nbr_heures = new TableColumn<Formation, Integer>();
    @FXML
    private TableColumn<Formation, Integer> Id_formation;
    @FXML
    private Button annuler;
    @FXML
    private Button modifier;
    @FXML
    private Button fb;
 
    /**
     * Initializes the controller class.
     */
    private List<Formation> findFormation() throws SQLException {
        int x = patissier.getId_user();
        return ServiceFormation.mes_formations(x);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                

        try { 
            
            ajouter.setVisible(true);
            modifier.setVisible(false);
            date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_debut"));
            datee.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
            Nbr_participant.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbr_participant"));
            Info_formation.setCellValueFactory(new PropertyValueFactory<Formation, String>("info_formation"));
            dateee.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin_inscri"));
            Nbr_heures.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("nbr_h"));
 
            Id_formation.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id_formation"));
            ObservableList<Formation> items = FXCollections.observableArrayList(findFormation());
            tableview.setItems(items); 
            
            tableview.setRowFactory(new Callback<TableView<Formation>, TableRow<Formation>>() {
                @Override //manich fehma belgde hedha
                public TableRow<Formation> call(TableView<Formation> TableView) {
                    final TableRow<Formation> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                    final MenuItem editMenuItem = new MenuItem("Modifier");
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                    supprimer(row.getItem().getId_formation());

                        } 

                    });
                    editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("id_formation"+row.getItem().getId_formation());
                            formation.setId_formation(row.getItem().getId_formation()); 
                    Calendar cal = Calendar.getInstance();
                            cal.setTime(row.getItem().getDate_debut());
                            date_debut.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
                           cal.setTime(row.getItem().getDate_fin());
                            date_fin.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
                           
                            nbr_participant.setText(String.valueOf(row.getItem().getNbr_participant())); 
                            info_formation.setText(row.getItem().getInfo_formation());
                            cal.setTime(row.getItem().getDate_fin_inscri());
                            date_fin_inscription.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
                                               
                            nbr_heures.setText(String.valueOf(row.getItem().getNbr_h()));                                                   
                            ajouter.setVisible(false);
                            modifier.setVisible(true);

                        }

                    });
                    contextMenu.getItems().add(removeMenuItem);
                    contextMenu.getItems().add(editMenuItem);
                      row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu)
                    );
                    return row; 
                } 
                 });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
        } }

    

    // TODO
   
    @FXML
    private void ajouter(ActionEvent event) {
        boolean test = false;
        String message = ""; 
         Date date_de = Date.from(date_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date_fi = Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date_fi_i = Date.from(date_fin_inscription.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (date_debut.getValue() == null) {

            test = true;
            message = "Champs Date_debut  obligatoire";
        } 
        else if (!testDate_debut(date_debut.getValue())) {
            test = true;
            message = " date debut invalid";
        }
        else if (!testDate_fin(date_fin.getValue())) {
            test = true;
            message = " date fin invalid";
        }

        else if (date_fin.getValue() == null) {

            test = true;
            message = "Champs Date_fin  obligatoire";
        } else if (nbr_participant.getText().length() == 0) {
            test = true;
            message = "Champs nbr obligatoire";
        } else if (!testNbr_participant(nbr_participant.getText())) {
            test = true;
            message = " nbr invalid";
        } else if (info_formation.getText().length() == 0) {
            test = true;
            message = "Champs info_formation obligatoire";
        } else if (nbr_heures.getText().length() == 0) {
            test = true;
            message = "Champs heures obligatoire";
        } else if (!testNbr_heures(nbr_heures.getText())) {
            test = true;
            message = " heures invalid";
        } else if (date_fin_inscription.getValue() == null) {

            test = true;
            message = "Champs Date_fin_incription Ã©xpiration obligatoire"; }
            else if(date_de.compareTo(date_fi)==1) {  
                test = true;
            message = "verifiez vos dates de debut et fin  obligatoire";       
            } 
        else if(date_de.compareTo(date_fi_i)==-1) {  
                test = true;
            message = "verifiez vos dates de debut et fin_inscription  obligatoire";
            } 
            else if(date_fi.compareTo(date_fi_i)==-1) {  
                test = true;
            message = "verifiez vos dates de fin et fin_inscription  obligatoire";
           
                
            }
        

        if (test) {
            showMessage(message);
        } else { 
            
            Date date = Date.from(date_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date1 = Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date2 = Date.from(date_fin_inscription.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Formation f = new Formation(date, date1, Integer.parseInt(nbr_participant.getText()), info_formation.getText(), date2, Integer.parseInt(nbr_heures.getText()),patissier);
            try {
                        //FacebookType response=fbClient.publish("me/feed",FacebookType.class,Parameter.with("message","Ma formation est :"+info_formation.getText()+" Bienvenu"));
                        //System.out.println("fbcom/"+response.getId());
                        
                ServiceFormation.ajouterFormation(f); 
                 ObservableList<Formation> items = FXCollections.observableArrayList(findFormation());
                tableview.setItems(items);
                clear();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        alert.show();
    }

    private boolean testDate_debut(LocalDate localDate) {
        try {
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); 
          
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean testDate_fin(LocalDate localDate) {
        try {
            Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); 
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean testDate_fin_inscription(LocalDate localDate) {
        try {
            Date date2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean testNbr_participant(String np) {
        try {
            Integer.parseInt(np);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean testNbr_heures(String nh) {
        try {
            Integer.parseInt(nh);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    

    
        private void clear() {
        date_debut.setValue(null);
        date_fin.setValue(null);
        nbr_participant.setText("");
        info_formation.setText(""); 
        date_fin_inscription.setValue(null);
        nbr_heures.setText("");
       
        
    }

    @FXML
    private void annuler(ActionEvent event) { 
        clear();
    }

    @FXML
    private void modifier(ActionEvent event) { 
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de modifier!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                System.out.println("**" + date_debut.getValue());

                Date date = Date.from(date_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
System.out.println("**" + date_fin.getValue());

                Date date1 = Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
System.out.println("**" + date_fin_inscription.getValue());

                Date date2 = Date.from(date_fin_inscription.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                formation = new Formation(formation.getId_formation(),date,date1,Integer.parseInt(nbr_participant.getText()), info_formation.getText(),
                        date2,Integer.parseInt(nbr_heures.getText()), Config.id_user);
                System.out.println("**" + date);
                System.out.println("**" + date1);
                System.out.println("**" + date2);
                 System.out.println("id_formation"+formation.getId_formation());
                ServiceFormation.modifier(formation);

                ObservableList<Formation> items = FXCollections.observableArrayList(findFormation());
                tableview.setItems(items);
                clear();

            }

        } catch (Exception ex) {
            Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    private void supprimer(int id_formation) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText("vous etes suur de supprimer!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServiceFormation.supprimer(id_formation);
                ObservableList<Formation> items = FXCollections.observableArrayList(findFormation());
                tableview.setItems(items);
            }

        } catch (Exception ex) {
            Logger.getLogger(FXMLCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fb(ActionEvent event) { 
    }
    }


