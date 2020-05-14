/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;
import fr.insalyon.dasi.metier.modele.Medium;
import javax.persistence.Entity;

/**
 *
 * @author Yumahey
 */
@Entity
public class Astrologue extends Medium{
    
    String formation;
    int promo;
    
    public Astrologue(){
    }
    
    public Astrologue(String denom, String presentation, Genre genre, String formation, int promo){
        super(denom, presentation, genre);
        this.formation = formation;
        this.promo = promo;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }
    
    @Override
    public String toString() {
        return "Astrologue : " + super.toString() + ", formation=" + formation + ", promo=" + promo;
    }
    
}
