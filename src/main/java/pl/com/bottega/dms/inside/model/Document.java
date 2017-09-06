package pl.com.bottega.dms.inside.model;

import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;
import pl.com.bottega.dms.inside.model.commands.VerifyDocumentCommand;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkState;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String title;

    private LocalDateTime createdAt, verifiedAt, publishedAt;

    private Long creatorId, verifierId, publisherId;

    @Lob
    @Column(length = Short.MAX_VALUE)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    public Document() {}

    public Document(CreateDocumentCommand createDocumentCommand) {
        title = createDocumentCommand.getTitle();
        createdAt = LocalDateTime.now();
        status = DocumentStatus.DRAFT;
        number = UUID.randomUUID().toString();
        creatorId = createDocumentCommand.getEmployeeId();
    }

    public void verify(VerifyDocumentCommand command) {
        checkState(status == DocumentStatus.DRAFT);
        status = DocumentStatus.VERIFIED;
        verifierId = command.getEmployeeId();
        verifiedAt = LocalDateTime.now();
    }

    public String getNumber() {
        return number;
    }
}
