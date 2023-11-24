package org.hw10.controller;

import org.hw10.dao.DepartmentDao;
import org.hw10.dao.EmployeeDao;
import org.hw10.dao.impl.DepartmentDaoImpl;
import org.hw10.dao.impl.EmployeeDaoImpl;
import org.hw10.entity.Department;
import org.hw10.entity.Employee;
import org.hw10.service.DepartmentCrudService;
import org.hw10.service.EmployeeCrudService;
import org.hw10.service.impl.DepartmentCrudServiceImpl;
import org.hw10.service.impl.EmployeeCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Controller {
    private final EmployeeCrudService employeeCrudService = new EmployeeCrudServiceImpl();
    private final DepartmentCrudService departmentCrudService = new DepartmentCrudServiceImpl();
    private final DepartmentDao departmentDao = new DepartmentDaoImpl();
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
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
            case "9" -> findAllEmployee(bufferedReader);
            case "10" -> findAllDepartment(bufferedReader);
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
        Long id = Long.valueOf(reader.readLine());
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
        Long id = Long.valueOf(reader.readLine());
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
        return departmentCrudService.findOne(id);
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
        if (departmentDao.findById(Long.valueOf(id)).isPresent()) {
            Collection<Employee> employees = employeeDao.findAllNoPagination();
            for (Employee e :
                    employees) {
                if(e.getDepartment() != null) {
                    if (e.getDepartment().equals((findOneDepartmentForDelete(id).getName()))) {
                        employee = employeeCrudService.findOne(String.valueOf(e.getId()));
                        employee.setDepartment(null);
                        employeeCrudService.update(employee);
                    }
                }

            }
        }

        departmentCrudService.delete(id);
    }

    private void findAllDepartment(BufferedReader bufferedReader) throws IOException {
        Collection<Department> departments = departmentDao.findAllNoPagination();
        departments.forEach(department -> {
            System.out.println("id = " + department.getId());
            System.out.println("first name = " + department.getName());
        });
    }

    private void findAllEmployee(BufferedReader bufferedReader) {
        Collection<Employee> employees = employeeDao.findAllNoPagination();
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
        int idDep = Integer.parseInt(reader.readLine());
        System.out.println("Please enter employee id");
        int idEmp = Integer.parseInt(reader.readLine());
        Department department = new Department();
        department = departmentCrudService.findOne(String.valueOf(idDep));
        Employee employee = new Employee();
        employee.setId((long) idEmp);
        employee.setFn(employeeCrudService.findOne(String.valueOf(idEmp)).getFn());
        employee.setLn(employeeCrudService.findOne(String.valueOf(idEmp)).getLn());
        employee.setAge(employeeCrudService.findOne(String.valueOf(idEmp)).getAge());
        employee.setDepartment(department.getName());
        employeeCrudService.update(employee);

    }
}
