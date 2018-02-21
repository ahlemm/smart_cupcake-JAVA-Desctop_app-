/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Patisserie;
import entite.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;

/**
 *
 * @author 3D-Artist
 */
public class ServiceUser {
    
    private final Connection con;
    private static ServiceUser instance;
    
    public ServiceUser() {
        con = DataSource.getInstance().getConnection();
    }
    
    public static ServiceUser getInstance()
    {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance; 
    }
    
    
    
    public User findUserById(int id_user)
    {
        User owner = new User();
        int count = 0;
           
        String requete="select * from user where id_user="+id_user;
        try{
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
                owner.setId_user(rsl.getInt(1));
                owner.setNom(rsl.getString(2));
                owner.setPrenom(rsl.getString(3));
                owner.setType(rsl.getString(4));
//                owner.setDate_naissance(rsl.getDate(5));
                owner.setEmail(rsl.getString(6));
                owner.setAdresse(rsl.getString(7));
                owner.setNum_tel(rsl.getInt(8));
                owner.setLogin(rsl.getString(9));
                owner.setPassword(rsl.getString(10));
             
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return owner;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
//    
//    
    public boolean checkCinExist(int id_user)
    {
        
        int count = 0;
           
        String requete="select * from user where id_user="+id_user;
        try{
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
                count++;
            }
           if(count == 0){
                return false;
           }else{
               return true;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
    
    
    public User login(String email, String password){
        User owner = new User();
        try {
            String requete = "select * from user where email='"+email+"' and password='"+password+"'";
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            int count = 0;
            while(rsl.next()){
                count ++;
                owner.setId_user(rsl.getInt(1));
                owner.setNom(rsl.getString(2));
                owner.setPrenom(rsl.getString(3));
                owner.setType(rsl.getString(4));
                owner.setDate_naissance(rsl.getDate(5));
                owner.setEmail(rsl.getString(6));
                owner.setAdresse(rsl.getString(7));
                owner.setNum_tel(rsl.getInt(8));
                owner.setLogin(rsl.getString(9));
                owner.setPassword(rsl.getString(10));
            }
            System.out.println(count);
            if(count == 0){
                return null;
            }else{
                return owner;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
//    
    public boolean ajouteUser(User client)throws SQLException
    {
        int numberRows;
        try {
            String requete = "insert into user set id_user=?,nom=?,prenom=?, type=?,  email=?, adresse=?, num_tel=?, login=?, password=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, client.getId_user());
            pst.setString(2, client.getNom());
            pst.setString(3, client.getPrenom());
            pst.setString(4, client.getType());
        //    pst.setDate(5, client.getDate_naissance());
            pst.setString(5, client.getEmail());
            pst.setString(6, client.getAdresse());
            pst.setInt(7, client.getNum_tel());
            pst.setString(8, client.getLogin());
            pst.setString(9, client.getPassword());
           
            numberRows = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(numberRows == 0){
            return false;
        }else{
                    System.out.println("ajout user avec succées");

            return true;
        }
    }
    
//    
    public boolean supprimerUser(int id_user)
    {
        int test=0;
        boolean check;
        try{
            List<Patisserie> patisseries = new ArrayList<>();
           // patisseries = ServicePatisserie.getInstance().getPatisserieById(id_user);
            if(patisseries != null){
                for(int i=0; i<patisseries.size();i++){
                    ServicePatisserie.getInstance().supprimer(patisseries.get(i).getId_patisserie());
                }
            }
            
            PreparedStatement pst = con.prepareStatement("delete from user where id_user="+id_user); 
            test= pst.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (test == 0)
        {
            check=false;
        }else{
            check=true;
        }
        return check ;
    }
    
    
    public boolean approuverUser(int id)
    {
        int test=0;
        boolean check;
        try{
            PreparedStatement pst = con.prepareStatement("update user set approuved=1 where id="+id); 
            test= pst.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (test == 0)
        {
            check=false;
        }else{
            check=true;
        }
        return check ;
    }
    
//    
    public List<User> getAllUsers()
    {
        List<User> list = new ArrayList<User>();
        int count = 0;
           
        String requete="select * from user";
        try{
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
                if(! rsl.getString(11).equals("ROLE_ADMIN")){
                    User owner = new User();
                  owner.setId_user(rsl.getInt(1));
                owner.setNom(rsl.getString(2));
                owner.setPrenom(rsl.getString(3));
                owner.setType(rsl.getString(4));
                owner.setDate_naissance(rsl.getDate(5));
                owner.setEmail(rsl.getString(6));
                owner.setAdresse(rsl.getString(7));
                owner.setNum_tel(rsl.getInt(8));
                owner.setLogin(rsl.getString(9));
                owner.setPassword(rsl.getString(10));
                   
                count++;
                    list.add(owner);
                }
            }
           if(count == 0){
                return null;
           }else{
               return list;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }




}
    

