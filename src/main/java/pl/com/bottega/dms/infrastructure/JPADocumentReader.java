package pl.com.bottega.dms.infrastructure;

import pl.com.bottega.dms.api.DocumentDto;
import pl.com.bottega.dms.api.DocumentReader;
import pl.com.bottega.dms.api.DocumentSearchCriteria;
import pl.com.bottega.dms.model.Document;

import javax.persistence.EntityManager;
import java.util.List;

public class JPADocumentReader implements DocumentReader {

    private EntityManager entityManager;

    public JPADocumentReader(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Document> searchDocuments(DocumentSearchCriteria criteria) {
        return null;
    }

    public List<DocumentDto> searchDocumentDtos(DocumentSearchCriteria criteria) {
        return null;
    }
}
