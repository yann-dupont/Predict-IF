/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class ContacterMediumAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request){
        System.out.println("ContacterMediumAction bien appelé pour le medium d'id "+request.getParameter("id"));

        Service s = new Service();
        long idMedium = Long.parseLong(request.getParameter("id"));
        long idClient = Long.parseLong(request.getParameter("idClient"));
        
        System.out.println("ID Client : "+ idClient);

        Medium m = s.rechercherMediumParId(idMedium);
        Client c = s.rechercherClientParId(idClient);
        
        System.out.println("ID Medium : "+ m.getId());
        System.out.println("nom Client : "+ c.getPrenom());
        System.out.println("denom Medium : "+ m.getDenom());
        
        if(m == null || c == null){
            System.out.print("Client ou medium null : ");
            System.out.print(c);
            System.out.print(", ");
            System.out.print(m);
            
            request.setAttribute("success", false);
            request.setAttribute("cause", "c ou m null");
            return;
        }
        
        
        Consultation consult = s.contacterMedium(m, c);
        
        if(consult == null){
            System.out.println("Erreur : pas d'Employe adéquat trouvé par le service contacterMedium");
            request.setAttribute("success", false);
            request.setAttribute("cause", "plus d'Employe dispo");
        }else{
            request.setAttribute("success", true);
        }
        
    }
}
