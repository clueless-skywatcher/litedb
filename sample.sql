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