/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author esprit
 */
public class PanierVo {

    private int id_produit;
    private String nom_produit;
    private double prix_unitaire;
    private int quantite;
    private double prix_totale_produit;

    public PanierVo() {
    }

    public PanierVo(int id_produit, String nom_produit, double prix_unitaire, int quantite, double prix_totale_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.prix_totale_produit = prix_totale_produit;
    }

    public PanierVo(String nom_produit, double prix_unitaire, int quantite, double prix_totale_produit) {
        this.nom_produit = nom_produit;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.prix_totale_produit = prix_totale_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_totale_produit() {
        return prix_totale_produit;
    }

    public void setPrix_totale_produit(double prix_totale_produit) {
        this.prix_totale_produit = prix_totale_produit;
    }

}
