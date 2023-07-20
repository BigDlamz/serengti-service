--DDL Statements
DROP TABLE IF EXISTS line_items;
DROP TABLE IF EXISTS receipts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS pos_systems;
DROP TABLE IF EXISTS stores;

CREATE TABLE stores
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY,
    vat_registration_number VARCHAR(255),
    name                    VARCHAR(255),
    address                 VARCHAR(255),
    PRIMARY KEY (id)
);
ALTER TABLE stores
    ADD COLUMN store_logo_url VARCHAR(255);
CREATE TABLE pos_systems
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name   VARCHAR(255),
    vendor VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    mobile_number   VARCHAR(255),
    email_address   VARCHAR(255),
    identifier_type VARCHAR(255) NOT NULL,
    CONSTRAINT unique_mobile_number UNIQUE (mobile_number),
    CONSTRAINT unique_email_address UNIQUE (email_address),
    CONSTRAINT check_identifier CHECK (
            (mobile_number IS NOT NULL AND email_address IS NULL AND identifier_type = 'mobile_number') OR
            (mobile_number IS NULL AND email_address IS NOT NULL AND identifier_type = 'email_address')
        )
);

CREATE TABLE products
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY,
    pos_system_id     BIGINT NOT NULL REFERENCES pos_systems (id),
    store_id          BIGINT NOT NULL REFERENCES stores(id),
    sku               VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    price             NUMERIC(19, 2),
    quantity          INTEGER,
    CONSTRAINT unique_sku_per_pos_system_per_store
        UNIQUE (pos_system_id, sku, store_id),
    PRIMARY KEY (id)
);


CREATE TABLE receipts
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY,
    store_id          BIGINT,
    pos_system_id     BIGINT,
    customer_id       BIGINT,
    timestamp         TIMESTAMP NOT NULL,
    total_amount_paid NUMERIC(19, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_receipt_store FOREIGN KEY (store_id) REFERENCES stores (id),
    CONSTRAINT fk_receipt_pos_system FOREIGN KEY (pos_system_id) REFERENCES pos_systems (id),
    CONSTRAINT fk_receipt_customer FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE line_items
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY,
    receipt_id BIGINT,
    product_id BIGINT,
    quantity   INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_receipt_item_receipt FOREIGN KEY (receipt_id) REFERENCES receipts (id),
    CONSTRAINT fk_receipt_item_product FOREIGN KEY (product_id) REFERENCES products (id)
);



--Sample Data

-- Insert sample data into pos_systems
INSERT INTO pos_systems (name, vendor)
VALUES ('SnapPOS', 'InfoSys'),
       ('CoolPOS', 'Oracle');

INSERT INTO stores (vat_registration_number, name, address)
VALUES ('123456789', 'Woolworths', '123 Main St'),
       ('987654321', 'Checkers', '456 Second Ave');


-- Insert sample data into customers
INSERT INTO customers (name, mobile_number, email_address, identifier_type)
VALUES ('Will Smith', '+27662012488', NULL, 'mobile_number'),
       ('Jane Doe', NULL, 'jane.doe@example.com', 'email_address');

-- Insert sample data into products
INSERT INTO products (pos_system_id, store_id, sku, description, price, quantity)
VALUES (5, 1, 'sku123', 'Milk', 9.99, 50),
       (5, 1, 'sku1234', 'Break', 19.99, 30),
       (6, 2, 'sku12345', 'Cheese', 29.99, 40),
       (6, 2, 'sku12346', 'Water', 39.99, 20);

-- Insert sample data into receipts
INSERT INTO receipts (store_id, pos_system_id, customer_id, timestamp, total_amount_paid)
VALUES (1, 5, 5, '2023-05-01 10:30:00', 29.97),
       (2, 6, 6, '2023-05-02 15:45:00', 59.98);

-- Insert sample data into line_items
INSERT INTO line_items (receipt_id, product_id, quantity)
VALUES (3, 9, 3),
       (4, 10, 2);