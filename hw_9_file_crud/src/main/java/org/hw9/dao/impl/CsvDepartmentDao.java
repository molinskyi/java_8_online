package org.hw9.dao.impl;

import com.google.gson.Gson;
import org.hw9.dao.DepartmentDao;
import org.hw9.entity.Department;
import org.hw9.util.DepartmentDbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvDepartmentDao implements DepartmentDao {
    private List<Department> departments = new ArrayList<>();

    @Override
    public void create(Department department) {
        readCsv();
        department.setId((int) DepartmentDbUtil.generateId(departments));
        departments.add(department);
        writeCsv();
    }

    @Override
    public void update(Department department) {
        readCsv();
        int index = -1;
        for (int i = 0; i < departments.size(); i++) {
            if (String.valueOf(departments.get(i).getId()).equals(String.valueOf(department.getId()))) {
                index = i;
            }
        }
        if (index != -1) {
            departments.set(index, department);
        }
        writeCsv();
    }

    @Override
    public void delete(String id) {
        readCsv();
        departments.removeIf(department -> String.valueOf(department.getId()).equals(id.toString()));
        writeCsv();
    }

    @Override
    public boolean existsById(String id) {
        readCsv();
        return departments.stream().anyMatch(department -> String.valueOf(department.getId()).equals(id.toString()));
    }

    @Override
    public Optional<Department> findById(String id) {
        readCsv();
        return departments.stream().filter(s -> String.valueOf(s.getId()).equals(id.toString())).findFirst();
    }

    @Override
    public Collection<Department> findAll() {
        readCsv();
        return this.departments;
    }

    private void readCsv() {
        Gson gson = new Gson();
        try {
            Department[] from = gson.fromJson(new FileReader("department.json"), Department[].class);
            if (from != null) {
                this.departments = new ArrayList<>();
                this.departments.addAll(Arrays.asList(from));
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeCsv() {
        Gson gson = new Gson();
        String json = gson.toJson(this.departments);
        try(FileWriter fileWriter = new FileWriter("department.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
