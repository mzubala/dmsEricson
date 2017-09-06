package pl.com.bottega.dms.outside.read;

import org.apache.commons.lang.StringUtils;
import pl.com.bottega.dms.inside.api.DocumentDto;
import pl.com.bottega.dms.inside.api.DocumentReader;
import pl.com.bottega.dms.inside.api.DocumentSearchCriteria;
import pl.com.bottega.dms.inside.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.function.Consumer;

public class JPADocumentReader implements DocumentReader {

    private EntityManager entityManager;

    public JPADocumentReader(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Document> searchDocuments(DocumentSearchCriteria criteria) {
        List<String> queryComponents = new LinkedList<>();
        queryComponents.add("SELECT distinct(d) FROM Document d");
        Set<String> conditions = new HashSet<>();
        Set<Consumer<Query>> paramAdders = new HashSet<>();
        if (criteria.getStatus() != null) {
            conditions.add("d.status = :status");
            paramAdders.add((q) -> q.setParameter("status", criteria.getStatus()));
        }
        if (criteria.getContent() != null) {
            conditions.add("d.content LIKE :contentLike");
            paramAdders.add((q) -> q.setParameter("contentLike", "%" + criteria.getContent() + "%"));
        }
        if (criteria.getTitle() != null) {
            conditions.add("d.title LIKE :titleLike");
            paramAdders.add((q) -> q.setParameter("titleLike", "%" + criteria.getTitle() + "%"));
        }
        if (criteria.getAuthorId() != null) {
            queryComponents.add("JOIN d.author a");
            conditions.add("a.id = :authorId");
            paramAdders.add((q) -> q.setParameter("authorId", criteria.getAuthorId()));
        }
        if(conditions.size() > 0) {
            queryComponents.add("WHERE");
            queryComponents.add(StringUtils.join(conditions, " AND "));
        }
        String queryString = StringUtils.join(queryComponents, " ");
        Query query = entityManager.createQuery(queryString);
        paramAdders.stream().forEach((adder) -> adder.accept(query));
        return query.getResultList();
    }

    @Override
    public List<DocumentDto> searchDocumentDtos(DocumentSearchCriteria criteria) {
        return null;
    }
}