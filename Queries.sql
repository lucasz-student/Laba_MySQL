<<<<<<< HEAD
=======
USE universitydb;

ALTER TABLE class
DROP Classcol; 

ALTER TABLE professor
ADD SchoolGraduatedFrom VARCHAR(40);

ALTER TABLE sportsteam
ADD yearEstablished VARCHAR(40);

ALTER TABLE textbooks 
ADD yearPublished VARCHAR(40);

ALTER TABLE researchlab
DROP age; 

ALTER TABLE researchlab 
ADD yearEstablished INT;

ALTER TABLE textbook
ADD bookCondition VARCHAR(45);

INSERT INTO student(name, year, major)
VALUES 
	('John', 1, 'Chemistry'),
    ('Santa', 2, 'Physics'),
    ('Greg', 1, 'Mathematics'),
    ('Fu', 2, 'Mathematics'),
    ('Bar', 3, 'Mathematics');
    
INSERT INTO class(name, subject)
VALUES 
	('IntroToChem', 'Science'),
	('OrganicChem', 'Science'), 
	('Calc I', 'Math'),
    ('Calc II', 'Math'),
    ('Linear Algebra', 'Math'),
    ('Pure Mathematics', 'Math');

INSERT INTO class_has_student(Class_idClass, Student_idStudent) 
VALUES 
	(1, 1),
    (1, 2),
    (2, 3),
    (3, 1),
    (3, 2);
    
INSERT INTO professor(name, age, className) 
VALUES 
	('Dr. John', 45, 'Bio I'),
    ('Dr. Fubar', 60, 'Trig I'),
    ('Dr. Doe', 91, 'Econ I'),
    ('Dr. Fervor', 100, 'Gov I'),
    ('Dr. Eggs', 75, 'Geometry');
    
INSERT INTO professor_has_student(Teachers_idTeachers, Students_idStudent)
VALUES 
	(1, 2),
    (3, 1),
    (2, 3),
    (4, 1);
    
INSERT INTO sportsteam(sportName, gamesPlayed, yearEstablished)
VALUES
	('Football', 100, 1999),
    ('Lacrosse', 50, 2000),
    ('Hockey', 60, 2010),
    ('Soccer', 10, 1950),
    ('Tennis', 5, 1900),
    ('Swim', 40, 1980);
    
INSERT INTO sportsteam_has_student(SportsTeam_idSportsTeam, Student_idStudents) 
VALUES
	(1, 3),
	(2, 1),
	(1, 2);

INSERT INTO researchlab(papersPublished, age, name, topic) 
VALUES 
	(11, 10, 'GeneticsLab', 'Science'),
    (20, 1, 'EvoLab', 'Evolution'), 
    (10, 4, 'Chemlab', 'CompoundMixing'),
    (1, 12, 'Mylab', 'History'),
    (5, 14, 'Cruzlab', 'DNATesting'),
    (5, 20, 'Jokiclab', 'virology');
    
INSERT INTO student_has_researchlab(Students_idStudents, ResearchLabs_idResearchLabs)
VALUES 
	(1, 3),
    (1, 2),
    (1, 4),
    (2, 5),
    (3, 5);
    
INSERT INTO textbook(name, bookCondition)
VALUES 
	('BiologyTextBook', 'Brand New'),
    ('Calculus 2', 'Slightly Used'),
    ('BiologyTextBook', 'UNUSABLE'),
    ('IntroBiologyTextBook', 'UNUSABLE');

DELETE FROM textbook WHERE bookCondition='UNUSABLE';
DELETE FROM researchlab WHERE papersPublished<10;
DELETE FROM student WHERE name='Santa';
DELETE FROM professor WHERE age>80;
DELETE FROM class WHERE name='Pure Mathematics';
DELETE FROM class WHERE name='calc II';
DELETE FROM student_has_researchlab WHERE Students_idStudents=1 AND ResearchLabs_idResearchLabs=4;
DELETE FROM sportsteam WHERE gamesPlayed<30;
DELETE FROM sportsteam WHERE yearEstablished<1930;
DELETE FROM professor WHERE name='Dr. Eggs';

UPDATE student 
SET major='biochem'
WHERE name='John';

SELECT student.major, textbook.name FROM student
INNER JOIN textbook ON student.idStudents=textbook.idTextbooks;

SELECT student.major, textbook.name FROM student
LEFT JOIN textbook ON student.idStudents=textbook.idTextbooks;

SELECT student.major, textbook.name FROM student
RIGHT JOIN textbook ON student.idStudents=textbook.idTextbooks;

SELECT max(year), major FROM student
GROUP BY major;

SELECT max(year), major FROM student
GROUP BY major HAVING max(year)>1;






>>>>>>> 9243d643e70accd366c1f32b25d90efb439679fe
