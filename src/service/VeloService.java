/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Velo;
import entities.Categories;
import entities.user;

import connection.ConnectionDB;
import utils.Session;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.net.MalformedURLException;
/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;

public class VeloService {
    private Connection connexion;
Integer  res;
    
public VeloService() {
    connexion = ConnectionDB.getinstance().getCnx();

       }
  
    public void addBike(String Quantity,LocalDate date_circulation, String localitsation_velo, String Description, String price_location,Velo v ,File selectedFile,Categories combo) throws SQLException, AWTException, MalformedURLException {

        String c=combo.getNom();
        System.out.println(c); 
       
        PreparedStatement pt2 = connexion.prepareStatement("select id from Categories where Nom='"+combo+"'");
            ResultSet rs2 = pt2.executeQuery();
            while(rs2.next()){
               Integer  res =rs2.getInt(1);
                        System.out.println(res);

            
      String req="INSERT INTO Velo (quantity,etat_vendu,etat_location,datePublication,date_circulation,description,localitsation_velo,photo,price_location,categories_id,user_id) VALUES (?,?, ?,?,?,?,?,?,?,?,?)" ;
             

                System.out.println(Session.getUser().getId());
         
        
     try{
         PreparedStatement ps1 = connexion.prepareStatement(req);
     
            ps1.setInt(1, Integer.valueOf(Quantity));
            ps1.setInt(2, Integer.valueOf(0));
            ps1.setInt(3, Integer.valueOf(1));
     
            ps1.setDate(5 ,Date.valueOf(date_circulation));
            ps1.setDate(4 ,new Date(System.currentTimeMillis())); 

            ps1.setString(6, Description);
            ps1.setString(7, localitsation_velo);
            ps1.setString(8 ,selectedFile.getName());
                        ps1.setFloat(9 ,Integer.valueOf(price_location));

                        ps1.setInt(10 ,res);
                        ps1.setInt(11 ,Session.getUser().getId());

            
          
            ps1.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null , ex);
        } 
        try {
                Notification.sendNotification("Success", "Bike of location added with successful",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);     
}}}
       public void updateBike(Velo v1, File selectedFile,Categories combo) throws SQLException, MalformedURLException {
            
           
        PreparedStatement pt2 = connexion.prepareStatement("select id from Categories where Nom='"+combo+"'");
            ResultSet rs2 = pt2.executeQuery();
            while(rs2.next()){
               Integer  res =rs2.getInt(1);
                        System.out.println(res);
           String sql = "UPDATE velo SET quantity=?,description=?,localitsation_velo=?,etat_location=?,etat_vendu=?,date_circulation=?,datePublication=?,photo=?,categories_id=?,user_id=? WHERE id=?";

PreparedStatement statement = connexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{
           statement.setInt(1, Integer.valueOf(v1.getQuantity()));

           
       

            statement.setString(2 ,v1.getDescription());
            statement.setString(3 ,v1.getLocalitsation_velo());
       
               statement.setInt(4, 1);
              
            statement.setInt(5, 0);
   
 statement.setInt(11 ,v1.getId());
             statement.setDate(6, (Date) v1.getDate_circulation());
            statement.setDate(7 ,new Date(System.currentTimeMillis())); 

            statement.setString(8 ,selectedFile.getName());
             statement.setInt(9 ,res);
                        statement.setInt(10 ,Session.getUser().getId());
   statement.executeUpdate();
    System.out.println("Updated");
    
    }
catch (Exception ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
   
       
}         try {
                Notification.sendNotification("Success", "Bike of location updated with successful",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);     
}      

}}
           public List<Velo> readAl() throws SQLException  {
        List<Velo> evs =new ArrayList<>();
         userService us=new userService();
         int id=Session.getUser().getId();
       
              System.out.println(id);
        Statement stm = connexion.createStatement();
              ResultSet rest1= 
                    stm.executeQuery("select Nom from categories ");
              while(rest1.next()){
                  String rs=rest1.getString("Nom");
              }
          try {  ResultSet rest= 
                    stm.executeQuery("select * from velo v where etat_vendu=0 and etat_location=1 and user_id='"+ id+ "'  ");
            while(rest.next()){
                Velo ev = new Velo();
                ev.setId(rest.getInt("id"));
                    ev.setDate_circulation(rest.getDate("date_circulation"));

              ev.setDatePublication(rest.getDate("datePublication"));
                              ev.setDescription(rest.getString("description"));
                ev.setLocalitsation_velo(rest.getString("localitsation_velo"));
                                ev.setPhoto(rest.getString("photo"));
                                ev.setPrice_location(rest.getInt("price_location"));

           ev.setQuantity(rest.getInt("quantity"));
             Categories c= new Categories(rest.getInt("categories_id"));
           ev.setCategories_id(c);
                
       ev.setPrice_location(rest.getInt("price_location"));
               evs.add(ev);
                System.out.println(evs);
                 }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
              
         return evs;
    }
 
    public List<Velo> readAll() throws SQLException  {
        List<Velo> evs =new ArrayList<>();
       int  id= Session.getUser().getId();
       // System.out.println("id"+ id);
            Statement stm = connexion.createStatement();
              ResultSet rest1= 
                    stm.executeQuery("select Nom from categories ");
              while(rest1.next()){
                  String rs=rest1.getString("Nom");
              }
          try {  ResultSet rest= 
                    stm.executeQuery("select * from velo ");
            while(rest.next()){
                Velo ev = new Velo();
                ev.setId(rest.getInt("id"));
                    ev.setDate_circulation(rest.getDate("date_circulation"));

              ev.setDatePublication(rest.getDate("datePublication"));
                              ev.setDescription(rest.getString("description"));
                ev.setLocalitsation_velo(rest.getString("localitsation_velo"));
                                ev.setPhoto(rest.getString("photo"));
                                ev.setPrice_location(rest.getInt("price_location"));

           ev.setQuantity(rest.getInt("quantity"));
             Categories c= new Categories(rest.getInt("categories_id"));
           ev.setCategories_id(c);
           ev.setUser_id(rest.getInt("user_id"));
                
       ev.setPrice_location(rest.getInt("price_location"));
               evs.add(ev);
                System.out.println(evs);
                 }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
              
         return evs;
    }
 
        public ArrayList<Velo> getAllSousCategorie() throws SQLException {
       ArrayList<Velo> sousCategories = new ArrayList<>();
        
        String req = "select * from categories;";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Categories c= new Categories(result.getString("Nom"));
            System.out.println(c);
           Velo sc = new Velo(c);
            sousCategories.add(sc);
        }
        
        return sousCategories;
    }
    
    
    public void delete(Velo v) 
    {
        try { 
            String delete = "DELETE FROM velo WHERE id = ? ";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        int id = v.getId();
        
        st2.setInt(1,id);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }

      

    
    
    }
 

    public List<Velo> afficherVelo() {
  List<Velo> le = new ArrayList<>();     
  try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from velo v where etat_vendu=0 and etat_location=1  ");
            while(rest.next()){
                Velo v = new Velo();
                v.setId(rest.getInt("id"));
                    v.setDate_circulation(rest.getDate("date_circulation"));
//
            //  v.setDatePublication(rest.getDate("datePublication"));
                              v.setDescription(rest.getString("description"));
                v.setLocalitsation_velo(rest.getString("localitsation_velo"));
                                //v.setPhoto(rest.getString("photo"));

           v.setQuantity(rest.getInt("quantity"));
                      v.setPrice_location(rest.getInt("price_location"));

                               v.setPhoto(rest.getString("photo"));

                   le.add(v);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;    
    }
}