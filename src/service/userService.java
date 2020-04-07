/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.ConnectionDB;
import controllers.AdministratorController;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.management.Notification;
import utils.BCrypt;
import utils.Session;


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

   public void ajouterUser(String username, String username_canonical, String email, String email_canonical, String password, String Roles, String Cine, String nom, String prenom, String sexe, String date_naissance, String num_tel, String adresse, String poste, String civilite, String pays, String ville, String code_postal, File selectedFile) {
      try{
            
            PreparedStatement ps1=c.prepareStatement("INSERT INTO `velo` . `fos_user` (username,username_canonical,email,email_canonical,enabled,password,last_login,roles,Cin,Nom,Prenom,Sexe,Date_naissance,Num_tel,Adresse,Poste,Civilite,Pays,Ville,Code_postal,photo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
               
                       
            ps1.setString(1, username);  
            ps1.setString(2, username_canonical);
            ps1.setString(3, email);
            ps1.setString(4, email_canonical);
            ps1.setBoolean(5, true);
            ps1.setString(6, BCrypt.hashpw(password, BCrypt.gensalt()));

            ps1.setDate(7,new Date(System.currentTimeMillis()) );
           
            ps1.setString(8, Roles);
            

            ps1.setString(9, Cine);
                        ps1.setString(10, nom);

                                    ps1.setString(11, prenom);
                                    
                                    ps1.setString(12,sexe);

            ps1.setDate(13, Date.valueOf(date_naissance));
            ps1.setString(14, num_tel);
            ps1.setString(15, adresse);
            ps1.setString(16 , poste);           
            ps1.setString(17, civilite);
            ps1.setString(18, pays);
            ps1.setString(19, ville);
            ps1.setString(20 ,code_postal); 
            ps1.setString(21, selectedFile.getPath());
        
            ps1.executeUpdate();
                         //   fetRowList();

        } catch (SQLException ex){
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null , ex);
        } 
        
    
   
        
    
    }
  
  
  public List<user> readAll()  {
        List<user> evs =new ArrayList<>();
        try {
            Statement stm = c.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from fos_user  ");
            while(rest.next()){
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

     
   public void delete(int u)
    {
        try { 
            String delete = "DELETE FROM fos_user WHERE id = ? ";
            System.out.println(u);
        PreparedStatement st2 = c.prepareStatement(delete);
       
        
        st2.setInt(1 ,u);
 

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
    
    public boolean checkRole(String username) {
        boolean exist = false;
        try {
            String req = "select roles from fos_user where username=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString(1).equals("Admin")) {
                    exist = true;
                } 
              else  if (rs.getString(1).equals("Vendeur")) {
                    exist = true;
                } 
               else  if (rs.getString(1).equals("Reparateur")) {
                    exist = true;
                } 
               else  if (rs.getString(1).equals("Achteur")) {
                    exist = true;
                }
                else  if (rs.getString(1).equals("chef")) {
                    exist = true;
                }
                else {
                    exist = false;
                    
                }
                

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exist;
    }
    
   

    
  
    public user findById(Integer id) {
        user u = null;
        try {
            String req = "select id,username,password from fos_user where id=? ";
            PreparedStatement st = c.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               u = new user(rs.getInt(1),
                        rs.getString(2),
                         rs.getString(3));
                       
       
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }


     /* public int GetIdUser(String username,String password) throws SQLException
    {
        List<user> list =AfficherUsers();
        for(user u:list)
        {
            if((u.getUsername().equals(username))&&(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()).equals(password)))
            {
                return u.getId();
            }
        }
        return -1;
    }
      
    public List<user> AfficherUsers() throws SQLException {
        List<user> list=new ArrayList<>();
    st=c.createStatement();
    ResultSet rs=st.executeQuery("select * from fos_user");
     while (rs.next()) {    
     
               user user=new user(rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("type"));
     list.add(user);
     }
    return list;
    }

  public Boolean Login(String username,String password) throws SQLException {
        List<user> list =AfficherUsers();
        for(user u:list)
        {
            if((u.getUsername().equals(username))&&(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()).equals(password)))
            {
                if(getstatus(u.getId())==true)
                {
                    return true;
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("Error");
                                alert.setHeaderText("Your account is not activated");
                                    alert.setContentText("Please activate your account");
                                        alert.showAndWait();
                    return false;
                }
                
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("Error");
                                alert.setHeaderText("Invalid Informations");
                                    alert.setContentText("Please verify");
                                        alert.showAndWait();
        return false;
    }

    public boolean getstatus(int id) throws SQLException
    {
        return getUserById(id).isEnabled();
    }
    public user getUserById(int id) throws SQLException {
        st=c.createStatement();
        String query="select * from fos_user where id="+id;
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
          user user=new user(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getBoolean("enabled"),rs.getString("salt"),rs.getString("password"),rs.getDate("last_login"),rs.getBoolean("confirmation_token"),rs.getDate("password_requested_at"),rs.getString("roles"));
            return user;
        }
        return null;
    }*/
   }

