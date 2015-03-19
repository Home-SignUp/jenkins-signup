drop table if exists person;
create table person (
	id integer generated by default as identity(start with 1) primary key,
	user_name varchar(255) not null,
	first_name varchar(255),
	last_name varchar(255)
);

insert into person (user_name, first_name, last_name) values ('thoward333', 'Trey', 'Howard');
insert into person (user_name, first_name, last_name) values ('joeherbers', 'Joe', 'Herbers');
insert into person (user_name, first_name, last_name) values ('jdoe', 'John', 'Doe');

-- select * from person;
