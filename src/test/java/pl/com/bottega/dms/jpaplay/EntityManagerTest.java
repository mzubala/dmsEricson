package pl.com.bottega.dms.jpaplay;

import static org.assertj.core.api.Assertions.assertThat;


public class EntityManagerTest {
/*
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

    @Test
    public void getsDtos() {
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
                document.setTitle("Dokument " + i * j);
                employee.getCreatedDocuments().add(document);
                document.setAuthor(employee);
            }
            em.persist(user);
        }
        em.getTransaction().commit();
        Query q = em.createQuery("SELECT new pl.com.bottega.dms.inside.api.read.DocumentDto(d.number, d.title, d.status) FROM Document d");
        List<DocumentDto> dtos = q.getResultList();
        assertThat(dtos.size()).isEqualTo(n * k);
    }

    @Test(expected = OptimisticLockException.class)
    public void optimisticLockingTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        Employee employee = new Employee();
        user.setEmployee(employee);
        employee.setUser(user);
        Document document = new Document();
        document.setTitle("Tytuł początkowy");
        employee.getCreatedDocuments().add(document);
        document.setAuthor(employee);
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Document documentRead = em.find(Document.class, document.getId());
        documentRead.setTitle("Zmieniony tytuł");
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        document.setTitle("Konkurencyjna zmiana");
        em.merge(document);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testLocking() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        Employee employee = new Employee();
        user.setEmployee(employee);
        employee.setUser(user);
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.find(User.class, user.getId(), LockModeType.PESSIMISTIC_WRITE);
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }
*/
}
