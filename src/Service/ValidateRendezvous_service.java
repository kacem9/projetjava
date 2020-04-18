
package service;

import connection.ConnexionBD;
import Entities.fos_user;
import Entities.rendezvous;
import Entities.validrendezvous;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.TrayIcon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Date;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.userService;
import javafx.scene.chart.PieChart;

/**
 *
 * @author HP
 */
public class ValidateRendezvous_service {
 
               
         Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;
     int res1;
   int res, total,totals; 
    float countAcrh, countEncrs, countTraite, countNonTraite ;
    public ValidateRendezvous_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }

          
 public static String sendMail(String recep ,String obj,String price,String promo ,String date,String etat ) throws Exception{
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
 
     
        javax.mail.Message message=prepareMessage(session,e_mail,recep,obj,price,promo,date,etat);
        javax.mail.Transport.send(message);
          Notification.sendNotification("Notification Appointement", "Email envoyé ",TrayIcon.MessageType.INFO);
       return e_mail;
    }
private static javax.mail.Message prepareMessage(Session session,String e_mail, String recipient,String obj,String price,String promo,String date,String etat) throws javax.mail.MessagingException{

    javax.mail.Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("Valid appointement");
message.setText(obj +"price :"+price+"with promotion="+promo+"date :"+date+" At "+etat);

return message;

}
       public void countRendezvous(ObservableList<PieChart.Data> pieChartData, PieChart statPie){
        try {
            ResultSet rs, rs1, rs2, rs3, rs4;
            PreparedStatement pt1;
            pt1  = c.prepareStatement("SELECT COUNT(*) FROM validrendezvous");
            //pt1.setString(1, name);
             rs4 = pt1.executeQuery();
            while (rs4.next()) {
                total=rs4.getInt(1);
            }
            pt1  = c.prepareStatement("SELECT COUNT(*) FROM validrendezvous WHERE etat='At home'");
            //pt1.setString(1, name);
             rs = pt1.executeQuery();
            while (rs.next()) {
                countNonTraite=(rs.getInt(1))*100/total;
                //System.out.println(countNonTraite);
            }
            pt1 = c.prepareStatement("SELECT COUNT(*) FROM validrendezvous WHERE etat='at etablissement'");
            //pt1.setString(1, name);
            rs1 = pt1.executeQuery();
            while (rs1.next()) {
                countTraite=(rs1.getInt(1))*100/total;
                //System.out.println(countTraite);
            }
          
        
            pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("At home " +countNonTraite+ "%", countNonTraite),
                new PieChart.Data("at etablissement "  +countTraite+ "%", countTraite));
          
            statPie.setData(pieChartData);
            } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

       public void countRendezvousValid(ObservableList<PieChart.Data> pieChartData, PieChart statPie){
        try {
            ResultSet rs, rs1, rs2, rs3, rs4;
            PreparedStatement pt1;
             PreparedStatement pt2;
            pt1  = c.prepareStatement("SELECT COUNT(*) FROM validrendezvous");
            //pt1.setString(1, name);
             rs4 = pt1.executeQuery();
            while (rs4.next()) {
                total=rs4.getInt(1);
            }
             pt2  = c.prepareStatement("SELECT COUNT(*) FROM rendezvous");
            //pt1.setString(1, name);
             rs3 = pt2.executeQuery();
            while (rs3.next()) {
                totals=((rs3.getInt(1))*100)-total;
            }
        

          
        
            pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Rendezvous " +totals+ "%", totals),
                new PieChart.Data("Valid rendezvous "  +total+ "%", total));
          
            statPie.setData(pieChartData);
            } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    