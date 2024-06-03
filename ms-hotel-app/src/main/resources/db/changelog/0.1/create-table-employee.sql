--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-employee.sql

CREATE TABLE IF NOT EXISTS employee
(
    id       bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(255),
    position varchar(255),
    surname  varchar(255)
);