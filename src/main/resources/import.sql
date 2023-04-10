INSERT INTO customers (first_name, last_name, email)
VALUES ('John', 'Doe', 'john.doe@example.com'),
       ('Jane', 'Doe', 'jane.doe@example.com'),
       ('Bob', 'Smith', 'bob.smith@example.com');

INSERT INTO pos_systems (name, vendor)
VALUES ('POS1', 'VendorA'),
       ('POS2', 'VendorB'),
       ('POS3', 'VendorC');

INSERT INTO products (name, sku, price, quantity)
VALUES ('ProductA', 'SKU001', 9.99, 100),
       ('ProductB', 'SKU002', 19.99, 50),
       ('ProductC', 'SKU003', 4.99, 200);

INSERT INTO stores (name, address, vat_registration_number)
VALUES ('StoreA', '123 Main St.', 'VAT123'),
       ('StoreB', '456 Oak St.', 'VAT456');

INSERT INTO receipts (receipt_id, store_id, pos_system_id, customer_id, timestamp, total_amount_paid)
VALUES ('123456', 1, 1, 1, '2022-01-01 10:00:00', 25.98),
       ('234567', 2, 2, 2, '2022-01-02 11:00:00', 29.99),
       ('345678', 1, 3, NULL, '2022-01-03 12:00:00', 15.96);

INSERT INTO receipt_items (receipt_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 2, 1),
       (2, 3, 2),
       (3, 1, 4),
       (3, 3, 1);
