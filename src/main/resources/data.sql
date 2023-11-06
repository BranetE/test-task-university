INSERT INTO lector (id, degree, name, salary)
VALUES
    (1, 'PROFESSOR', 'John Smith', 80000.00),
    (2, 'ASSOCIATE_PROFESSOR', 'Jane Doe', 60000.00),
    (3, 'ASSISTANT', 'Bob Johnson', 40000.00),
    (4, 'PROFESSOR', 'Mary Johnson', 85000.00),
    (5, 'ASSOCIATE_PROFESSOR', 'James Wilson', 65000.00),
    (6, 'ASSISTANT', 'Emily Brown', 45000.00),
    (7, 'PROFESSOR', 'Michael Davis', 90000.00),
    (8, 'ASSISTANT', 'Sophia Evans', 47000.00);
INSERT INTO department (id, name, head_id)
VALUES
    (1, 'Computer Science', 2),
    (2, 'Mathematics', 3),
    (3, 'Physics', 1),
    (4, 'Chemistry', 4),
    (5, 'Biology', 5),
    (6, 'History', 6),
    (7, 'English Literature', 7),
    (8, 'Economics', 8);
INSERT INTO department_lector (department_id, lector_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (4, 4),
    (4, 5),
    (5, 6),
    (6, 7),
    (7, 8),
    (8, 1),
    (8, 2),
    (8, 3);