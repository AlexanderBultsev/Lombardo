package com.pristine.lombardo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "propertiesIdSeq", sequenceName = "properties_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertiesIdSeq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price", unique = true, nullable = false)
    @NotNull
    private Float price;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Loan loan;


    public Property(String name, String type, Float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ": " +
                "{name=" + name +
                ", type=" + type +
                ", price=" + price +
                ", loan=" + loan.getId() +
                '}';
    }
}

