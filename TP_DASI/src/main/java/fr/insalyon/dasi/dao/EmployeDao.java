/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Statut;
import javax.persistence.Query;

/**
 *
 * @author Yumahey
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    // renvoie l'employe :
    // - disponible
    // - du genre indiqué
    // - ayant le moins de consultations sur la dernière semaine
    public Employe chercherEmployePourConsultation(Genre genre) {
        
        String requeteInfame = "select employe_id from (select employe_id, count(id) as NbConsult from (select {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} diff, c.* from consultation as c where {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} < 7 and {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} >= 0) as recent group by employe_id) as consultParEmp, Employe e where e.ID = consultParEmp.Employe_ID and NbConsult = (select MIN(NbConsult) from (select employe_id, count(*) as NbConsult from (select {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} as diff, c.* from consultation c where {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} < 7 and {fn timestampdiff(SQL_TSI_DAY, c.date, CURRENT_TIME)} >= 0) as recent group by employe_id) as oui) and e.STATUT = 0 and e.GENRE = 1 --- fin de la requête infame -- PS : le SQL de Derby est fumant";
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        //TypedQuery<Employe> query = em.createQuery(requeteInfame, Employe.class);
        Query query = em.createNativeQuery(requeteInfame);

        query.setParameter("genre", genre); // correspond au paramètre ":genre" dans la requête
        query.setParameter("statut", 0);//Statut.Dispo); // correspond au paramètre ":statut" dans la requête
        //List<Object[]> employes = query.getResultList();
        List<Long> employes = query.getResultList();
        Employe result = null;
        System.out.println("ID de l'employé trouvé : " + employes.toString());
        if (!employes.isEmpty()) {
            
            result = this.chercherParId(employes.get(0)); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    // modifier
    public Employe modifier(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(employe);
    }
    
}
