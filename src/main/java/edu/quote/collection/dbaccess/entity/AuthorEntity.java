package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "author", schema = "public")
@Entity
public class AuthorEntity extends PersistableEntity {

    private String name;
    private String surname;
    private List<BookEntity> books;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    public List<BookEntity> getBooks() {
        return books;
    }
}
