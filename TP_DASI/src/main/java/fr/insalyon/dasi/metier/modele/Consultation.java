/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;
import java.util.Calendar;
import javax.persistence.Entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Yumahey
 */
@Entity
public class Consultation {
    
    public static final int TERMINEE = 0;
    public static final int A_FAIRE = 1;
    public static final int EN_COURS = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @ManyToOne(cascade=CascadeType.REFRESH)
    Employe employe;
    @ManyToOne(cascade=CascadeType.REFRESH)
    Client client;
    @ManyToOne(cascade=CascadeType.REFRESH)
    Medium medium;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    int statut;
    String commentaire;
    
    public Consultation(){
    }
    
    public Consultation(Employe employe, Client client, Medium medium) {
        this.employe = employe;
        this.client = client;
        this.medium = medium;
    }

    // inutile ?
    public Consultation(Employe employe, Client client, Medium medium, int annee, int mois, int jour, int heure, int minute) {
        this.employe = employe;
        this.client = client;
        this.medium = medium;
        this.setDate(annee, mois, jour, heure, minute);
        this.statut = 0;
        this.commentaire = "";
    }

    public Long getId() {
        return id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDateNow(){
        this.date = new Date();
    }
    
    public void setDate(int annee, int mois, int jour, int heure, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(annee, mois - 1, jour, heure, minute);
        this.date = cal.getTime();
    }
    
}
