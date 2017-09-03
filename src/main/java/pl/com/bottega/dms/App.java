package pl.com.bottega.dms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.com.bottega.dms.model.Document;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMS");
        EntityManager em = emf.createEntityManager();

        em.flush();
        em.close();
        emf.close();
    }
}
