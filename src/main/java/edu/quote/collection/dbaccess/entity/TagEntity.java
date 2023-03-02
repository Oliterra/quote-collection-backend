package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "tag", schema = "public")
@Entity
public class TagEntity extends PersistableEntity {

    private String name;
    private List<QuoteEntity> quotes;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    public List<QuoteEntity> getQuotes() {
        return quotes;
    }
}
