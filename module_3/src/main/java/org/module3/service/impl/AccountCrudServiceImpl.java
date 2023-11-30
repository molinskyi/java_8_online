package org.module3.service.impl;

import org.module3.dao.AccountCrudDao;
import org.module3.dao.impl.AccountCrudDaoImpl;
import org.module3.entity.Account;
import org.module3.service.AccountCrudService;

import java.util.Collection;
import java.util.Optional;

public class AccountCrudServiceImpl implements AccountCrudService {
    AccountCrudDao accountCrudDao = new AccountCrudDaoImpl();

    @Override
    public void create(Account account) {
        accountCrudDao.create(account);
    }

    @Override
    public void update(Account account) {
        accountCrudDao.update(account);
    }

    @Override
    public void delete(String id) {
        accountCrudDao.delete(Long.valueOf(id));
    }

    @Override
    public Optional<Account> findOne(String id) {
        return accountCrudDao.findById(Long.valueOf(id));
    }

    @Override
    public Collection<Account> findAll() {
        return accountCrudDao.findAll();
    }
}
