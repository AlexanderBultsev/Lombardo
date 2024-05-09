package com.pristine.lombardo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "users", uniqueConstraints =
@UniqueConstraint(columnNames = "tel"))
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel", unique = true, nullable = false)
    @NotNull
    private String tel;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    public User(String name, String tel, String password, Collection<Role> roles) {
        this.name = name;
        this.tel = tel;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id + ": " +
                "{name=" + name +
                ", tel=" + tel +
                ", password=" + password +
                ", roles=" + roles +
                '}';
    }
}
