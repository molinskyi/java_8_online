package org.module3.service;

import org.module3.entity.Account;

import java.util.Collection;

public interface AccountCrudService extends CrudService<Account>{
    Collection<Account> findAll();
}
