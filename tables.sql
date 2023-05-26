CREATE TABLE images (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  original_file_name VARCHAR(255),
  size BIGINT,
  content_type VARCHAR(255),
  is_preview_image BOOLEAN,
  bytes BYTEA,
  product_id BIGINT,
  FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  price FLOAT,
  city VARCHAR(255),
  description TEXT,
  preview_image_id BIGINT,
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE product_characteristics (
  product_id BIGINT,
  characteristic_key VARCHAR(255),
  characteristic_value VARCHAR(255),
  PRIMARY KEY (product_id, characteristic_key),
  FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255),
  password VARCHAR(255),
  phone_number VARCHAR(255),
  name VARCHAR(255),
  role VARCHAR(255)
);

CREATE TABLE user_favorite_products (
    user_id BIGINT,
    product_id BIGINT,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);