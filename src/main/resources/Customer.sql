//Create a MySQL table customer table with id primary key with auto increment, name with maximum size to be set as 50 if applicable, email as varchar(150), lei, swiftbic, avox, dtcc, cici, status as columns
CREATE TABLE IF NOT EXISTS customer (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(150) NOT NULL,
  lei VARCHAR(50) NOT NULL,
  swiftbic VARCHAR(50) NOT NULL,
  avox VARCHAR(50) NOT NULL,
  dtcc VARCHAR(50) NOT NULL,
  cici VARCHAR(50) NOT NULL,
  status VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--create a mysql procedure to accept tradeid and transactiontype as input and return the trade details
DELIMITER $$
CREATE PROCEDURE getTradeDetails(IN tradeId INT, IN transactionType INT)
BEGIN
SELECT * FROM trades WHERE tradeId = tradeId AND transactionType = transactionType;
END$$
DELIMITER ;

--create a oracle procedure to accept tradeid and transactiontype as input and return the trade details
CREATE OR REPLACE PROCEDURE getTradeDetails(tradeId IN NUMBER, transactionType IN NUMBER)
IS





