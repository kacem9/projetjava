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
import utils.Session;

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
    
    public void Participer(Event e) throws SQLException {
        
        try {
            String req="INSERT INTO participation (id_user,event) VALUES (?,?)";
            pst=cnx.prepareStatement(req);
            pst.setInt(1, Session.getUser().getId());
            pst.setInt(2, e.getId());
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("participation done");
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AnnulerParticipation(int id_user, int id_event) throws SQLException
    {
        try {
        String req = "DELETE FROM participation WHERE id_user =? AND event =?";
                        pst=cnx.prepareStatement(req);
                        pst.setInt(1,id_user);
                        pst.setInt(2,id_event);
                        pst.executeUpdate();
                        System.out.println(pst);
                        System.out.println(" done");
           } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean checkParticipation(int id_user, int id_event){
        ResultSet rs = null;

        String req="select * from participation where id_user=? and event=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id_user);
            pst.setInt(2,id_event);
           rs= pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return true;

    }
}
