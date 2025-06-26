-- Drop tables if they exist
DROP TABLE IF EXISTS position CASCADE;
DROP TABLE IF EXISTS employee CASCADE;


create TABLE position (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    is_delete INT NOT NULL DEFAULT 0
);

create TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    position_id INT REFERENCES position(id),
    id_number INT UNIQUE NOT NULL ,
    gender INT NOT NULL ,
    is_delete INT NOT NULL DEFAULT 0
);

TRUNCATE TABLE employee, position RESTART IDENTITY CASCADE;

INSERT INTO position (code,name)
VALUES
    ('SA', 'System Analyst'),
    ('BPS', 'BPS'),
    ('PRG', 'Programmer'),
    ('TEST', 'Tester'),
    ('HELP', 'Help Desk');

INSERT INTO employee (name, birth_date, position_id, id_number,gender)
VALUES
    ('Yogi Lestari', '1990-02-14', 5, 14021990, 1),
    ('Anggi Setiawan', '1991-05-10', 2, 10051991, 1),
    ('Rosiana', '1993-04-20', 4, 20041993, 2),
    ('Yudi Ismiaji','1994-01-11', 3, 11011994,1);


