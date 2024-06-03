--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-service.sql

CREATE TABLE IF NOT EXISTS service
(
    id          bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description varchar(255),
    name        varchar(255),
    price       integer NOT NULL
);