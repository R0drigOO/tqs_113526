DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO employee (name) VALUES ('John Doe');
INSERT INTO employee (name) VALUES ('Jane Doe');
INSERT INTO employee (name) VALUES ('Alice');
INSERT INTO employee (name) VALUES ('Bob');
