package pl.com.bottega.dms.inside.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login, password;

}
