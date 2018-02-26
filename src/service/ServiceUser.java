/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Produit;
import entite.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utile.Config.id_user;
import utile.DataSource;

/**
 *
 * @author esprit
 */
public class ServiceUser {
    
    private Connection con = DataSource.getInstance().getConnection();
        private static ServiceUser instance;

    public ServiceUser() {
        
    }
    
    

    public static ServiceUser getInstance()
    {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance; 
    }
    
    
    
    
    public List<User> findAllPatisserie() throws SQLException {
        List<User> ls = new ArrayList<User>();
        String sql = "SELECT * FROM user where type='patiserie'";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        User us = new User();
        while (rs.next()) {
            us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            
            ls.add(us);
        }
        return ls;
    }
    
    public List<User> afficherUser() throws SQLException {
        List<User> ls = new ArrayList();
        String sql = "SELECT * FROM  user u";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        User u = null;
        while (rs.next()) {
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            ls.add(u);
        }
        return ls;
    }
    
    public void ajouterUser(User u) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        Date sqlDate = new Date(u.getDate_naissance().getTime());
        String req = "INSERT INTO `user` (nom,prenom,type,date_naissance,email,adresse,num_tel,login,password)" + " VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, u.getNom());
        pre.setString(2, u.getPrenom());
        pre.setString(3, u.getType());
        pre.setDate(4, sqlDate);
        pre.setString(5, u.getEmail());
        pre.setString(6, u.getAdresse());
        pre.setInt(7, u.getNum_tel());
        pre.setString(8, u.getLogin());
        pre.setString(9, u.getPassword());
        
        pre.executeUpdate();
        
    }

    public List<User> afficherUserLogin(String login) throws SQLException {
        List<User> ls = new ArrayList<User>();
        String sql = "SELECT * FROM user u where login=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, login);
        ResultSet rs = pre.executeQuery();
        User u = new User();
        while (rs.next()) {
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            ls.add(u);
        }
        return ls;
    }

    public List<User> afficherUserEmail(String email) throws SQLException {
        List<User> ls = new ArrayList<User>();
        String sql = "SELECT * FROM user u where email=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        User u = new User();
        while (rs.next()) {
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            ls.add(u);
        }
        return ls;
    }

    public List<User> afficherUserExist(String login, String password) throws SQLException {
        List<User> ls = new ArrayList<User>();
        String sql = "SELECT * FROM user u where login=? and password=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, login);
        pre.setString(2, password);
        
        ResultSet rs = pre.executeQuery();
        User u = new User();
        while (rs.next()) {
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            ls.add(u);
        }
        return ls;
    }
    
    public List<User> afficherUserById(int id) throws SQLException {
        List<User> ls = new ArrayList<User>();
        String sql = "SELECT * FROM user u where id_user=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        User u = new User();
        while (rs.next()) {
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            ls.add(u);
        }
        return ls;
    }
    
    public void supprimer(int id) throws SQLException {
        String sql = "delete from user where id_user=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        pre.executeUpdate();
    }

    public void modifierCompte(User u) throws SQLException {

        Date sqlDate = new Date(u.getDate_naissance().getTime());
        String req = "update user set nom=?,prenom=?,"
                + " date_naissance=?,email=?,login=?,num_tel=?"
               + ",adresse=? where id_user=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, u.getNom());
        pre.setString(2, u.getPrenom());
        
        pre.setDate(3, sqlDate);
        pre.setString(4, u.getEmail());
        pre.setString(7, u.getAdresse());
        pre.setInt(6, u.getNum_tel());
      
        pre.setString(5, u.getLogin());
        pre.setInt(8, u.getId_user());
        
        pre.executeUpdate();
        
    }
   public void modifierMDP(User u) throws SQLException {

      
        String req = "update user set password=? where id_user=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, u.getPassword());
        
        pre.setInt(2, u.getId_user());
        
        pre.executeUpdate();
        
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

}
