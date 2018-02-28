/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.ArrayList;
import java.sql.Blob;

/**
 *
 * @author Zouhour
 */
public class Recette {
    private int id_recette;
    private String nom_recette;
    private String info_recette;
    private double prix_recette;
    private Blob image;
    private int id_user;
    private ArrayList<Ingredient> ingredients ;

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId_recette() {
        return id_recette;
    }

    public void setId_recette(int id_recette) {
        this.id_recette = id_recette;
    }

    public String getNom_recette() {
        return nom_recette;
    }

    public void setNom_recette(String nom_recette) {
        this.nom_recette = nom_recette;
    }

    public String getInfo_recette() {
        return info_recette;
    }

    public void setInfo_recette(String info_recette) {
        this.info_recette = info_recette;
    }

    public double getPrix_recette() {
        return prix_recette;
    }

    public void setPrix_recette(double prix_recette) {
        this.prix_recette = prix_recette;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Recette{" + "id_recette=" + id_recette + ", nom_recette=" + nom_recette + ", info_recette=" + info_recette + ", prix_recette=" + prix_recette + ", id_user=" + id_user + '}';
    }

    public Recette(int id_recette, String nom_recette, String info_recette, double prix_recette, int id_user) {
        this.id_recette = id_recette;
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
        this.id_user = id_user;
    }

   
    public Recette() {
    }

    public Recette(String nom_recette, String info_recette, double prix_recette, int id_user) {
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
        this.id_user = id_user;
    }

    public Recette(String nom_recette, String info_recette, double prix_recette) {
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
    }

    public Recette(int id_recette, String nom_recette, String info_recette, double prix_recette) {
        this.id_recette = id_recette;
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
    }

    public Recette(String nom_recette, String info_recette, double prix_recette, Blob image, int id_user) {
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
        this.image = image;
        this.id_user = id_user;
    }
    public Recette(int id_recette , String nom_recette, String info_recette, double prix_recette, int id_user, Blob image ,ArrayList<Ingredient> ingredients ) {
        this.id_recette = id_recette ;
        this.nom_recette = nom_recette;
        this.info_recette = info_recette;
        this.prix_recette = prix_recette;
        this.image = image;
        this.id_user = id_user;
        this.ingredients = ingredients ;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
    
}
