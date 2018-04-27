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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aleconte, clagadec
 */
@Entity
public class Voyance {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date debut;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fin;
    
    protected String commentaire;
    
    @ManyToOne
    protected Client client;

    @ManyToOne
    protected Medium medium;
    
    @ManyToOne
    protected Employe employe;
    
    // Méthode public

    public int getId() {
        return id;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Medium getMedium() {
        return medium;
    }

    public Employe getEmploye() {
        return employe;
    }
    
    
    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "Voyance{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + ", commentaire=" + commentaire + ", client=" + client + ", medium=" + medium + ", employe=" + employe + '}';
    }
    
    
    public Voyance() {
    }

    public Voyance(Date debut, Date fin, String commentaire, Client client, Medium medium, Employe employe) {
        this.debut = debut;
        this.fin = fin;
        this.commentaire = commentaire;
        this.client = client;
        this.medium = medium;
        this.employe = employe;
    }

    public Voyance(Client client, Medium medium, Employe employe) {
        this.client = client;
        this.medium = medium;
        this.employe = employe;
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
        final Voyance other = (Voyance) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
