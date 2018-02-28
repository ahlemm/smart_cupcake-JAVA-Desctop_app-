/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.control.TableColumn;

/**
 *
 * @author Arbia
 */
public class Promotion {  
    private int id_promotion ;
    private int pourcentage; 
    private double prix_produit;  
    private double nv_prix ; 
    private String nom_produit ;  
    private int id_produit ; 
    private int id_user ;  

    

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public double getNv_prix() {
        return nv_prix;
    }

    public void setNv_prix(double nv_prix) {
        this.nv_prix = nv_prix;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Promotion(int pourcentage, double prix_produit, double nv_prix, String nom_produit, int id_produit, int id_user) {
        this.pourcentage = pourcentage;
        this.prix_produit = prix_produit;
        this.nv_prix = nv_prix;
        this.nom_produit = nom_produit;
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public Promotion() {
    }

    public Promotion(int id_promotion, int pourcentage, double prix_produit, double nv_prix, String nom_produit, int id_produit, int id_user) {
        this.id_promotion = id_promotion;
        this.pourcentage = pourcentage;
        this.prix_produit = prix_produit;
        this.nv_prix = nv_prix;
        this.nom_produit = nom_produit;
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public Promotion(int pourcentage, double prix_produit) {
        this.pourcentage = pourcentage;
        this.prix_produit = prix_produit;
    }

   

    
    
    
}
