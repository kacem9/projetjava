/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Connection.MyDataBase;
import Entites.Event;
import Entites.Fos_User;
import Entites.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ParticipationServices 
{
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ParticipationServices() 
    {
        cnx=MyDataBase.getInstance().getCnx();
    }
    
    public void Participer(Fos_User u,Event e) throws SQLException {
        
        try {
            String req="INSERT INTO participation (id_user,event) VALUES (?,?)";
            pst=cnx.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.setInt(2, e.getId());
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("participation done");
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AnnulerParticipation(Participation p, Fos_User u) throws SQLException 
    {
        try {
        String req = "DELETE FROM participation WHERE id_participation =? ";
                        pst=cnx.prepareStatement(req);
                        pst.setInt(1,p.getId_participation());
                        pst.executeUpdate();
                        System.out.println(pst);
                        System.out.println(" done");
           } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
