package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "role", schema = "public")
@Entity
public class RoleEntity extends PersistableEntity {

    private String name;
    private List<PermissionEntity> permissions;
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_to_permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id"))
    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    public List<UserEntity> getUsers() {
        return users;
    }
}
