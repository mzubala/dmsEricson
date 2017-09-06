package pl.com.bottega.dms.inside.api.handlers;

import pl.com.bottega.dms.inside.api.handlers.di.DocumentRepositoryAware;
import pl.com.bottega.dms.inside.model.Document;
import pl.com.bottega.dms.inside.model.DocumentRepository;
import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;

public class CreateDocumentHandler implements Handler<CreateDocumentCommand, String>, DocumentRepositoryAware {

    private DocumentRepository documentRepository;

    @Override
    public String handle(CreateDocumentCommand createDocumentCommand) {
        Document document = new Document(createDocumentCommand);
        documentRepository.save(document);
        return document.getNumber();
    }

    @Override
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
}
