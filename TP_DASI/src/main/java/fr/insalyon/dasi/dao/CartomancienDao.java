package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Cartomancien;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class CartomancienDao {
    
    public void creer(Cartomancien cartomancien) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(cartomancien);
    }
    
    public Cartomancien chercherParId(Long cartomancienId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Cartomancien.class, cartomancienId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Cartomancien chercherParDenom(String cartomancienDenom) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Cartomancien> query = em.createQuery("SELECT c FROM Cartomancien c WHERE c.denom = :denom", Cartomancien.class);
        query.setParameter("denom", cartomancienDenom); // correspond au paramètre ":denom" dans la requête
        List<Cartomancien> cartomanciens = query.getResultList();
        Cartomancien result = null;
        if (!cartomanciens.isEmpty()) {
            result = cartomanciens.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Cartomancien> listerCartomanciens() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Cartomancien> query = em.createQuery("SELECT c FROM Cartomancien c ORDER BY c.nom ASC, c.prenom ASC", Cartomancien.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}
