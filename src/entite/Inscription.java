/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;

/**
 *
 * @author Arbia
 */
public class Inscription {  
    private int id_inscription; 
    private String login;
    private int id_formation; 

    public Inscription() {
    } 
    

    public Inscription( String login, int id_formation) {
        
        this.login = login;
        this.id_formation = id_formation; 
       
    }

    public Inscription(String login) {
        this.login = login;
    } 

    public int getId_inscription() {
        return id_inscription;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

   

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }
    
}
    

    

    
    
    
    

