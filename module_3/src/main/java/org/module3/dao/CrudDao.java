package org.module3.dao;

import org.module3.entity.BaseEntity;

import java.util.Optional;

public interface CrudDao<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
}
