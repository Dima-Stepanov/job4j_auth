/*Таблица Employee содержит обязательно данные:
  имя и фамилия, ИНН, дата найма, а также ссылку на список его аккаунтов*/
create table if not exists employee
(
    id      serial primary key,
    name    varchar(100),
    surname varchar(100),
    inn     numeric not null unique,
    hiring timestamp
);

insert into employee(name, surname, inn, hiring)
VALUES ('Дима', 'Красин', 12345678, now());
insert into employee(name, surname, inn, hiring)
VALUES ('Вася', 'Майснер', 87654321, now());