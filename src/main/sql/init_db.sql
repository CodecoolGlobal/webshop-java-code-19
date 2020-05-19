ALTER TABLE IF EXISTS ONLY products DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY products DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY products DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY product_categories DROP CONSTRAINT IF EXISTS pk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY suppliers DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS products_id_seq;
DROP SEQUENCE IF EXISTS suppliers_id_seq;
DROP SEQUENCE IF EXISTS product_categories_id_seq;
CREATE TABLE IF NOT EXISTS "suppliers"(
    "id" serial NOT NULL,
    "name" text,
    "description" text
);

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

INSERT INTO suppliers values (1,'Amazon', 'Digital content and services');
INSERT INTO suppliers values (2,'Lenovo', 'Computers');
INSERT INTO suppliers values (3,'Apple', 'Designed in California');
INSERT INTO suppliers values (4,'Samsung', 'Samsung is great');

SELECT pg_catalog.setval('suppliers_id_seq',4,true);

CREATE TABLE IF NOT EXISTS "product_categories"(
    "id" serial NOT NULL,
    "name" text,
    "department" text,
    "description" text
);

ALTER TABLE ONLY product_categories
    ADD CONSTRAINT pk_category_id PRIMARY KEY (id);

INSERT INTO product_categories values (1,'tablet', 'Hardware','A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
    SELECT pg_catalog.setval('product_categories_id_seq',1,true);
CREATE TABLE IF NOT EXISTS "products" (
    "id" serial NOT NULL,
    "name" text,
    "default_price" float,
    "currency_string" text,
    "description" text,
    "category_id" integer,
    "supplier_id" integer
);
ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) references suppliers(id),
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) references product_categories(id);

INSERT INTO products values (1,'Amazon Fire', 49.99, 'USD','Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1,1);
SELECT pg_catalog.setval('products_id_seq',1,true);
