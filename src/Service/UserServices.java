/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Connection.*;
import Entites.*;
import java.sql.Connection;
import java.sql.Date;
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
 * @author root
 */
public class UserServices 
{
    private Connection cnx;
    
    public UserServices() 
    {
        cnx=MyDataBase.getInstance().getCnx();
    }
    
    public void ajouterUser(Fos_User u) throws SQLException
    {
            String req = "insert into  fos_user(Cin,Nom,Prenom,Username,Ville,Sexe,Civilite,Date_naissance,Code_postal,Pays,"
                    + "Num_tel,Poste,Email,Password,Photo,roles) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            
    }
}
