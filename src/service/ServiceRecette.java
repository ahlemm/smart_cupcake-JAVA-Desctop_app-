/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utile.DataSource;
import entite.Ingredient;
import entite.Recette;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Zouhour
 */
public class ServiceRecette {
   Connection con=DataSource.getInstance().getConnection();
        private Statement ste;

         public ServiceRecette() {
        try{   
         ste = con.createStatement();}
        catch (SQLException ex) {
            System.out.println(ex);
        }
}  
           
         public void ajouterRecette(Recette r, ArrayList<Ingredient> ingredients) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update

        String req ="INSERT INTO `recette` (`nom_recette`,`info_recette`, `prix_recette`,`id_user`,`image`)"+" VALUES (?,?,?,?,?)";
        String req2="INSERT INTO `recette_ingredient` (`id_recette`,`id_ingredient`,`quantite`)"+" VALUES (?,?,?)";
             int id_rec = 0 ;
        try{
        PreparedStatement pre=con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pre.setString(1, r.getNom_recette());
        pre.setString(2, r.getInfo_recette()); 
        pre.setDouble(3, r.getPrix_recette()); 
        pre.setInt(4, r.getId_user());
        pre.setBlob(5, r.getImage());
        pre.executeUpdate();
            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_rec = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating recette failed, no ID obtained.");
                }
            }
            for (Ingredient ing: ingredients) {
                PreparedStatement pre2=con.prepareStatement(req2);
                pre2.setInt(1, id_rec);
                pre2.setInt(2, ing.getId_ingredient());
                pre2.setDouble(3, ing.getQuantite());
                pre2.executeUpdate();
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("id = "+id_rec+" ajout avec succÃ©es ");
        
    }


    public Recette detailsRecette(int id) throws SQLException {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Recette recette = null;

        String sql = "SELECT * FROM recette r WHERE id_recette=? ";
        String sql2 = "SELECT i.*, ri.quantite FROM ingredient as i INNER JOIN recette_ingredient as ri ON i.id_ingredient = ri.id_ingredient WHERE ri.id_recette = ? ";
        PreparedStatement pre = con.prepareStatement(sql);
        PreparedStatement pre2 = con.prepareStatement(sql2);
        pre.setInt(1,id);
        pre2.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        ResultSet rs2 = pre2.executeQuery();

        while (rs2.next()) {
            Ingredient ingredient = new Ingredient(rs2.getInt(1) , rs2.getString(2), rs2.getDouble(3),rs2.getDouble(5));
            ingredients.add(ingredient);
            ingredient = null;
        }
        while (rs.next()) {
            recette = new Recette(rs.getInt(1) , rs.getString(2),rs.getString(3), rs.getDouble(4), rs.getInt(4),rs.getBlob(6) ,ingredients);
        }
        return recette;
    }

         public List<Recette> afficherRecette() throws SQLException {
         List<Recette> ls = new ArrayList();
        
        String sql = "SELECT * FROM recette r";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
          Recette r = null;
       
        while (rs.next()) {
            r = new Recette(rs.getString(2),rs.getString(3), rs.getDouble(4));
            r.setId_recette(rs.getInt(1));
            ls.add(r);
        }
        return ls;
    }
          public void modifierRecette(Recette r) throws SQLException {

        String req = "UPDATE `recette` SET nom_recette=?,info_recette=?,prix_recette=? WHERE id_recette=?";

       
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, r.getNom_recette());
 
        pre.setString(2, r.getInfo_recette());
         pre.setDouble(3, r.getPrix_recette());
        pre.setInt(4, r.getId_recette());


        pre.executeUpdate();

    }
          public void supprimerR(int id) throws SQLException {
 String req1 = "delete from  recette where id_recette= ?";
       

        PreparedStatement pre = con.prepareStatement(req1); 
//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id);
          

        pre.executeUpdate();// taamel delete 3al base de donnÃ© 
    
    }
 public List<Recette> TrieRecette() throws SQLException {
         List<Recette> ls = new ArrayList();
        
        String sql = "SELECT * FROM recette r ORDER BY prix_recette";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
          Recette r = null;
       
        while (rs.next()) {
            r = new Recette(rs.getString(2),rs.getString(3), rs.getDouble(4));
            r.setId_recette(rs.getInt(1));
            ls.add(r);
        }
        return ls;
    }

}