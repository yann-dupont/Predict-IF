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
public class Cartomancien extends Medium{
    
    public Cartomancien(){
    }
    
    public Cartomancien(String denom, String presentation, Genre genre){
        super(denom, presentation, genre);
    }
    
    @Override
    public String toString() {
        return "Cartomancien : " + super.toString();
    }
    
}
