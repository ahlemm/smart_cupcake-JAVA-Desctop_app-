/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;

/**
 *
 * @author esprit
 */
public class Produit {

    private int id;
    private String type;
    private String nom;
    private double prix;
    private Date delai_expiration;
    private int id_user;
 
    public Produit() {
    }

    public Produit(String type, String nom, double prix, Date delai_expiration, int id_user) {
        this.type = type;
        this.nom = nom;
        this.prix = prix;
        this.delai_expiration = delai_expiration;
        this.id_user = id_user;
    }

    public Produit(int id, String type, String nom, double prix, Date delai_expiration, int id_user) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.prix = prix;
        this.delai_expiration = delai_expiration;
        this.id_user = id_user;
    }

    public Date getDelai_expiration() {
        return delai_expiration;
    }

    public void setDelai_expiration(Date delai_expiration) {
        this.delai_expiration = delai_expiration;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

   

    @Override
    public String toString() {
        return  nom ;
    }

}
