package org.module_1.db;

import org.module_1.entity.Department;

import java.util.Arrays;

public class DepartmentDb {

    private static Department[] departments = new Department[10];
    private int lastDriverIndex = 0;
    DepEmplDb depEmplDb = new DepEmplDb();
    EmployeeDb employeeDb = new EmployeeDb();

    public void create(Department department) {
        if (lastDriverIndex == departments.length - 1) {
            Department[] newDepartment = new Department[departments.length * 2];
            System.arraycopy(departments, 0, newDepartment, 0, departments.length);
            departments = newDepartment;
            department.setId(String.valueOf(lastDriverIndex + 1));
            departments[lastDriverIndex] = department;
            lastDriverIndex++;

        } else {
            department.setId(String.valueOf(lastDriverIndex + 1));
            departments[lastDriverIndex] = department;
            lastDriverIndex++;
        }
        depEmplDb.create(department);
    }

    int index = 0;

    public Department findOne(String id) {
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                if (departments[i].getId().equalsIgnoreCase(id)) {
                    index = i;
                }
            }
        }
        return departments[index];
    }

    public void delete(String id) {
        int k = 0;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                if (departments[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        Department[] a = Arrays.copyOfRange(departments, 0, k);
        Department[] b = Arrays.copyOfRange(departments, k + 1, departments.length);
        Department[] departments = Arrays.copyOf(a, a.length + b.length + 1);
        System.arraycopy(b, 0, departments, a.length, b.length);
        depEmplDb.deleteEmployee(id);
    }


    public Department[] findAll() {
        return departments;
    }

}
