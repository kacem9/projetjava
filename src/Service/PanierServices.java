/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import entities.Velo;
import entities.ligne_de_commande;
import utils.Session;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class PanierServices {
  private Map<Integer, Integer> p;
   private Velo               v;
 ProductServices               ps  = new ProductServices();

    private int                   cid;
    private Velo               produit;

        Integer prix;
CommandeService cs=new CommandeService();
LigneCommandeServices lcs=new LigneCommandeServices();
        
    public Integer getPrixPanier() {
        Integer prix;

        prix = 0;
        p    = Session.getInstance(Session.getUser()).getPanier();
        System.out.println(p);
        prix = p.keySet().stream().map((key) -> {
            produit = ps.getP(key);
            System.out.println(produit);
                return key;
            })  .map((key) -> produit.getPrice_location()* p.get(key)).reduce(prix,Integer::sum);

        return prix;
    }

    public int validerPanier(String adresse, String numTel,String ville) throws SQLException {
        System.out.println( Session.getUser().getId());
      p    = Session.getInstance(Session.getUser()).getPanier();
      //  System.out.println(p);
        if (p.isEmpty()) {
            return -1;
        }
        Commande c = new Commande(new Date(System.currentTimeMillis()),
                         new Date(System.currentTimeMillis()+3),
                         Session.getUser().getId(),
                          1,
                         0);
                         
        cid = cs.create(c);
      //  System.out.println(cid);
        c   = cs.FindOne(cid);
        System.out.println(c);
      /*  for (Integer key : p.keySet()) {
            produit = ps.getP(key);
                    System.out.println(p);

            ligne_de_commande lc  = new ligne_de_commande(
                                       adresse,
                                       numTel,
                                         ville,
                                               new Date(System.currentTimeMillis()),
                                          p.get(key),
                                     
                                        (double) produit.getPrice_location()* p.get(key),
                                        produit.getId(),
                                        cid);
            
            lcs.create(lc);
        }

     
*/
        return 1;
    }



}


