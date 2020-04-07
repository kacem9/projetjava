/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and openprivate the template in the editor.
 */
package entities;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author HP
 */
public class rendezvous {

private int Cin  ;
private String nom;
private String prenom  ;
private String email  ;
private String adresse ; 
private String message  ;
private String typepanne;
private int numtel;
private int user ;
private CheckBox checkbox;

    public rendezvous(String typepanne,int Cin ) {
        this.Cin = Cin;
        this.typepanne = typepanne;
    }





    public rendezvous() {
    }

    public rendezvous(int Cin, String nom, String prenom, String email, String adresse, String message, String typepanne, int numtel, int user) {
        this.Cin = Cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.message = message;
        this.typepanne = typepanne;
        this.numtel = numtel;
         this.user = user;
      
    }

    public int getCin() {
        return Cin;
    }

    public void setCin(int Cin) {
        this.Cin = Cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypepanne() {
        return typepanne;
    }

    public void setTypepanne(String typepanne) {
        this.typepanne = typepanne;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public String toString() {
        return "rendezvous{" + "typepanne=" + typepanne + ", Cin=" + Cin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.Cin;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final rendezvous other = (rendezvous) obj;
        if (this.Cin != other.Cin) {
            return false;
        }
        return true;
    }

 

}
