package org.module_1.service.impl;

import org.module_1.db.EmployeeDb;
import org.module_1.entity.Department;
import org.module_1.entity.Employee;
import org.module_1.service.EmployeeCrudService;

import java.io.IOException;

public class EmployeeCrudServiceImpl implements EmployeeCrudService {
    EmployeeDb employeeDb = new EmployeeDb();

    @Override
    public void create(Employee employee) {
        employeeDb.create(employee);
    }

    @Override
    public Employee update(Employee employee) throws IOException {
        return null;
    }

    @Override
    public Department[] delete(String id) throws IOException {
        return new Department[0];
    }

    @Override
    public Employee findOne(String id) throws IOException {
        return null;
    }

    @Override
    public Employee[] findAll() {
        return employeeDb.findAll();
    }
}
