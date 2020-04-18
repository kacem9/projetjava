/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Categories;
import connection.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class CategoriesService {
      private Connection connexion;

    public CategoriesService() {
    connexion = ConnectionDB.getinstance().getCnx();

       }
    public void addCategorie(String Nom, String Age) {
   
          String req="INSERT INTO Categories (Nom,Age) VALUES (?,?)" ;
        
       
     try{
         PreparedStatement ps1 = connexion.prepareStatement(req);
     
          
            ps1.setString(1, Nom);
            ps1.setString(2, Age);
         
            
          
            ps1.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null , ex);
        } 
         
           
}  
    public List<Categories> readAll()  {
        List<Categories> evs =new ArrayList<>();
        try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from Categories v  ");
            while(rest.next()){
                Categories ev = new Categories();
                ev.setId(rest.getInt("id"));

                              ev.setNom(rest.getString("Nom"));
                ev.setAge(rest.getString("Age"));
                                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
         return evs;
    }
    
    
    
    
    
    
    

    public void delete(Categories c) {
               try { 
            String delete = "DELETE FROM Categories WHERE id = ? ";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        int id = c.getId();
        
        st2.setInt(1,id);
 


        st2.executeUpdate();
       

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void UpdateCategorie(Categories c) throws SQLException {

String sql = "UPDATE categories SET Nom=?,Age=? WHERE id=?";

PreparedStatement statement = connexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        
       
try{
           
       

            statement.setString(1 ,c.getNom());
            statement.setString(2 ,c.getAge());
       
 statement.setInt(3 ,c.getId());
        
          
   statement.executeUpdate();
    System.out.println("Updated");
    
    }
catch (Exception ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
   
       
         

    
    
}}
    public List<Categories> readNom()
    {
        List<Categories> evs =new ArrayList<>();
        try {
            Statement stm = connexion.createStatement();
            ResultSet rest= 
                    stm.executeQuery("select Nom from categories  ");
            while(rest.next()){
                Categories ev = new Categories();
         

                              ev.setNom(rest.getString("nom"));
             
                                
                evs.add(ev);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return evs;
}
    public ArrayList<Categories> getAllCategorie() throws SQLException {
       ArrayList<Categories> categories = new ArrayList<>();
        
        String req = "select * from categories";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Categories c = new Categories(result.getInt("id"),result.getString("Nom"));
            categories.add(c);
        }
        
        return categories;
    }
    
    public Categories getCategorieById(int id) throws SQLException {
       Categories categorie = new Categories();
        
        String req = "select * from categories where id="+id+";";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            categorie = new Categories(result.getInt("id"), result.getString("Nom"));
            
        }
        
        return categorie;
    }
    
    
}