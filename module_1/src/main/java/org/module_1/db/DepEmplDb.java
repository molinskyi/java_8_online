package org.module_1.db;

import org.module_1.entity.Department;
import org.module_1.entity.Employee;

public class DepEmplDb {
    private static int connection[][] = new int[10][10];
    private int lastDepartmentIndex = 0;
    private int lastEmployeeIndex = 0;

    public void create(Department department) {
        if (lastDepartmentIndex == connection.length - 1) {
            int newConnection[][] = new int[connection.length * 2][connection[lastDepartmentIndex].length];
            System.arraycopy(connection, 0, newConnection, 0, connection.length);
            connection = newConnection;
            connection[lastDepartmentIndex][0] = Integer.valueOf(department.getId());
            lastDepartmentIndex++;
        } else {
            connection[lastDepartmentIndex][0] = Integer.valueOf(department.getId());
            lastDepartmentIndex++;
        }
    }

    String name;

    public void add(Employee employee, String id, Department[] department) {
        for (int i = 0; i < connection.length; i++) {
            if (connection[i][0] == Integer.valueOf(id)) {
                if (lastEmployeeIndex == connection[i].length - 1) {
                    int newConnection[][] = new int[i][connection[i].length * 2];
                    System.arraycopy(connection, 0, newConnection, 0, connection.length);
                    connection = newConnection;
                    for (int j = 0; j < connection[i].length; j++) {
                        if (connection[i][j] == 0) {
                            lastEmployeeIndex = j;
                            break;
                        }
                    }
                    connection[i][lastEmployeeIndex] = Integer.parseInt(employee.getId());
                    for (int j = 0; j < department.length; j++) {
                        if (department[j] != null) {
                            if (department[j].getId().equalsIgnoreCase(id)) {
                                name = department[j].getName();
                            }
                        }
                    }
                    employee.setDepartment(name);
                } else {
                    for (int j = 0; j < connection[i].length; j++) {
                        if (connection[i][j] == 0) {
                            lastEmployeeIndex = j;
                            break;
                        }
                    }
                    connection[i][lastEmployeeIndex] = Integer.parseInt(employee.getId());
                    for (int j = 0; j < department.length; j++) {
                        if (department[j] != null) {
                            if (department[j].getId().equalsIgnoreCase(id)) {
                                name = department[j].getName();
                            }
                        }
                    }
                    employee.setDepartment(name);
                }
            }
        }
    }

    public void find() {
        for (int k = 0; k < connection.length; k++) {
            System.out.print("{");
            for (int i = 0; i < connection[k].length; i++) {
                System.out.print(connection[k][i] + ",");
            }
            System.out.println("}");
        }
    }

    public void deleteEmployee(String id) {
        for (int i = 0; i < connection.length; i++) {
            for (int j = 1; j < connection[i].length; j++) {
                if (connection[i][j] == Integer.valueOf(id)) {
                    connection[i][j] = 0;
                }
            }
        }
    }

    public int deleteDepartment(String id) {
        int mb = 0;
        for (int i = 0; i < connection.length; i++) {
            if (connection[i][0] == Integer.valueOf(id)) {
                for (int j = 1; j < connection[i].length; j++) {
                    if (connection[i][j] != 0) {
                        mb = connection[i][j];
                    }
                }
            }
        }
        return mb;
    }
}