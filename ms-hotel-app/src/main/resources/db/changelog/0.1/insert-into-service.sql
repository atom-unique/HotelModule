--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/insert-into-service.sql

INSERT INTO service
(
    description,
    name,
    price
)
VALUES
(
    'Parking space in the parking lot',
    'Parking',
    20
),
(
    'Additional room cleaning',
    'Cleaning',
    10
),
(
    'Topping up the minibar with drinks',
    'Drinks',
    12
),
(
    'Dinner delivery to the room',
    'Dinner',
    15
);