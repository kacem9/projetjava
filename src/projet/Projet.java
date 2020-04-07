/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Connection.*;
import Service.*;
import Entites.*;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
/**
 *
 * @author root
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        MyDataBase db = MyDataBase.getInstance();
        System.out.println(db);

     //  Event e1 = new Event(1, "kacem", "aaaa", "korba", 12.0, 10);
     //  Event e2 = new Event(2, "yedes", "bbbb", "jorba", 12.0, 20);
        EventServices es =new EventServices();
        try{
             //  es.AjouterEvent(e1);
              // es.AjouterEvent(e2);
              // es.ModifierEvent(13, 2, "yedes", "bbbb", "jorba", 12.0, 20);
              es.AfficherEvents();
             //  es.SupprimerEvent(e2);
              // System.out.println(es.getEvent("kacem"));
               
        } catch ( SQLException ex){
               Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
