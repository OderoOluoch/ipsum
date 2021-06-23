SET MODE PostgresSQL;
CREATE DATABASE
CREATE TABLE users(id serial PRIMARY KEY, name varchar, carId int, parkingId int);
CREATE TABLE IF NOT EXISTS slots (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    carId INTEGER
);

