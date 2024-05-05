INSERT INTO merchants (vat_registration_no, name, address, store_logo_url)
VALUES ('123456789', 'Woolworths', '123 Main Street Sandton',
        'https://serengeti-bucket.s3.af-south-1.amazonaws.com/woolworths.jpg'),
       ('987654321', 'Checkers', '456 Second Avenue Linden',
        'https://serengeti-bucket.s3.af-south-1.amazonaws.com/checkers-hyper.jpg'),
       ('678765550', 'Pick n Pay', '12 Zeiss Road Honeydew',
        'https://serengeti-bucket.s3.af-south-1.amazonaws.com/pick-n-pay-logo-vector.png'),
       ('678765550', 'Zando', '1400 Chime Street Weltevreden Park',
        'https://serengeti-bucket.s3.af-south-1.amazonaws.com/zando.png'),
       ('678765550', 'Mr Price', '500 Ntuli Road Hammersadale',
        'https://serengeti-bucket.s3.af-south-1.amazonaws.com/mrprice.jpg');

INSERT INTO shoppers (name, mobile_number, email_address)
VALUES ('Philani', '+27662012488', 'philani.dlamini@gmail.com'),
       ('Jane Doe', NULL, 'jane.doe@example.com');


INSERT INTO specials (product_name, product_description, special_image_url, old_price, new_price, start_date, end_date,
                      merchant_id)
VALUES ('Milk', 'Discounted Full Cream Milk', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/milk.jpg', 25.00,
        20.00, '2023-01-01', '2023-01-31', 1),
       ('Bread', 'Discounted Brown Bread', 'https://serengeti-bucket.s3.af-south-1.amazonaws.com/bread.jpg', 18.00, 15.00, '2023-02-01', '2023-02-28',2);