/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class fos_user {
    int id; 
    String Cin,Prenom,Nom,Sexe,roles,Civilite,Pays,Ville,Code_postal,Poste,Email,Username,Photo,Adresse,Num_tel,Password;
    Date Date_naissance;

    public fos_user(int id, String Cin, String Prenom, String Nom, String Sexe, String roles, String Civilite, String Pays, String Ville, String Code_postal, String Poste, String Email, String Username, String Photo, String Adresse, String Num_tel, String Password, Date Date_naissance) {
        this.id = id;
        this.Cin = Cin;
        this.Prenom = Prenom;
        this.Nom = Nom;
        this.Sexe = Sexe;
        this.roles = roles;
        this.Civilite = Civilite;
        this.Pays = Pays;
        this.Ville = Ville;
        this.Code_postal = Code_postal;
        this.Poste = Poste;
        this.Email = Email;
        this.Username = Username;
        this.Photo = Photo;
        this.Adresse = Adresse;
        this.Num_tel = Num_tel;
        this.Password = Password;
        this.Date_naissance = Date_naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String Cin) {
        this.Cin = Cin;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCivilite() {
        return Civilite;
    }

    public void setCivilite(String Civilite) {
        this.Civilite = Civilite;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public String getCode_postal() {
        return Code_postal;
    }

    public void setCode_postal(String Code_postal) {
        this.Code_postal = Code_postal;
    }

    public String getPoste() {
        return Poste;
    }

    public void setPoste(String Poste) {
        this.Poste = Poste;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getNum_tel() {
        return Num_tel;
    }

    public void setNum_tel(String Num_tel) {
        this.Num_tel = Num_tel;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getDate_naissance() {
        return Date_naissance;
    }

    public void setDate_naissance(Date Date_naissance) {
        this.Date_naissance = Date_naissance;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", Cin=" + Cin + ", Prenom=" + Prenom + ", Nom=" + Nom + ", Sexe=" + Sexe + ", roles=" + roles + ", Civilite=" + Civilite + ", Pays=" + Pays + ", Ville=" + Ville + ", Code_postal=" + Code_postal + ", Poste=" + Poste + ", Email=" + Email + ", Username=" + Username + ", Photo=" + Photo + ", Adresse=" + Adresse + ", Num_tel=" + Num_tel + ", Password=" + Password + ", Date_naissance=" + Date_naissance + '}';
    }

         
    
    
    

    
}
