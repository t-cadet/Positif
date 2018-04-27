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
public class Astrologue extends Medium {
    
    protected String ecole;
    
    protected String promotion;

    public String getEcole() {
        return ecole;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" + "id=" + id + ", nom=" + nom  + ", talent=" + talent + ", ecole=" + ecole + ", promotion=" + promotion + ", bio=" + bio +'}';
    }
    
    public Astrologue() {}

    public Astrologue(String nom, String talent, String ecole, String promotion, String bio) {
        super(nom, bio, talent);
        this.ecole = ecole;
        this.promotion = promotion;
    }
    
}
