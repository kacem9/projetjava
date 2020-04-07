/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.Produit;
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
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class ServiceCommande  {
private Connection connection=utils.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Commande> TriAll() {
        ArrayList<Commande> list =  new ArrayList<Commande>();
        String req="select * from Commande";
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int id=rs.getInt("id");
               Date dateCommande= rs.getDate("dateCommande");
               Date dateMax =rs.getDate("dateMax");
               int id_user =rs.getInt("id_user");
               int etat_vendu =rs.getInt("etat_vendu");
               int etatLocation =rs.getInt("etatLocation");
            
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Commande s = new Commande( id,dateCommande, dateMax ,id_user,etat_vendu,etatLocation);
               s.setId(id);
               list.add(s);
              
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return list; 
    }
    public ObservableList <Commande> FindAll() {
        ObservableList <Commande> list =  FXCollections.observableArrayList();
        String req="select * from Commande";
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int id=rs.getInt("id");
               Date dateCommande= rs.getDate("dateCommande");
               Date dateMax =rs.getDate("dateMax");
               int id_user =rs.getInt("id_user");
               int etat_vendu =rs.getInt("etat_vendu");
               int etatLocation =rs.getInt("etatLocation");
            
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Commande s = new Commande( id,dateCommande, dateMax ,id_user,etat_vendu,etatLocation);
               s.setId(id);
               list.add(s);
              
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }  list.stream().forEach(System.out::println);
        return  list; 
    }
    public void delete(Commande t) {
        String req="delete from Commande where id = '"+t.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
              System.out.println("erreur");
        }  
    }

    public ObservableList<Commande> listerRecherche(String text) {
        ObservableList<Commande> list =  (ObservableList<Commande>) new ArrayList<Commande>();
        String req="select * from Commande";
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int id=rs.getInt("id");
               Date dateCommande= rs.getDate("dateCommande");
               Date dateMax =rs.getDate("dateMax");
               int id_user =rs.getInt("id_user");
               int etat_vendu =rs.getInt("etat_vendu");
               int etatLocation =rs.getInt("etatLocation");
            
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Commande s = new Commande( id,dateCommande, dateMax ,id_user,etat_vendu,etatLocation);
               s.setId(id);
               list.add(s);
              
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return list; 
    }

  
    public void remove(Commande T) {
    String req="delete from Commande where id = '"+T.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }


    public void Modif(Commande T) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
