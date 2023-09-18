package org.module_1.entity;

public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if(this.department == null){
            this.department = department;
        }else if(department != this.department){
            this.department += ", " + department;
        }else{this.department = department;}
    }

    public void setterNew(String department){
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
