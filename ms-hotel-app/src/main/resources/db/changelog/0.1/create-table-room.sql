--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-bank_branch.sql

CREATE TABLE IF NOT EXISTS room
(
    id            bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    capacity      integer NOT NULL,
    is_in_service boolean NOT NULL,
    is_occupied   boolean NOT NULL,
    price         integer NOT NULL,
    rating        integer NOT NULL
);