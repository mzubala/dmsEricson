package pl.com.bottega.dms.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdAt, verifiedAt, publishedAt;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee verifier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee publisher;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    private Collection<Employee> readers;

    @Lob
    @Column(length = Short.MAX_VALUE)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    private String number;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Collection<Employee> getReaders() {
        return readers;
    }
}
