package org.hw10.service.impl;

import org.hw10.dao.DepartmentDao;

import org.hw10.dao.impl.DepartmentDaoImpl;
import org.hw10.data.PaginationData;
import org.hw10.entity.Department;
import org.hw10.service.DepartmentCrudService;

import java.util.Collection;
import java.util.Optional;

public class DepartmentCrudServiceImpl implements DepartmentCrudService {
    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void create(Department department) {
        departmentDao.create(department);
    }

    @Override
    public void update(Department department) {
        if (departmentDao.findById(department.getId()).isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        departmentDao.update(department);
    }

    @Override
    public void delete(String id) {
        if (departmentDao.findById(Long.valueOf(id)).isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        departmentDao.delete(Long.valueOf(id));
    }

    @Override
    public Department findOne(String id) {
        Optional<Department> optionalDepartment = departmentDao.findById(Long.valueOf(id));
        if (optionalDepartment.isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        return optionalDepartment.get();
    }

    @Override
    public Collection<Department> findAll(PaginationData data) {
        return departmentDao.findAll(data);
    }
}
