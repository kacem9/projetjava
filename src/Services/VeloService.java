/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Velo;
import Entities.Categories;

import Utils.ConnectionDB;
import java.io.File;
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

public class VeloService {
    private Connection connexion;

    public VeloService() {
    connexion = ConnectionDB.getinstance().getCnx();

       }
  
    public void addBike(String Quantity,LocalDate date_circulation, String localitsation_velo, String Description, String price_location,Velo v ,File selectedFile) throws SQLException {

      String req="INSERT INTO Velo (id,quantity,etat_vendu,etat_location,datePublication,date_circulation,description,localitsation_velo,photo,price_location) VALUES (?, ?,?, ?,?,?,?,?,?, ?)" ;
        
       
     try{
         PreparedStatement ps1 = connexion.prepareStatement(req);
     
            ps1.setInt(1, v.getId());
            ps1.setInt(2, Integer.valueOf(Quantity));
            ps1.setInt(3, Integer.valueOf(0));
            ps1.setInt(4, Integer.valueOf(1));
     
            ps1.setDate(6 ,Date.valueOf(date_circulation));
            ps1.setDate(5 ,new Date(System.currentTimeMillis())); 

            ps1.setString(7, Description);
            ps1.setString(8, localitsation_velo);
            ps1.setString(9 ,selectedFile.getPath());
            
            ps1.setFloat(10, Float.valueOf(price_location));    
            
          
            ps1.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null , ex);
        } 
         
}
       public void updateBike(Velo v1, File selectedFile) throws SQLException {
            String sql = "UPDATE velo SET quantity=?,description=?,localitsation_velo=?,etat_location=?,etat_vendu=?,date_circulation=?,datePublication=?,photo=? WHERE id=?";

PreparedStatement statement = connexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{
           statement.setInt(1, Integer.valueOf(v1.getQuantity()));

           
       

            statement.setString(2 ,v1.getDescription());
            statement.setString(3 ,v1.getLocalitsation_velo());
       
               statement.setInt(4, 1);
              
            statement.setInt(5, 2);
   
 statement.setInt(9 ,v1.getId());
             statement.setDate(6, (Date) v1.getDate_circulation());
            statement.setDate(7 ,new Date(System.currentTimeMillis())); 

            statement.setString(8 ,selectedFile.getPath());
          
   statement.executeUpdate();
    System.out.println("Updated");
    
    }
catch (Exception ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
   
       
         

}}
    public List<Velo> readAll()  {
        List<Velo> evs =new ArrayList<>();
        try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from velo v where etat_vendu=0 and etat_location=1 ");
            while(rest.next()){
                Velo ev = new Velo();
                ev.setId(rest.getInt("id"));
                    ev.setDate_circulation(rest.getDate("date_circulation"));

              ev.setDatePublication(rest.getDate("datePublication"));
                              ev.setDescription(rest.getString("description"));
                ev.setLocalitsation_velo(rest.getString("localitsation_velo"));
                                ev.setPhoto(rest.getString("photo"));

           ev.setQuantity(rest.getInt("quantity"));
                      ev.setPrice_location(rest.getFloat("price_location"));

                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
         return evs;
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
}