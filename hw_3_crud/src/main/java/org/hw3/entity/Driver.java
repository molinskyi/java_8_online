package org.hw3.entity;

public class Driver extends BaseEntity {
    private String name;
    private String licenceCategory;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenceCategory() {
        return licenceCategory;
    }

    public void setLicenceCategory(String licenceCategory) {
        this.licenceCategory = licenceCategory;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
