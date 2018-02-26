/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;



import java.sql.Date;

import entite.Patisserie;
import entite.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ServicePatisserie;
import service.ServiceUser;
import utile.DataSource;
import static utile.Utils.patissier;
/**
 *
 * @author esprit
 */
public class Test {

    public static void main(String[] args) {
         DataSource da = DataSource.getInstance();
        Connection con = da.getConnection();
        
      System.out.println(con);
      
        
        
          ServicePatisserie s = new ServicePatisserie();
        try {
      Patisserie p1 = new Patisserie( "lala", "gateaux", "akk", 4D, 6D, "dkk", 1,patissier );
            s.ajouterPatisserie(p1);
      //      sp.ajouterProduit(p1);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    //  User h=new User("ghada","dridi","patissier","g@gmail.com","bzrt",7888,"a","e");
       
//    //    Patisserie p1 = new Patisserie( "lala", "gateaux", "akk", 4D, 6D, "dkk", 1 );
         
ServiceUser su = new ServiceUser ();
 //User patissier=new User(3,"a","a","patissier", "hh@h","ddd",77,"ff","dd");
    //  User patissier=new User(3,"an","a","patissier", "hh@h","ddd",77,"ff","dd");

  Patisserie p = new Patisserie("wiz","gg","jj",5.6,7.5,"dd", patissier);
  
 // p.setPatissier(patissier);
 // boolean a=su.checkCinExist(11);
   //System.out.println(a);
   
ServicePatisserie sp = new ServicePatisserie();

    }}
//sp.updatePatisserie(12, "halima", "creme", "h", "d");
//  sp.ModifierPatisserie(p);
//
// try {
//        } catch (SQLException ex) {
//         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);}}}/*
////     
//User a=su.findUserById(1);    
// Patisserie p1= sp.getPatisserieById(5);
//
// System.out.println(p1);
//        try {
//            sp.supprimer(3);
//         
//
//        
//        } catch (SQLException ex) {
//         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//      }
////    *************
////         try {
////           sp.supprimer(11);
////        } catch (SQLException ex) {
////            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
////        }} 
////}
//// *************
////sp.ModifierPatisserie(p )
//
//       List<Patisserie> l = new ArrayList<>();
//              List<Patisserie> l1 = new ArrayList<>();
//l=sp.searchPatisserie("vicoria", "", "");
// System.out.println(l);}}
//// sp.approuver(14);
 
//            // l = sp.getAllPatisserieNonApprouved();
//           
//
//        
////
////
////    } ****
////sp.approuver(31);
////sp.etudier(34);
////sp.getPatisserieById(34);
////sp.getPatisseriebynom("masmoudi");
////        List<Patisserie> listPatisseries = new ArrayList<>();
////   System.out.println(listPatisseries);
////listPatisseries=sp.searchPatisserie("3", "a", "dkk");
////   System.out.println(listPatisseries);
//
//
//

    

    
    
    
        
    
