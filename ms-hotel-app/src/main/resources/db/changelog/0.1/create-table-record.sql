--liquibase formatted sql
--changeset Y.Kravchenko:0.1 logicalFilePath:0.1/create-table-record.sql

CREATE TABLE IF NOT EXISTS record
(
    id             bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    check_in_date  date,
    check_out_date date,
    client_id      bigint,
    employee_id    bigint,
    room_id        bigint,
    is_checked_out boolean DEFAULT false NOT NULL,
    CONSTRAINT "fk_record_client_id_client" FOREIGN KEY (client_id) REFERENCES client(id) NOT DEFERRABLE,
    CONSTRAINT "fk_record_employee_id_employee" FOREIGN KEY (employee_id) REFERENCES employee(id) NOT DEFERRABLE,
    CONSTRAINT "fk_record_room_id_room" FOREIGN KEY (room_id) REFERENCES room(id) NOT DEFERRABLE
);