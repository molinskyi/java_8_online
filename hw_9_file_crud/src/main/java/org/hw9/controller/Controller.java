package org.hw9.controller;

import org.hw9.dao.DepartmentDao;
import org.hw9.dao.impl.CsvDepartmentDao;
import org.hw9.entity.Department;
import org.hw9.entity.Employee;
import org.hw9.service.DepartmentCrudService;
import org.hw9.service.EmployeeCrudService;
import org.hw9.service.impl.DepartmentCrudServiceImpl;
import org.hw9.service.impl.EmployeeCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Controller {
    private EmployeeCrudService employeeCrudService = new EmployeeCrudServiceImpl();
    private DepartmentCrudService departmentCrudService = new DepartmentCrudServiceImpl();
    private DepartmentDao departmentDao = new CsvDepartmentDao();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create employee please enter 1");
        System.out.println("If you want create department please enter 2");
        System.out.println("If you want find employee please enter 3");
        System.out.println("If you want find department please enter 4");
        System.out.println("If you want delete employee please enter 5");
        System.out.println("If you want delete department please enter 6");
        System.out.println("If you want update employee please enter 7");
        System.out.println("If you want update department please enter 8");
        System.out.println("If you want find all employees please enter 9");
        System.out.println("If you want find all departments please enter 10");
        System.out.println("If you want connect employee to department 11");
        System.out.println("If you want close app please enter 12");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> createEmployee(bufferedReader);
            case "2" -> createDepartment(bufferedReader);
            case "3" -> findOneEmployee(bufferedReader);
            case "4" -> findOneDepartment(bufferedReader);
            case "5" -> deleteEmployee(bufferedReader);
            case "6" -> deleteDepartment(bufferedReader);
            case "7" -> updateEmployee(bufferedReader);
            case "8" -> updateDepartment(bufferedReader);
            case "9" -> findAllEmployee();
            case "10" -> findAllDepartment();
            case "11" -> connect(bufferedReader);
            case "12" -> System.exit(0);
        }
    }

    private void createEmployee(BufferedReader reader) throws IOException {
        System.out.println("Please enter first name");
        String fn = reader.readLine();
        System.out.println("Please enter last name");
        String ln = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        Employee employee = new Employee();
        employee.setFn(fn);
        employee.setLn(ln);
        employee.setAge(age);
        employeeCrudService.create(employee);
    }

    private void createDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        Department department = new Department();
        department.setName(n);
        departmentCrudService.create(department);
    }

    private void updateEmployee(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        int id = Integer.valueOf(reader.readLine());
        System.out.println("Please enter first name");
        String fn = reader.readLine();
        System.out.println("Please enter last name");
        String ln = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFn(fn);
        employee.setLn(ln);
        employee.setAge(age);
        employee.setDepartment(employeeCrudService.findOne(String.valueOf(id)).getDepartment());
        employeeCrudService.update(employee);
    }

    private void updateDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        int id = Integer.valueOf(reader.readLine());
        System.out.println("Please enter first name");
        String n = reader.readLine();
        Department department = new Department();
        department.setId(id);
        department.setName(n);
        departmentCrudService.update(department);
    }

    private void findOneEmployee(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        Employee employee = employeeCrudService.findOne(id);
        System.out.println("id = " + employee.getId());
        System.out.println("first name = " + employee.getFn());
        System.out.println("last name = " + employee.getLn());
        System.out.println("age = " + employee.getAge());
        System.out.println("department = " + employee.getDepartment());
    }

    private void findOneDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        Department department = departmentCrudService.findOne(id);
        System.out.println("id = " + department.getId());
        System.out.println("name = " + department.getName());
    }

    private Department findOneDepartmentForDelete(String id) throws IOException {
        Department department = departmentCrudService.findOne(id);
        return department;
    }

    private void deleteEmployee(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        employeeCrudService.delete(id);
    }

    private void deleteDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        Employee employee = new Employee();
        if (departmentDao.existsById(id)) {
            Collection<Employee> employees = employeeCrudService.findAll();
            for (Employee e :
                    employees) {
                if (e.getDepartment().equals((findOneDepartmentForDelete(id).getName()))) {
                    employee = employeeCrudService.findOne(String.valueOf(e.getId()));
                    employee.setDepartment(null);
                }
            }
        }
        employeeCrudService.update(employee);
        departmentCrudService.delete(id);
    }

    private void findAllDepartment() {
        Collection<Department> departments = departmentCrudService.findAll();
        departments.forEach(department -> {
            System.out.println("id = " + department.getId());
            System.out.println("name = " + department.getName());
        });
    }

    private void findAllEmployee() {
        Collection<Employee> employees = employeeCrudService.findAll();
        employees.forEach(employee -> {
            System.out.println("id = " + employee.getId());
            System.out.println("first name = " + employee.getFn());
            System.out.println("last name = " + employee.getLn());
            System.out.println("age = " + employee.getAge());
            System.out.println("department = " + employee.getDepartment());
        });
    }

    private void connect(BufferedReader reader) throws IOException {
        System.out.println("Please enter department id");
        int idDep = Integer.valueOf(reader.readLine());
        System.out.println("Please enter employee id");
        int idEmpl = Integer.valueOf(reader.readLine());
        Department department = new Department();
        department = departmentCrudService.findOne(String.valueOf(idDep));
        Employee employee = new Employee();
        employee.setId(idEmpl);
        employee.setFn(employeeCrudService.findOne(String.valueOf(idEmpl)).getFn());
        employee.setLn(employeeCrudService.findOne(String.valueOf(idEmpl)).getLn());
        employee.setAge(employeeCrudService.findOne(String.valueOf(idEmpl)).getAge());
        employee.setDepartment(department.getName());
        employeeCrudService.update(employee);

    }
}
