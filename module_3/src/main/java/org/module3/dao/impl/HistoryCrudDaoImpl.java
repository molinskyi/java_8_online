package org.module3.dao.impl;

import org.module3.dao.HistoryCrudDao;
import org.module3.entity.History;
import org.module3.entity.Operation;
import org.module3.factory.JdbcFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class HistoryCrudDaoImpl implements HistoryCrudDao {
    @Override
    public void create(History entity) {

    }

    @Override
    public void update(History entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<History> findById(Long id) {
        return Optional.empty();
    }
    private final JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    @Override
    public Collection<Operation> findAllNoPagination(Long id, String date1, String date2) {
        Collection<Operation> operations = new ArrayList<>();
        try(ResultSet rs = jdbcFactory.getStatement().executeQuery("select * from operations where from_id = '"+ id + "' " +
                "and date between '" + date1 +"' and '" + date2 + "'")) {
            while(rs.next()){
                Operation operation = new Operation();
                operation.setId(rs.getLong("id"));
                operation.setFrom_id(rs.getLong("from_id"));
                operation.setTo_id(rs.getLong("to_id"));
                operation.setAmount(rs.getLong("amount"));
                operation.setDate(rs.getDate("date"));
                operation.setCategory(rs.getString("category"));
                operations.add(operation);
                System.out.println(1);
            }
            return operations;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return operations;
    }
}
