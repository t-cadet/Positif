/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 *
 * @author aleconte, clagadec
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int id;
    
    protected String nom;
    
    protected String bio;
    
    protected String talent;
    
    @ManyToMany
    List <Employe> employes = new ArrayList<Employe>();

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getBio() {
        return bio;
    }

    public String getTalent() {
        return talent;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public void addEmployes(Employe employe) {
        this.employes.add(employe);
    }

    @Override
    public String toString() {
        return "Medium{" + "nom=" + nom + ", bio=" + bio + ", talent=" + talent + '}';
    }
    
    public Medium() {}
    
    public Medium(String nom, String bio, String talent) {
        this.nom = nom;
        this.bio = bio;
        this.talent = talent;
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
        final Medium other = (Medium) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
