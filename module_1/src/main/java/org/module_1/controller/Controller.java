package org.module_1.controller;

import org.module_1.db.DepEmplDb;
import org.module_1.db.DepartmentDb;
import org.module_1.db.EmployeeDb;
import org.module_1.entity.Department;
import org.module_1.entity.Employee;
import org.module_1.service.DepartmentCrudService;
import org.module_1.service.EmployeeCrudService;
import org.module_1.service.impl.DepartmentCrudServiceImpl;
import org.module_1.service.impl.EmployeeCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    DepartmentCrudService departmentCrudService = new DepartmentCrudServiceImpl();
    EmployeeCrudService employeeCrudService = new EmployeeCrudServiceImpl();
    DepartmentDb db = new DepartmentDb();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String position = "";
        while ((position) != null) {
            menu();
            position = bufferedReader.readLine();
            crud(position, bufferedReader);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Create department 1");
        System.out.println("Create employee 2");
        System.out.println("Close 3");
        System.out.println("Show departments 4");
        System.out.println("Show employees 5");
        System.out.println("Connect employee to department 6");
        System.out.println("Update department 7");
        System.out.println("Update employee 8");
        System.out.println("Delete employee 9");
        System.out.println("Delete department 10");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        String id = "";
        switch (position) {
            case ("1"):
                departmentCreate(bufferedReader);
                break;
            case ("2"):
                employeeCreate(bufferedReader);
                break;
            case ("3"):
                System.exit(0);
                break;
            case ("4"):
                findAllDepartments();
                break;
            case ("5"):
                findAllEmployees();
                break;
            case ("6"):
                connect(bufferedReader);
                break;
            case ("7"):
                System.out.println("Type id if you are going to update department");
                id = String.valueOf(bufferedReader.readLine());
                updateDepartment(db.findOne(id), bufferedReader);
                break;
            case ("8"):
                System.out.println("Type id if you are going to update employee");
                id = String.valueOf(bufferedReader.readLine());
                updateEmployee(employeeDb.findOne(id), bufferedReader);
                break;
            case ("9"):
                System.out.println("Type id if you are going to delete employee");
                id = String.valueOf(bufferedReader.readLine());
                employeeDb.delete(id);
                break;
            case ("10"):
                System.out.println("Type id if you are going to delete department");
                id = String.valueOf(bufferedReader.readLine());
                employeeDb.deleteDepartment(db.findOne(id));
                db.delete(id);
                break;
        }
    }

    private void departmentCreate(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        Department department = new Department();
        department.setName(n);
        departmentCrudService.create(department);
    }

    private void employeeCreate(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter last name");
        String ln = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        Employee employee = new Employee();
        employee.setFirstName(n);
        employee.setLastName(ln);
        employee.setAge(age);
        employeeCrudService.create(employee);
    }

    private void findAllEmployees() {
        Employee[] employees = employeeCrudService.findAll();
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            if (employee != null) {
                System.out.println("Department = " + employee.getDepartment());
                System.out.println("Name = " + employee.getFirstName());
                System.out.println("Last name = " + employee.getLastName());
                System.out.println("Age = " + employee.getAge());
                System.out.println("id= " + employee.getId());
                System.out.println();
            }
        }
    }

    private Department[] findAllDepartments() {
        Department[] departments = departmentCrudService.findAll();
        for (int i = 0; i < departments.length; i++) {
            Department department = departments[i];
            if (department != null) {
                System.out.println("Name = " + department.getName());
                System.out.println("id= " + department.getId());
                System.out.println();
            }
        }

        return departments;
    }

    DepEmplDb depEmplDb = new DepEmplDb();
    EmployeeDb employeeDb = new EmployeeDb();

    private void connect(BufferedReader reader) throws IOException {
        System.out.println("Enter department id");
        String depId = reader.readLine();
        System.out.println("Enter employee id");
        String emplId = reader.readLine();
        depEmplDb.add(employeeDb.findOne(emplId), depId, findAllDepartments());
    }

    private void updateDepartment(Department department, BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        employeeDb.update(department, n);
        department.setName(n);
    }

    private void updateEmployee(Employee employee, BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter last name");
        String ln = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        employee.setFirstName(n);
        employee.setLastName(ln);
        employee.setAge(age);
    }

}