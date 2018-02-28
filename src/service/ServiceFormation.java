/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utile.DataSource;
import entite.Formation;
import entite.Patisserie;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Arbia
 */
public class ServiceFormation { 
    Connection con; 
   
    public  ServiceFormation() {
      con=DataSource.getInstance().getConnection();
    } 
    public void ajouterFormation(Formation f) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
Date sqlDate_debut = new Date(f.getDate_debut().getTime()); 
Date sqlDate_fin = new Date(f.getDate_fin().getTime()); 
Date sqlDate_fin_inscri = new Date(f.getDate_fin_inscri().getTime());
        String req="INSERT INTO `formation` (`date_debut`, `date_fin`, `nbr_participant`, `info_formation`, `date_fin_inscri`, `nbr_h`, `id_user`)"+" VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(req);
        pre.setDate(1, sqlDate_debut);
        pre.setDate(2, sqlDate_fin);  
        pre.setInt(3, f.getNbr_participant());
        pre.setString(4, f.getInfo_formation());
        pre.setDate(5, sqlDate_fin_inscri);
        pre.setInt(6, f.getNbr_h()); 
        pre.setInt(7, f.getPatissier().getId_user());
        pre.executeUpdate();
         System.out.println("ajout avec succÃ©es");
    
    
    } 
     public List<Formation> afficher() throws SQLException {
        List<Formation> ls = new ArrayList();
        String sql = "SELECT * FROM formation f";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        Formation f = null;
        while (rs.next()) {
            f = new Formation(rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getDate(6),rs.getInt(7),rs.getInt(8));
            f.setId_formation(rs.getInt(1));
            ls.add(f);
        }
        return ls;
    } 
     
     
    



    public ObservableList<Formation> mes_formations(int id) throws SQLException {
        ObservableList<Formation> listFormations = FXCollections.observableArrayList();

        try {
            String requete = "select * from formation where id_user=" + id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) { 
                Formation f;
                 f = new Formation(rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getDate(6),rs.getInt(7),rs.getInt(8));
            f.setId_formation(rs.getInt(1));
            listFormations.add(f);
        }
                //  p.setEtat_patisserie(rsl.getInt(8));

//   int id_patissier = rsl.getInt(9);
//                ServiceUser daoUser = ServiceUser.getInstance();
//                p.setPatissier(daoUser.findUserById(id_patissier));
               
            

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listFormations;
    }


     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      public void modifier(Formation f) throws SQLException {

        String req = "UPDATE `formation` SET date_debut=?,date_fin=?,"
                + "nbr_participant=?,info_formation=?,date_fin_inscri=?,nbr_h=? WHERE id_formation=?";

        System.out.println("**" + f.getDate_debut());
        Date sqlDate_debut = new Date(f.getDate_debut().getTime());
        System.out.println("**" + f.getDate_fin());
        Date sqlDate_fin = new Date(f.getDate_fin().getTime()); 
        System.out.println("**" + f.getDate_fin_inscri());
        Date sqlDate_fin_inscri = new Date(f.getDate_fin_inscri().getTime());
        PreparedStatement pre = con.prepareStatement(req);

       pre.setDate(1, sqlDate_debut);
        pre.setDate(2, sqlDate_fin);  
        pre.setInt(3, f.getNbr_participant());
        pre.setString(4, f.getInfo_formation());
        pre.setDate(5, sqlDate_fin_inscri);
        pre.setInt(6, f.getNbr_h());  
        pre.setInt(7, f.getId_formation());
        pre.executeUpdate();

    }

    public void supprimer(int id_formation) throws SQLException {

        String req = "delete from  formation where id_formation= ?";

        PreparedStatement pre = con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id_formation);

        pre.executeUpdate();// taamel delete 3al base de donnÃ© 

    }
     

    
    
    }
    

