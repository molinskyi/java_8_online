package org.module3.dao;

import org.module3.entity.Account;

import java.util.Collection;

public interface AccountCrudDao extends CrudDao<Account>{
    Collection<Account> findAll();
}
