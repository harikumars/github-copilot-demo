# github-copilot-demo

###To download the active mq and start
Download ActiveMQ binary from here.[http://activemq.apache.org/download.html]
Unzip your bundle, and Open Terminal.
Set the Terminal path to ActiveMQ -> bin
Write command ./activemq start
Open http://localhost:8161/admin/ 

## to use the mqproducer for doing the transactions for DR/CR
http://localhost:8080/mqProducer - 
HTTP METHOD: POST
Sample Json Input:
{
  "transactionId": "1",
  "transactionAmount": "100",
  "transactionDate": "02-SEP-22",
  "transactionType": "DR",
  "transactionStatus": "OK",
  "description": "ATM WITHDRAWL",
  "customerId": "1"
}

## to get balance for customerid -1
http://localhost:8080/transaction/getBalance/1 
HTTP METHOD: POST

## to create a customer
http://localhost:8080/customer
HTTP METHOD:POST
sample Input -
{
  "customerName": "Krisanth S",
  "customerAddress": "123 Main St",
  "customerPhone": "123-456-7890",
  "customerEmail": "ks@test.com",
  "customerZipcode":"1232",
  "customerCity":"city",
  "customerState":"state"
}

## to update a cusotmer
http://localhost:8080/customer
HTTP METHOD: PUT
sample input-
{
  "customerName": "Krisanth S",
  "customerAddress": "123 Main St",
  "customerPhone": "123-456-7890",
  "customerEmail": "ks@test.com",
  "customerZipcode": "1232",
  "customerCity": "NJ",
  "customerState": "state",
  "customerId":7
}
## to get a customer detail for a customerid
http://localhost:8080/customer/1
HTTP METHOD: GET


