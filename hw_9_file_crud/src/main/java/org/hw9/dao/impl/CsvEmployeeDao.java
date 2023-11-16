package org.hw9.dao.impl;

import com.google.gson.Gson;
import org.hw9.dao.EmployeeDao;
import org.hw9.entity.Employee;
import org.hw9.util.EmployeeDbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvEmployeeDao implements EmployeeDao {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void create(Employee employee) {
        readCsv();
        employee.setId((int) EmployeeDbUtil.generateId(employees));
        employees.add(employee);
        writeCsv();
    }

    @Override
    public void update(Employee employee) {
        readCsv();
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (String.valueOf(employees.get(i).getId()).equals(String.valueOf(employee.getId()))) {
                index = i;
            }
        }
        if (index != -1) {
            employees.set(index, employee);
        }
        writeCsv();
    }

    @Override
    public void delete(String id) {
        readCsv();
        employees.removeIf(employee -> String.valueOf(employee.getId()).equals(id.toString()));
        writeCsv();
    }

    @Override
    public boolean existsById(String id) {
        readCsv();
        return employees.stream().anyMatch(employee -> String.valueOf(employee.getId()).equals(id.toString()));
    }

    @Override
    public Optional<Employee> findById(String id) {
        readCsv();
        return employees.stream().filter(s -> String.valueOf(s.getId()).equals(id.toString())).findFirst();
    }

    @Override
    public Collection<Employee> findAll() {
        readCsv();
        return this.employees;
    }


    private void readCsv() {
        Gson gson = new Gson();
        try {
            Employee[] from = gson.fromJson(new FileReader("employee.json"), Employee[].class);
            if (from != null) {
                this.employees = new ArrayList<>();
                this.employees.addAll(Arrays.asList(from));
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeCsv() {
        Gson gson = new Gson();
        String json = gson.toJson(this.employees);
        try(FileWriter fileWriter = new FileWriter("employee.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
