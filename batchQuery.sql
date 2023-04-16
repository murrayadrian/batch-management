CREATE DATABASE batchmanagement;
GO
use batchmanagement;
GO
CREATE TABLE Interviewer(
	id int PRIMARY KEY IDENTITY(1,1),
	name varchar(10),
	age int,
	experience int
)
GO
INSERT INTO Interviewer VALUES ('phi',25,6);
INSERT INTO Interviewer VALUES ('duong',27,10);
INSERT INTO Interviewer VALUES ('huy',26,8);
GO
CREATE TABLE Candidate(
	id int PRIMARY KEY IDENTITY(1,1),
	name varchar(20),
	age int,
	status varchar(20)
)
GO
INSERT INTO CANDIDATE VALUES ('vinh',22,'fail');
INSERT INTO CANDIDATE VALUES ('phuong',24,'fail');
INSERT INTO CANDIDATE VALUES ('bac',24,'fail');
INSERT INTO CANDIDATE VALUES ('nguyen',24,'fail');
INSERT INTO CANDIDATE VALUES ('tan',23,'fail');

GO
CREATE TABLE Batch(
	id int PRIMARY KEY IDENTITY(1,1),
	interviewerID int,
	candidateID int,
	FOREIGN KEY (interviewerID) REFERENCES Interviewer(id),
	FOREIGN KEY (candidateID) REFERENCES Candidate(id)
)

INSERT INTO Batch VALUES (1,1);
INSERT INTO Batch VALUES (1,2);

