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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utile.DataSource;

/**
 *
 * @author esprit
 */
public class ServicePatisserie {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    private static ServicePatisserie instance;

    public static ServicePatisserie getInstance() {
        if (instance == null) {
            instance = new ServicePatisserie();
        }
        return instance;
    }

    public ServicePatisserie() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterPatisserie(Patisserie p) throws SQLException {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update

        String req = "INSERT INTO `patisserie` ( `nom_patisserie`, `activite`,`info_patisserie`,`longitude`,`latitude`,`adresse_patisserie`,`id_user`,`photo_patisserie`)" + " VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, p.getNom_patisserie());

        pre.setString(2, p.getAcitivite());
        pre.setString(3, p.getInfo_patisserie());
        pre.setDouble(4, p.getLongitude());
        pre.setDouble(5, p.getLatitude());
        pre.setString(6, p.getAdresse_patisserie());
        //      pre.setInt(7, p.getEtat_patisserie());
        pre.setInt(7, p.getPatissier().getId_user());
//       pre.setObject(7,p.getPatissier());
        
        pre.setString(8, p.getPhoto_patisserie());
        pre.executeUpdate();
        System.out.println("ajout avec succées");

    }

    public void ModifierPatisserie(Patisserie p) {
        String req = "UPDATE patisserie set id_patisserie=?,nom_patisserie=?,activite=?,info_patisserie=?,longitude=?,latitude=?,adresse_patisserie=?,etat_patisserie=? WHERE  id_patisserie=" + p.getId_patisserie();

        try {
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, p.getId_patisserie());
            pre.setString(2, p.getNom_patisserie());

            pre.setString(3, p.getAcitivite());
            pre.setString(4, p.getInfo_patisserie());
            pre.setDouble(5, p.getLongitude());
            pre.setDouble(6, p.getLatitude());
            pre.setString(7, p.getAdresse_patisserie());
            pre.setInt(8, p.getEtat_patisserie());
            //   pre.setInt(8,p.getPatissier().getId_user());

            pre.executeUpdate();
            System.out.println("modification avec succés");

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean updatePatisserie(int id_patisserie, String a, String b, String c, String d) {

        String req = "update patisserie set nom_patisserie='" + a + "',activite='" + b + "',info_patisserie='" + c + "',adresse_patisserie='" + d + "' WHERE  id_patisserie=" + id_patisserie;
        try {
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("modification avec succés");

            return true;
        }

        return false;
    }

    public boolean updatePatisserie1(int id_patisserie, String a, String b, String c, Double p, Double w, String d) {

        String req = "update patisserie set nom_patisserie='" + a + "',activite='" + b + "',info_patisserie='" + c + "',longitude='" + p + "',latitude='" + w + "',adresse_patisserie='" + d + "' WHERE  id_patisserie=" + id_patisserie;
        try {
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("modification avec succés");

            return true;
        }

        return false;
    }

//
//    public List<Patisserie> afficher() throws SQLException {
//        List<Patisserie> ls = new ArrayList();
//        String sql = "SELECT * FROM patisserie";
//        ResultSet rs = ste.executeQuery(sql);
//        Patisserie p = null;
//        while (rs.next()) {
//            p = new Patisserie(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getInt(8));
//            p.setId_patisserie(rs.getInt(1));
//            ls.add(p);
//        }
//        return ls;
//    }
//
    public ObservableList<Patisserie> getAllPatisseries() {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();

        try {
            String req = "select * from patisserie";
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(req);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));

                int id_user = rsl.getInt(9);

                ServiceUser daoUser = ServiceUser.getInstance();
                // p.setPatissier(daoUser.findUserById(id_user));
                p.setPhoto_patisserie(rsl.getString(10));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }
//
//    /* public void modifier(Patisserie p){
//        String req ="UPDATE `produit` SET `type`=?,`nom`=?,`prix`=? WHERE `id`=?"; 
//        try {
//           
//            PreparedStatement pre=con.prepareStatement(req);
//            
//             pre.setInt(1, p.getId_patisserie());
//        pre.setString(2, p.getNom_patisserie());  
//        pre.setString(3, p.getAcitivite());
//        pre.setString(4, p.getInfo());
//        pre.setString(5, p.getAdresse());
//        pre.setInt(6, p.getEtat_patisserie());
//            pre.executeUpdate();
//            System.out.println("modification avec succés");
//           
// 
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//     */

    public void supprimer(int id_patisserie) throws SQLException {

        String req = "delete from  patisserie where id_patisserie= ?";

        PreparedStatement pre = con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
        pre.setInt(1, id_patisserie);

        pre.executeUpdate();// taamel delete 3al base de donné 
        System.out.println("patisserie supprimer");

    }
//

    public void approuver(int id_patisserie) {

        try {
            String req = ("update patisserie set etat_patisserie=3 where id_patisserie=" + id_patisserie);
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate();
            System.out.println("approuvée avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//

    public void etudier(int id_patisserie) {
        ;
        try {
            PreparedStatement pre = con.prepareStatement("update patisserie set etat_patisserie=2 where id_patisserie=" + id_patisserie);
            pre.executeUpdate();
            System.out.println("entrain d'etudier la patisserie d'id =" + id_patisserie);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//getPatisserieById

    public ObservableList<Patisserie> getPatisserieById(int id_patisserie) {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {
            String requete = "select * from patisserie where id_patisserie=" + id_patisserie;
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));
                p.setPhoto_patisserie(rsl.getString(9));

//   int id_patissier = rsl.getInt(9);
//                ServiceUser daoUser = ServiceUser.getInstance();
//                p.setPatissier(daoUser.findUserById(id_patissier));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

    public ObservableList<Patisserie> searchPatisserie(String nom_patisserie, String activite, String adresse_patisserie) {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {

            String requete = "select * from patisserie where nom_patisserie LIKE '%" + nom_patisserie + "%' && activite LIKE '%" + activite + "%' && adresse_patisserie LIKE '%" + adresse_patisserie + "%'";
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));
                ServicePatisserie service = ServicePatisserie.getInstance();

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));

                int id_patissier = rsl.getInt(9);

                ServiceUser daoUser = ServiceUser.getInstance();
                p.setPatissier(daoUser.findUserById(id_patissier));
                p.setPhoto_patisserie(rsl.getString(10));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

    public ObservableList<Patisserie> search_nom(String nom_patisserie) {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {
            String requete = "select * from patisserie where nom_patisserie LIKE '%" + nom_patisserie + "%'";
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));
                ServicePatisserie service = ServicePatisserie.getInstance();

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));

                int id_patissier = rsl.getInt(9);
                ServiceUser daoUser = ServiceUser.getInstance();

                p.setPatissier(daoUser.findUserById(id_patissier));
                p.setPhoto_patisserie(rsl.getString(10));

                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

////    
//    
    public ObservableList<Patisserie> getAllPatisserieNonApprouved() {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {
            String req = "select * from patisserie where etat_patisserie=1";

            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(req);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));
                int id_patissier = rsl.getInt(9);
                ServiceUser daoUser = ServiceUser.getInstance();
                p.setPatissier(daoUser.findUserById(id_patissier));
                p.setPhoto_patisserie(rsl.getString(10));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

    public ObservableList<Patisserie> getAllPatisserieàEtudier() {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {
            String req = "select * from patisserie where etat_patisserie=2";

            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(req);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));
                int id_patissier = rsl.getInt(9);
                ServiceUser daoUser = ServiceUser.getInstance();
                p.setPatissier(daoUser.findUserById(id_patissier));
                p.setPhoto_patisserie(rsl.getString(10));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }
//    
//    

    public ObservableList<Patisserie> getAllPatisserieApprouved() {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();
        try {
            String req = "select * from patisserie where etat_patisserie=3";

            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(req);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));
                int id_patissier = rsl.getInt(9);
                ServiceUser daoUser = ServiceUser.getInstance();
                p.setPatissier(daoUser.findUserById(id_patissier));
                p.setPhoto_patisserie(rsl.getString(10));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePatisserie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

    public ObservableList<Patisserie> getPatisseriesByidPatissier(int id) {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();

        try {
            String requete = "select * from patisserie where id_user=" + id;
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                p.setEtat_patisserie(rsl.getInt(8));
                p.setPhoto_patisserie(rsl.getString(10));

//   int id_patissier = rsl.getInt(9);
//                ServiceUser daoUser = ServiceUser.getInstance();
//                p.setPatissier(daoUser.findUserById(id_patissier));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }

    public ObservableList<Patisserie> mes_patisserie(int id) {
        ObservableList<Patisserie> listPatisseries = FXCollections.observableArrayList();

        try {
            String requete = "select * from patisserie where id_user=" + id;
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);

            while (rsl.next()) {
                Patisserie p = new Patisserie();

                p.setId_patisserie(rsl.getInt(1));
                p.setNom_patisserie(rsl.getString(2));
                p.setAcitivite(rsl.getString(3));
                p.setInfo_patisserie(rsl.getString(4));

                p.setLongitude(rsl.getDouble(5));
                p.setLatitude(rsl.getDouble(6));
                p.setAdresse_patisserie(rsl.getString(7));
                //  p.setEtat_patisserie(rsl.getInt(8));

//   int id_patissier = rsl.getInt(9);
//                ServiceUser daoUser = ServiceUser.getInstance();
//                p.setPatissier(daoUser.findUserById(id_patissier));
                listPatisseries.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listPatisseries;
    }



}