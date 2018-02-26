/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import entite.Panier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utile.DataSource;

/**
 *
 * @author esprit
 */
public class ServicePanier {
     private Connection con = DataSource.getInstance().getConnection();

    public void ajouter(Panier p) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        
        String req = "INSERT INTO `panier` (id_commande,id_produit,quantite,prix_totale_produit )" + " VALUES (?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,p.getId_commande() );
        pre.setInt(2, p.getId_produit());
        pre.setInt(3, p.getQuantite());
        pre.setDouble(4,p.getPrix_totale_produit());
       
        pre.executeUpdate();
      
    }
}
