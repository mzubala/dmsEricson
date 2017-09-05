package pl.com.bottega.dms.api;

import pl.com.bottega.dms.model.DocumentStatus;

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
