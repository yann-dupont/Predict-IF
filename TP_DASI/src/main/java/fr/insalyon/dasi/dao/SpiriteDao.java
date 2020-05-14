package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class SpiriteDao {
    
    public void creer(Spirite spirite) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(spirite);
    }
    
    public Spirite chercherParId(Long spiriteId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Spirite.class, spiriteId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Spirite chercherParDenom(String spiriteDenom) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Spirite> query = em.createQuery("SELECT s FROM Spirite s WHERE s.denom = :denom", Spirite.class);
        query.setParameter("denom", spiriteDenom); // correspond au paramètre ":denom" dans la requête
        List<Spirite> spirites = query.getResultList();
        Spirite result = null;
        if (!spirites.isEmpty()) {
            result = spirites.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Spirite> listerSpirites() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Spirite> query = em.createQuery("SELECT c FROM Spirite c ORDER BY c.nom ASC, c.prenom ASC", Spirite.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}
