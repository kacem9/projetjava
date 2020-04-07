
package Service;
import controllers.*;

import Utils.*;
import entities.rendezvous;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<rendezvous> getAll() {
        ObservableList<rendezvous> list = FXCollections.observableArrayList();
        String requete = "select * from rendezvous";
        try {
           PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                list.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
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
    
    
    
     public void ajouterRendezvous( rendezvous t, String nom,String combo_Type ,String prenom,String email ,String adresse,String num){
        try {           
         
    
            String Req_Add="INSERT INTO rendezvous ( nom,  prenom,email,adresse, message,typepanne,numtel) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pt = connection.prepareStatement(Req_Add);   
           
            pt.setString(1,(t.getNom()));
            pt.setString(2,(t.getPrenom()));
            pt.setString(3,(t.getEmail()));
            pt.setString(4,(t.getAdresse()));
            pt.setString(5,(t.getMessage()));
             pt.setString(6,(t.getTypepanne()));
            pt.setInt(7,(t.getNumtel()));
           
        
            pt.executeUpdate();
              
         
           
        } catch (SQLException ex) {
            Logger.getLogger(Rendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
        public void insertPStatement(rendezvous cr) throws ParseException {
        try {
         
            System.out.println(cr.toString());
            
            String requete =
                    "INSERT INTO rendezvous( nom,  prenom,email,adresse, message,typepanne,numtel,user ) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
            st.setString(1,(cr.getNom()));
            st.setString(2,(cr.getPrenom()));
            st.setString(3,(cr.getEmail()));
            st.setString(4,(cr.getAdresse()));
            st.setString(5,(cr.getMessage()));
             st.setString(6,(cr.getTypepanne()));
            st.setInt(7,(cr.getNumtel()));
            st.setInt(8,(cr.getUser()));
            
           
           
           
            st.executeUpdate();
            System.out.println("contrat ajoutée");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }    
    }
    
}
