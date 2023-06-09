USE  universityDB;

DELETE FROM Student;
DELETE FROM Class;
DELETE FROM Textbook;
DELETE FROM Professor;
DELETE FROM Professor_has_Student;
DELETE FROM ResearchLab;
DELETE FROM ResearchLab_has_Professor;
DELETE FROM Student_has_ResearchLab;
DELETE FROM Class_has_Student;
DELETE FROM SportsTeam;
DELETE FROM SportsTeam_has_Student;
DELETE FROM Gym;

ALTER TABLE Student AUTO_INCREMENT = 1;
ALTER TABLE Class AUTO_INCREMENT = 1;
ALTER TABLE Textbook AUTO_INCREMENT = 1;
ALTER TABLE Professor AUTO_INCREMENT = 1;
ALTER TABLE Professor_has_Student AUTO_INCREMENT = 1;
ALTER TABLE ResearchLab AUTO_INCREMENT = 1;
ALTER TABLE ResearchLab_has_Professor AUTO_INCREMENT = 1;
ALTER TABLE Student_has_ResearchLab AUTO_INCREMENT = 1;
ALTER TABLE Class_has_Student AUTO_INCREMENT = 1;
ALTER TABLE SportsTeam AUTO_INCREMENT = 1;
ALTER TABLE SportsTeam_has_Student AUTO_INCREMENT = 1;
ALTER TABLE Gym AUTO_INCREMENT = 1;

INSERT INTO Student (name, yearJoined, major) VALUES
('John Doe', 2020, 'Computer Science'),
('Jane Smith', 2019, 'Physics'),
('Mike Johnson', 2021, 'Biology'),
('Luke Cage', 2021, 'Biology');

INSERT INTO Class (name, subject) VALUES
('Math101', 'Mathematics'),
('Physics202', 'Physics'),
('Biology101', 'Biology'),
('AdvancedMath', 'Mathematics'),
('Chemistry', 'Chemistry');

INSERT INTO Textbook (name, Student_id, bookCondition, Class_id) VALUES
('Calculus I', 1, 'Good', 1),
('Physics I', 2, 'Excellent', 2),
('Biology Intro', 3, 'Fair', 3),
('Calculus II', 1, 'New', 4),
('Chemistry PreReq', 2, 'UNUSABLE', 5);

INSERT INTO Textbook (name, student_id, bookCondition) VALUES
('BookNoClass', 1, 'New');

INSERT INTO Professor (name, age, Class_id) VALUES
('Dr. Smith', 40, 1),
('Dr. Dubar', 45, 2),
('Dr. Eggs', 55, 3);

INSERT INTO Professor_has_Student (Professor_id, Student_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO ResearchLab (papersPublished, age, name, topic) VALUES
(10, 5, 'Biology Lab', 'Genetics'),
(5, 3, 'Physics Lab', 'Quantum Mechanics'),
(5, 2, 'Physics Lab', 'Time Travel'),
(602, 60, 'Einstein', 'Time Travel'),
(509, 35, 'Physics Lab', 'Astronomy'),
(123, 35, 'Computer Science', 'AI'),
(430, 23, 'Sasha Lab', 'Quantum Mechanics'),
(221, 15, 'Hikaru Lab', 'Quantum Mechanics'),
(140, 25, 'Levon Lab', 'Quantum Mechanics');

INSERT INTO ResearchLab_has_Professor (ResearchLab_id, Professor_id) VALUES
(1, 1),
(2, 2);

INSERT INTO Student_has_ResearchLab (Student_id, ResearchLab_id) VALUES
(1, 1),
(2, 2),
(3, 1);

INSERT INTO Class_has_Student (Class_id, Student_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(4, 2),
(3, 2);

INSERT INTO SportsTeam (sportName, gamesPlayed) VALUES
('Football', 10),
('Basketball', 15),
('Baseball', 8);

INSERT INTO SportsTeam_has_Student (SportsTeam_id, Student_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO Gym (Location, gymAge, price) VALUES
('Building A', 3, 50),
('Building B', 5, 40),
('Building C', 2, 60);

UPDATE Student SET major = 'Computer Science' WHERE id = 1;
UPDATE Class SET name = 'Physics I' WHERE id = 2;
UPDATE Textbook SET bookCondition = 'Good' WHERE id = 3;
UPDATE Professor SET age = 82 WHERE id = 1;
UPDATE ResearchLab SET name = 'Chemlab' WHERE id = 2;
UPDATE ResearchLab SET topic = 'Nootropics' WHERE id = 2;
UPDATE SportsTeam SET gamesPlayed = 10 WHERE id = 1;
UPDATE Gym SET Location = 'Building C, 2nd Floor' WHERE id = 2;
UPDATE Student SET name = 'Lucas Hsieh' WHERE id = 1;
UPDATE Professor SET name = 'Dr. Sunrise' WHERE id = 2;
UPDATE Class_has_Student SET Student_id = 2 WHERE Class_id = 1;

SELECT * 
FROM Student
JOIN class_has_student on class_has_student.Student_id=Student_id
JOIN class on class.id=class_has_student.student_id
JOIN professor on professor.Class_id=class.id
JOIN researchlab_has_professor on researchlab_has_professor.Professor_id=professor.id
JOIN researchlab on researchlab.id=researchlab_has_professor.ResearchLab_id
JOIN sportsteam_has_student on sportsteam_has_student.Student_id=student.id
JOIN sportsteam on sportsteam_has_student.SportsTeam_id=sportsteam.id
JOIN student_has_researchlab on student_has_researchlab.Student_id=student.id
JOIN gym on gym.Student_id=student.id;

SELECT * FROM student 
JOIN class_has_student on class_has_student.student_id=student.id
JOIN class on class_has_student.class_id=class.id;

SELECT *
FROM Gym
LEFT JOIN Student ON Gym.Student_id = Student.id
UNION
SELECT *
FROM Gym
RIGHT JOIN Student ON Gym.Student_id = Student.id;

SELECT * FROM student
LEFT JOIN class_has_student ON class_has_student.student_id=student.id
LEFT JOIN class on class.id=class_has_student.class_id;

SELECT * FROM class
RIGHT JOIN textbook on textbook.class_id=class.id;

SELECT * FROM student
JOIN gym on student.id=gym.student_id;


SELECT name, min(age) FROM professor
GROUP BY name;

SELECT name, max(age) FROM professor
GROUP BY name;

SELECT subject, count(student_id) FROM class
JOIN class_has_student on class_has_student.class_id=class.id
JOIN student on student.id=class_has_student.student_id
GROUP BY subject;

SELECT class.name, count(student_id) FROM class
JOIN class_has_student on class_has_student.class_id=class.id
JOIN student on student.id=class_has_student.student_id
GROUP BY class.name;

SELECT age, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY age; 

SELECT name, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY name; 

SELECT topic, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY topic; 



SELECT name, min(age) FROM professor
GROUP BY name HAVING min(age)>30;

SELECT name, max(age) FROM professor
GROUP BY name HAVING min(age)<50;

SELECT subject, count(student_id) FROM class
JOIN class_has_student on class_has_student.class_id=class.id
JOIN student on student.id=class_has_student.student_id
GROUP BY subject
HAVING subject!='Mathematics';

SELECT class.name, count(student_id) FROM class
JOIN class_has_stu dent on class_has_student.class_id=class.id
JOIN student on student.id=class_has_student.student_id
GROUP BY class.name
HAVING class.name!='Calculus I';

SELECT age, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY age
HAVING age>10; 

SELECT name, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY name
HAVING name!='Einstein Lab'; 

SELECT topic, max(researchlab.papersPublished) as MostPapersPublished from researchLab
GROUP BY topic
HAVING topic!='Time Travel'; 
