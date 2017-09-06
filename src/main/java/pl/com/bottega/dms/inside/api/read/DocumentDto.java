package pl.com.bottega.dms.inside.api.read;

import pl.com.bottega.dms.inside.model.DocumentStatus;

public class DocumentDto {

    private String number;
    private DocumentStatus status;
    private String title;

    public DocumentDto(String number, DocumentStatus status, String title) {
        this.number = number;
        this.status = status;
        this.title = title;
    }



}
