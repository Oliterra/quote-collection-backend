package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "category", schema = "public")
@Entity
public class CategoryEntity extends PersistableEntity {

    private String name;
    private List<BookEntity> books;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    public List<BookEntity> getBooks() {
        return books;
    }
}
