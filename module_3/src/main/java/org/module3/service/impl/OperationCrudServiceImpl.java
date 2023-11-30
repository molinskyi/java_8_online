package org.module3.service.impl;

import org.module3.dao.OperationCrudDao;
import org.module3.dao.impl.OperationCrudDaoImpl;
import org.module3.entity.Operation;
import org.module3.service.OperationCrudService;

import java.util.Collection;
import java.util.Optional;

public class OperationCrudServiceImpl implements OperationCrudService {
    OperationCrudDao operationCrudDao = new OperationCrudDaoImpl();

    @Override
    public void create(Operation operation) {
        operationCrudDao.create(operation);
    }

    @Override
    public void update(Operation operation) {
        operationCrudDao.update(operation);
    }

    @Override
    public void delete(String id) {
        operationCrudDao.delete(Long.valueOf(id));
    }

    @Override
    public Optional<Operation> findOne(String id) {
        return operationCrudDao.findById(Long.valueOf(id));
    }

    @Override
    public Collection<Operation> findAll() {
        return null;
    }
}
