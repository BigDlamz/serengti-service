SELECT
    r.id as receipt_id,
    r.timestamp,
    r.total_amount_paid,
    s.name as store_name,
    s.address as store_address,
    p.name as pos_name,
    p.vendor as pos_vendor,
    c.name as customer_name,
    c.mobile_number,
    c.email_address,
    i.quantity as item_quantity,
    pr.name as product_name,
    pr.sku as product_sku,
    pr.price as product_price
FROM
    receipts r
    JOIN stores s ON r.store_id = s.id
    JOIN pos_systems p ON r.pos_system_id = p.id
    JOIN customers c ON r.customer_id = c.id
    JOIN receipt_items i ON r.id = i.receipt_id
    JOIN products pr ON i.product_id = pr.id
WHERE
    r.id = {receipt_id};