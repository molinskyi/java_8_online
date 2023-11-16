package org.hw9.service.impl;

import org.hw9.dao.EmployeeDao;
import org.hw9.dao.impl.CsvEmployeeDao;
import org.hw9.entity.Employee;
import org.hw9.service.EmployeeCrudService;

import java.util.Collection;
import java.util.Optional;

public class EmployeeCrudServiceImpl implements EmployeeCrudService {
    private EmployeeDao employeeDao = new CsvEmployeeDao();

    @Override
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public void update(Employee employee) {
        if (!employeeDao.existsById(String.valueOf(employee.getId()))) {
            throw new RuntimeException("Employee not found");
        }
        employeeDao.update(employee);
    }

    @Override
    public void delete(String id) {
        if (!employeeDao.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeDao.delete(id);
    }

    @Override
    public Employee findOne(String id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        return optionalEmployee.get();
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeDao.findAll();
    }
}