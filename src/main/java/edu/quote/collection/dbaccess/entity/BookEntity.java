package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "book", schema = "public")
@Entity
public class BookEntity extends PersistableEntity {

    private String name;
    private AuthorEntity author;
    private UserEntity user;
    private List<CategoryEntity> categories;
    private List<QuoteEntity> quotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public AuthorEntity getAuthor() {
        return author;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_to_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    public List<QuoteEntity> getQuotes() {
        return quotes;
    }
}
