package org.module_1.service;

import org.module_1.entity.BaseEntity;
import org.module_1.entity.Department;

import java.io.IOException;

public interface CrudService <BE extends BaseEntity>{

    void create(BE be);
    BE update(BE be) throws IOException;
    Department[] delete(String id) throws IOException;
    BE findOne(String id) throws IOException;
    BE[] findAll();

}
