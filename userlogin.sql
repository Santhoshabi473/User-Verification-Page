use sys;



create table userlogin(
Id int primary key,
name varchar(100) not null,
userpassword varchar(100) not null
);


insert into userlogin(id,name,userpassword)
values
(1,'aravind','aravind@123'),
(2,'santhosh','sandy@123');
select*from userlogin;
drop table userlogin;