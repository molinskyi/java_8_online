package org.module3.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.module3.dao.OperationCrudDao;
import org.module3.entity.Operation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OperationCrudDaoImpl implements OperationCrudDao {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public void create(Operation entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Operation entity) {
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
    public Optional<Operation> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Operation.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Operation> findAll() {
        return (List<Operation>) entityManager.createQuery("select o from Operation o").getResultList();
    }
}
