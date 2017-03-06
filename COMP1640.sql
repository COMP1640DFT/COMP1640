create database COMP1640
use COMP1640

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

create table tblClaim(
	idClaim int primary key identity,
	title varchar(200),
	content varchar(500),
	sendDate date,
	filedata varchar(300),
	idUser varchar(20),
	idCourse int,
	idCM int
)

create table tblDecision(
	idClaim int primary key identity,
	content varchar(500),
	sendDate date,
	status int,
	idUser varchar(20)
)

create table tblAcademyYear(
	id int primary key identity,
	_year varchar(50)
)

create table tblClaimManage(
	idCM int primary key identity,
	createDate date,
	_status int
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

