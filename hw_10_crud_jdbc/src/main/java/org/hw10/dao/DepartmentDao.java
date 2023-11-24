package org.hw10.dao;

import org.hw10.dto.DepartmentStatistics;
import org.hw10.entity.Department;

import java.util.Collection;

public interface DepartmentDao extends CrudDao<Department>{
    void attachEmployeeToDepartment(Long depId, Long empId);
    Collection<DepartmentStatistics> findDepartmentStatistics();
    Collection<Department> findAllNoPagination();
}
