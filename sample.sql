create table students (id int, name varchar(100));
insert into students (id, name) values (1, 'Toby');
insert into students (id, name) values (2, 'Yumi');
insert into students (id, name) values (3, 'Damini');
insert into students (id, name) values (4, 'Alan');
insert into students (id, name) values (5, 'Rahul');

update students set name = 'Adi' where id = 1;
delete from students where id = 1;
delete from students where id = 4;

select * from students;

create table students2 (id2 int, name2 varchar(100));
insert into students2 (id2, name2) values (1, 'Toby');
insert into students2 (id2, name2) values (2, 'Yumi');
insert into students2 (id2, name2) values (3, 'Damini');
insert into students2 (id2, name2) values (4, 'Alan');
insert into students2 (id2, name2) values (5, 'Rahul');

select * from students, students2;

create table students3 (id int, name varchar(100));
insert into students3 (id, name) values (1, 'Toby');
insert into students3 (id, name) values (2, 'Yumi');