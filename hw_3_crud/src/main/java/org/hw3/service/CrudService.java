package org.hw3.service;

import org.hw3.entity.BaseEntity;
import org.hw3.entity.Driver;

import java.io.IOException;

public interface CrudService<BE extends BaseEntity> {

    void create(BE be);
    BE update(String id) throws IOException;
    Driver[] delete(String id) throws IOException;
    BE findOne(String id) throws IOException;

    BE[] findAll();

}