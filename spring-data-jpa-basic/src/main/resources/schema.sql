DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS my_orders;

CREATE TABLE customer (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
) AUTO_INCREMENT = 100; 

CREATE TABLE my_orders (
  order_id int,
  product_id int,
  order_name VARCHAR(20),
  amount int, 
  PRIMARY KEY (order_id, product_id)
);