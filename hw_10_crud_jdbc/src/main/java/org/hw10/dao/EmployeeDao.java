package org.hw10.dao;

import org.hw10.entity.Employee;

import java.util.Collection;

public interface EmployeeDao extends CrudDao<Employee>{
    Collection<Employee> findAllByDepartment(Long depId);
    Collection<Employee> findAllNoPagination();
}
