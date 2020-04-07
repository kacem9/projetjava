/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Commande {
    private int id;
    private Date dateCommande;
    private Date dateMax;
    private int id_user;
    private int etat_vendu;
    private int etatLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getEtat_vendu() {
        return etat_vendu;
    }

    public void setEtat_vendu(int etat_vendu) {
        this.etat_vendu = etat_vendu;
    }

    public int getEtatLocation() {
        return etatLocation;
    }

    public void setEtatLocation(int etatLocation) {
        this.etatLocation = etatLocation;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", dateCommande=" + dateCommande + ", dateMax=" + dateMax + ", id_user=" + id_user + ", etat_vendu=" + etat_vendu + ", etatLocation=" + etatLocation + '}';
    }

    public Commande(Date dateCommande, Date dateMax, int id_user, int etat_vendu, int etatLocation) {
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
        this.id_user = id_user;
        this.etat_vendu = etat_vendu;
        this.etatLocation = etatLocation;
    }

    public Commande(int id, Date dateCommande, Date dateMax, int id_user, int etat_vendu, int etatLocation) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
        this.id_user = id_user;
        this.etat_vendu = etat_vendu;
        this.etatLocation = etatLocation;
    }
    
    
    
}
