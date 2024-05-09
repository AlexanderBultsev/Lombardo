package com.pristine.lombardo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel", unique = true, nullable = false)
    @NotNull
    private String tel;

    @Column(name = "balance")
    private Float balance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;


    public Client(String name, String tel, Float balance, List<Loan> loans) {
        this.name = name;
        this.tel = tel;
        this.balance = balance;
        this.loans = loans;
    }

    @Override
    public String toString() {
        return id + ": " +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", balance=" + balance +
                ", loans=" + loans.size() +
                '}';
    }
}
