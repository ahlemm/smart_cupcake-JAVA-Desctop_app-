/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utile.DataSource;
import entite.Formation;
import entite.Inscription;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Arbia
 */
public class ServiceInscription {
 
    Connection con; 
   
    /**
     *
     */
    public ServiceInscription()   {
      con=DataSource.getInstance().getConnection();
    }  

    /**
     *
     * @param i
     * @throws SQLException
     */
    public void ajouterInscription(Inscription i) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
 
        String req="INSERT INTO `inscription` (`login`,`id_formation`)"+" VALUES (?,?)";
        PreparedStatement pre=con.prepareStatement(req);  
        pre.setString(1, i.getLogin());
        pre.setInt(2, i.getId_formation());
         
      
        pre.executeUpdate();
         System.out.println("ajout avec succÃ©es");
    
    
    } 
    
}
