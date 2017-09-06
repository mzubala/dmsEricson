package pl.com.bottega.dms.inside.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<Document> createdDocuments = new LinkedList<>();

    @OneToMany(mappedBy = "verifier", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<Document> verifiedDocuments;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<Document> publishedDocuments;

    @ManyToMany(mappedBy = "readers", cascade = CascadeType.ALL)
    private Collection<Document> readDocuments;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "countryCode", column = @Column(name = "work_phone_country_code")),
            @AttributeOverride(name = "number", column = @Column(name = "work_phone_number"))
    })
    private PhoneNumber workPhone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "countryCode", column = @Column(name = "private_phone_country_code")),
            @AttributeOverride(name = "number", column = @Column(name = "private_phone_number"))
    })
    private PhoneNumber privatePhone;

    public Long getId() {
        return id;
    }

    public Collection<Document> getCreatedDocuments() {
        return createdDocuments;
    }

    public void setUser(User user) {
        this.user = user;
    }
}