/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Commande {
     private static final long serialVersionUID = 1L;

   


    private Integer id;
  
    private int id_user;
    
    private Float prixTotal;
    
    private Date dateCommande;
  private Date dateMax;

  private Integer etatLocation;

    private Integer etat_vendu;

    public Commande(Date dateCommande, Date dateMax,int id_user, Integer etatLocation, Integer etat_vendu) {
        this.id_user = id_user;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
        this.etatLocation = etatLocation;
        this.etat_vendu = etat_vendu;
    }

    public Commande(Integer id, Date dateCommande, Date dateMax) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
    }

    public Commande() {
    }

    public Commande(int id, Date dateCommande, Date dateMax) {

        this.id = id;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
    }


    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

 

    public Float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public Integer getEtatLocation() {
        return etatLocation;
    }

    public void setEtatLocation(Integer etatLocation) {
        this.etatLocation = etatLocation;
    }

    public Integer getEtat_vendu() {
        return etat_vendu;
    }

    public void setEtat_vendu(Integer etat_vendu) {
        this.etat_vendu = etat_vendu;
    }
    
    

 
}
