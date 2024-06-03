--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-client.sql

CREATE TABLE IF NOT EXISTS client
(
    id      bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    varchar(255),
    surname varchar(255),
    room_id bigint,
    CONSTRAINT fk_client_room_id_room FOREIGN KEY (room_id)
    REFERENCES room(id) NOT DEFERRABLE
);