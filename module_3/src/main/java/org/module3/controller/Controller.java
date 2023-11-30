package org.module3.controller;

import au.com.bytecode.opencsv.CSVWriter;
import org.module3.dao.HistoryCrudDao;
import org.module3.dao.impl.HistoryCrudDaoImpl;
import org.module3.entity.Account;
import org.module3.entity.Operation;
import org.module3.entity.User;
import org.module3.service.AccountCrudService;
import org.module3.service.OperationCrudService;
import org.module3.service.UserCrudService;
import org.module3.service.impl.AccountCrudServiceImpl;
import org.module3.service.impl.OperationCrudServiceImpl;
import org.module3.service.impl.UserCrudServiceImpl;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Controller {
    AccountCrudService accountCrudService = new AccountCrudServiceImpl();
    UserCrudService userCrudService = new UserCrudServiceImpl();
    OperationCrudService operationCrudService = new OperationCrudServiceImpl();
    HistoryCrudDao historyCrudDao = new HistoryCrudDaoImpl();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create user please enter 1");
        System.out.println("If you want create account please enter 2");
        System.out.println("If you want find user info please enter 3");
        System.out.println("If you want make operation please enter 4");
        System.out.println("If you want export history please enter 5");
        System.out.println("If you want close app please enter 6");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> createUser(bufferedReader);
            case "2" -> createAccount(bufferedReader);
            case "3" -> findOne(bufferedReader);
            case "4" -> makeOperation(bufferedReader);
            case "5" -> history(bufferedReader);
            case "6" -> System.exit(0);
        }
    }

    private void history(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter account id :");
        String id = bufferedReader.readLine();
        System.out.println("Enter start date in format YYYY-MM-DD :");
        String date1 = bufferedReader.readLine();;
        System.out.println("Enter end date in format YYYY-MM-DD :");
        String date2 = bufferedReader.readLine();
        writeHistoryCsv(Long.valueOf(id),date1,date2);
    }

    private void makeOperation(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter user id send from:");
        String idUserFrom = bufferedReader.readLine();
        System.out.println("Choose account id");
        for (Account a :
                accountCrudService.findAll()) {
            if (a.getUser_id().equals(idUserFrom)) {
                System.out.println(a.getId());
            }
        }
        String idFrom = bufferedReader.readLine();
        System.out.println("Enter user id send to:");
        String idUserTo = bufferedReader.readLine();
        System.out.println("Choose account id");
        for (Account a :
                accountCrudService.findAll()) {
            if (a.getUser_id().equals(idUserTo)) {
                System.out.println(a.getId());
            }
        }
        String idTo = bufferedReader.readLine();
        System.out.println("Amount:");
        String amount = bufferedReader.readLine();
        Date date = new Date();

        Account account;
        try {
            if (accountCrudService.findOne(idFrom).get().getAmount() >= Long.valueOf(amount)) {
                if (Long.parseLong(amount) != 0) {
                    Operation operation1 = new Operation();
                    Operation operation2 = new Operation();
                    operation1.setFrom_id(Long.valueOf(idFrom));
                    operation1.setTo_id(Long.valueOf(idTo));
                    operation1.setAmount(Long.valueOf(amount) * (-1L));
                    operation1.setDate(date);
                    operation1.setCategory("costs");
                    operationCrudService.create(operation1);
                    operation2.setFrom_id(Long.valueOf(idTo));
                    operation2.setTo_id(Long.valueOf(idFrom));
                    operation2.setAmount(Long.valueOf(amount));
                    operation2.setDate(date);
                    operation2.setCategory("income");
                    operationCrudService.create(operation2);
                    account = accountCrudService.findOne(idFrom).get();
                    account.setAmount(account.getAmount() - Long.parseLong(amount));
                    accountCrudService.update(account);
                    account = accountCrudService.findOne(idTo).get();
                    account.setAmount(account.getAmount() + Long.parseLong(amount));
                    accountCrudService.update(account);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("e = " + e);
        }
    }

    private long totalAmount(String id) {
        Optional<User> user = userCrudService.findOne(id);
        long amount = 0;
        for (Account a :
                accountCrudService.findAll()) {
            if (a.getUser_id().equals(id)) {
                amount += a.getAmount();
            }
        }
        return amount;
    }

    private void findOne(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter user id:");
        String id = bufferedReader.readLine();
        Optional<User> user = userCrudService.findOne(id);
        long amount = 0;
        for (Account a :
                accountCrudService.findAll()) {
            if (a.getUser_id().equals(id)) {
                amount += a.getAmount();
            }
        }
        System.out.println("ID: " + user.get().getId());
        System.out.println("User name: " + user.get().getName());
        System.out.println("amount = " + amount);
    }

    private void createAccount(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter user id to create and connect account:");
        String id = bufferedReader.readLine();
        System.out.println("Enter amount to deposit:");
        String deposit = bufferedReader.readLine();
        Account account = new Account();
        account.setUser_id(id);
        account.setAmount(Long.valueOf(deposit));
        accountCrudService.create(account);
    }

    private void createUser(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter name:");
        String name = bufferedReader.readLine();
        User user = new User();
        user.setName(name);
        userCrudService.create(user);
    }
    Operation operation = new Operation();
    private void writeHistoryCsv(Long id, String date1, String date2) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("history.csv"), ';')) {
            List<String[]> list = new ArrayList<>();
            String[] strings = new String[0];
            for (Operation o : historyCrudDao.findAllNoPagination(id, date1, date2)) {
                System.out.println(String.valueOf(o.getDate()));
                    strings = new String[]{
                            String.valueOf(o.getId()),
                            String.valueOf(o.getFrom_id()),
                            String.valueOf(o.getTo_id()),
                            String.valueOf(o.getAmount()),
                            String.valueOf(o.getDate()),
                            o.getCategory()
                    };
                    list.add(strings);
            }
            System.out.println(list.size());
            csvWriter.writeAll(list);
            csvWriter.flush();
        }

    }
}