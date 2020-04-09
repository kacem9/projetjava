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
public class EventServices 
{
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public EventServices() 
    {
        cnx=MyDataBase.getInstance().getCnx();
    }
    
    public void AjouterEvent(Event e) throws SQLException 
    {
        try {
            String req="INSERT INTO event (categories_id,Nom,Date_event,Description,Lieu_event,Prix,Nbr_participant,Photo,etat) VALUES (?,?,?,?,?,?,?,?,?)";
            pst=cnx.prepareStatement(req);
            pst.setInt(1, e.getCategories_id());
            pst.setString(2,e.getNom());
            pst.setDate(3,e.getDate_event());
            pst.setString(4,e.getDescription());
            pst.setString(5,e.getLieu_event());
            pst.setDouble(6, e.getPrix());
            pst.setInt(7, e.getNbr_participant());
            pst.setString(8, e.getPhoto());
            pst.setInt(9, e.getEtat());  //
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ModifierEvent(Event e) throws SQLException
    {
       
           String sql = "UPDATE event SET categories_id=?,Nom=?,Date_event=?,Description=?,Lieu_event=?,Prix=?,Nbr_participant=?,Photo=?,etat=? WHERE id=?";
           PreparedStatement statement = cnx.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
          try {
              statement.setInt(1, Integer.valueOf(e.getCategories_id()));
              statement.setString(2, e.getNom());
              statement.setDate(3, e.getDate_event());
              statement.setString(4, e.getDescription());
              statement.setString(5, e.getLieu_event());
              statement.setDouble(6, e.getPrix());
              statement.setInt(7, e.getNbr_participant());
              statement.setString(8, e.getPhoto());
              statement.setInt(9, e.getEtat());  //
              statement.setInt(10, e.getId());
              System.out.println(statement);
              statement.executeUpdate();
              System.out.println("update done");
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void SupprimerEvent(Event e) throws SQLException 
    {

        String req = "DELETE FROM event WHERE Nom =?";
                        pst=cnx.prepareStatement(req);
                        pst.setString(1,e.getNom());
                        pst.executeUpdate();

    }
    
    public Event getEvent(String Nom) throws SQLException{
        Event e=new Event();
        try {
            String req="SELECT * FROM event where Nom='"+Nom+"'";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {
            e.setCategories_id(rs.getInt("Categories_id"));
            e.setNom(rs.getString("Nom"));
            e.setDate_event(rs.getDate("Date_event"));
            e.setDescription(rs.getString("description"));
            e.setLieu_event(rs.getString("Lieu_event"));
            e.setPhoto(rs.getString("Photo"));
            e.setPrix(rs.getDouble("Prix"));
            e.setNbr_participant(rs.getInt("Nbr_participant"));
            e.setEtat(rs.getInt("etat"));  //
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    public List<Event> AfficherEvents() throws SQLException {
        List<Event> l = new ArrayList<>();
        try {
            String req="SELECT * FROM event";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {

                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setCategories_id(rs.getInt("Categories_id"));
                e.setNom(rs.getString("Nom"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setDescription(rs.getString("description"));
                e.setLieu_event(rs.getString("Lieu_event"));
                e.setPrix(rs.getDouble("Prix"));
                e.setPhoto(rs.getString("Photo"));
                e.setNbr_participant(rs.getInt("Nbr_participant"));
                e.setEtat(rs.getInt("etat"));  //
                l.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public List<Event> AfficherAcceptedEvents() throws SQLException {
        List<Event> l = new ArrayList<>();
        try {
            String req="SELECT * FROM event where etat = 1";
            stm=cnx.createStatement();
            rs=stm.executeQuery(req);
            while(rs.next())
            {

                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setCategories_id(rs.getInt("Categories_id"));
                e.setNom(rs.getString("Nom"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setDescription(rs.getString("description"));
                e.setLieu_event(rs.getString("Lieu_event"));
                e.setPrix(rs.getDouble("Prix"));
                e.setPhoto(rs.getString("Photo"));
                e.setNbr_participant(rs.getInt("Nbr_participant"));
                e.setEtat(rs.getInt("etat"));  //
                l.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public void ValiderEvent(int id,int etat ) throws SQLException
    {
        String sql = "UPDATE event SET `etat`='"+etat+ "' WHERE id='"+id+"' ";
           pst= cnx.prepareStatement(sql);
             try {
              
              pst.executeUpdate();
              System.out.println("update done");
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}
