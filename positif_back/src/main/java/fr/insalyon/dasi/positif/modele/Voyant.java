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
public class Voyant extends Medium {
    
    protected String support;

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "Voyant{" + "id=" + id + ", nom=" + nom  + ", talent=" + talent + ", support=" + support + ", bio=" + bio +'}';
    }
    
    public Voyant() {}

    public Voyant(String nom, String talent, String support, String bio) {
        super(nom, bio, talent);
        this.support = support;
    }
    
}
