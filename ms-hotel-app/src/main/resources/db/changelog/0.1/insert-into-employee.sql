--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/insert-into-employee.sql

INSERT INTO employee
(
    name,
    position,
    surname
)
VALUES
(
    'Holly',
    'Administrator',
    'Dolly'
),
(
    'Oleg',
    'Administrator',
    'Gazmanov'
);