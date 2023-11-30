package org.module3.service;

import org.module3.entity.Operation;

import java.util.Collection;

public interface OperationCrudService extends CrudService<Operation>{
    Collection<Operation> findAll();
}
