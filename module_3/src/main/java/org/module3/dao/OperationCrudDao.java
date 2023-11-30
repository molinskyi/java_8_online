package org.module3.dao;

import org.module3.entity.Operation;

import java.util.Collection;

public interface OperationCrudDao extends CrudDao<Operation>{
    Collection<Operation> findAll();
}
