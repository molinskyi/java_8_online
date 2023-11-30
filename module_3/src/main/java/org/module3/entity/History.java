package org.module3.entity;

import java.util.List;

public class History extends BaseEntity {
    List<Operation> history;

    public List<Operation> getHistory() {
        return history;
    }

    public void setHistory(List<Operation> history) {
        this.history = history;
    }
}
