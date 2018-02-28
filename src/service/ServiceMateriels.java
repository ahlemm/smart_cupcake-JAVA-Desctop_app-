package service;

import entite.Materiels;
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
public class ServiceMateriels {
     Connection con=DataSource.getInstance().getConnection();
        private Statement ste;     
    private Materiels p;

    public ServiceMateriels() {
        try{   
         ste = con.createStatement();}
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void ajouterMateriels(Materiels p) throws SQLException{
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update

        String req="INSERT INTO `Materiels` (`type`, `info`, `prixMateriel`,`etat`)"+" VALUES (?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(req);
        pre.setString(1, p.getType());
        pre.setString(2, p.getInfo());  
        pre.setFloat(3, p.getPrixMateriel());
        pre.setString(4, p.getEtat());  
        pre.executeUpdate();
         System.out.println("ajout avec succÃ©es");
    
    
    }
    
    public  ObservableList<Materiels>afficher() throws SQLException{
   // List<Materiels> ls=new ArrayList();
   
    ObservableList<Materiels> ls= FXCollections.observableArrayList();
    String sql="SELECT * FROM `materiels`";
    ResultSet rs=ste.executeQuery(sql);
  // Materiels p=null;
    while(rs.next()){
        p=new Materiels(rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getString(7));
      
        p.setIdMateriels(rs.getInt(2));
        ls.add(p);
    }
    return ls;
    }
     
    
    
   
    
     public void modifier(Materiels p){
         System.out.println("   hhhhhhh");
        String req ="UPDATE Materiels SET type=?,info=?,prixMateriel=? ,etat=? WHERE idMateriels=?"; 
        
        try {
           
            PreparedStatement pre=con.prepareStatement(req);
            
            pre.setString(1, p.getType());
            pre.setString(2, p.getInfo());
            pre.setFloat(3, p.getPrixMateriel());                                        
            pre.setString(4, p.getEtat());
            pre.setInt(5, p.getIdMateriels());
            pre.executeUpdate();
            System.out.println("modification avec succÃ©s");
           
 
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
      public void supprimer (int idMateriels  ) throws SQLException  {
     
  String req= "delete from  materiels where idMateriels= ?" ;
       
      PreparedStatement pre=con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
         pre.setInt(1,idMateriels);
        
          pre.executeUpdate();// taamel delete 3al base de donnÃ© 
          
          
  }

    public ObservableList<?> affichers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Materiels> searcher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
      
}
