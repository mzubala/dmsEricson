package pl.com.bottega.dms.inside.api.read;

public interface DocumentReader {

    SearchResults<DocumentDto> searchDocumentDtos(DocumentSearchCriteria criteria);

}
