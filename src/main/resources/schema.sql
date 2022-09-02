--MySQL Create Customer table with id, name, email, phone, address, city, state, zip as columns
CREATE TABLE IF NOT EXISTS customer (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL,
  zip VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--MySQL insert 5  records into customer table
INSERT INTO customer (name, email, phone, address, city, state, zip) VALUES
('John Smith', 'js@test.com', '123-456-7890', '123 Main St', 'Anytown', 'NY', '12345'),
('Jane Doe', 'jd@test.com', '123-456-7890', '123 Main St', 'Anytown', 'NY', '12345'),
('John Doe', 'jd@test.com', '123-456-7890', '123 Main St', 'Anytown', 'NY', '12345'),
('Jane Smith', 'jw@test.com', '123-456-7890', '123 Main St', 'Anytown', 'NY', '12345'),
('John Williams', 'jww@test.com', '123-456-7890', '123 Main St', 'Anytown', 'NY', '12345');
--MySQL Create Transaction table with id, customerId , date, type, desc, amount, with primary key as id and foreign key to customer table
CREATE TABLE IF NOT EXISTS transaction (
  id INT NOT NULL AUTO_INCREMENT,
  customerId INT NOT NULL,
  date VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  amount VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customerId) REFERENCES customer(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
