package org.hw9.service;

import org.hw9.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<BE extends BaseEntity> {
    void create(BE be);
    void update(BE be);
    void delete(String id);
    BE findOne(String id);
    Collection<BE> findAll();
}
