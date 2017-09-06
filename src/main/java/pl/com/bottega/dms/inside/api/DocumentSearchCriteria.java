package pl.com.bottega.dms.inside.api;

import pl.com.bottega.dms.inside.model.DocumentStatus;

public class DocumentSearchCriteria {

    private DocumentStatus status;
    private String title;
    private String content;
    private Long authorId;

    public DocumentStatus getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
