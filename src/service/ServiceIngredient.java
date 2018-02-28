/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utile.DataSource;
import entite.Ingredient;
import entite.Recette;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Zouhour
 */
public class ServiceIngredient {
    Connection connection;
     public ServiceIngredient() {
     connection =DataSource.getInstance().getConnection();
       
       
        }

  public void ajouterIngredient(Ingredient i) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update

        String req="INSERT INTO `ingredient` (`nom_ingredient`, `prix_ingredient`)"+" VALUES (?,?)";
        
        try{
        PreparedStatement pre=connection.prepareStatement(req);
        pre.setString(1, i.getNom_ingredient());
        pre.setDouble(2, i.getPrix_ingredient());  
  
        pre.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("ajout avec succÃ©es");
        
        
}
  public List<Ingredient> afficher() throws SQLException {
         List<Ingredient> ls = new ArrayList();
        
        String sql = "SELECT * FROM ingredient i";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
          Ingredient i = null;
       
        while (rs.next()) {
            i = new Ingredient(rs.getString(2), rs.getDouble(3));
            i.setId_ingredient(rs.getInt(1));
            ls.add(i);
        }
        return ls;
    }
    public void remove(Integer i) throws SQLException {
        String req = "delete from ingredient where id_ingredient=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,i);
            preparedStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();   
        }
        
    }
     public void supprimer(int id) throws SQLException {

        String req = "delete from  ingredient where id_ingredient= ?";

        PreparedStatement pre = connection.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id);

        pre.executeUpdate();// taamel delete 3al base de donnÃ© 

    }
     public void modifier(Ingredient i) throws SQLException {

        String req = "UPDATE `ingredient` SET nom_ingredient=?,prix_ingredient=? WHERE id_ingredient=?";

       
        PreparedStatement pre = connection.prepareStatement(req);

        pre.setString(1, i.getNom_ingredient());
 
        pre.setDouble(2, i.getPrix_ingredient());
        pre.setInt(3, i.getId_ingredient());


        pre.executeUpdate();

    }
}
