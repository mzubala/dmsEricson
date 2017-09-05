package pl.com.bottega.dms.jpaplay;

import org.hibernate.LazyInitializationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.com.bottega.dms.model.Document;
import pl.com.bottega.dms.model.Employee;
import pl.com.bottega.dms.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;

import static org.assertj.core.api.Assertions.assertThat;
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
        Document document = new Document();
        em.persist(document);
        em.getTransaction().commit();

        assertThat(document.getId()).isNotNull();

        Document documentRead = em.find(Document.class, document.getId());
        assertThat(documentRead).isNotNull();

    }

    @Test
    public void tracksChangesInEntities() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Document document = new Document();
        em.persist(document);

        document.setTitle("hey hey");

        em.getTransaction().commit();

        Document documentRead = em.find(Document.class, document.getId());
        assertThat(documentRead.getTitle()).isEqualTo("hey hey");
    }

    @Test
    public void cannotLazyLoadOutsideTx() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Document document = new Document();
        em.persist(document);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Document documentRead = em.find(Document.class, document.getId());
        em.close();
        assertThatThrownBy(() -> documentRead.getReaders().size()).isInstanceOf(LazyInitializationException.class);
    }

    @Test
    public void mergesEntities() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Document document = new Document();
        em.persist(document);
        em.getTransaction().commit();
        em.close();

        document.setTitle("hey hey");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        document = em.merge(document);
        em.persist(document);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Document documentRead = em.find(Document.class, document.getId());
        assertThat(documentRead.getTitle()).isEqualTo("hey hey");
    }

    @Test
    public void addingUserAddsEmployee() {
        User user = new User();
        user.setEmployee(new Employee());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, user.getEmployee().getId());
        assertThat(employee).isNotNull();
    }


    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
