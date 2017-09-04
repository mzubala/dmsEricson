package pl.com.bottega.dms.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login, password;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Employee employee;

}
