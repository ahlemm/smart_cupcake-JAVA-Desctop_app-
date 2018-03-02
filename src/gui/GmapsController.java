
package gui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class GmapsController implements Initializable, MapComponentInitializedListener {
    
    @FXML
    private GoogleMapView mapView;
    
    @FXML
    private TextField addressTextField;
    
    private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    
   
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
    }    
    
    
     @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
       
            MapOptions mapOptions = new MapOptions();
      

        map = mapView.createMap(mapOptions);
        
           LatLong masmoudi = new LatLong(35.764252, 10.811289);
        LatLong monaliza = new LatLong(47.6397, 10.0919243);
        LatLong victoria = new LatLong(36.8665367, 10.1647233);
        LatLong  chezmounir = new LatLong(35.824503, 10.634584);
        LatLong lella = new LatLong(33.7071551, 8.9714623);
        
        
        //Set the initial properties of the map.
    
        
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
        
        com.lynden.gmapsfx.javascript.object.InfoWindowOptions infoWindowOptions5 = new com.lynden.gmapsfx.javascript.object.InfoWindowOptions();
        infoWindowOptions5.content("<h2> e Masmoudi</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow5 = new InfoWindow(infoWindowOptions5);
        fredWilkeInfoWindow5.open(map, Masmoudii);
        
         com.lynden.gmapsfx.javascript.object.InfoWindowOptions infoWindowOptions1 = new com.lynden.gmapsfx.javascript.object.InfoWindowOptions();
        infoWindowOptions1.content("<h2>lella</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 30 minutes" );

        InfoWindow fredWilkeInfoWindow1 = new InfoWindow(infoWindowOptions1);
        fredWilkeInfoWindow1.open(map, lellaa);
        
        
        
         com.lynden.gmapsfx.javascript.object.InfoWindowOptions infoWindowOptions3 = new com.lynden.gmapsfx.javascript.object.InfoWindowOptions();
        infoWindowOptions3.content("<h2> victoria</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow3 = new InfoWindow(infoWindowOptions3);
        fredWilkeInfoWindow3.open(map, victoriaa);
        
        com.lynden.gmapsfx.javascript.object.InfoWindowOptions infoWindowOptions4 = new com.lynden.gmapsfx.javascript.object.InfoWindowOptions();
        infoWindowOptions4.content("<h2> monaliza</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 15 minutes" );

        InfoWindow fredWilkeInfoWindow4 = new InfoWindow(infoWindowOptions4);
        fredWilkeInfoWindow4.open(map,monalizaa );
        
        com.lynden.gmapsfx.javascript.object.InfoWindowOptions infoWindowOptions2 = new com.lynden.gmapsfx.javascript.object.InfoWindowOptions();
        infoWindowOptions2.content("<h2>chez_mounir</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 20 minutes" );

        InfoWindow fredWilkeInfoWindow2 = new InfoWindow(infoWindowOptions2);
        fredWilkeInfoWindow2.open(map, chezmounirr);
        
    }
    
    
    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);

        });
    }
}