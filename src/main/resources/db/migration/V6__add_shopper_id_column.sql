ALTER TABLE payments
    ADD COLUMN shopper_id BIGINT,
    ADD CONSTRAINT fk_payments_shoppers
        FOREIGN KEY (shopper_id) REFERENCES shoppers (shopper_id);