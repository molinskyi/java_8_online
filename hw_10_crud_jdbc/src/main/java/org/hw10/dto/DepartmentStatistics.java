package org.hw10.dto;

public class DepartmentStatistics {
    private Long departmentId;
    private String name;
    public Long countOfEmployees;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountOfEmployees() {
        return countOfEmployees;
    }

    public void setCountOfEmployees(Long countOfEmployees) {
        this.countOfEmployees = countOfEmployees;
    }

    @Override
    public String toString() {
        return "DepartmentStatistics{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", countOfEmployees=" + countOfEmployees +
                '}';
    }
}
