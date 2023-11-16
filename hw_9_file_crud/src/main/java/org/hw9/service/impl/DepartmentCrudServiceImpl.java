package org.hw9.service.impl;

import org.hw9.dao.DepartmentDao;
import org.hw9.dao.impl.CsvDepartmentDao;
import org.hw9.entity.Department;
import org.hw9.service.DepartmentCrudService;

import java.util.Collection;
import java.util.Optional;

public class DepartmentCrudServiceImpl implements DepartmentCrudService {
    private DepartmentDao departmentDao = new CsvDepartmentDao();

    @Override
    public void create(Department department) {
        departmentDao.create(department);
    }

    @Override
    public void update(Department department) {
        if (!departmentDao.existsById(String.valueOf(department.getId()))) {
            throw new RuntimeException("Department not found");
        }
        departmentDao.update(department);
    }

    @Override
    public void delete(String id) {
        if (!departmentDao.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        departmentDao.delete(id);
    }

    @Override
    public Department findOne(String id) {
        Optional<Department> optionalDepartment = departmentDao.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        return optionalDepartment.get();
    }

    @Override
    public Collection<Department> findAll() {
        return departmentDao.findAll();
    }
}
