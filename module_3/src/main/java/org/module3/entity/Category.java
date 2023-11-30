package org.module3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "operations")
public class Category {
    @Column(name = "category")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
