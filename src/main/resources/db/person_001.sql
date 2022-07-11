/*Скрипт таблицы person, и первичные данные в ней*/
create table if not exists person
(
    id serial primary key,
    login varchar(2000),
    password varchar(2000),
    employee_id int references employee(id)
);
insert into person(login, password, employee_id)
values('parsentev', '123', (select distinct id from employee where inn = 12345678));
insert into person(login, password, employee_id)
values('ban', '123', (select distinct id from employee where inn = 12345678));
insert into person(login, password, employee_id)
values('ivan', '123', (select distinct id from employee where inn = 87654321));
