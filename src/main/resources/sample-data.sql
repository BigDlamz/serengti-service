
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