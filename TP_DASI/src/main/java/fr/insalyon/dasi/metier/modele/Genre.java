/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

/**
 *
 * @author Yumahey
 */

public enum Genre {
    H("H"), F("F"), A("A");
    
    private String val = "";
   
    //Constructeur
    Genre(String val){
        this.val = val;
    }
  
    public String toString(){
        return val;
    }
};
