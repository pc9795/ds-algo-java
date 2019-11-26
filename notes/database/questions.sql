--"first_name" from worker table using the alias name as "worker_name"
select first_name as worker_name from worker;

--"first_name" from worker table in upper case
select upper(first_name) from worker;

--unique values of department from worker table
select distinct department from worker;

--first three characters of first_name from worker table
select substring(first_name, 1, 3), first_name from worker;

--find the position of the alphabet('A') in the first_name column 'Amitabh' from worker table
--it is case sensitive
select position('A' in first_name) from worker where first_name = 'Amitabh';

--first_name from worker table after removing white spaces from the right side
select rtrim(first_name) from worker;

--departiment from the worker table after removign white spaces from the left side
select ltrim(department) from worker;

--unique values of department from worker table and prints its length
select length(department) from( select distinct department from worker) as temp;

--first_name from worker table after replacing 'A' wth 'B'
--there is a reexp_replace function
select first_name, replace(first_name,'a','A') from worker;

--first_name and last_name from worker table into a single column complete_name. A space char should separate them.
select concat(first_name, ' ', last_name) as complete_name from worker;

--all worker details from the worker table order by first_name ascending
select * from worker order by first_name asc;

--all worker details from the worker table order by first_name ascending and department descending
select first_name, department from worker order by first_name, department desc;

--workers with the first name as "Vipul" and "Satish" from worker table.
select * from worker where first_name in ('Vipul','Satish');

--workers excluding first names, "Vipul" and "Satish" from worker table.
select * from worker where first_name not in ('Vipul', 'Satish');

--workers with department name as "Admin"
select * from worker where department = 'Admin';

--workers whose first_name contains 'A'
select * from worker where first_name like '%A%';

--workers whose first_name with 'A'
select * from worker where first_name like '%a';

--workers whose first_name ends with h and contains six alphabets
select * from worker where first_name like '_____h';

--workers whose salary lies between 100000 and 500000
--inclusive
select * from worker where salary between 100000 and 500000;

--workers who have joined in feb 2014
select * from worker where extract(year from joining_date)= 2020 and extract(month from joining_date)= 2;

--count of employees working in the department 'Admin'
select count(*) from worker where department = 'Admin';

--worker names with salaries >=50000 and <=100000
--inclusive
select concat(first_name, ' ', last_name), salary from worker where salary between 50000 and 100000;

--no of workers for each department in the descending order
select count(*) as count, department from worker group by department order by count desc;

--workers who are also managers
--Joins are better that subquery for performance. Subquery are better that joins for readability.
select w.* from worker as w, title as t where w.worker_id = t.worker_ref_id and t.worker_title = 'Manager';

select * from worker as w where w.worker_id in (select t.worker_ref_id from title as t where t.worker_title = 'Manager');

--duplicate records having matching data in some fields of a table. 
select worker_title, affected_from from title group by worker_title, affected_from having count(*)>1;

--odd rows from a table. 
select * from  (select row_number() over () as row_num,* from worker) e  where mod(row_num,2) <> 0;


--even rows from a table.
select * from  (select row_number() over () as row_num,* from worker) e where mod(row_num,2) = 0;

--clone a new table from another table. 
--create table is super set and preferred. select into is not available in PL/pgSQL
create table worker_temp as ( select * from worker);
select * into worker_temp from worker;

--clone table without information
select * into worker_temp from worker where 1 = 0;

--intersecting records of two tables
--same number of columns
(select * from worker)
intersect 
(select * from worker_temp);

--records from one table that another table does not have
(select * from worker)
except 
(select * from worker_temp);

--show current date and time
select now();

--top n records of a table
select * from worker limit 10;

--nth highest salary from table
--taking it as sub table will return null if there is no value
select (select distinct salary from worker order by salary desc limit 1 offset 7) as temp;

--nth highest salary without using top or limit. 
select * from worker w1 
where (select count(distinct w2.salary) from worker w2 where w2.salary >= w1.salary)= 4;

--list of employees with the same salary. 
select distinct w.worker_id, w.first_name, w.salary from worker w, worker w1
where w.salary = w1.salary and w.worker_id <> w1.worker_id;

--second highest salary from a table
select distinct salary from  worker order by salary desc offset 1 limit 1;
select max(salary) from worker where salary not in ( select max(salary) from worker);

--one row twice in results from a table
--union removes duplicate records, union alll does not.
select * from worker union all select * from worker;

--first 50% records from a table.
--assuming that ids are contigious
select * fromworker where worker_id <= (select count(worker_id)/ 2 from worker);

--departments that have less than 5 people in it.
select department, count(*) from worker group by department
having count(*)<= 5;

--last record from a table
select * from worker where worker_id = (select max(worker_id) from worker);

--first row of a table
select * from worker
where worker_id = (select min(worker_id) from worker);

--last 5 records from a table
--assuming that ids are contigious
select * from worker w order by w.worker_id desc limit 5

--name of employees having the highest salary in each department
select first_name, w.salary from worker w, (select max(salary) as salary, department from worker group by department) as temp
where w.department=temp.department and w.salary=temp.salary;

--three max salaries from a table
select distinct salary from worker order by salary desc limit 3;

--three min salaries from a table
select distinct salary from worker order by salary limit 3;

--departments along with the total salaries paid for each of them
select department,sum(salary) from worker group by department;
 
--names of workers who earn the highest salary
select first_name,salary from worker where salary= (select max(salary) from worker);

--

--count of employees working in project 'P1'
select count(*) from employee_salary where project='P1';

--employee names having salary greaterh than or equal to 5000 and less than or equal 10000
select ed.full_name,es.salary 
from employee_details ed, employee_salary es 
where ed.emp_id=es.emp_id and es.salary between 5000 and 10000;

--project wise count of employees sorted by project's count in descending order.
select project, count(*) 
from employee_salary es 
group by es.project
order by count desc

--first name from the fullname column of employee_details table
select substring(full_name, 1, position(' ' in full_name))  from employee_details;

--employee names and salary records.Return employee details even if the salary record is not present for the employee.
select * 
from employee_details ed left join employee_salary es
on ed.emp_id=es.emp_id;

--employees who are also managers from employee details table.
select * 
from employee_details ed, employee_details ed1
where ed.emp_id=ed1.manager_id;

--employee records from employee details table who have a salary record in employee salary table
select ed.* 
from employee_details ed, employee_salary es
where ed.emp_id=es.emp_id;

select * from employee_details e
where exists (select * from employee_salary s where e.emp_id=s.emp_id);

--remove duplicates from a table without using temporary table
--it will remove all the values. 
delete from employee_details
where full_name in 
(select full_name from employee_details group by full_name having count(*)>1 );
 
delete from employee_details e1
where emp_id <> 
(select max(emp_id) from employee_details e2 where e2.full_name=e1.full_name);
---

--we can use group by with where
select emp_id, count(*) from employee_details where emp_id<>121 group by emp_id;

--case insensitive search
select * from employee_details where upper(full_name) like concat('%',upper('oHn'),'%');

---

--Leet code questions
--Interview bit questions

---

--posts of friends
--subquery
select * from post where user_id in (
	select user_id as friend from relationship where friend_id=1 union select friend_id as friend from relationship where user_id=1
) limit 1 offset 1;

--join
select * from post p,
(select case when user_id=2 then friend_id else user_id end as friend_id from relationship where user_id = 2 or friend_id = 2) as temp
where p.user_id=temp.friend_id;
 
--mutual friends
select case when user_id=1 then friend_id else user_id end as friend_id from relationship where user_id = 1 or friend_id = 1
intersect
select case when user_id=4 then friend_id else user_id end as friend_id from relationship where user_id = 4 or friend_id = 4;

--nested commenting
select * from "comments" where parent_post_id = 1 order by parent_id nulls first;
--with exists nested queries
 
 