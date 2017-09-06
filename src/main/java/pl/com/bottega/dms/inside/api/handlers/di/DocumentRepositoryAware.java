package pl.com.bottega.dms.inside.api.handlers.di;

import pl.com.bottega.dms.inside.model.DocumentRepository;

public interface DocumentRepositoryAware {

    void setDocumentRepository(DocumentRepository documentRepository);

}
