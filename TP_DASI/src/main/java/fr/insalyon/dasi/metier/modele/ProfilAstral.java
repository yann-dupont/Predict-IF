/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author emman
 */

@Entity
public class ProfilAstral implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String zodiaque;
    private String sgChinois;
    private String couleur;
    private String animal;
    
    public ProfilAstral(){
    }
    
    public ProfilAstral(String zodiaque, String sgChinois, String couleur, String animal){
        this.zodiaque = zodiaque;
        this.sgChinois = sgChinois;
        this.couleur = couleur;
        this.animal = animal;
    }

    public String getZodiaque() {
        return zodiaque;
    }

    public void setZodiaque(String zodiaque) {
        this.zodiaque = zodiaque;
    }

    public String getSgChinois() {
        return sgChinois;
    }

    public void setSgChinois(String sgChinois) {
        this.sgChinois = sgChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
    @Override
    public String toString() {
        return "ProfilAstral{" + "id=" + id + ", zodiaque=" + zodiaque 
                + ", sgChinois=" + sgChinois + ", couleur=" + couleur 
                + ", animal=" + animal + '}';
    }
    
}
