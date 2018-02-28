/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Recette;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Zouhour
 */
public class YoutubeFXMLController implements Initializable {

    @FXML
    private WebView webView;

    /**
     * Initializes the controller class.
     */
    public static String nom_recette = "" ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
      String youtube="https://www.youtube.com/results?search_query=recette+";
      String lien=youtube+nom_recette.replace(" ","+");
        engine.load(lien);
        System.out.println(lien);
        // TODO
    }    
    
}
