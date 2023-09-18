package org.module_1.db;

import org.module_1.entity.Department;
import org.module_1.entity.Employee;

import java.io.IOException;
import java.util.Arrays;

public class EmployeeDb {
    private static Employee[] employees = new Employee[10];
    private int lastEmployeeIndex = 0;

    DepEmplDb depEmplDb = new DepEmplDb();

    public void create(Employee employee) {
        if (lastEmployeeIndex == employees.length - 1) {
            Employee[] newEmployee = new Employee[employees.length * 2];
            System.arraycopy(employees, 0, newEmployee, 0, employees.length);
            employees = newEmployee;
            add(employee);
        } else {
            add(employee);
        }
    }

    public void add(Employee employee) {
        employee.setId(String.valueOf(lastEmployeeIndex + 1));
        employees[lastEmployeeIndex] = employee;
        lastEmployeeIndex++;
    }

    int index = 0;

    public Employee findOne(String id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId().equalsIgnoreCase(id)) {
                    index = i;
                }
            }
        }
        return employees[index];
    }

    public Employee[] findAll() {
        return employees;
    }

    public void deleteDepartment(Department department) {
        String newDepName;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                int index = employees[i].getDepartment().indexOf(department.getName());
                if (employees[i].getDepartment().contains(department.getName())) {
                    newDepName = employees[i].getDepartment().substring(0, index) +
                            employees[i].getDepartment().substring(index + department.getName().length(),
                                    employees[i].getDepartment().length());
                    employees[i].setterNew(newDepName);
                }
            }
        }
    }

    public Employee[] delete(String id) throws IOException {
        int k = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        Employee[] a = Arrays.copyOfRange(employees, 0, k);
        Employee[] b = Arrays.copyOfRange(employees, k + 1, employees.length);
        Employee[] employees = Arrays.copyOf(a, a.length + b.length + 1);
        System.arraycopy(b, 0, employees, a.length, b.length);
        depEmplDb.deleteEmployee(id);
        return this.employees = employees;
    }

    public void update(Department department, String name) {
        String newDepName;
        for (int i = 0; i < employees.length; i++) {
            if(!(employees[i] == null)) {
                int index = employees[i].getDepartment().indexOf(department.getName());
                if(employees[i].getDepartment().contains(department.getName())){
                    newDepName = employees[i].getDepartment().substring(0, index) + name +
                            employees[i].getDepartment().substring(index + department.getName().length(),
                                    employees[i].getDepartment().length());
                    employees[i].setterNew(newDepName);
                }
            }
        }

    }
}
