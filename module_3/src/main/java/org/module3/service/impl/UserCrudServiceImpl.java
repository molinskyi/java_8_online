package org.module3.service.impl;

import org.module3.dao.UserCrudDao;
import org.module3.dao.impl.UserCrudDaoImpl;
import org.module3.entity.User;
import org.module3.service.UserCrudService;

import java.util.Optional;

public class UserCrudServiceImpl implements UserCrudService {
    UserCrudDao userCrudDao = new UserCrudDaoImpl();

    @Override
    public void create(User user) {
        userCrudDao.create(user);
    }

    @Override
    public void update(User user) {
        userCrudDao.update(user);
    }

    @Override
    public void delete(String id) {
        userCrudDao.delete(Long.valueOf(id));
    }

    @Override
    public Optional<User> findOne(String id) {
        return userCrudDao.findById(Long.valueOf(id));
    }
}
