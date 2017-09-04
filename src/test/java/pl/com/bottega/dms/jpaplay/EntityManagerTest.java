package pl.com.bottega.dms.jpaplay;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class EntityManagerTest {

    private static EntityManagerFactory emf;

    @Entity
    @Table(name="test_entity")
    class TestEntity {
        @Id
        @GeneratedValue
        private Long id;
    }

    @BeforeClass
    public static void initEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("DMS-TEST");
    }

    @Test
    public void needsTransactionToSaveSomething() {
        EntityManager em = emf.createEntityManager();
        em.persist(new TestEntity());
        assertThatThrownBy(() -> em.flush()).isInstanceOf(TransactionRequiredException.class);
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
