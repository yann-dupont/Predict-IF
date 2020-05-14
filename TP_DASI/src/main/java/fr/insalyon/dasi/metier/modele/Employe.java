package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Employe extends Utilisateur implements Serializable {

    private Genre genre;
    private Statut statut;
    
    protected Employe() {
    }
    
    public Employe(String nom, String prenom, String mail, String motDePasse, String tel, Genre genre) {
        super(nom, prenom, mail, motDePasse, tel);
        this.genre = genre;
        this.statut = Statut.Dispo;
    }

    public Genre getGenre(){
        return this.genre;
    }
    
    public void setGenre(Genre genre){
        this.genre = genre;
    }

    public Statut getStatut(){
        return this.statut;
    }
    
    public void setStatut(Statut statut){
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Employe " + this.genre + " : " + super.toString();
    }
    
}
