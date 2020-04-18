
package service;
import Entities.reclamation;
import controllers.*;

import Utils.*;

import utils.Session;
import Entities.rendezvous;
import entities.user;
import Entities.validrendezvous;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import connection.ConnexionBD;

//import javax.mail.Session;

/**
 *
 * @author HP
 */
public class Rendezvous_service {
      
        Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public Rendezvous_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }
/*public ObservableList<rendezvous> getAll() throws SQLException {
        
        ObservableList<rendezvous> list = FXCollections.observableArrayList();
            Statement stm = connection.createStatement();
                 userService us=new userService();
         int id=Session.getUser().getId();
        try {
         
            ResultSet rest= 
                    stm.executeQuery("select * from rendezvous where user='"+ id+ "'  ");
           PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user ,fos from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                list.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user"),rs.getInt("fos")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
    }*/
    
    

    
      
        
       public void afficherRendezvous(ObservableList<rendezvous> oblist) throws IOException{
        userService us=new userService();
       
           try {
               
                int id=Session.getUser().getId();
          
            PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user,fos from rendezvous where user='"+id+"' ");
            ResultSet rs = pt1.executeQuery();
            
            while (rs.next()) {
  
          oblist.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user"),rs.getInt("fos")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    
    

    
    public void delete(rendezvous cn) {
        try {
        String requete =
                "DELETE FROM rendezvous where Cin='"
                +cn.getCin()
                +"'";
                st =connection.createStatement();
                st.executeUpdate(requete);
                System.out.println("rendezvous supprimée");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        } 
    }

    
       
       public void updateTypepanne(rendezvous t ) {
             String requete = "update rendezvous set   typepanne=? "
                     + "  where Cin= ?";
        try {
            pst= connection.prepareStatement(requete);
            
           
             pst.setString(1 , t.getTypepanne());
            pst.setInt(2, t.getCin());  
            
                     
            pst.executeUpdate();
            System.out.println("type panne Modifier avec succée: "); 
            
        } catch (SQLException ex) {
            Logger.getLogger(Rendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            public void updateMessage(rendezvous t ) {
             String requete = "update rendezvous set   message=? "
                     + "  where Cin= ?";
        try {
            pst= connection.prepareStatement(requete);
            
           
             pst.setString(1 , t.getTypepanne());
            pst.setInt(2, t.getCin());  
            
                     
            pst.executeUpdate();
            System.out.println("Message Modifier avec succée: "); 
            
        } catch (SQLException ex) {
            Logger.getLogger(Rendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
        public void insertPStatement(rendezvous cr) throws ParseException {
        try {
          String nom=Session.getUser().getNom();  
       System.out.println(nom);
     
             
            String requete =
                    "INSERT INTO rendezvous( nom,prenom,email,adresse, message,typepanne,numtel,fos,user ) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
         st.setString(1 ,Session.getUser().getNom());
         st.setString(2 ,Session.getUser().getPrenom());
            st.setString(3,(Session.getUser().getEmail()));
           st.setString(4,(Session.getUser().getAdresse()));
            st.setString(5,(cr.getMessage()));
             st.setString(6,(cr.getTypepanne()));
           st.setString(7,(Session.getUser().getNum_tel()));
          
        st.setInt(8 ,Session.getUser().getId());
              st.setInt(9,(cr.getUser()));
           
           
           
            st.executeUpdate();
            System.out.println("rendezvous ajoutée");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }    
    }
           public void afficherValidRendezvous(ObservableList<validrendezvous> oblist){
        try {
          int id=Session.getUser().getId();
            PreparedStatement pt1 = c.prepareStatement("select emailR, dateheure, prix,promo , etat, message, reference, user,user_cc from validrendezvous where  user_cc='"+id+"'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
          
            oblist.add(new validrendezvous(rs.getString("emailR"), rs.getString("dateheure"), rs.getString("prix"),rs.getString("promo"), rs.getString("etat"), rs.getString("message"), rs.getInt("reference"), rs.getInt("user"), rs.getInt("user_cc")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(Rendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
            public void insertPStatements(validrendezvous s) throws ParseException {
        try {
         
            System.out.println(s.toString());
            
            String requete =
                    "INSERT INTO validrendezvous( emailR, dateheure,prix,promo,etat,message,user,user_cc ) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
            st.setString(1,(s.getEmailR()));
             st.setString(2,(s.getDateheure()));
            st.setString(3,(s.getPrix()));
            st.setString(4,(s.getPromo()));
            st.setString(5,(s.getEtat()));
             st.setString(6,(s.getMessage()));
          
           st.setInt(7,(s.getUser()));
             st.setInt(8 ,Session.getUser().getId());
           
           
           
            st.executeUpdate();
            System.out.println("validate");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }   
        
        
               }
       
    public void afficherMonDemande(ObservableList<rendezvous> oblist ) throws ParseException {
    userService us=new userService();
        try {
          int ci=Session.getUser().getId();
           PreparedStatement pt1 = c.prepareStatement("select * from rendezvous where user='"+ci+"' ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                oblist.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user"),rs.getInt("fos")));
               }} 
        catch (SQLException ex) {
            Logger.getLogger(Rendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
            public void insertePStatement(reclamation s) throws ParseException {
        try {
         
            System.out.println(s.toString());
            
            String requete =
                    "INSERT INTO reclamation( nom, prenom, email,sujet,message,adresse,numtel, user ) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
          
            st.setString(1 ,Session.getUser().getNom());
             st.setString(2 ,Session.getUser().getPrenom());
          
             st.setString(3 ,Session.getUser().getEmail());
           
           st.setString(4,(s.getSujet()));
             st.setString(5,(s.getMessage()));
          st.setString(6 ,Session.getUser().getAdresse());
             st.setString(7 ,Session.getUser().getNum_tel());
             st.setInt(8 ,Session.getUser().getId());

            
           
           
           
            st.executeUpdate();
            System.out.println("validate");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }   
        
        
               }
    
}
