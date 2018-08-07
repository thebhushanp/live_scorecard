create table country(id SERIAL primary key ,
code varchar(10) not null,
name varchar(30) not null);


create table player(id SERIAL primary key ,
age integer not null,
name varchar(80) not null);
