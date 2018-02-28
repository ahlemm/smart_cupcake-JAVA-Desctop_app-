/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Zouhour
 */
public class Ingredient {
    private int id_ingredient;
    private String nom_ingredient;
    private double prix_ingredient;
    private double quantite;

    public Ingredient( int id_ingredient,  String nom_ingredient,  double prix_ingredient ,double quantite) {
        this.quantite = quantite ;
        this.id_ingredient = id_ingredient ;
        this.nom_ingredient = nom_ingredient ;
        this.prix_ingredient = prix_ingredient ;

    }
    public Ingredient( int id_ingredient,  double quantite) {
        this.id_ingredient = id_ingredient ;
        this.quantite = quantite ;

    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getNom_ingredient() {
        return nom_ingredient;
    }

    public void setNom_ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public double getPrix_ingredient() {
        return prix_ingredient;
    }

    public void setPrix_ingredient(Double prix_ingredient) {
        this.prix_ingredient = prix_ingredient;
    }

   

    
    public Ingredient(int id_ingredient, String nom_ingredient, double prix_ingredient) {
        this.id_ingredient = id_ingredient;
        this.nom_ingredient = nom_ingredient;
        this.prix_ingredient = prix_ingredient;
       
    }

 
    

    public Ingredient() {
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id_ingredient=" + id_ingredient + ", nom_ingredient=" + nom_ingredient + ", prix_ingredient=" + prix_ingredient + '}';
    }

  

   
    

    public Ingredient(String nom_ingredient, Double prix_ingredient) {
        this.nom_ingredient = nom_ingredient;
        this.prix_ingredient = prix_ingredient;
       
    }

}
