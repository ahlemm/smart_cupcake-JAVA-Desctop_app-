/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utile.DataSource;

/**
 *
 * @author esprit
 */
public class ServiceCommande {

    private Connection con = DataSource.getInstance().getConnection();

    public int ajouterCommande(Commande c) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        Date sqlDate = new Date(c.getDate_commande().getTime());
        Date sqlDate1 = new Date(c.getDate_livraison().getTime());
        String req = "INSERT INTO `commande` (date_commande, date_livraison, prix_totale,etat,id_user)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pre.setDate(1, sqlDate);
        pre.setDate(2, sqlDate1);
        pre.setDouble(3, c.getPrix_totale());

        pre.setString(4, "En attente");
        pre.setInt(5, c.getId_user());
        pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();
        rs.next();
        int auto_id = rs.getInt(1);
        return auto_id;
    }
    
     public void modifierCommande(Commande c) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        Date sqlDate = new Date(c.getDate_commande().getTime());
        Date sqlDate1 = new Date(c.getDate_livraison().getTime());
        String req = "update `commande` set date_commande=?, date_livraison=?, prix_totale=?,etat=? where id_commande=?";
        PreparedStatement pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pre.setDate(1, sqlDate);
        pre.setDate(2, sqlDate1);
        pre.setDouble(3, c.getPrix_totale());

        pre.setString(4, c.getEtat_commande());
        pre.setInt(5, c.getId_commande());
        pre.executeUpdate();
       
    }
    
     public List<Commande> afficherByIdUser(int Id_user) throws SQLException {
        List<Commande> ls = new ArrayList<Commande>();
        String sql = "SELECT * FROM commande where id_user=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1,Id_user );
        ResultSet rs = pre.executeQuery();
        Commande c = new Commande();
        while (rs.next()) {
            c = new Commande(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5));
            ls.add(c);
        }
        return ls;
    }

}
