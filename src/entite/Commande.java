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
public class Commande {
    private int id_commande;
    private int id_user;
    private Date date_commande;
    private Date date_livraison;
    private  double prix_totale;
    private String etat_commande;

    
    
    public Commande(int id_commande, Date date_commande, Date date_livraison, double prix_totale, String etatcommande) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.prix_totale = prix_totale;
        this.etat_commande = etatcommande;
    }

   
    

    public Commande(int id_user, Date date_commande, Date date_livraison, double prix_totale) {
        this.id_user = id_user;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.prix_totale = prix_totale;
    }

    public Commande() {
    }

    public Commande(int id_commande, int id_user, Date date_commande, Date date_livraison, double prix_totale) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.prix_totale = prix_totale;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public double getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(double prix_totale) {
        this.prix_totale = prix_totale;
    }

    public String getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }
    
    
    
}
