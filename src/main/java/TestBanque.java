import tpBanque.BO.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class TestBanque {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //o insérer un compte associé à 2 clients
        Compte compte1 = new Compte("123456789", 500.5);
        Client client1 = new Client("roy", "loic", LocalDate.of(1994, 10, 25));
        Client client2 = new Client("roy", "yoann", LocalDate.of(1994, 10, 25));
        Banque banque = new Banque("la banque postale");
        Adresse adresse = new Adresse(33, "impasse josephine baker", 44850, "Ligné");
        client1.setAdresse(adresse);
        client2.setAdresse(adresse);
        client1.setBanque(banque);
        client2.setBanque(banque);
        compte1.addClient(client1);
        compte1.addClient(client2);

        entityManager.persist(compte1);

        /*
        o insérer un client avec plusieurs comptes :
            ▪ 1 compte de type assurance vie
            ▪ 1 compte de type livret A
         */
        Client client3 = new Client("jedusor", "tom", LocalDate.of(1995, 5, 12));
        Banque banque2 = new Banque("credit agricole");
        Adresse adresse2 = new Adresse(5, "rue de l'amitié", 25630, "Nantes");
        Compte assuranceVie = new AssuranceVie("789456123", 250, LocalDate.now().plusDays(20), 2);
        Compte livretA = new LivretA("456789123", 150, 1500);
        client3.addCompte(assuranceVie);
        client3.addCompte(livretA);
        client3.setBanque(banque2);
        client3.setAdresse(adresse2);
        entityManager.persist(client3);


        //o insérer des opérations de type virements sur un compte
        Operation virement1 = new Virement(LocalDate.now(), 10, "argent de poche", "nolhann");
        Operation virement2 = new Virement(LocalDate.now(), 150, "frais de formation", "DIGNAMIC");
        Compte compte2 = new Compte("456123789", 1800);
        compte2.addClient(client3);
        compte2.addOperation(virement1);
        compte2.addOperation(virement2);

        //o insérer des opérations de type opérations sur un compte
        Operation operation1 = new Operation(LocalDate.now(), 1200, "salaire");
        Operation operation2 = new Operation(LocalDate.now(), 50, "part de la banque");
        compte2.addOperation(operation1);
        compte2.addOperation(operation2);
        entityManager.persist(compte1);
        entityManager.persist(compte2);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
