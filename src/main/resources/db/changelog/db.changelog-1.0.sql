--liquibase formatted sql

--changeset ilya:1
CREATE TABLE wallet(
id SERIAL PRIMARY KEY,
amount DOUBLE PRECISION NOT NULL,
currency VARCHAR(10) NOT NULL
);

--changeset ilya:2
CREATE TABLE users(
id SERIAL PRIMARY KEY,
firstName VARCHAR(64) NOT NULL,
wallet_id INT REFERENCES wallet (id)
);
