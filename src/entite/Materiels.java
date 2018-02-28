/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;

/**
 *
 * @author user
 */
public class Materiels {
    private int idMateriels;
    private String  type;
    private String info;
    private float prixMateriel;
    private String etat;

    public int getIdMateriels() {
        return idMateriels;
    }

    public void setIdMateriels(int idMateriels) {
        this.idMateriels = idMateriels;
    }

    
   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrixMateriel() {
        return prixMateriel;
    }

    public void setPrixMateriel(float prixMateriel) {
        this.prixMateriel = prixMateriel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Materiels(int idMateriels, String type, String info, float prixMateriel, String etat) {
        this.idMateriels = idMateriels;
        this.type = type;
        this.info = info;
        this.prixMateriel = prixMateriel;
        this.etat = etat;
    }
    
    public Materiels(String type, String info, float prixMateriel, String etat) {
       
        this.type = type;
        this.info = info;
        this.prixMateriel = prixMateriel;
        this.etat = etat;
    }
    public Materiels() {
       
       
    }

    @Override
    public String toString() {
        return "Materiels{" + "idMateriels=" + idMateriels + ", type=" + type + ", info=" + info + ", prixMateriel=" + prixMateriel + ", etat=" + etat + '}';
    }
    
}
