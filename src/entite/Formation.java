/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import java.util.*;


/**
 *
 * @author Arbia
 */
public class Formation { 
      private int id_formation ;
     private Date date_debut; 
     private Date date_fin; 
     private int nbr_participant;  
     private String info_formation; 
     private Date date_fin_inscri; 
     private int nbr_h; 
     private int id_user; 
         private User patissier;

   

   

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public String getInfo_formation() {
        return info_formation;
    }

    public void setInfo_formation(String info_formation) {
        this.info_formation = info_formation;
    }

    public Date getDate_fin_inscri() {
        return date_fin_inscri;
    }

    public void setDate_fin_inscri(Date date_fin_inscri) {
        this.date_fin_inscri = date_fin_inscri;
    }

    public int getNbr_h() {
        return nbr_h;
    }

    public void setNbr_h(int nbr_h) {
        this.nbr_h = nbr_h;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

   

    public Formation(Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h, int id_user) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
        this.id_user = id_user;
       
    }

    public Formation(Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
    }

    public Formation(int id_formation, Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h, int id_user) {
        this.id_formation = id_formation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
        this.id_user = id_user;
        
    }

    public Formation() {
    }

    @Override
    public String toString() { 
        return 
        this.getInfo_formation(); 
         
         
    }

    public Formation(int id_formation, Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h, int id_user, User patissier) {
        this.id_formation = id_formation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
        this.id_user = id_user;
        this.patissier = patissier;
    }

    public Formation(Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h, int id_user, User patissier) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
        this.id_user = id_user;
        this.patissier = patissier;
    }

    public User getPatissier() {
        return patissier;
    }

    public void setPatissier(User patissier) {
        this.patissier = patissier;
    }

    public Formation(Date date_debut, Date date_fin, int nbr_participant, String info_formation, Date date_fin_inscri, int nbr_h, User patissier) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_participant = nbr_participant;
        this.info_formation = info_formation;
        this.date_fin_inscri = date_fin_inscri;
        this.nbr_h = nbr_h;
        this.patissier = patissier;
    }



     
    
    
}
