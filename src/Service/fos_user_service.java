
package Service;

import controllers.*;


import Utils.*;

import entities.fos_user;
import entities.rendezvous;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author HP
 */
public class fos_user_service {
      Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;
     int res1;
 int res ;
    public fos_user_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }
    public ObservableList<fos_user> getAll() {
        ObservableList<fos_user> list = FXCollections.observableArrayList();
        String requete = "select * from fos_user";
        try {
           PreparedStatement pt1 = c.prepareStatement("select id, username, username_canonical, email, email_canonical, enabled, salt, password, last_login,  confirmation_token,  password_requested_at,  roles,  Cin,  Nom, Prenom, Sexe, Date_naissance, Num_tel, Adresse,  Poste, Civilite, Pays, Ville,  Code_postal, photo from fos_user ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                list.add(new fos_user(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getDate("last_login"),rs.getString("confirmation_token"),rs.getDate("password_requested_at"),rs.getString("roles"),rs.getString("Cin"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Sexe"),rs.getDate("Date_naissance"),rs.getString("Num_tel"),rs.getString("Adresse"),rs.getString("Poste"),rs.getString("Civilite"),rs.getString("Pays"),rs.getString("Ville"),rs.getString("Code_postal"),rs.getString("photo")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
    }
    
    
       public void afficherReparateur(ObservableList<fos_user> oblist) throws IOException{
        try {
           // oblist = FXCollections.observableArrayList();
            PreparedStatement pt1 = c.prepareStatement("select id, username, username_canonical, email, email_canonical, enabled, salt, password, last_login,  confirmation_token,  password_requested_at,  roles,  Cin,  Nom, Prenom, Sexe, Date_naissance, Num_tel, Adresse,  Poste, Civilite, Pays, Ville,  Code_postal, photo from fos_user ");
            ResultSet rs = pt1.executeQuery();
            
            while (rs.next()) {
  
          oblist.add(new fos_user(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getDate("last_login"),rs.getString("confirmation_token"),rs.getDate("password_requested_at"),rs.getString("roles"),rs.getString("Cin"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Sexe"),rs.getDate("Date_naissance"),rs.getString("Num_tel"),rs.getString("Adresse"),rs.getString("Poste"),rs.getString("Civilite"),rs.getString("Pays"),rs.getString("Ville"),rs.getString("Code_postal"),rs.getString("photo")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    public void modifierReparateur(fos_user r, int IDSel, String msgEdit, String Prenom, String Nom){
        try {
            PreparedStatement pt1 = c.prepareStatement("select id from fos_user where Prenom='"+Prenom+"' and Nom='"+Nom+"'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
             res1=rs.getInt(1);      
            }
            PreparedStatement pt3 = c.prepareStatement("update fos_user  set id=? where id=?");
            pt3.setString(1, msgEdit);
            pt3.setInt(2, r.getId());
            pt3.setInt(3, IDSel);
            pt3.executeUpdate();
            
          }  catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    
    public void afficherPrenomReparateur(ComboBox combo){
        try {
            PreparedStatement pt = c.prepareStatement("select id from fos_user  ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
             //System.out.println(rs.getString(1) + " " +rs.getString(2));
                
             combo.getItems().add(rs.getString(1));
               //System.out.println(rs.getString(1)+" "+rs.getString(2));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
       
    public void afficherNomReparateur(String Prenom, ComboBox comboNom){
        try {
            PreparedStatement pt = c.prepareStatement("select id from fos_user where Prenom='"+Prenom+"' ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
            // System.out.println(rs.getString(1));
             comboNom.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ajouterRendezvous(String msg, rendezvous t, String item_selName, String item_selPrename, String combo_Type){
        try {           
            PreparedStatement pt2 = c.prepareStatement("select id from fos_user where Prenom='"+item_selPrename+"' and Nom='"+item_selName+"' ");
            
            ResultSet rs = pt2.executeQuery();
           
            while(rs.next()){
                 res =rs.getInt(1);
            }
            String Req_Add="INSERT INTO `rendezvous` ( 'Cin', 'message', 'typepanne', 'adresse','nom','prenom') VALUES (?,?,?,?,0,0)";
            PreparedStatement pt = c.prepareStatement(Req_Add);   
            pt.setInt(1, t.getCin());
             pt.setString(2, msg);
              pt.setString(3, "non");
               pt.setString(4, "hhhh");
                  pt.setString(5, "hhhh");
            
            
           
          
            pt.setInt(6, res);
            pt.executeUpdate();
            
           
           
        } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void afficherPrenomChauffeur(ComboBox combo){
        try {
            PreparedStatement pt = c.prepareStatement("select Prenom from fos_user ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
             //System.out.println(rs.getString(1) + " " +rs.getString(2));
                
             combo.getItems().add(rs.getString(1));
               //System.out.println(rs.getString(1)+" "+rs.getString(2));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherNomChauffeur(String Prenom, ComboBox comboNom){
        try {
            PreparedStatement pt = c.prepareStatement("select Nom from fos_user where Prenom='"+Prenom+"'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
            // System.out.println(rs.getString(1));
             comboNom.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(fos_user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
