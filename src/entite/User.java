/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mdin Ahlem
 */
public class User {
    
    private int id_user;
    private String nom;
        private String prenom;
                private String type;
                private Date date_naissance;
                private String email;
                private String adresse;
                private int num_tel;
                
                private String login;
                private String password;

    public User() {
    }

    public User(int id_user, String nom, String prenom, String type, Date date_naissance, String email, String adresse, int num_tel, String login, String password) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.date_naissance = date_naissance;
        this.email = email;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.login = login;
        this.password = password;
    }

    public User(String nom, String prenom, String type, Date date_naissance, String email, String adresse, int num_tel, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.date_naissance = date_naissance;
        this.email = email;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.login = login;
        this.password = password;
    }

    public User(int id_user, String text, String text0, java.util.Date date, String text1, String text2, int parseInt, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int id_user, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", date_naissance=" + date_naissance + ", email=" + email + ", adresse=" + adresse + ", num_tel=" + num_tel + ", login=" + login + ", password=" + password + '}';
    }

    public User(String nom, String prenom, String type, String email, String adresse, int num_tel, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.email = email;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.login = login;
        this.password = password;
    }

    public User(int id_user, String nom, String prenom, String type, String email, String adresse, int num_tel, String login, String password) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.email = email;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.login = login;
        this.password = password;
    }


    
    
    
    
    
    
    
}
