


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;

import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.sun.prism.PhongMaterial.MapType;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import  com.lynden.gmapsfx.GoogleMapView;
import jdk.nashorn.api.scripting.JSObject;
import entite.Patisserie;
import entite.User;
import java.io.File;
import service.ServicePatisserie;
import service.ServiceUser;
import gui.AjoutPatController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;


public class GooglemapsController implements Initializable, MapComponentInitializedListener {
    
    
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }    

    @Override
    public void mapInitialized() {
    
               List<Patisserie> l = new ArrayList<>();
              ServicePatisserie sp = new ServicePatisserie();
              l=sp.getAllPatisserieApprouved();
           
 
//         
//  for (Patisserie p : l) {
//double a= p.getLatitude();
//double b=p.getLongitude();
//  String c=p.getNom_patisserie();
//  System.out.println(a);
//   System.out.println(b);
//   System.out.println(c);
//  }


                    
      
        
        
        
           LatLong masmoudi = new LatLong(35.764252, 10.811289);
        LatLong monaliza = new LatLong(47.6397, 10.0919243);
        LatLong victoria = new LatLong(36.8665367, 10.1647233);
        LatLong  chezmounir = new LatLong(35.824503, 10.634584);
        LatLong lella = new LatLong(33.7071551, 8.9714623);
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(33.7071551, 8.9714623))
                //.mapType(MapType.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(8);
                   
        map = mapView.createMap(mapOptions);
                   
          

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(lella);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(chezmounir);
        
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(victoria);
        
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(monaliza);
        
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(masmoudi);
        
        Marker lellaa = new Marker(markerOptions1);
        Marker chezmounirr = new Marker(markerOptions2);
        Marker victoriaa = new Marker(markerOptions3);
        Marker monalizaa= new Marker(markerOptions4);
        Marker Masmoudii = new Marker(markerOptions5);
        
        map.addMarker( lellaa );
        map.addMarker( chezmounirr );
        map.addMarker( victoriaa );
        map.addMarker( monalizaa );
        map.addMarker( Masmoudii );
        
        InfoWindowOptions infoWindowOptions5 = new InfoWindowOptions();
        infoWindowOptions5.content("<h2> e Masmoudi</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow5 = new InfoWindow(infoWindowOptions5);
        fredWilkeInfoWindow5.open(map, Masmoudii);
        
         InfoWindowOptions infoWindowOptions1 = new InfoWindowOptions();
        infoWindowOptions1.content("<h2>lella</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow1 = new InfoWindow(infoWindowOptions1);
        fredWilkeInfoWindow1.open(map, lellaa);
        
        
        
         InfoWindowOptions infoWindowOptions3 = new InfoWindowOptions();
        infoWindowOptions3.content("<h2> victoria</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow3 = new InfoWindow(infoWindowOptions3);
        fredWilkeInfoWindow3.open(map, victoriaa);
        
        InfoWindowOptions infoWindowOptions4 = new InfoWindowOptions();
        infoWindowOptions4.content("<h2> monaliza</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow4 = new InfoWindow(infoWindowOptions4);
        fredWilkeInfoWindow4.open(map,monalizaa );
        
        InfoWindowOptions infoWindowOptions2 = new InfoWindowOptions();
        infoWindowOptions2.content("<h2>chez_mounir</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow2 = new InfoWindow(infoWindowOptions2);
        fredWilkeInfoWindow2.open(map, chezmounirr);
        
   

 }

   
}