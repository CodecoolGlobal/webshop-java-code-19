ALTER TABLE IF EXISTS ONLY products
    DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY products
    DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY products
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY product_categories
    DROP CONSTRAINT IF EXISTS pk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY suppliers
    DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS products_id_seq;
DROP SEQUENCE IF EXISTS suppliers_id_seq;
DROP SEQUENCE IF EXISTS product_categories_id_seq;
CREATE TABLE IF NOT EXISTS "suppliers"
(
    "id"          serial NOT NULL,
    "name"        text,
    "description" text
);

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

INSERT INTO suppliers
values (1, 'Amazon', 'Digital content and services');
INSERT INTO suppliers
values (2, 'Lenovo', 'Computers');
INSERT INTO suppliers
values (3, 'Apple', 'Designed in California');
INSERT INTO suppliers
values (4, 'Samsung', 'Samsung is great');

SELECT pg_catalog.setval('suppliers_id_seq', 4, true);

CREATE TABLE IF NOT EXISTS "product_categories"
(
    "id"          serial NOT NULL,
    "name"        text,
    "department"  text,
    "description" text
);

ALTER TABLE ONLY product_categories
    ADD CONSTRAINT pk_category_id PRIMARY KEY (id);

INSERT INTO product_categories
values (1, 'tablet', 'Hardware',
        'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
SELECT pg_catalog.setval('product_categories_id_seq', 1, true);
CREATE TABLE IF NOT EXISTS "products"
(
    "id"              serial NOT NULL,
    "name"            text,
    "default_price"   float,
    "currency_string" text,
    "description"     text,
    "category_id"     integer,
    "supplier_id"     integer
);
ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) references suppliers (id),
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) references product_categories (id);

INSERT INTO products
values (1, 'Amazon Fire', 49.99, 'USD',
        'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);
INSERT INTO products
values (2, 'Lenovo IdeaPad Miix 700', 479, 'USD',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2);
INSERT INTO products
values (3, 'Amazon Fire HD 8', 89, 'USD', 'Amazons latest Fire HD 8 tablet is a great value for media consumption.', 1,
        1);
INSERT INTO products
values (4, 'Apple iPad Pro', 899, 'USD',
        '11-Inch edge-to-edge Liquid Retina display with Promotion, true Tone, and wide Color A12X Bionic chip with Neural Engine Face ID for secure authentication and Apple Pay 12MP back camera, 7MP True Depth front camera',
        1, 3);
INSERT INTO products
values (5, 'Samsung Galaxy Tab 4', 250, 'USD',
        'Android 4.4 Kit Kat OS, 1.2 GHz quad-core Qualcomm processor ; 16 GB Flash Memory, 1.5 GB RAM Memory and battery life is up to 10 hours',
        1, 4);


SELECT pg_catalog.setval('products_id_seq', 5, true);
