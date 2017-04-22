# Database Schema Setup

# Create the users table to store user information
CREATE TABLE tblUsers (
	userid varchar(10),
	password varchar(255),
	management_role ENUM('y', 'n'), # whether they have manager priviliges
	primary key (userid)
);

# Create the gases table to store gas prices and quantity
CREATE TABLE tblGases (
	gasName varchar(20) not null,
	gasPrice decimal(5, 2) not null,
	gasQuantity decimal(30, 2) not null,
	primary key (gasName)
);