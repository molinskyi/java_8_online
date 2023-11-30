create schema module_3 default character set utf8;

use module_3;

create table users
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table accounts
(
    id bigint auto_increment primary key,
    user_id bigint not null,
    amount bigint not null,
    foreign key (user_id) references users(id)
);

create table operations
(
    id bigint auto_increment primary key,
    from_id bigint not null,
    to_id bigint not null,
    amount bigint not null,
    date varchar(45),
    category varchar(45),
    foreign key (from_id) references accounts(id),
    foreign key (to_id) references accounts(id)
);