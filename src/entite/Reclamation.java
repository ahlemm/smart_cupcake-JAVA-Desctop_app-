/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entite;
import java.sql.Date;
/**
 *
 * @author user
 */
public class Reclamation {
    private int idReclamation;
    private String info;
    private int tel;
    private String email;
    private java.sql.Date dateReclamation;

    public Reclamation(int idReclamation, String info, int tel,String email,java.sql.Date dateReclamation) {
        this.idReclamation = idReclamation;
        this.info = info;
        this.tel = tel;
        this.email=email;
        this.dateReclamation = dateReclamation;
    }
     public Reclamation( int idReclamation,String info, int tel,String email) {
        
        this.info = info;
        this.tel = tel;
        this.email=email;
       
    }
    
     public Reclamation( String info, int tel, java.sql.Date dateReclamation,String email) {
        
        this.info = info;
        this.tel = tel;
        this.email=email;
        this.dateReclamation = dateReclamation;
    }    
     public Reclamation() {
        
        
    } 

   /* public Reclamation(String infoM, int telM, java.sql.Date date, String emailM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

  /*  public Reclamation(String infoM, int telM, java.sql.Date date, String emailM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(java.sql.Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", info=" + info + ", tel=" + tel + ", email=" + email + ", dateReclamation=" + dateReclamation + '}';
    }

    public Object getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  

    
     
    
}
