/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.fos_user;
import Utils.ConnectionDB;
/**
 *
 * @author user
 */import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This program demonstrates how to upload files to a web server
 * using HTTP POST request without any HTML form.
 * @author www.codejava.net
 *
 */
public class UserService {
    private Connection c;

    public UserService() {
        c = ConnectionDB.getinstance().getCnx();
    }
    public void ajouterUser(fos_user u) throws SQLException{
    
       
            String req = "insert into  fos_user(Cin,Nom,Prenom,Username,Ville,Sexe,Civilite,Date_naissance,Code_postal,Pays,"
                    + "Num_tel,Poste,Email,Password,Photo,roles) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             Statement stm = c.createStatement();
        stm.executeUpdate(req);
            
         
       
    
    }
    
    
}