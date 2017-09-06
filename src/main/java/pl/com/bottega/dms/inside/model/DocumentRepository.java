package pl.com.bottega.dms.inside.model;

public interface DocumentRepository {

    void save(Document document);

    Document load(String number);

}
