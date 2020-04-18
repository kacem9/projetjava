/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author HP   adresse2 	ville
 */
public class ligne_de_commande {
    
    private static final long serialVersionUID = 1L;
     private Integer id;
        private Integer id_commande;
     private Integer id_velo;

    private Integer quantite;

    private Double prixTotal;
    
    private String numTel	;
    private Date dateLivraison;
    private String adresse2;
    private String ville;   
    private String adresse;
    private int etatLocation,etat_vendu;

    public ligne_de_commande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getEtatLocation() {
        return etatLocation;
    }

    public void setEtatLocation(int etatLocation) {
        this.etatLocation = etatLocation;
    }

    public int getEtat_vendu() {
        return etat_vendu;
    }

    public void setEtat_vendu(int etat_vendu) {
        this.etat_vendu = etat_vendu;
    }
    
   

    public ligne_de_commande( String adresse ,String numTel ,String ville ,Date dateLivraison,Integer quantite,Double prixTotal,Integer id_velo,Integer id_commande) {
        this.id_commande = id_commande;
        this.id_velo = id_velo;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.numTel = numTel;
        this.dateLivraison = dateLivraison;
        this.ville = ville;
        this.adresse = adresse;
    }

    public ligne_de_commande(Integer id, Integer id_commande) {
        this.id = id;
        this.id_commande = id_commande;
    }

    public ligne_de_commande(Integer id, Integer id_velo, Integer quantite) {
        this.id = id;
        this.id_velo = id_velo;
        this.quantite = quantite;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_commande() {
        return id_commande;
    }

    public void setId_commande(Integer id_commande) {
        this.id_commande = id_commande;
    }

    public Integer getId_velo() {
        return id_velo;
    }

    public void setId_velo(Integer id_velo) {
        this.id_velo = id_velo;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }



    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
        
    
}
