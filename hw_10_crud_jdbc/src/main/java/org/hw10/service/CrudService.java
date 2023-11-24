package org.hw10.service;

import org.hw10.data.PaginationData;
import org.hw10.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<BE extends BaseEntity> {
    void create(BE be);
    void update(BE be);
    void delete(String id);
    BE findOne(String id);
    Collection<BE> findAll(PaginationData data);
}
