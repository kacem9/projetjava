/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Connection.MyDataBase;
import Entites.Categories_event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class CategoriesServices 
{
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CategoriesServices() 
    {
        cnx=MyDataBase.getInstance().getCnx();
    }
    
    public ArrayList<Categories_event> getAllCategorie() throws SQLException {
       ArrayList<Categories_event> categories = new ArrayList<>();
        String req = "SELECT * FROM `categories__event` ";
        Statement stm = cnx.createStatement();
        ResultSet result =  stm.executeQuery(req);
        while(result.next()){
            Categories_event c = new Categories_event(result.getInt("id"), result.getString("type"));
            categories.add(c);
        }
        return categories;
    }
    
    public Categories_event getCategorieById(int id) throws SQLException {
       Categories_event categorie = new Categories_event();
        String req = "select * from `categories__event` where id="+id+";";
        Statement stm = cnx.createStatement();
        ResultSet result =  stm.executeQuery(req);
        while(result.next()){
            categorie = new Categories_event(result.getInt("id"), result.getString("type"));
        }
        return categorie;
    }
}
