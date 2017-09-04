package pl.com.bottega.dms.jpaplay;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.com.bottega.dms.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class EntityManagerTest {

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void initEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("DMS-TEST");
    }

    @Test
    public void needsTransactionToSaveSomething() {
        EntityManager em = emf.createEntityManager();
        em.persist(new Document());
        assertThatThrownBy(() -> em.flush()).isInstanceOf(TransactionRequiredException.class);
    }

    @Test
    public void savesAndReads() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Document());
        em.getTransaction().commit();
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
