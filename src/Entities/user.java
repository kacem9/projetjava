/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TextField;

/**
 *
 * @author Asus
 */
public class user {

    int id;
    String Cin, Prenom, Nom, Sexe, roles, Civilite, Pays, Ville, Code_postal, Poste, Email, Username, Photo, Adresse, Num_tel, Password, username_canonical, email_canonical,
            salt, confirmation_token;
    //LocalDate Date_naissance;
    boolean enabled;
    Timestamp password_requested_at, last_login, Date_naissance;
    int tentative,etat;
    public user(String username, String username_canonical, String email, String email_canonical, Boolean enabled, String salt,
            String password, Timestamp last_login, String confirmation_token, Timestamp password_requested_at, String Roles, String Cine,
            String nom, String prenom, String sexe, Timestamp date_naissance, String num_tel, String adresse, String poste, String civilite,
            String pays, String ville, String code_postal, String selectedFile) {

        this.Username = username;
        this.username_canonical = username_canonical;
        this.Email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.Password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = Roles;
        this.Cin = Cine;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Sexe = sexe;
        this.Date_naissance = date_naissance;
        this.Num_tel = num_tel;
        this.Adresse = adresse;
        this.Poste = poste;
        this.Civilite = civilite;

        this.Ville = ville;

        this.Photo = selectedFile;
        this.Code_postal = code_postal;
        this.Pays = pays;
    }

    public user(String username, String email, String Nom, String Prenom, String Roles) {
        this.Username = username;

        this.Email = email;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.roles = Roles;

    }

    public user(int id, String username, String username_canonical, String email, String email_canonical, Boolean enabled, String salt,
            String password, String confirmation_token, Timestamp password_requested_at, String Roles, String Cine,
            String nom, String prenom, String sexe, String num_tel, String adresse, String poste, String civilite,
            String pays, String ville, String code_postal) {
        this.id = id;
        this.Username = username;
        this.username_canonical = username_canonical;
        this.Email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.Password = password;

        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = Roles;
        this.Cin = Cine;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Sexe = sexe;
        this.Num_tel = num_tel;
        this.Adresse = adresse;
        this.Poste = poste;
        this.Civilite = civilite;

        this.Ville = ville;

        this.Code_postal = code_postal;
        this.Pays = pays;
    }

    public user(int id, String Cin, String Prenom, String Nom, String Sexe, String roles, String Civilite, String Pays, String Ville, String Code_postal, String Poste, String Email, String Username, String Photo, String Adresse, String Num_tel, String Password, String username_canonical, String email_canonical, String salt, String confirmation_token, boolean enabled, Timestamp password_requested_at, Timestamp last_login, Timestamp Date_naissance) {
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
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.salt = salt;
        this.confirmation_token = confirmation_token;
        this.enabled = enabled;
        this.password_requested_at = password_requested_at;
        this.last_login = last_login;
        this.Date_naissance = Date_naissance;
    }

    public user(int id, String username, String email, String password, String role, String Nom, String Prenom) {
        this.id = id;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.roles = role;
        this.Nom = Nom;
        this.Prenom = Prenom;

    }
//constructeur de get userby id dans userservice

    public user(int id, String Username, String Password,int tentative,int etat) {
        this.id = id;
        this.Username = Username;
        this.Password = Password;
        this.tentative=tentative;
        this.etat=etat;
    }

    public user(int id, String Prenom, String Nom, String Email, String Username, String Adresse, String Num_tel, String Password, int tentative, int etat) {
        this.id = id;
        this.Prenom = Prenom;
        this.Nom = Nom;
        this.Email = Email;
        this.Username = Username;
        this.Adresse = Adresse;
        this.Num_tel = Num_tel;
        this.Password = Password;
        this.tentative = tentative;
        this.etat = etat;
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

    public Timestamp getDate_naissance() {
        return Date_naissance;
    }

    public void setDate_naissance(Timestamp Date_naissance) {
        this.Date_naissance = Date_naissance;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public user() {
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    public Timestamp getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Timestamp password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public int getTentative() {
        return tentative;
    }

    public void setTentative(int tentative) {
        this.tentative = tentative;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", Cin=" + Cin + ", Prenom=" + Prenom + ", Nom=" + Nom + ", Sexe=" + Sexe + ", roles=" + roles + ", Civilite=" + Civilite + ", Pays=" + Pays + ", Ville=" + Ville + ", Code_postal=" + Code_postal + ", Poste=" + Poste + ", Email=" + Email + ", Username=" + Username + ", Photo=" + Photo + ", Adresse=" + Adresse + ", Num_tel=" + Num_tel + ", Password=" + Password + ", username_canonical=" + username_canonical + ", email_canonical=" + email_canonical + ", salt=" + salt + ", Date_naissance=" + Date_naissance + ", last_login=" + last_login + ", password_requested_at=" + password_requested_at + ", enabled=" + enabled + ", confirmation_token=" + confirmation_token + '}';
    }

}
