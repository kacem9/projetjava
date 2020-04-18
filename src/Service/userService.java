/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.ConnectionDB;
import Admin.AdministratorController;
import entities.user;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.management.Notification;
import utils.BCrypt;
import utils.Session;
import javafx.scene.paint.Paint;
import javax.mail.MessagingException;
import utils.EmailAttachmentSender;

import utils.Mail;

/**
 *
 * @author Asus
 */
public class userService {

    private Connection c;
    private PreparedStatement ps1;
    private Statement st;
    private ResultSet rs;

    public userService() {
        c = ConnectionDB.getinstance().getCnx();
    }

    public void ajouterUser(String username, String username_canonical, String email, String email_canonical, String password, String Roles, String Cine, String nom, String prenom, String sexe, String date_naissance, String num_tel, String adresse, String poste, String civilite, String pays, String ville, String code_postal, File selectedFile) throws Exception {
        try {

            PreparedStatement ps1 = c.prepareStatement("INSERT INTO `velo`.`fos_user` (username,username_canonical,email,email_canonical,enabled,password,last_login,roles,Cin,Nom,Prenom,Sexe,Date_naissance,Num_tel,Adresse,Poste,Civilite,Pays,Ville,Code_postal,photo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            //String token = UUID.randomUUID().toString();
            ps1.setString(1, username);
            ps1.setString(2, username_canonical);
            ps1.setString(3, email);
            ps1.setString(4, email_canonical);
            ps1.setBoolean(5, true);
            ps1.setString(6, BCrypt.hashpw(password, BCrypt.gensalt()));

            ps1.setDate(7, new Date(System.currentTimeMillis()));
          
            ps1.setString(8, Roles);

            ps1.setString(9, Cine);
            ps1.setString(10, nom);

            ps1.setString(11, prenom);

            ps1.setString(12, sexe);

            ps1.setDate(13, Date.valueOf(date_naissance));
            ps1.setString(14, num_tel);
            ps1.setString(15, adresse);
            ps1.setString(16, poste);
            ps1.setString(17, civilite);
            ps1.setString(18, pays);
            ps1.setString(19, ville);
            ps1.setString(20, code_postal);
            ps1.setString(21, selectedFile.getPath());
        //  EmailAttachmentSender.sendEmailWithAttachments(email, "Confirmation du Compte User", "<h1> Cher utilisateur,</h1></br> <p>Nous avons l'honneur de vous accueiller parmi notre communauté. Vous devez confirmer votre compte en copiant le code ci-dessous lors de votre prochaine authentification.</p>" + token + "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Velo.tn</h3>");

            ps1.executeUpdate();
            //   fetRowList();

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<user> readAll() {
        List<user> evs = new ArrayList<>();
        try {
            Statement stm = c.createStatement();
            ResultSet rest
                    = stm.executeQuery("select * from fos_user  ");
            while (rest.next()) {
                user ev = new user();
                ev.setId(rest.getInt("id"));
                ev.setNom(rest.getString("Nom"));

                ev.setPrenom(rest.getString("Prenom"));
                ev.setUsername(rest.getString("username"));
                ev.setEmail(rest.getString("email"));
                ev.setRoles(rest.getString("roles"));

                evs.add(ev);

            }

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evs;
    }

    /*  
public void updateuser(user u) throws SQLException {
            String sql = "UPDATE fos_user SET username=?,username_canonical=?,email=?,email_canonical=?,enabled=?,password=?,last_login=?,roles=?,"
                    + "Cin=?,Nom=?,Prenom=?,Sexe=?,Date_naissance=?,Num_tel=?,Adresse=?,Poste=?,Civilite=?,Pays=?,Ville=?,Code_postal=?,photo=? WHERE id=?";

PreparedStatement statement = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{
                statement.setString(1, u.getUsername());  
            statement.setString(2, u.getUsername_canonical());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getEmail_canonical());
            statement.setBoolean(5, true);
            statement.setString(6, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));

            statement.setDate(7,new Date(System.currentTimeMillis()) );
           
            statement.setString(8, u.getRoles());
            

            statement.setString(9, u.getCin());
                        statement.setString(10, u.getNom());

                                    statement.setString(11, u.getPrenom());
                                    
                                    statement.setString(12,u.getSexe());

            statement.setDate(13, Date.valueOf(u.getDate_naissance()));
            statement.setString(14, u.getNum_tel());
            statement.setString(15, u.getAdresse());
            statement.setString(16 , u.getPoste());           
            statement.setString(17, u.getCivilite());
            statement.setString(18, u.getPays());
            statement.setString(19, u.getVille());
            statement.setString(20 ,u.getCode_postal()); 
            statement.setString(21, u.getPhoto().getPath());
            
          
   statement.executeUpdate();
    System.out.println("get");
    }
catch (Exception ex) {
                Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
   
       
         

}}*/
    public void delete(int u) {
        try {
            String delete = "DELETE FROM fos_user WHERE id = ? ";
            System.out.println(u);
            PreparedStatement st2 = c.prepareStatement(delete);

            st2.setInt(1, u);

            st2.executeUpdate();
            System.out.println("delete");

        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Authentification(user u) {
        boolean status = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, u.getUsername());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {

                    status = true;
                    u = this.findById(rs.getInt("id"));
                    Session.setUser(u);
                    System.out.println(u.getId());

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public String checkRole(String username) {
        String default_return = "ND";
        try {
            String req = "select roles from fos_user where username=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString(1).equals("Admin")) {

                    return "Admin";
                } else if (rs.getString(1).equals("Vendeur")) {
                    return "Vendeur";
                } else if (rs.getString(1).equals("Reparateur")) {
                    return "Reparateur";
                } else if (rs.getString(1).equals("Achteur")) {
                    return "Achteur";
                } else if (rs.getString(1).equals("chef")) {
                    return "chef";
                } else {
                    return "Achteur";

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return default_return;
    }

    public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

    public user findById(Integer id) {
        user u = null;
        try {
            String req = "select id ,prenom,  nom, email,  username,adresse, Num_tel, password, tentative, etat from fos_user where id=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                u = new user(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10));

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

    /* public void mdpoublier(String username, String password){
        try {
                Mail mail =new Mail("nejibalazzem@gmail.com","nejiba1997","nejiba.lazzem@esprit.tn","","<h1>Your password is:"+password);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
}*/
    public void SendactivationByMail(user u, int act_code) {
        try {
            Mail mail = new Mail("nejibalazzem@gmail.com", "nejiba1997", u.getEmail(), "", "Your Activation code is:<h1>" + act_code + "</h1>");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
    

    public Boolean CheckToken(user u, String token) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? and confirmation_token" + "=?  ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, u.getEmail());
            st.setString(2, token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;

    }

    public void ressettingpassword(user u) {

        try {
            String req = "update fos_user SET password=?,confirmation_token=?  where email=?";
            PreparedStatement st = c.prepareStatement(req);
            String password = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
            st.setString(1, password);
            st.setString(2, null);
            st.setString(3, u.getEmail());
            st.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Boolean Checkconfirmationtoken(String email, String Token) {
        boolean exisit = false;
        try {
            String req = "select * from fos_user where email=? and confirmation_token=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, Token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                exisit = true;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

    public void ConfirmAccount(user u) throws MessagingException {
        String req2 = "update fos_user set enabled=? , confirmation_token=? where email=?  ";

        try {
            PreparedStatement st1 = c.prepareStatement(req2);
            st1.setInt(1, 1);
            st1.setString(2, null);
            st1.setString(3, u.getEmail());
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Compte user confirmé ", "<h1> Cher utilisateur,</h1></br> <p>Votre compte a été activé. Nous vous souhaitons une bonne expérience sur notre plateforme.</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Velo.tn </h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void SendMailAndAddTokenToUser(user u) {
        try {
            String token = UUID.randomUUID().toString();
            String req = "update fos_user SET confirmation_token=?  where email=?";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, token);
            st.setString(2, u.getEmail());

            st.executeUpdate();
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Code de récupération de mot de passe ", "<h1> Cher utilisateur,</h1></br> <p>Veuillez trouver ci-dessous le code pour la recupération de votre mot de passe.</p>" + token + "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Velo.tn</h3>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String GetUsernameUser(String password) throws SQLException {
        List<user> list = readAll();
        for (user u : list) {
            if ((BCrypt.hashpw(password, BCrypt.gensalt()).equals(password))) {
                return u.getUsername();
            }
        }
        return "";
    }
}
