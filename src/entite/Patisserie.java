/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author Mdin Ahlem
 */
public class Patisserie {

    private int id_patisserie;
    private String nom_patisserie;

    private String acitivite;
       private String info_patisserie;

   private Double longitude;
      private Double latitude;


    private String adresse_patisserie;
    private int etat_patisserie;
    private User patissier;
    

    public Patisserie() {
    }

    public Patisserie(int id_patisserie, String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie, int etat_patisserie) {
        this.id_patisserie = id_patisserie;
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
        this.etat_patisserie = etat_patisserie;
    }

    public Patisserie(String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie, int etat_patisserie) {
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
        this.etat_patisserie = etat_patisserie;
    }

    public Patisserie(String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie) {
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
    }

//    public Patisserie(String text, String text0, String text1, double parseDouble, double parseDouble0, String text2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public int getId_patisserie() {
        return id_patisserie;
    }

    public void setId_patisserie(int id_patisserie) {
        this.id_patisserie = id_patisserie;
    }

    public String getNom_patisserie() {
        return nom_patisserie;
    }

    public void setNom_patisserie(String nom_patisserie) {
        this.nom_patisserie = nom_patisserie;
    }

    public String getAcitivite() {
        return acitivite;
    }

    public void setAcitivite(String acitivite) {
        this.acitivite = acitivite;
    }

    public String getInfo_patisserie() {
        return info_patisserie;
    }

    public void setInfo_patisserie(String info_patisserie) {
        this.info_patisserie = info_patisserie;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAdresse_patisserie() {
        return adresse_patisserie;
    }

    public void setAdresse_patisserie(String adresse_patisserie) {
        this.adresse_patisserie = adresse_patisserie;
    }

    public int getEtat_patisserie() {
        return etat_patisserie;
    }

    public void setEtat_patisserie(int etat_patisserie) {
        this.etat_patisserie = etat_patisserie;
    }

    public User getPatissier() {
        return patissier;
    }

    public void setPatissier(User patissier) {
        this.patissier = patissier;
    }

    @Override
    public String toString() {
        return "Patisserie{" + "id_patisserie=" + id_patisserie + ", nom_patisserie=" + nom_patisserie + ", acitivite=" + acitivite + ", info_patisserie=" + info_patisserie + ", longitude=" + longitude + ", latitude=" + latitude + ", adresse_patisserie=" + adresse_patisserie + ", etat_patisserie=" + etat_patisserie + ", patissier=" + patissier + '}';
    }

    public Patisserie(String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie, int etat_patisserie, User patissier) {
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
        this.etat_patisserie = etat_patisserie;
        this.patissier = patissier;
    }

    public Patisserie(int id_patisserie, String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie, int etat_patisserie, User patissier) {
        this.id_patisserie = id_patisserie;
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
        this.etat_patisserie = etat_patisserie;
        this.patissier = patissier;
    }

    public Patisserie(String nom_patisserie, String acitivite, String info_patisserie, Double longitude, Double latitude, String adresse_patisserie, User patissier) {
        this.nom_patisserie = nom_patisserie;
        this.acitivite = acitivite;
        this.info_patisserie = info_patisserie;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse_patisserie = adresse_patisserie;
        this.patissier = patissier;
    }

   
    
    
    
}