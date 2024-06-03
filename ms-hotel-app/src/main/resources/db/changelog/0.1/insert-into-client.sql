--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/insert-into-client.sql

INSERT INTO client
(
    name,
    surname,
    room_id
)
VALUES
(
    'Peter',
    'Parker',
    1
),
(
    'Jenny',
    'Kolt',
    NULL
),
(
    'Mary',
    'Jane',
    1
),
(
    'Poll',
    'Robinson',
    4
);