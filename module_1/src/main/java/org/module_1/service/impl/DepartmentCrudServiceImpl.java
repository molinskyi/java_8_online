package org.module_1.service.impl;

import org.module_1.db.DepartmentDb;
import org.module_1.entity.Department;
import org.module_1.service.DepartmentCrudService;

import java.io.IOException;

public class DepartmentCrudServiceImpl implements DepartmentCrudService{

    DepartmentDb departmentDb = new DepartmentDb();
    @Override
    public void create(Department department) {
        departmentDb.create(department);
    }

    @Override
    public Department update(Department department) throws IOException {
        return null;
    }

    @Override
    public Department[] delete(String id) throws IOException {
        return new Department[0];
    }

    @Override
    public Department findOne(String id) throws IOException {
        return null;
    }

    @Override
    public Department[] findAll() {
        return departmentDb.findAll();
    }
}
