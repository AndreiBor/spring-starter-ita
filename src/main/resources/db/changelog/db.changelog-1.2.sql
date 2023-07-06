--liquibase formatted sql

--changeset aborisov:1
ALTER TABLE users
ADD COLUMN info varchar(128);