package org.hw11.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hw11.dao.DepartmentDao;
import org.hw11.entity.Department;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public void create(Department entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Department entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        if (findById(id).isPresent()) {
            entityManager.remove(findById(id).get());
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Department> findAll() {
        return (List<Department>) entityManager.createQuery("select d from Department d").getResultList();
    }
}