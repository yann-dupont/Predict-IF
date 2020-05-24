package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class ConsultationDao {
    
    public void creer(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }
    
    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Consultation> chercherParClient(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.statut = 0 and c.client = :client", Consultation.class); // voir pour les numeros de statut
        query.setParameter("client", client); // correspond au paramètre ":client" dans la requête
        List<Consultation> consultations = query.getResultList();
        return consultations;
    }
    
    public List<Consultation> chercherParMedium(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE statut = 1 and c.medium = :medium", Consultation.class); // statut = 1 : a faire
        query.setParameter("medium", medium.getId()); // correspond au paramètre ":medium" dans la requête
        List<Consultation> consultations = query.getResultList();
        return consultations;
    }
    
    public Consultation chercherParEmploye(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Consultation consultation;
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE statut = 1 and c.employe = :employe", Consultation.class); // statut = 1 : a faire
        query.setParameter("employe", employe.getId()); // correspond au paramètre ":employe" dans la requête
        
        try{
            consultation = query.getResultList().get(0);
        }catch(Exception e){
            consultation = null;
        }
        return consultation;
    }
    
    public List<Consultation> listerConsultations() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c ORDER BY c.client ASC, c.medium ASC, c.employe ASC", Consultation.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}
