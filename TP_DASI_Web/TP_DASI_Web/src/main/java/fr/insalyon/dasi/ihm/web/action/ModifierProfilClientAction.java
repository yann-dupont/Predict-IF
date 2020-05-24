/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ModifierProfilClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        System.out.println("ID de l'utilisateur de modifier de profil : " + request.getParameter("userId"));
        Client client = service.rechercherClientParId(Long.parseLong(request.getParameter("userId")));
        Boolean success = service.updateClient(client, request.getParameter("newNom"), request.getParameter("newPrenom"), request.getParameter("newMail"), request.getParameter("newTel"));

        request.setAttribute("success", success);
    }
    
}
