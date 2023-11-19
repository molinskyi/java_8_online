package org.module_2.entity;

import java.util.ArrayList;
import java.util.List;

public class City extends BaseEntity{
    String name;
    List<Neighbor> neighbors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }
}
