CREATE TABLE passport(
    id SERIAL PRIMARY KEY,
    seria INT,
    number INT
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE passport_people(
    id SERIAL PRIMARY KEY,
    passport_id INT REFERENCES passport(id) UNIQUE,
    people_id INT REFERENCES people(id) UNIQUE
);

INSERT INTO passport(seria, number) VALUES (4044, 333333);
INSERT INTO people(name) VALUES ('Vlad');
INSERT INTO passport_people(passport_id, people_id) VALUES (1, 1);

SELECT * FROM passport;
SELECT * FROM people;
SELECT * FROM passport_people;
SELECT * FROM passport_people;
