
package service;

import controllers.*;


import Utils.*;
import Entities.fos_user;
import Entities.rendezvous;

import Entities.validrendezvous;
import Reparateur.ValidateRendezvousController;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.Notification;
import connection.ConnexionBD;


/**
 *
 * @author HP
 */
public class Validrendezvous_service {
     Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;
     int res1;

    public Validrendezvous_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }
    public ObservableList<validrendezvous> getAll() {
        ObservableList<validrendezvous> list = FXCollections.observableArrayList();
        String requete = "select * from validrendezvous";
        try {
           PreparedStatement pt1 = c.prepareStatement("select emailR, dateheure, prix, promo, etat, message ,reference ,user,user_cc from validrendezvous ");
            ResultSet rs = pt1.executeQuery();;
            while(rs.next()){
                list.add(new validrendezvous(rs.getString("emailR"),rs.getString("dateheure"),rs.getString("prix"),rs.getString("promo"),rs.getString("etat"),rs.getString("message"),rs.getInt("reference"),rs.getInt("user"),rs.getInt("user_cc")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
    }
    
       public void delete(validrendezvous cn) {
        try {
        String requete =
                "DELETE FROM validrendezvous where emailR='"
                +cn.getEmailR()
                +"'";
                st =connection.createStatement();
                st.executeUpdate(requete);
                System.out.println("rendezvous validée supprimée");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        } 
    }
       
       
       
    public void modifierRendezvous(validrendezvous r, int IDSel, String msgEdit, String Prenom, String Nom){
        try {
            PreparedStatement pt1 = c.prepareStatement("select id from fos_user where prenom='"+Prenom+"' and nom='"+Nom+"'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
             res1=rs.getInt(1);      
            }
            PreparedStatement pt3 = c.prepareStatement("update validrendezvous, user set message=?, =?, id="+res1+" where emailR=?");
            pt3.setString(1, msgEdit);
            pt3.setString(2, r.getMessage());
            pt3.setInt(3, IDSel);
            pt3.executeUpdate();
           
          }  catch (SQLException ex) {
            Logger.getLogger(Validrendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    
    
       public void afficherPrenomUser(ComboBox combo)
       { try {
            PreparedStatement pt = c.prepareStatement("select Prenom from fos_user where email='admin@gmail.com'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
             //System.out.println(rs.getString(1) + " " +rs.getString(2));
                
             combo.getItems().add(rs.getString(1));
               //System.out.println(rs.getString(1)+" "+rs.getString(2));         
            }}
         catch (SQLException ex) {
            Logger.getLogger(Validrendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
          
  /* public void afficherValidRendezvous(ObservableList<validrendezvous> oblist){
        try {
         
            PreparedStatement pt1 = c.prepareStatement("select emailR, dateheure, prix,promo , etat, message,user_cc from validrendezvous, fos_user where id=emailR ");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
          
            oblist.add(new validrendezvous(rs.getString("emailR"), rs.getString("dateheure"), rs.getString("prix"),rs.getString("promo"), rs.getString("etat"), rs.getString("message"), rs.getInt("reference"), rs.getInt("user"), rs.getInt("user_cc")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(Validrendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public ObservableList<rendezvous> getAlls() {
        ObservableList<rendezvous>list = FXCollections.observableArrayList();
        String requete = "select * from rendezvous";
        try {
           PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user,fos from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                list.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user"),rs.getInt("fos")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
    }
    
        
       public void afficherRendezvous(ObservableList<rendezvous> oblist) throws IOException{
        try {
           // oblist = FXCollections.observableArrayList();
            PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user ,fos from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            
            while (rs.next()) {
  
          oblist.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user"),rs.getInt("fos")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(Validrendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 

   
    public void refuse( String recep) throws MessagingException, AWTException, MalformedURLException {
   Properties p= new Properties();  
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
   
    String e_mail="nesrinezouaoui583@gmail.com";
    String pass = "nesrine58967048";
    

    Session session =Session.getInstance(p,new javax.mail.Authenticator(){
        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
          return new javax.mail.PasswordAuthentication(e_mail, pass);
        }
    });
 
     
        javax.mail.Message message=prepareMessages(session,e_mail,recep);
        javax.mail.Transport.send(message);
          doryan.windowsnotificationapi.fr.Notification.sendNotification("Notification Appointement", "Email envoyé ",TrayIcon.MessageType.INFO);



    }
    
    
    
    private static javax.mail.Message prepareMessages(Session session,String e_mail, String recipient) throws javax.mail.MessagingException{

    javax.mail.Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("appointement");
message.setText("your appointement refuse");

return message;

}
       public void deleteRen(int selectedCin ){
        try {    
            PreparedStatement pt2 = c.prepareStatement("delete from rendezvous where Cin='"+selectedCin+"'");
            pt2.executeUpdate();
            try {
                doryan.windowsnotificationapi.fr.Notification.sendNotification("Notification", "Rendezvous deleted",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
