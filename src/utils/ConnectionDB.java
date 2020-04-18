/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConnectionDB {
    
    private static String url = "jdbc:mysql://localhost:3306/Velo"; 
    private static String user = "root"; 
    private static String passwd = ""; 
    
    private static Connection cnx; 
    
    private static ConnectionDB cdb; 

    public Connection getCnx() {
        return cnx;
    }
    
     private ConnectionDB() {
         
        try {
            cnx = DriverManager.getConnection(url,user,passwd);
            System.out.println("Connextion cv");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
     
    public static ConnectionDB getinstance(){
    
    if (cdb==null){
        cdb = new ConnectionDB();
    
    }
    
    return cdb;
    
    }

    
    

    
    
    
    
    
    
}
