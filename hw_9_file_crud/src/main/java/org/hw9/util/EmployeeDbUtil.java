package org.hw9.util;

import org.hw9.entity.Employee;

import java.util.Collection;
import java.util.Random;

public class EmployeeDbUtil {
    private final static Random random = new Random();
    public static <E extends Employee> long generateId(Collection<E> entities) {
        long id = random.nextLong(1_000_000);
        if (entities.stream().anyMatch(e -> String.valueOf(e.getId()).equals(String.valueOf(id)))) {
            generateId(entities);
        }
        return id;
    }
}
