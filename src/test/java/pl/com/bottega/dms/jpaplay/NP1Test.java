package pl.com.bottega.dms.jpaplay;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.com.bottega.dms.inside.model.Document;
import pl.com.bottega.dms.inside.model.Employee;
import pl.com.bottega.dms.inside.model.User;

import javax.persistence.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class NP1Test {

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
    public void np1Test() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        int n = 100;
        int k = 3;
        for (int i = 0; i < n; i++) {
            User user = new User();
            Employee employee = new Employee();
            user.setEmployee(employee);
            employee.setUser(user);
            for (int j = 0; j < k; j++) {
                Document document = new Document();
                employee.getCreatedDocuments().add(document);
                document.setAuthor(employee);
            }
            em.persist(user);
        }
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        Query q = em.createQuery("SELECT DISTINCT(e) FROM Employee e LEFT JOIN FETCH e.createdDocuments");
        List<Employee> employees = q.getResultList();
        assertThat(em.createQuery("SELECT count(d) FROM Document d").getSingleResult()).isEqualTo((long)n * k);
        for (Employee employee : employees) {
            assertThat(employee.getCreatedDocuments().size()).isEqualTo(k);
        }
        assertThat(stats.getCollectionLoadCount() + 1).isEqualTo(n + 1);
        assertThat(employees.size()).isEqualTo(n);
    }


    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
