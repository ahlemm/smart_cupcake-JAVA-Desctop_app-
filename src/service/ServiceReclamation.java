
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utile.DataSource;

/**
 *
 * @author esprit
 */
public class ServiceReclamation {
     Connection con=DataSource.getInstance().getConnection();
        private Statement ste;     
    private Reclamation p;

    public ServiceReclamation() {
        try{   
         ste = con.createStatement();}
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
     public void ajouterReclamation(Reclamation p) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update
//java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
  //Date sqlDateReclamation = new Date(p.getDateReclamation().getTime());
  
  
 //  java.util.Date date_util=new java.util.Date();
   //java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String req="INSERT INTO `reclamation` (`info`, `tel`,`email`,`dateReclamation`)"+" VALUES (?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(req);
        pre.setString(1, p.getInfo());
        pre.setInt(2, p.getTel());  
        pre.setString(3, p.getEmail());
        pre.setDate(4, p.getDateReclamation());
       
        pre.executeUpdate();
         System.out.println("ajout avec succÃ©es");
    
        

    }
      public ObservableList<Reclamation>afficher() throws SQLException{
    //ObservableList<Reclamation> ls=new ObservableArrayList();
     ObservableList<Reclamation> ls= FXCollections.observableArrayList();
    String sql="SELECT * FROM reclamation";
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next()){
       // infoM, telM, dateM, emailM
        p=new Reclamation(rs.getString(3),rs.getInt(5),rs.getDate(8),rs.getString(6));
        
       // p.setIdReclamation(rs.getInt(7));
        ls.add(p);
        System.out.println(p.getDateReclamation());
    }
          //System.out.println(ls.toString());
    return ls;
    }

    public Object getDateReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
