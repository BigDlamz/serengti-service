DROP TABLE IF EXISTS payment_transactions;
DROP TABLE IF EXISTS line_items;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS receipts;
DROP TABLE IF EXISTS specials;
DROP TABLE IF EXISTS shoppers;
DROP TABLE IF EXISTS cashiers;
DROP TABLE IF EXISTS tills;
DROP TABLE IF EXISTS merchants;

CREATE TABLE merchants
(
    merchant_id         BIGINT GENERATED BY DEFAULT AS IDENTITY,
    vat_registration_no VARCHAR(255),
    name                VARCHAR(255),
    address             VARCHAR(255),
    store_logo_url      VARCHAR(255),
    PRIMARY KEY (merchant_id)
);

CREATE TABLE shoppers
(
    shopper_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255),
    mobile_number VARCHAR(255),
    email_address VARCHAR(255) NOT NULL,
    CONSTRAINT unique_mobile_number UNIQUE (mobile_number)
);

CREATE TABLE tills
(
    till_id     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    till_number BIGINT NOT NULL
);

CREATE TABLE cashiers
(
    cashier_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(50),
    surname    VARCHAR(50)
);

CREATE TABLE receipts
(
    receipt_id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    merchant_id         BIGINT,
    shopper_id          BIGINT,
    till_id             BIGINT,
    cashier_id          BIGINT,
    transaction_date    TIMESTAMP NOT NULL,
    subtotal            NUMERIC(19, 2),
    vat_rate            NUMERIC(5, 2),
    vat_amount          NUMERIC(19, 2),
    discount_amount     NUMERIC(19, 2),
    total_due_after_tax NUMERIC(19, 2),
    amount_paid         NUMERIC(19, 2),
    change_due          NUMERIC(19, 2),
    viewed              BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (receipt_id),
    CONSTRAINT fk_receipt_merchant FOREIGN KEY (merchant_id) REFERENCES merchants (merchant_id),
    CONSTRAINT fk_receipt_shopper FOREIGN KEY (shopper_id) REFERENCES shoppers (shopper_id),
    CONSTRAINT fk_receipt_cashier FOREIGN KEY (cashier_id) REFERENCES cashiers (cashier_id),
    CONSTRAINT fk_receipt_till FOREIGN KEY (till_id) REFERENCES tills (till_id)
);

CREATE TABLE payment_transactions
(
    payment_transaction_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    receipt_id             BIGINT         NOT NULL REFERENCES receipts (receipt_id),
    payment_method         VARCHAR(50)    NOT NULL,
    payment_amount         NUMERIC(19, 2) NOT NULL,
    payment_date           TIMESTAMP      NOT NULL,
    CONSTRAINT fk_payment_transaction_receipt FOREIGN KEY (receipt_id) REFERENCES receipts (receipt_id)
);

CREATE TABLE line_items
(
    line_item_id        BIGINT GENERATED BY DEFAULT AS IDENTITY,
    receipt_id          BIGINT,
    product_name        VARCHAR(255),
    product_description VARCHAR(255),
    quantity            INTEGER,
    unit_price          NUMERIC(19, 2),
    PRIMARY KEY (line_item_id),
    CONSTRAINT fk_receipt_item_receipt FOREIGN KEY (receipt_id) REFERENCES receipts (receipt_id)
);

CREATE TABLE specials
(
    special_id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_name        VARCHAR(255)   NOT NULL,
    product_description VARCHAR(255),
    special_image_url   VARCHAR(255),
    old_price           NUMERIC(19, 2) NOT NULL,
    new_price           NUMERIC(19, 2) NOT NULL,
    start_date          DATE           NOT NULL,
    end_date            DATE           NOT NULL,
    merchant_id         BIGINT         NOT NULL REFERENCES merchants (merchant_id),
    CONSTRAINT chk_price CHECK (new_price < old_price)
);

CREATE TABLE feedback
(
    feedback_id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    receipt_id    BIGINT NOT NULL REFERENCES receipts (receipt_id),
    rating        INTEGER CHECK (rating >= 1 AND rating <= 5),
    user_comment  TEXT,
    feedback_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_receipt_feedback UNIQUE (receipt_id)
);