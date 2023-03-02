package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@MappedSuperclass
public class PersistableEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    @SequenceGenerator(name = "SEQUENCE", allocationSize = 10, sequenceName = "idSeq")
    @Column(name = "id", unique = true, nullable = false, precision = 19)
    public Long getId() {
        return id;
    }
}
