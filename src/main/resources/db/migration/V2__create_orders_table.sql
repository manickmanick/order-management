CREATE TABLE orders(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(100) NOT NULL UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    customer_id BIGINT NOT NULL,

    CONSTRAINT fk_orders_customer
                   FOREIGN KEY(customer_id)
                   REFERENCES customer(id)

);