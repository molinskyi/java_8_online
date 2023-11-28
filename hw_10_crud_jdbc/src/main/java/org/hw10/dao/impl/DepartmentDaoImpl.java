package org.hw10.dao.impl;

import org.hw10.dto.DepartmentStatistics;
import org.hw10.entity.Department;
import org.hw10.factory.JdbcFactory;
import org.hw10.dao.DepartmentDao;
import org.hw10.data.PaginationData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();

    private static final String ATTACH_EMPLOYEE_TO_DEPARTMENT = "insert into dep_emp values (?, ?)";
    private static final String CREATE_DEPARTMENT = "insert into departments values (default, ?);";
    private static final String UPDATE_DEPARTMENT = "update departments set name = ? where id = ?";
    private static final String DELETE_DEPARTMENT = "delete from departments where id = ?";
    private static final String FIND_DEPARTMENT = "select * from departments where id = ";
    private static final String FIND_ALL_DEPARTMENT = "select * from departments order by ? limit ?, ?";
    private static final String FIND_ALL_DEPARTMENT_NO_ORDER = "select * from departments";
    private static final String FIND_DEPARTMENT_STATISTICS = "select d.id, d.name, count(d.id) as count_of_employees from departments as d left join dep_emp as de on d.id = de.dep_id group by d.id";

    @Override
    public void create(Department entity) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(CREATE_DEPARTMENT)) {
            ps.setString(1, entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Department entity) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(UPDATE_DEPARTMENT)) {
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(DELETE_DEPARTMENT)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Department> findById(Long id) {
        try(ResultSet rs = jdbcFactory.getStatement().executeQuery(FIND_DEPARTMENT + id)) {
            rs.next();
            return Optional.of(generateDepartmentByResultSet(rs));
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Collection<Department> findAll(PaginationData data) {
        StringBuilder order = new StringBuilder();
        order.append(data.getSort());
        order.append(" ");
        order.append(data.getOrderBy());
        int start = (data.getPage() - 1) * data.getSize();
        Collection<Department> departments = new ArrayList<>();
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(FIND_ALL_DEPARTMENT)) {
            ps.setString(1, order.toString());
            ps.setInt(2, start);
            ps.setInt(3, data.getSize());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departments.add(generateDepartmentByResultSet(rs));
            }
            return departments;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void attachEmployeeToDepartment(Long depId, Long empId) {
        try(PreparedStatement ps = jdbcFactory.getConnection().prepareStatement(ATTACH_EMPLOYEE_TO_DEPARTMENT)) {
            ps.setLong(1, depId);
            ps.setLong(2, empId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Collection<DepartmentStatistics> findDepartmentStatistics() {
        try(ResultSet rs = jdbcFactory.getStatement().executeQuery(FIND_DEPARTMENT_STATISTICS)) {
            Collection<DepartmentStatistics> collection = new ArrayList<>();
            while (rs.next()) {
                collection.add(generateDepartmentStatisticsByResultSet(rs));
            }
            return collection;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return null;
    }

    private DepartmentStatistics generateDepartmentStatisticsByResultSet(ResultSet rs) throws SQLException {
        DepartmentStatistics departmentStatistics = new DepartmentStatistics();
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Long countOfEmployees = rs.getLong("count_of_employees");
        departmentStatistics.setDepartmentId(id);
        departmentStatistics.setName(name);
        departmentStatistics.setCountOfEmployees(countOfEmployees);
        return departmentStatistics;
    }

    private Department generateDepartmentByResultSet(ResultSet rs) throws SQLException {
        Department department = new Department();
        Long id = rs.getLong("id");
        String n = rs.getString("name");
        department.setId(id);
        department.setName(n);
        return department;
    }

    @Override
    public Collection<Department> findAllNoPagination() {
        Collection<Department> departments = new ArrayList<>();
        try(ResultSet rs = jdbcFactory.getStatement().executeQuery(FIND_ALL_DEPARTMENT_NO_ORDER)) {
            while(rs.next()){
                Department department = new Department();
                department.setId(rs.getLong("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
            return departments;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return departments;
    }
}