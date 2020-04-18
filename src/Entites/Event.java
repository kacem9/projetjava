/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author root
 */
public class Event 
{
    int id  ;
    int categories_id ;
    String Nom ;
    Date Date_event ;
    String Description ;
    String Lieu_event ;
    String Photo;
    Double Prix ;
    int Nbr_participant ;
    int etat ;
    int User ;

    public Event() {
    }

    public Event(int id, int categories_id, String Nom, Date Date_event, String Description, String Lieu_event, String Photo, Double Prix, int Nbr_participant, int User) {
        this.id = id;
        this.categories_id = categories_id;
        this.Nom = Nom;
        this.Date_event = Date_event;
        this.Description = Description;
        this.Lieu_event = Lieu_event;
        this.Photo = Photo;
        this.Prix = Prix;
        this.Nbr_participant = Nbr_participant;
        this.User = User;
    }

   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategories_id() {
        return categories_id;
    }

    

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

 

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Date getDate_event() {
        return Date_event;
    }

    public void setDate_event(Date Date_event) {
        this.Date_event = Date_event;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLieu_event() {
        return Lieu_event;
    }

    public void setLieu_event(String Lieu_event) {
        this.Lieu_event = Lieu_event;
    }

    public Double getPrix() {
        return Prix;
    }

    public void setPrix(Double Prix) {
        this.Prix = Prix;
    }

    public int getNbr_participant() {
        return Nbr_participant;
    }

    public void setNbr_participant(int Nbr_participant) {
        this.Nbr_participant = Nbr_participant;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", categories_id=" + categories_id + ", Nom=" + Nom + ", Date_event=" + Date_event + ", Description=" + Description + ", Lieu_event=" + Lieu_event + ", Prix=" + Prix + ", Nbr_participant=" + Nbr_participant + ", etat=" + etat + ", User=" + User + '}';
    }

    
    
    
}
