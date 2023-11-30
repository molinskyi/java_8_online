package org.module3.dao;

import org.module3.entity.History;
import org.module3.entity.Operation;

import java.util.Collection;

public interface HistoryCrudDao extends CrudDao<History>{
    public Collection<Operation> findAllNoPagination(Long id, String date1, String date2);
}
