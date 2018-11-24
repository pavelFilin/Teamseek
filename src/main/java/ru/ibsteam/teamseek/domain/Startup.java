package ru.ibsteam.teamseek.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "startup")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Title;
    private String Description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cratedDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "startups_developers",
            joinColumns = { @JoinColumn(name = "startup_id") },
            inverseJoinColumns = { @JoinColumn(name = "developers_id") }
    )
    private Set<User> developer = new HashSet<>();

}
