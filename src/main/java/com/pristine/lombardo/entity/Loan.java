package com.pristine.lombardo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pristine.lombardo.addition.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "loansIdSeq", sequenceName = "loans_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loansIdSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @NotNull
    @JsonIgnore
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    @NotNull
    @JsonIgnore
    private Property property;

    @Column(name = "open")
    private Date open;

    @Column(name = "close")
    private Date close;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LoanStatus status;


    public Loan(Client client, Property property, Date open, Date close, LoanStatus status) {
        this.client = client;
        this.property = property;
        this.open = open;
        this.close = close;
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ": " +
                ", client=" + client.getId() +
                ", property=" + property.getId() +
                ", open=" + open +
                ", close=" + close +
                ", status=" + status +
                '}';
    }
}
