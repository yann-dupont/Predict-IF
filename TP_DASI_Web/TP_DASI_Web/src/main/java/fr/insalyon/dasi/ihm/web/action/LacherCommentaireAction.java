/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Statut;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class LacherCommentaireAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service s = new Service();
        
        request.setAttribute("success", false); // par défaut
            
        System.out.println(request.getParameter("id"));
        Long employeId = Long.parseLong(request.getParameter("id"));
        Employe emp = s.rechercherEmployeParId(employeId);
        
        if(emp == null){
            return;
        }
        Consultation c = s.obtenirConsultationAFaire(emp);
                
        if(c == null){
            return;
        }
        
        int res = s.terminerConsultation(c, emp, request.getParameter("text"));
        
        if (res == 0){
            request.setAttribute("success", true);
            System.out.println("Succès LacherCommentaireAction");
        }else{
            System.out.println("Echec LacherCommentaireAction");
        }
        
    }
    
}
