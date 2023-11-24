package org.hw10.service.impl;

import org.hw10.dao.EmployeeDao;
import org.hw10.dao.impl.EmployeeDaoImpl;
import org.hw10.data.PaginationData;
import org.hw10.entity.Employee;
import org.hw10.service.EmployeeCrudService;

import java.util.Collection;
import java.util.Optional;

public class EmployeeCrudServiceImpl implements EmployeeCrudService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public void update(Employee employee) {
        if (employeeDao.findById(employee.getId()).isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        employeeDao.update(employee);
    }

    @Override
    public void delete(String id) {
        if (employeeDao.findById(Long.valueOf(id)).isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        employeeDao.delete(Long.valueOf(id));
    }

    @Override
    public Employee findOne(String id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(Long.valueOf(id));
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        return optionalEmployee.get();
    }

    @Override
    public Collection<Employee> findAll(PaginationData data) {
        return employeeDao.findAll(data);
    }
}