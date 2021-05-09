create database my_ledger;
use my_ledger;

CREATE TABLE IF NOT EXISTS enterprise (
   id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	enterprise_name VARCHAR(100) NOT NULL UNIQUE,
     INDEX (enterprise_name)
     );
    
CREATE TABLE IF NOT EXISTS purchase (
 	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     bill_no VARCHAR(10) NOT NULL,
     bill_date DATE NOT NULL,
     enterprise_id INTEGER UNSIGNED,
     amount FLOAT4 NOT NULL,
     amount_vat FLOAT4 NOT NULL,
     FOREIGN KEY (enterprise_id) REFERENCES enterprise (id) ON DELETE CASCADE
     );

CREATE TABLE IF NOT EXISTS payment_source(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   source_name VARCHAR(30) NOT NULL,
   source_code VARCHAR(10) NOT NULL,
   INDEX (source_code)
   );
    
CREATE TABLE IF NOT EXISTS payment(
	id INT AUTO_INCREMENT PRIMARY KEY,
    enterprise_id INTEGER UNSIGNED NOT NULL,
    payment_mode VARCHAR(10),
    payment_date DATE,
    amount FLOAT4,
    FOREIGN KEY (enterprise_id) REFERENCES enterprise (id) ON DELETE CASCADE,
    FOREIGN KEY (payment_mode) REFERENCES payment_source (source_code) ON DELETE CASCADE
   );

CREATE TABLE IF NOT EXISTS balance (
     id INT AUTO_INCREMENT PRIMARY KEY,
     enterprise_id INTEGER UNSIGNED NOT NULL,
     amount_due FLOAT4 NOT NULL,
     FOREIGN KEY (enterprise_id) REFERENCES enterprise (id) ON DELETE CASCADE
      );

CREATE TABLE IF NOT EXISTS transaction (
     id INT AUTO_INCREMENT PRIMARY KEY,
     transaction_type VARCHAR(10),
     transaction_date DATE,
     enterprise_id INTEGER UNSIGNED NOT NULL,
     transaction_amount FLOAT4 NOT NULL,
     FOREIGN KEY (enterprise_id) REFERENCES enterprise (id) ON DELETE CASCADE
      );

CREATE TABLE IF NOT EXISTS user (
   id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(15) NOT NULL,
 last_name VARCHAR(15) NOT NULL,
 email VARCHAR(50) NOT NULL UNIQUE,
 password VARCHAR(64) NOT NULL, 
 INDEX (email));

CREATE TABLE IF NOT EXISTS role (
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );
    
CREATE TABLE IF NOT EXISTS user_roles (
	user_id INTEGER UNSIGNED NOT NULL,
    role_id INTEGER UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
    );
