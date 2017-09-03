package pl.com.bottega.dms.jpaplay;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {

    private static EntityManagerFactory emf;

    @BeforeAll
    public static void initEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("DMS-TEST");
    }

    @AfterAll
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
