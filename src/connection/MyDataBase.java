/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection ;

/**
 *
 * @author root
 */
public class MyDataBase 
{
    private String url="jdbc:mysql://localhost:3306/velo";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyDataBase instance;
    
    private MyDataBase(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
           
        }
     }
    public static MyDataBase getInstance(){
     if(instance==null)
         instance=new MyDataBase();
     return instance;
   } 
   public Connection getCnx() {
        return cnx;
    }
}
