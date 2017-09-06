package pl.com.bottega.dms.outside.read;

import pl.com.bottega.dms.inside.api.read.DocumentDto;
import pl.com.bottega.dms.inside.api.read.DocumentReader;
import pl.com.bottega.dms.inside.api.read.DocumentSearchCriteria;
import pl.com.bottega.dms.inside.api.read.SearchResults;

import javax.persistence.EntityManager;

public class JPADocumentReader implements DocumentReader {

    private EntityManager entityManager;

    public JPADocumentReader(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public SearchResults<DocumentDto> searchDocumentDtos(DocumentSearchCriteria criteria) {
        return null;
    }
}