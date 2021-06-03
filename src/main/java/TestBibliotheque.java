import fr.diginamic.bo.Emprunt;
import fr.diginamic.bo.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestBibliotheque {
    public static void main(String[] args) {

        /*
        o Réalisez une requête qui permet d’extraire tous les emprunts d’un client donné.
        */

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test_jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Emprunt emprunt = entityManager.find(Emprunt.class, 2);
        List<Livre> livres = findLivreByEmpruntId(entityManager, emprunt.getId());
        List<Emprunt> emprunts = findAllEmpruntByClientId(entityManager, 1);

        System.out.println(emprunt);
        System.out.println(livres.get(0));
        System.out.println(emprunts.get(0));

        entityManager.close();
        entityManagerFactory.close();

    }

    private static List<Livre> findLivreByEmpruntId(EntityManager em, int id){
        return em.createQuery(String.format("SELECT l FROM Livre l WHERE ID=%d", id), Livre.class).getResultList();
    }

    private static List<Emprunt> findAllEmpruntByClientId(EntityManager em, int id){
        return em.createQuery(String.format("SELECT e FROM Emprunt e WHERE ID_CLIENT=%d", id), Emprunt.class).getResultList();
    }
}
