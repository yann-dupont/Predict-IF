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
public class Spirite extends Medium{
    
    String support;
    
    public Spirite(){
    }
    
    public Spirite(String denom, String presentation, Genre genre, String support){
        super(denom, presentation, genre);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    @Override
    public String toString() {
        return "Spirite : " + super.toString() + ", support=" + support;
    }
    
}
