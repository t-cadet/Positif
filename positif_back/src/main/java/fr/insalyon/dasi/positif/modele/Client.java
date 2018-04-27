/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aleconte, clagadec
 */
@Entity
public class Client {
    
    protected String nom;
    
    protected String prenom;
    
    @Temporal(TemporalType.DATE)
    protected Date dateNaissance;
    
    protected String civilite;
    
    protected String adressePostale;
    
    protected String numTelephone;
    
    protected String email;
    
    protected String signeZodiaque;
    
    protected String signeAstroChinois;
    
    protected String couleurPorteBonheur;
    
    protected String animalTotem;
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int numClient;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    public String getCivilite() {
        return civilite;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getEmail() {
        return email;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneAstroChinois() {
        return signeAstroChinois;
    }

    public String getCouleurPorteBonheur() {
        return couleurPorteBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneAstroChinois(String signeAstroChinois) {
        this.signeAstroChinois = signeAstroChinois;
    }

    public void setCouleurPorteBonheur(String couleurPorteBonheur) {
        this.couleurPorteBonheur = couleurPorteBonheur;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
                + ", civilite=" + civilite + ", adressePostale=" + adressePostale
                + ", numTelephone=" + numTelephone + ", email=" + email + ", signeZodiaque=" + signeZodiaque
                + ", signeAstroChinois=" + signeAstroChinois + ", couleurPorteBonheur=" + couleurPorteBonheur
                + ", animalTotem=" + animalTotem + ", numClient=" + numClient + '}';
    }
    
    public Client() {}

    public Client(String nom, String prenom, Date dateNaissance, String civilite, String adressePostale, String numTelephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.civilite = civilite;
        this.adressePostale = adressePostale;
        this.numTelephone = numTelephone;
        this.email = email;
    }

    public Client(String email, int numClient) {
        this.email = email;
        this.numClient = numClient;
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
        final Client other = (Client) obj;
        if (this.numClient != other.numClient) {
            return false;
        }
        return true;
    }
    
    
    
}
