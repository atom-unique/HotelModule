--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-client_services.sql

CREATE TABLE IF NOT EXISTS client_services
(
    clients_id  bigint NOT NULL,
    services_id bigint NOT NULL,
    CONSTRAINT "fk_client_services_clients_id_client" FOREIGN KEY (clients_id)
        REFERENCES client(id) NOT DEFERRABLE,
    CONSTRAINT "fk_client_services_services_id_service" FOREIGN KEY (services_id)
        REFERENCES service(id) NOT DEFERRABLE
);