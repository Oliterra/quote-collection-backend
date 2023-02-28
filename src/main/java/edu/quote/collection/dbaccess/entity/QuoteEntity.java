package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "quote", schema = "public")
@Entity
public class QuoteEntity extends PersistableEntity {

    private String text;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "can_be_added_to_group")
    private Boolean canBeAddedToGroup;
    @Column(name = "number_of_votes")
    private Integer numberOfVotes;
    private Double rating;
    private BookEntity book;
    private UserEntity user;
    private List<GroupEntity> groups;
    private List<TagEntity> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public BookEntity getBook() {
        return book;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "quotes")
    public List<GroupEntity> getGroups() {
        return groups;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quote_to_tag",
        joinColumns = @JoinColumn(name = "quote_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public List<TagEntity> getTags() {
        return tags;
    }
}
