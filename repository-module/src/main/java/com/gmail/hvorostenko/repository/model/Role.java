package com.gmail.hvorostenko.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Column
    private String description;

    public Role(RoleEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id) && description.equals(role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
