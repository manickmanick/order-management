CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE customer_product (
                                  customer_id BIGINT NOT NULL,
                                  product_id BIGINT NOT NULL,

                                  PRIMARY KEY (customer_id, product_id),

                                  CONSTRAINT fk_customer_product_customer
                                      FOREIGN KEY (customer_id)
                                          REFERENCES customer(id),

                                  CONSTRAINT fk_customer_product_product
                                      FOREIGN KEY (product_id)
                                          REFERENCES product(id)
);