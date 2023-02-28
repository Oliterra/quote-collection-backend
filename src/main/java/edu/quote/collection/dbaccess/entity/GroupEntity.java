package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "group", schema = "public")
@Entity
public class GroupEntity extends PersistableEntity {

    private String name;
    @Column(name = "is_public")
    private Boolean isPublic;
    private UserEntity user;
    private List<QuoteEntity> quotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "group_to_quote",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "quote_id"))
    public List<QuoteEntity> getQuotes() {
        return quotes;
    }
}
