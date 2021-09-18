--Banking_Application schema will model customers, accounttyps, accounts and transactions tables

--accounttyps has a one-to-many relationship with accounts. the accounttyps table is considered the "one" side and accounts is considered the "many" side
--customers has a one-to-many relationship with accounts. the customers table is considered the "one" side and accounts is considered the "many" side
--accounts has a one-to-many relationship with transactions. the accounts table is considered the "one" side and transactions is considered the "many" side

--Creating customers table
CREATE TABLE customers (
	customer_id serial PRIMARY KEY,
	name TEXT,
	adress TEXT,
	city TEXT,
	state TEXT,
	zipcode int
);
alter table CUSTOMERs rename column adress to address;
--Creating accounttypes table
CREATE TABLE accounttypes (
	accounttype_id serial PRIMARY KEY,
	type TEXT,
	rate decimal(7,5)
);

--Creating accounts table
CREATE TABLE accounts (
	account_id serial PRIMARY KEY,
	balance decimal(18,2),
	opendate date, --YYYY-MM-DD
	accounttype_id int REFERENCES accounttypes (accounttype_id), --foreign key: establishes a relationship between the tables
	customer_id int REFERENCES customers (customer_id) --foreign key: establishes a relationship between the tables
);

--Creating transactions table
CREATE TABLE transactions (
	transaction_id serial PRIMARY KEY,
	postdate date, --YYYY-MM-DD
	amount decimal(18,2),
	description TEXT,
	account_id int REFERENCES accounts (account_id) --foreign key: establishes a relationship between the tables
);

INSERT INTO accounttypes (type,rate) 
VALUES ('Saving',3.5),
		('Checking',4.5),
		('Credit',4.5);

