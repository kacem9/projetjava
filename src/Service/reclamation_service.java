
package service;

import Entities.reclamation;
import connection.ConnexionBD;
import Entities.rendezvous;
import Entities.validrendezvous;
import Reparateur.ValidateRendezvousController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import controllers.*;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class reclamation_service {
        
        Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public reclamation_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }
   
    
    
      
    
    
         
       public void afficherReclamation(ObservableList<reclamation> oblist) throws IOException{
        try {
           // oblist = FXCollections.observableArrayList();
            PreparedStatement pt1 = c.prepareStatement("select reference, nom, prenom,  email, sujet,  message,adresse, numtel, user from reclamation");
            ResultSet rs = pt1.executeQuery();
            
            while (rs.next()) {
  
          oblist.add(new reclamation(rs.getInt("reference"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("sujet"),rs.getString("message"),rs.getString("adresse"),rs.getInt("numtel"),rs.getInt("user")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
       
       
          public static String sendMail(String recep ,String obj, String objs) throws Exception{
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
 
     
        javax.mail.Message message=prepareMessage(session,e_mail,recep,obj,objs);
        javax.mail.Transport.send(message);
          Notification.sendNotification("Notification Reclamation", "Email envoyé ",TrayIcon.MessageType.INFO);
       return e_mail;
    }
private static javax.mail.Message prepareMessage(Session session,String e_mail, String recipient,String obj,String objs) throws javax.mail.MessagingException{

    javax.mail.Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("Reclamation");
message.setText(obj +"About"+ objs);

return message;

}
 public void deleteRec(int selectedReference ){
        try {    
            PreparedStatement pt2 = c.prepareStatement("delete from reclamation where reference='"+selectedReference+"'");
            pt2.executeUpdate();
            try {
                doryan.windowsnotificationapi.fr.Notification.sendNotification("Notification", "Reclamation deleted",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
 
       
              /* public void insertPStatement(reclamation s) throws ParseException {
        try {
         
            System.out.println(s.toString());
            
            String requete =
                    "INSERT INTO reclamation(  nom, prenom,  email, sujet, message, numtel, user ) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
            st.setString(1,(s.getNom()));
             st.setString(2,(s.getPrenom()));
           
            st.setString(3,(s.getEmail()));
            st.setString(5,(s.getSujet()));
             st.setString(6,(s.getMessage()));
              st.setInt(7,(s.getNumtel()));
             st.setInt(8,(s.getUser()));

            
           
           
           
            st.executeUpdate();
            System.out.println("validate");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }   
        
        
               }*/
    
}
