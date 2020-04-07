/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;

/**
 *
 * @author HP
 */
public class validrendezvous {
    
private String emailR  ;
private String dateheure  ;
private String prix  ;
private String promo ; 
private String etat;  
private String message;  
private int reference;  
private int user ;

    public validrendezvous() {
    }

    public validrendezvous(String emailR, String dateheure, String prix, String promo, String etat, String message, int reference, int user) {
        this.emailR = emailR;
        this.dateheure = dateheure;
        this.prix = prix;
        this.promo = promo;
        this.etat = etat;
        this.message = message;
        this.reference = reference;
        this.user = user;
    }

    public String getEmailR() {
        return emailR;
    }

    public void setEmailR(String emailR) {
        this.emailR = emailR;
    }

    public String getDateheure() {
        return dateheure;
    }

    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setDateheure(LocalDate dateheure) {
         dateheure = dateheure;
    }









}
