package pl.com.bottega.dms.inside.api.handlers;

import pl.com.bottega.dms.inside.model.Document;
import pl.com.bottega.dms.inside.model.DocumentRepository;
import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;

public class CreateDocumentHandler implements Handler<CreateDocumentCommand, String> {

    private DocumentRepository documentRepository;

    public CreateDocumentHandler(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public String handle(CreateDocumentCommand createDocumentCommand) {
        Document document = new Document(createDocumentCommand);
        documentRepository.save(document);
        return document.getNumber();
    }
}
