create table employees
(
    id bigint auto_increment primary key,
    first_name varchar(45),
    last_name varchar(45),
    age int,
    department varchar(45)
);

create table departments
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table dep_emp
(
    dep_id bigint not null,
    emp_id bigint not null,
    primary key (dep_id, emp_id),
    foreign key (dep_id) references departments(id),
    foreign key (emp_id) references employees(id)
);