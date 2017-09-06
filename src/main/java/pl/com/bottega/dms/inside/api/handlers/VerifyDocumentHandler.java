package pl.com.bottega.dms.inside.api.handlers;

import pl.com.bottega.dms.inside.api.handlers.di.DocumentRepositoryAware;
import pl.com.bottega.dms.inside.model.Document;
import pl.com.bottega.dms.inside.model.DocumentRepository;
import pl.com.bottega.dms.inside.model.commands.VerifyDocumentCommand;

public class VerifyDocumentHandler implements Handler<VerifyDocumentCommand, Void>, DocumentRepositoryAware {

    private DocumentRepository documentRepository;

    @Override
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Void handle(VerifyDocumentCommand command) {
        Document document = documentRepository.load(command.getDocumentNumber());

        document.verify(command);

        documentRepository.save(document);

        return null;
    }
}
