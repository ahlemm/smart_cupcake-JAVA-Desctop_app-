/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utile.DataSource;
import entite.Formation;
import entite.Promotion;
import entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arbia
 */
public class ServicePromotion {
      Connection con; 
       
       
      
   
    public  ServicePromotion() {
      con=DataSource.getInstance().getConnection();
    }
     public void ajouterPromotion(Promotion p) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        String req="INSERT INTO `promotion` (`pourcentage`, `prix_produit`, `nv_prix`, `nom_produit`, `id_produit`, `id_user`)"+" VALUES (?,?,?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(req);
        pre.setInt(1,p.getPourcentage());
        pre.setDouble(2, p.getPrix_produit());  
        pre.setDouble(3, p.getNv_prix());
        pre.setString(4, p.getNom_produit());
        pre.setInt(5, p.getId_produit()); 
        pre.setInt(6, p.getId_user());
        pre.executeUpdate();
         System.out.println("ajout avec succÃ©es");
    
    
    }   
     public List<Promotion> afficher() throws SQLException {
        List<Promotion> ls = new ArrayList();
        String sql = "SELECT * FROM promotion p";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        Promotion p = null;
        while (rs.next()) {
            p = new Promotion(rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5), rs.getInt(6),rs.getInt(7));
            p.setId_promotion(rs.getInt(1));
            ls.add(p);
        }
        return ls;
    }
    public void supprimer(int id_promotion) throws SQLException {

        String req = "delete from  promotion where id_promotion= ?";

        PreparedStatement pre = con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id_promotion);

        pre.executeUpdate();// taamel delete 3al base de donnÃ© 

    }
    
    
    
         
      
     
    }

