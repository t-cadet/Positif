/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.modele;

import javax.persistence.Entity;

/**
 *
 * @author aleconte, clagadec
 */
@Entity
public class Tarologue extends Medium {
    
    protected String cartes;

    public String getCartes() {
        return cartes;
    }

    public void setCartes(String cartes) {
        this.cartes = cartes;
    }

    @Override
    public String toString() {
        return "Tarologue{" + "id=" + id + ", nom=" + nom  + ", talent=" + talent + ", cartes=" + cartes + ", bio=" + bio +'}';
    }

    public Tarologue() {}

    public Tarologue(String nom, String talent, String cartes, String bio) {
        super(nom, bio, talent);
        this.cartes = cartes;
    }
    
    
}
