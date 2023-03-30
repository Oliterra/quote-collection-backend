package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@MappedSuperclass
public class PersistableEntity {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    public Long getId() {
        return id;
    }
}
