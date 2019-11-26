create table worker (
	worker_id serial primary key,
	first_name varchar,
	last_name varchar,
	salary int,
	joining_date timestamp,
	department varchar
);

insert into worker
	(worker_id, first_name, last_name, salary, joining_date, department) values
		(001, 'Monika', 'Arora', 100000, '14-02-20 09:00:00', 'HR'),
		(002, 'Niharika', 'Verma', 80000, '14-06-11 09:00:00', 'Admin'),
		(003, 'Vishal', 'Singhal', 300000, '14-02-20 09:00:00', 'HR'),
		(004, 'Amitabh', 'Singh', 500000, '14-02-20 09:00:00', 'Admin'),
		(005, 'Vivek', 'Bhati', 500000, '14-06-11 09:00:00', 'Admin'),
		(006, 'Vipul', 'Diwan', 200000, '14-06-11 09:00:00', 'Account'),
		(007, 'Satish', 'Kumar', 75000, '14-01-20 09:00:00', 'Account'),
		(008, 'Geetika', 'Chauhan', 90000, '14-04-11 09:00:00', 'Admin');

create table bonus (
	worker_ref_id INT,
	bonus_amount INT,
	bouns_date timestamp,
	foreign key (worker_ref_id)
		references Worker(worker_id)
        on delete cascade
);

insert into bonus
	(worker_ref_id, bonus_amount, bouns_date) VALUES
		(001, 5000, '16-02-20'),
		(002, 3000, '16-06-11'),
		(003, 4000, '16-02-20'),
		(001, 4500, '16-02-20'),
		(002, 3500, '16-06-11');

create table title (
	worker_ref_id INT,
	worker_title varchar,
	affected_from timestamp,
	foreign key (worker_ref_id)
		references worker(worker_id)
        on delete cascade
);

insert into title
	(worker_ref_id, worker_title, affected_from) values
 (001, 'Manager', '2016-02-20 00:00:00'),
 (002, 'Executive', '2016-06-11 00:00:00'),
 (008, 'Executive', '2016-06-11 00:00:00'),
 (005, 'Manager', '2016-06-11 00:00:00'),
 (004, 'Asst. Manager', '2016-06-11 00:00:00'),
 (007, 'Executive', '2016-06-11 00:00:00'),
 (006, 'Lead', '2016-06-11 00:00:00'),
 (003, 'Lead', '2016-06-11 00:00:00');
 
 create table employee_details(
	emp_id serial primary key,
	full_name varchar,
	manager_id int,
	date_of_joining date
);

insert into employee_details values
(121, 'John Snow', 321, '2014-01-31'),
(321, 'Walter White', 986, '2015-01-30'),
(421, 'Kuldeep Rana',876 , '2016-11-27');

create table employee_salary(
	emp_id int primary key,
	project varchar,
	salary int,
	foreign key(emp_id) references employee_details(emp_id)
);

insert into employee_salary values
(121,'P1',8000),
(321, 'P2', 1000),
(421,'P1',12000);

create table users(
	id serial primary key,
	name varchar unique not null
);

insert into users(name) values('prashant'), ('shivam'), ('gauri');
insert into users(name) values('varun');

create table relationship(
	user_id int,
	friend_id int,
	relationship int not null,
	initiator int not null,
	primary key(user_id, friend_id),
	check(user_id < friend_id),
	foreign key(user_id) references users(id)  on delete cascade,
	foreign key(friend_id) references users(id)  on delete cascade
);

insert into relationship(user_id,friend_id,relationship,initiator) values(1, 2, 1, 1), (1, 3, 1, 1);
insert into relationship(user_id,friend_id,relationship,initiator) values(2, 4, 2, 1);

create table post(
	id serial primary key,
	title varchar not null,
	content varchar not null,
	user_id int not null,
	foreign key(user_id) references users(id) on delete cascade
);

insert into post(title, content, user_id) values('title1','content1',2),('title2','content2',3);
insert into post(title, content, user_id) values('title3','content3',4),('title1','content1',1);

create table comments(
	id serial primary key,
	comment varchar not null,
	parent_id int,
	parent_post_id int not null,
	foreign key(parent_id) references comments(id)
);

insert into comments(comment,parent_id,parent_post_id) values('comment1',null,1),('comment2',1,1),('comment3',2,1);