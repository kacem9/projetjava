/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.ligne_de_commande;
import connection.ConnectionDB;
import entities.Velo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class LigneCommandeServices {
    
            private Connection connexion;

    public LigneCommandeServices() {
    connexion = ConnectionDB.getinstance().getCnx();

    }
    public void create(ligne_de_commande e) {
                   try {
                       String req = "INSERT INTO ligne_de_commande (id_commande,id_velo,prixTotal,quantite,adresse,ville,numTel,dateLivraison,etatLocation,etat_vendu) VALUES (?,?,?,?,?,?,?,?,?,?)";
                       System.out.println(e.getId_commande());
                       PreparedStatement st = connexion.prepareStatement(req);
                            st.setInt(1, e.getId_commande());
                            st.setInt(2, e.getId_velo());
                            st.setDouble(3, e.getPrixTotal());
                            st.setInt(4, e.getQuantite());   
                              st.setString(5, e.getAdresse());         

                               st.setString(6, e.getVille());         

                               st.setString(7, e.getNumTel());         
st.setDate(8,          (Date) e.getDateLivraison());
                                                    st.setInt(9,1);
                            st.setInt(10,0);

                            st.execute();
                        System.out.println("done");
                   } catch (SQLException ex) {
                       System.out.println(ex.getMessage());
                   }
               }
            
             public List<ligne_de_commande> findAll(int id) {
             List<ligne_de_commande> lcs=new ArrayList<>();

            try {
                String req = "select * from ligne_de_commande where ligne_de_commande.id_commande=?";
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ligne_de_commande lc = new ligne_de_commande(rs.getInt("id"),
                        
                    rs.getInt("id_commande"))
                            ;

                    lcs.add(lc);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());            }
            return lcs;

             }
}
/*
             public void FindOne(int id) {
                    ligne_de_commande lc = null;

                    try {
                        String select = "SELECT id_velo FROM ligne_de_commande WHERE  id = '" + id + "' ";
                        Statement statement = connexion.createStatement();
                        ResultSet rs = statement.executeQuery(select);

            while (rs.next()) {
            int res1=rs.getInt(id);      
            }
            PreparedStatement pt3 = connexion.prepareStatement("update Velo set quantity=?,  where id_Velo=+res1");
            pt3.setInt(1, msgEdit);
            
            pt3.executeUpdate();
                 
                    } catch (SQLException ex) {
                       System.out.println(ex.getMessage());
                    }
                    return lc;
                }

}*/
