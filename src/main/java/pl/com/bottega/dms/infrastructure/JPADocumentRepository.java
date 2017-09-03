package pl.com.bottega.dms.infrastructure;

import pl.com.bottega.dms.model.DocumentRepository;

import javax.persistence.EntityManager;

public class JPADocumentRepository implements DocumentRepository {

    private EntityManager entityManager;

    public JPADocumentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
