/* 
Update USE command to point to correct database
*/

USE rmeijer01;

CREATE TABLE students (
    studentID INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    course VARCHAR(50),
    mark INT
);

INSERT INTO students (studentID, firstName, lastName, course, mark) VALUES
(1, 'Tony', 'Stark', 'Computer Science', 92),
(2, 'Steve', 'Rogers', 'Cyber Security', 85),
(3, 'Natasha', 'Romanoff', 'Computer Science', 88),
(4, 'Bruce', 'Banner', 'Data Science', 91),
(5, 'Peter', 'Parker', 'Software Engineering', 79);

SELECT * FROM students;