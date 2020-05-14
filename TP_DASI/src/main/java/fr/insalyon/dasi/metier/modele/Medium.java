/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import fr.insalyon.dasi.metier.modele.Statut;

/**
 *
 * @author Yumahey
 */
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected String denom;
    protected String presentation;
    protected Genre genre;
    protected Statut statut;
    
    protected Medium(){
    }
    
    protected Medium(String denom, String presentation, Genre genre){
        this.denom = denom;
        this.presentation = presentation;
        this.genre = genre;
        this.statut = Statut.Dispo;
    }
    
    public Long getId() {
        return id;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    
    @Override
    public String toString() {
        return  genre + " : id=" + id + ", denom=" + denom + ", presentation=" + presentation + ", statut=" + statut;
    }
    
}
