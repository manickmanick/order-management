CREATE TABLE address(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(255),
  city VARCHAR(100),
  state VARCHAR(100),
  country VARCHAR(100)
);

CREATE TABLE customer(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  address_id BIGINT UNIQUE,

  CONSTRAINT fk_customer_address
                     FOREIGN KEY(address_id)
                     REFERENCES address(id)

);