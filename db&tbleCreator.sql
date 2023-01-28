-- Database Creation
CREATE DATABASE myDB;
USE myDB;

-- Tables Creation
CREATE TABLE highschool (
	id INT PRIMARY KEY AUTO_INCREMENT,
	
    first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	gender VARCHAR(255) NOT NULL,
	ip_address VARCHAR(255) NOT NULL,
	cm_height INT NOT NULL,
	age INT NOT NULL,
	has_car BOOLEAN NOT NULL,
	car_color VARCHAR(255), -- Can be NULL
	grade INT NOT NULL,
	grade_avg FLOAT NOT NULL,
	identification_card INT UNIQUE NOT NULL,
    CHECK (has_color = TRUE OR car_color IS NULL)
);

CREATE TABLE highschool_friendships (
	id INT PRIMARY KEY AUTO_INCREMENT,
    
    friend_id INT NOT NULL REFERENCES highschool(id),
	other_friend_id INT NOT NULL REFERENCES highschool(id),
    CHECK (friend_id != other_friend_id)
);

-- Sima's View
CREATE VIEW ImaSima AS SELECT identification_card, grade_avg FROM highschool;