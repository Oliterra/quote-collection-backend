package edu.quote.collection.dbaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Setter;

import java.util.List;

@Setter
@Table(name = "permission", schema = "public")
@Entity
public class PermissionEntity extends PersistableEntity {

    private String name;
    private List<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    public List<RoleEntity> getRoles() {
        return roles;
    }
}


