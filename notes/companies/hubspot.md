Coding Questions
--
Q.) Write a function that, given a list of numbers and a target number N, returns a list of pairs of numbers that sum to 
the target.

Q.)  Write a function that, given two sorted lists of numbers and a target value V, returns a sorted merged list with at 
most V elements.

Q.) Given an array of strings with only lowercase letters, create a function that returns an array of those same strings,
but each string has its letters rearranged such that it becomes a palindrome

Q.) Given a string s, and a number n, return the most common n-gram in s

Behavioral Questions
--
Q.)How did you manage to solve a communication problem withing your last group project?

Q.)How do you act when you fail?

Q.)Describe a hack that you were particularly proud of, ashamed of, or both.

Q.)Talk about a time you taught someone something  

Q.)Tell me about a time you were behind on a deadline?
  
Q.)How would you handle having a proposal for a design or new technology rejected?

Q.)Was there any situation when you just gave up on something?

Q.) Describe a time where you had to make a decision on a technology direction for a project. What was the project, the 
technology, and why did you choose it?

Design Questions
--
Q.) Design a shortening url.

SQL|NoSQL
---|---
Data use schemas|Data is schema-less
Relations|No(or a very few) relations
Data is distributed around multiple tables| Data is typically merged/nested in a few collections
Horizontal scaling is difficult/impossible; Vertical scaling is possible|Both horizontal and vertical scaling is possible
Limitations for lots of(thousands) read & write queries per second|Great performance for mass(simple) read & write requests

SQL Scaling
* Data partitioning - Pick a column, Pick a method
(Hashing, Range), Copies of reference tables, co-location
* Distributed SQL - Perform Select/Join/Aggregation on individual shards and collect the data
* Scaling transactions - 2PC; Prepare transactions on disk then commit. If a node goes down because transactions were 
saved it will be executed. Deadlock detection.

Citus provide a distribution layer for PostgreSQL.

MD5 produces a 128 bit hash while SHA produces 160bit hash

Q.) How you made an API in the past?

Q.) Design a database model for a social network with people who write posts and can have friends. Write a query to 
extract the latest posts from your friends.

Q.) How would you spec out a commenting application? 

Q.) How would you build a knowledge-sharing system? 

Q.) Design a system that can efficiently scale and store the count of all unique words from a very large document-based
corpus of text.

Q.)How and why would you use indexing on a table?

A database index is a data-structure that improves the speed of the data retrieval operations on a database table at the
cost of additional writes and storage space to maintain the index data structure. ex- Btree.  

Q.)What is a foreign key?

A Foreign key is a field(or collection of fields) in one table that refers to the PRIMARY KEY in other table. Foreign 
key can be NULL. A FOREIGN KEY constraint does not have to be linked only to a PRIMARY KEY constraint in another table;
it can also be defined to reference the columns of a UNIQUE constraint in another table.