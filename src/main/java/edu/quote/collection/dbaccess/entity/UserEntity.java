package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "user", schema = "public")
@Entity
public class UserEntity extends PersistableEntity {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private List<RoleEntity> roles;
    private List<QuoteEntity> quotes;
    private List<GroupEntity> groups;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_to_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<RoleEntity> getRoles() {
        return roles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public List<QuoteEntity> getQuotes() {
        return quotes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public List<GroupEntity> getGroups() {
        return groups;
    }
}
