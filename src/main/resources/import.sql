DROP TABLE IF EXISTS line_items;
DROP TABLE IF EXISTS receipts;
DROP TABLE IF EXISTS product_identifiers;
DROP TABLE IF EXISTS specials;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cashiers;
DROP TABLE IF EXISTS tills;
DROP TABLE IF EXISTS promotions;
DROP TABLE IF EXISTS stores;
DROP TABLE IF EXISTS pos_systems;
DROP TYPE IF EXISTS product_category CASCADE;

CREATE TABLE stores
(
    store_id                BIGINT GENERATED BY DEFAULT AS IDENTITY,
    vat_registration_number VARCHAR(255),
    name                    VARCHAR(255),
    address                 VARCHAR(255),
    store_logo_url          VARCHAR(255),
    PRIMARY KEY (store_id)
);

CREATE TABLE pos_systems
(
    pos_system_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name          VARCHAR(255),
    version       VARCHAR(255),
    PRIMARY KEY (pos_system_id)
);

CREATE TABLE users
(
    user_id     SERIAL PRIMARY KEY,
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

CREATE TYPE PRODUCT_CATEGORY AS ENUM (
    'ELECTRONICS',
    'GROCERIES',
    'CLOTHING',
    'BOOKS',
    'HOME AND GARDEN',
    'HEALTH AND BEAUTY',
    'TOYS AND GAMES',
    'AUTOMOTIVE',
    'SPORTS AND OUTDOORS',
    'OFFICE SUPPLIES',
    'PET SUPPLIES',
    'MUSIC AND ENTERTAINMENT',
    'JEWELRY AND ACCESSORIES',
    'TRAVEL AND LUGGAGE',
    'ARTS AND CRAFTS'
);

CREATE TABLE products
(
    product_id  BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name        VARCHAR(255),
    description VARCHAR(255),
    category    product_category NOT NULL,
    unit_price  NUMERIC(19,2),
    PRIMARY KEY (product_id)
);

CREATE TABLE Tills
(
    till_id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    store_id      BIGINT NOT NULL REFERENCES Stores (store_id),
    pos_system_id BIGINT NOT NULL REFERENCES POS_Systems (pos_system_id),
    till_number   BIGINT NOT NULL

);

CREATE TABLE Cashiers
(
    cashier_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(50),
    surname    VARCHAR(50)

);

CREATE TABLE Promotions
(
    promotion_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name         VARCHAR(50),
    description  VARCHAR(255),
    start_date   DATE,
    end_date     DATE,
    discount     NUMERIC(19, 2)

);

CREATE TABLE product_identifiers
(
    product_identifier_id  BIGINT GENERATED BY DEFAULT AS IDENTITY,
    product_id             BIGINT NOT NULL REFERENCES products (product_id),
    store_id               BIGINT NOT NULL REFERENCES stores (store_id),
    pos_system_id          BIGINT NOT NULL REFERENCES pos_systems (pos_system_id),
    ean13_code             VARCHAR(13),
    universal_product_code VARCHAR(12),
    sku                    VARCHAR(50),
    CONSTRAINT unique_sku_per_pos_system_per_store
        UNIQUE (pos_system_id, sku, store_id),
    PRIMARY KEY (product_identifier_id)
);

CREATE TABLE receipts
(
    receipt_id        BIGINT GENERATED BY DEFAULT AS IDENTITY,
    pos_system_id     BIGINT,
    store_id          BIGINT,
    customer_id       BIGINT,
    till_id           BIGINT,
    cashier_id        BIGINT,
    promotion_id      BIGINT,
    transaction_date   TIMESTAMP NOT NULL,
    subtotal NUMERIC(19, 2),
    vat_rate NUMERIC(5, 2),
    vat_amount NUMERIC(19, 2),
    discount_amount NUMERIC(19, 2),
    total_due_after_tax NUMERIC(19, 2),
    amount_paid NUMERIC(19, 2),
    change_due NUMERIC(19, 2),
    PRIMARY KEY (receipt_id),
    CONSTRAINT fk_receipt_store FOREIGN KEY (store_id) REFERENCES stores (store_id),
    CONSTRAINT fk_receipt_pos_system FOREIGN KEY (pos_system_id) REFERENCES pos_systems (pos_system_id),
    CONSTRAINT fk_receipt_customer FOREIGN KEY (customer_id) REFERENCES users (user_id),
    CONSTRAINT fk_receipt_cashier FOREIGN KEY (cashier_id) REFERENCES cashiers (cashier_id),
    CONSTRAINT fk_receipt_till FOREIGN KEY (till_id) REFERENCES tills (till_id),
    CONSTRAINT fk_receipt_promo_message FOREIGN KEY (promotion_id) REFERENCES promotions (promotion_id)
);

CREATE TABLE line_items
(
    line_item_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    receipt_id   BIGINT,
    product_id   BIGINT,
    quantity     INTEGER,
    total_price NUMERIC(19, 2),
    PRIMARY KEY (line_item_id),
    CONSTRAINT fk_receipt_item_receipt FOREIGN KEY (receipt_id) REFERENCES receipts (receipt_id),
    CONSTRAINT fk_receipt_item_product FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE specials
(
    special_id               BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    store_id                 BIGINT REFERENCES stores(store_id),
    product_id               BIGINT NOT NULL REFERENCES products(product_id),
    name                     VARCHAR(255) NOT NULL,
    description              VARCHAR(255),
    special_image_url        VARCHAR(255),
    old_price                NUMERIC(19, 2) NOT NULL,
    new_price                NUMERIC(19, 2) NOT NULL,
    start_date               DATE NOT NULL,
    end_date                 DATE NOT NULL,
    CONSTRAINT chk_price CHECK (new_price < old_price)  -- To ensure new price is indeed a special price.
);

CREATE TABLE feedback
(
    feedback_id      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    receipt_id       BIGINT NOT NULL REFERENCES receipts (receipt_id),
    star_rating      INTEGER CHECK (star_rating >= 1 AND star_rating <= 5),
    user_comment     TEXT,
    feedback_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_receipt_feedback UNIQUE (receipt_id)
);


CREATE CAST (character varying AS product_category)
    WITH INOUT
    AS IMPLICIT;

INSERT INTO pos_systems (name, version)
VALUES ('InfoSys Point of Sale System', 'V1.00'),
       ('Oracle Point of Sale System', 'V3.00');

INSERT INTO stores (vat_registration_number, name, address, store_logo_url)
VALUES ('123456789', 'Woolworths', '123 Main Street Sandton', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/woolworths.jpg'),
       ('987654321', 'Checkers', '456 Second Avenue Linden', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/checkers-hyper.jpg'),
       ('678765550', 'Pick n Pay', '12 Zeiss Road Honeydew', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/pick-n-pay-logo-vector.png'),
       ('678765550', 'Zando', '1400 Chime Street Weltevreden Park', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/zando.png'),
       ('678765550', 'Mr Price', '500 Ntuli Road Hammersadale', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/mrprice.jpg');

INSERT INTO users (name, mobile_number, email_address, identifier_type)
VALUES ('Will Smith', '+27662012488', NULL, 'mobile_number'),
       ('Jane Doe', NULL, 'jane.doe@example.com', 'email_address');

INSERT INTO products (name, description, category, unit_price)
VALUES ('Milk', 'Full Cream','GROCERIES', 20.00),
       ('Bread', 'Brown', 'GROCERIES',15.00),
       ('Cheese', 'Cheddar','GROCERIES', 30.00),
       ('Water', 'Sparkling','GROCERIES',10.00);

INSERT INTO cashiers (name, surname)
VALUES ('John', 'Zungu'),
       ('Jane', 'Dlomo');

INSERT INTO tills (store_id, pos_system_id, till_number)
VALUES (1, 1, 3),
       (2, 2, 4);

INSERT INTO promotions (name, description, start_date, end_date, discount)
VALUES ('Black Friday', 'Get 10% off on selected items', '2023-01-01', '2023-12-31', 10.00),
       ('Promotion 2', 'Get 20% off on selected items', '2023-01-01', '2023-12-31', 20.00);

INSERT INTO specials (product_id, name, description, special_image_url, old_price, new_price, start_date, end_date)
VALUES (1, 'Milk Special', 'Discounted Full Cream Milk', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/milk_special.jpg', 25.00, 20.00, '2023-01-01', '2023-01-31'),
       (2, 'Bread Special', 'Discounted Brown Bread', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/bread_special.jpg', 18.00, 15.00, '2023-02-01', '2023-02-28');

INSERT INTO product_identifiers (product_id, store_id, pos_system_id, ean13_code, universal_product_code, sku)
VALUES (1, 1, 1, '1234567890123', '123456789012', 'SKU_001'),
       (2, 1, 1, '2345678901234', '234567890123', 'SKU_002'),
       (3, 2, 2, '3456789012345', '345678901234', 'SKU_003'),
       (4, 2, 2, '4567890123456', '456789012345', 'SKU_004');

INSERT INTO receipts (store_id, pos_system_id, customer_id, till_id, cashier_id, promotion_id, transaction_date, subtotal, vat_rate, vat_amount, discount_amount, total_due_after_tax, amount_paid, change_due)
VALUES (1, 1, 1, 1, 1, 1, '2023-05-01 10:30:00', 10.00, 15.00, 2.25, 0.00, 12.25, 12.25, 0.00),
       (2, 2, 2, 2, 2, 2, '2023-05-02 15:45:00', 30.00, 45.00,  13.50, 0.00, 31.50, 31.50, 0.00);

INSERT INTO line_items (receipt_id, product_id, quantity)
VALUES (1, 1, 3),
       (2, 2, 2);

INSERT INTO feedback (feedback_id, receipt_id, star_rating, user_comment, feedback_date)
VALUES
    (1, 1, 1, 'I had a bad experience.', '2023-08-21 15:27:24'),
    (2, 2, 2, 'Will shop here again.', '2023-08-14 15:27:24');