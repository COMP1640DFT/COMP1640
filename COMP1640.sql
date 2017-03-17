create database COMP1640
go
use COMP1640
go
create table tblUser(
	idUser varchar(20) primary key,
	_passWord varchar(20),
	fullName varchar(50),
	dob date,
	email varchar(100),
	phoneNumber varchar(11),
	idAcademy int,
	idMajor int,
	lever int
)

create table tblMajor(
	id int primary key identity,
	name varchar(100)
)

create table tblCourse(
	id int primary key identity,
	name varchar(100),
	idMajor int
)

create table tblClass(
	id int primary key identity,
	name varchar(100),
	idMajor int
)

create table tblClassDetail(
	id int primary key identity,
	idUser varchar(20),
	idClass int
)
create table tblClaimManage(
	idCM int primary key identity,
	title nvarchar(100),
	createDate date,
	endDate date,
	idCourse int,
	_status int
)
create table tblClaim(
	idClaim int primary key identity,
	title varchar(200),
	content varchar(500),
	sendDate date,
	filedata varchar(300),
	_status int,
	idUser varchar(20),
	idCM int
)

create table tblDecision(
	idClaim int primary key not null,
	content varchar(500),
	sendDate date,
	_status int,
	idUser varchar(20)
)

create table tblAcademyYear(
	id int primary key identity,
	_year varchar(50)
)



ALTER TABLE tblUser ADD FOREIGN KEY (idAcademy) REFERENCES tblAcademyYear(id)
ALTER TABLE tblUser ADD FOREIGN KEY (idMajor) REFERENCES tblMajor(id)
ALTER TABLE tblCourse ADD FOREIGN KEY (idMajor) REFERENCES tblMajor(id)
ALTER TABLE tblClass ADD FOREIGN KEY (idMajor) REFERENCES tblMajor(id)
ALTER TABLE tblClassDetail ADD FOREIGN KEY (idClass) REFERENCES tblClass(id)
ALTER TABLE tblClassDetail ADD FOREIGN KEY (idUser) REFERENCES tblUser(idUser)
ALTER TABLE tblClaim ADD FOREIGN KEY (idUser) REFERENCES tblUser(idUser)
ALTER TABLE tblClaim ADD FOREIGN KEY (idCM) REFERENCES tblClaimManage(idCM)
ALTER TABLE tblDecision ADD FOREIGN KEY (idUser) REFERENCES tblUser(idUser)
ALTER TABLE tblDecision ADD FOREIGN KEY (idClaim) REFERENCES tblClaim(idClaim)

--insert academy year
insert into tblAcademyYear values('2016')
-- insert tble Major
insert into tblMajor values('IT'),('Business')
--insert course
insert into tblCourse values('Java Programming',1),('Mobile Development',1),('Financial management',2)
--insert class
insert into tblClass values('GC0904',1),('TPH1408',1),('BH903',2)
-- insert user
insert tblUser values('taincgc','123123','Nguyen Cong Tai','1995-01-22','tai@gmail.com','0977326975',1,1,1);
insert tblUser values('admin','123123','Nguyen Van Admin','1990-11-20','admin@gmail.com','098874332',1,1,2);
insert tblUser values('trangph','123123','Phan Huyen Trang','1996-08-13','trangph@gmail.com','0975582938',1,1,1);
insert tblUser values('dungkv','123123','Khuat Van Dung','1995-08-13','dungkv@gmail.com','0966432554',1,1,1);
-- insert class detail
insert into tblClassDetail values('taincgc',1),('trangph',1),('dungkv',2)
--insert claim manage
insert into tblClaimManage values('Deadline','2017-02-22','2017-04-01',1,1),('Deadline','2017-02-23','2017-03-28',2,1)
--insert claim 
insert into tblClaim values('Xin kiem tra lai bai thi', 'Bai thi cua em bi cham sai diem','2017-02-22','//system.data.pdf',1,'dungkv',1)
insert into tblClaim values('Yeu cau giang vien chinh sua diem thi', 'Em da lam bai va duoc 8 diem, nhung tren he thong chi duoc co 6','2017-02-22','//system.data2.pdf',1,'trangph',1)
insert into tblClaim values('Diem danh lai', 'Em co di hoc ngay 2017-01-10, thay kiem tra lai giup em','2017-01-11','',0,'taincgc',2)
--insert decision
insert into tblDecision values(1,'Thay da xem lai, ket qua hoan toan dung.','2017-02-27',1,'admin')
insert into tblDecision values(2,'Ok. Thay da chinh sua','2017-02-28',1,'admin')
select * from tblDecision 
select * from tblUser
select * from tblClaimManage
select * from tblClaim
-- c.idClaim, c.title, c.sendDate, u.fullName
--select all student up claim without evidence
select c.idClaim, c.title, c.sendDate, u.fullName, cl.name from tblClaim c 
inner join tblUser u on c.idUser = u.idUser 
inner join tblClassDetail cd on u.idUser = cd.idUser
inner join tblClass cl on cd.idClass = cl.id
where c.filedata = ''
--
--select all student up claim without evidence
select c.idClaim, c.title, c.sendDate, u.fullName, cl.name from tblClaim c 
inner join tblUser u on c.idUser = u.idUser 
inner join tblClassDetail cd on u.idUser = cd.idUser
inner join tblClass cl on cd.idClass = cl.id
where c.filedata = ''
--select all claim unresolved after 14days
select c.idClaim, c.title,c.content, c.sendDate,c.filedata, c._status, c.idUser, u.fullName 
from tblClaim c 
join tblUser u on c.idUser = u.idUser 
where c.sendDate <= DATEADD(WEEK,-2,GETDATE()) and _status = 0



