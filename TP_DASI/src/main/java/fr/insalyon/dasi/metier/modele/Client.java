package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.util.AstroTest;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client extends Utilisateur implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    private ProfilAstral profil;
    
    protected Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse) {
        super(nom, prenom, mail, motDePasse, "06 00 00 00 00");
        this.setDateNaissance(1999, 1, 1);
        obtenirPA();
    }

    
    public Client(String nom, String prenom, String mail, String motDePasse, String tel, int jour, int mois, int annee) {
        super(nom, prenom, mail, motDePasse, tel);
        this.setDateNaissance(annee, mois, jour);
        obtenirPA();
    }
    
    public void obtenirPA(){  
        try{
            AstroTest astro = new AstroTest();
            List<String> res = astro.getProfil(this.getPrenom(), this.getDateNaissance());

            profil = new ProfilAstral(res.get(0), res.get(1), res.get(2), res.get(3));
        }catch(Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel à la méthode obtenirPA() sur le client :\n"+this.toString()+"\n", e);
            profil = null;
        }
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public void setDateNaissance(int annee, int mois, int jour) {
        Calendar cal = Calendar.getInstance();
        cal.set(annee, mois - 1, jour);
        this.dateNaissance = cal.getTime();
    }

    public ProfilAstral getProfil() {
        return profil;
    }

    @Override
    public String toString() {
        return "Client : " + super.toString() + ", ne(e) le : " + dateNaissance.toString();
    }
    
}
