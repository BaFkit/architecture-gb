create table if not exists students
(
    id   bigserial primary key,
    name varchar(255),
    age  int
);

insert into students (name, age)
values ('Anton', 20),
       ('Viktor', 22),
       ('Misha', 25);