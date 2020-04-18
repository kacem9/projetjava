
package Entities;

public class reclamation {
    private int reference;
    private String nom;
    private String prenom ;
  
    private String email;
    private String  sujet ;
    private String message ;
    private String adresse ;
    private int numtel;
    private int user;

    public reclamation() {
    }

    public reclamation(int reference, String nom, String prenom, String email, String sujet, String message, String adresse, int numtel, int user) {
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.adresse = adresse;
        this.numtel = numtel;
        this.user = user;
    }


    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
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

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

   
    
            
    
}
