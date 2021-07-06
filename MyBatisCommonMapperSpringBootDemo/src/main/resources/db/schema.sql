drop table if exists vos.user;
create table vos.user
(
    id int auto_increment
        primary key,
    name varchar(10) null,
    address varchar(32) null
);