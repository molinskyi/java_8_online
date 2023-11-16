package org.hw9.util;

import org.hw9.entity.Department;

import java.util.Collection;
import java.util.Random;

public class DepartmentDbUtil {
    private final static Random random = new Random();
    public static <E extends Department> long generateId(Collection<E> entities) {
        long id = random.nextInt(1_000_000);
        if (entities.stream().anyMatch(e -> String.valueOf(e.getId()).equals(String.valueOf(id)))) {
            generateId(entities);
        }
        return id;
    }
}
