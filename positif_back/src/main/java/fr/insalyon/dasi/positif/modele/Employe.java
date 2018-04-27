/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aleconte, clagadec
 */
@Entity
public class Employe {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int numEmploye;
    
    protected String nom;
    
    protected String prenom;
    
    protected String numTelephone;

    public int getNumEmploye() {
        return numEmploye;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    @Override
    public String toString() {
        return "Employe{" + "numEmploye=" + numEmploye + ", nom=" + nom + ", prenom=" + prenom + ", numTelephone=" + numTelephone + '}';
    }
    
    
    public Employe() {}

    public Employe(String nom, String prenom, String numTelephone) {
        this.numTelephone = numTelephone;
        this.nom = nom;
        this.prenom = prenom;
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
        final Employe other = (Employe) obj;
        if (this.numEmploye != other.numEmploye) {
            return false;
        }
        return true;
    }
    
}
