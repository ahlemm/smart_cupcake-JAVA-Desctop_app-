/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Produit;
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
public class ServiceProduit {

    private Connection con = DataSource.getInstance().getConnection();

    public ServiceProduit() {

    }

    public void ajouterProduit(Produit p) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
        Date sqlDate = new Date(p.getDelai_expiration().getTime());
        String req = "INSERT INTO `produit` (type_produit, nom_produit, prix_produit,delai_expiration,id_user)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(2, p.getNom());
        pre.setString(1, p.getType());
        pre.setDouble(3, p.getPrix());
        pre.setDate(4, sqlDate);
        pre.setInt(5, p.getId_user());
        pre.executeUpdate();

    }

    public List<Produit> afficher() throws SQLException {
        List<Produit> ls = new ArrayList();
        String sql = "SELECT * FROM produit p";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        Produit p = null;
        while (rs.next()) {
            p = new Produit(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getInt(6));
            p.setId(rs.getInt(1));
            ls.add(p);
        }
        return ls;
    }

    public List<Produit> afficherProduitParPatisserie(int id) throws SQLException {
        List<Produit> ls = new ArrayList<Produit>();
        String sql = "SELECT * FROM produit p where id_user=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        Produit p = new Produit();
        while (rs.next()) {
            p = new Produit(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getInt(6));
            p.setId(rs.getInt(1));
            ls.add(p);
        }
        return ls;
    }

    public void modifier(Produit p) throws SQLException {

        String req = "UPDATE `produit` SET type_produit=?,nom_produit=?,"
                + "prix_produit=?,delai_expiration=? WHERE id_produit=?";

        System.out.println("**" + p.getDelai_expiration());
        Date sqlDate = new Date(p.getDelai_expiration().getTime());
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, p.getType());
        pre.setString(2, p.getNom());
        pre.setDouble(3, p.getPrix());
        pre.setInt(5, p.getId());
        pre.setDate(4, sqlDate);

        pre.executeUpdate();

    }

    public void supprimer(int id) throws SQLException {

        String req = "delete from  produit where id_produit= ?";

        PreparedStatement pre = con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id);

        pre.executeUpdate();// taamel delete 3al base de donné 

    }

    public List<Produit> filtrerProduit(String nomProd, String nomPat, String type, double prixMin, double prixMax) throws SQLException {
        List<Produit> ls = new ArrayList<Produit>();

        String sql = "SELECT p.* FROM produit p ";
        if (nomPat.length() != 0) {
            sql += " inner join user u on u.id_user=p.id_user and nom  = '" + nomPat + "'";
        }
        sql += " where 1";
        if (nomProd.length() != 0) {
            sql += " and nom_produit = '" + nomProd + "'";
        }

        if (type.length() != 0) {
            sql += " and type_produit = '" + type + "'";
        }

        if (prixMin != 0 && prixMax == 0) {
            sql += " and prix_produit >" + prixMin;
        } else if (prixMin == 0 && prixMax != 0) {
            sql += " and prix_produit <" + prixMax;
        } else if (prixMin != 0 && prixMax != 0) {
            sql += " and prix_produit between " + prixMin + " and " + prixMax;
        }

        System.out.println(sql);
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        Produit p = null;
        while (rs.next()) {
            p = new Produit(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getInt(6));
            p.setId(rs.getInt(1));
            ls.add(p);
        }
        return ls;
    }
}
