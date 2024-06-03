--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/insert-into-client_services.sql

INSERT INTO client_services
("clients_id", "services_id")
VALUES
(1,	3),
(3,	3),
(2,	3),
(4,	1),
(4,	2),
(4,	4),
(4,	4);


