package pl.com.bottega.dms.outside.repositories;

import pl.com.bottega.dms.inside.model.Document;
import pl.com.bottega.dms.inside.model.DocumentRepository;

import javax.persistence.EntityManager;

public class JPADocumentRepository implements DocumentRepository {

    private EntityManager entityManager;

    public JPADocumentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Document document) {

    }
}
