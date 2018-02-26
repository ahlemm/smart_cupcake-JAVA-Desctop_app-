/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author esprit
 */
public class Panier {
   private int id_produit;
   private int id_commande;
   private int quantite;
   private double prix_totale_produit;

    public Panier() {
    }

    public Panier(int id_produit, int quantite, double prix_totale_produit) {
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix_totale_produit = prix_totale_produit;
    }

    public Panier(int id_produit, int id_commande, int quantite, double prix_totale_produit) {
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.quantite = quantite;
        this.prix_totale_produit = prix_totale_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
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
