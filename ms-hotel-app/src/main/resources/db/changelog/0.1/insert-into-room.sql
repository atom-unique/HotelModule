--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/insert-into-room.sql

INSERT INTO room
(
    capacity,
    is_in_service,
    is_occupied,
    price,
    rating
)
VALUES
(2, false, true, 85, 3),
(2, false, false, 80, 3),
(3, false, false, 110, 3),
(3, false, true, 150, 4),
(2, false, false, 150, 5),
(2, true, false, 150, 5),
(1, false, false, 120, 5);

