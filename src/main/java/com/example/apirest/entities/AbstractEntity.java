package com.example.apirest.entities;

import com.example.apirest.entities.interfaces.IAbstract;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe abstraite contenant :
 * - un id sous forme d'un Long
 * - equals et hashcode reposant sur l'id
 */
@MappedSuperclass
public abstract class AbstractEntity implements IAbstract, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    public AbstractEntity() {
    }

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if(o == null)
            return false;

        if(getClass() != o.getClass())
            return false;

        AbstractEntity other = (AbstractEntity) o;
        return Objects.equals(id, other.id);
    }
}
