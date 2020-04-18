/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import connection.ConnectionDB;
import entities.user;
import utils.Session;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class CommandeService {
          private Connection connexion;

    public CommandeService() {
    connexion = ConnectionDB.getinstance().getCnx();

       }
    public Commande FindOne(int id) {
        Commande c = null;

        try {
            String    select    = "SELECT * FROM Commande WHERE  id = '" + id + "' ";
            Statement statement = connexion.createStatement();
            ResultSet result    = statement.executeQuery(select);

            if (result.next()) {
                c = new Commande();
                c.setId(id);
                c.setId_user(result.getInt("id_user"));
                c.setDateCommande(result.getDate("dateCommande"));
                c.setDateMax(result.getDate("dateMax"));
               
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }


    public int create(Commande e) {
        try {
            String req =
                "INSERT INTO commande (id_user,dateCommande,dateMax,etatLocation,etat_vendu) VALUES (?,?,?,?,?)";
            PreparedStatement st = connexion.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, Session.getInstance().getUser().getId());
            st.setDate(2, (Date) e.getDateCommande());
            st.setDate(3, (Date) e.getDateMax());
          

            st.setInt(4, 0);
            st.setInt(5, 1);
            st.execute();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                System.out.println(rs.getLong(1));
                System.out.println("hii"); 
               return (int) rs.getLong(1);
            }

            return -1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return -1;
        }
    }

  
    public void delete(int id) {
        try {
            String            req = "DELETE FROM commande WHERE commande.id = ? ";
            PreparedStatement st  = connexion.prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("deleted ");
  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void supp(int c) {

        try {
            String req1 = "DELETE FROM ligne_de_commande WHERE id_commande = ? ";
            PreparedStatement st  = connexion.prepareStatement(req1);

            st.setInt(1,c);
        
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<Commande> findAll() {
        ObservableList<Commande> cs = FXCollections.observableArrayList();

        try {
            String            req = "select * from commande";
            PreparedStatement ps  = connexion.prepareStatement(req);
            ResultSet         rs  = ps.executeQuery();

            while (rs.next()) {
                Commande c = new Commande(rs.getInt("id"),
                                         
                                           rs.getDate("dateCommande")
                                         , rs.getDate("datemax"));
                                          
                cs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cs;
    }

 
}
