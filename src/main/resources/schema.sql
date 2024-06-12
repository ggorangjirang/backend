-- 외래 키 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 삭제
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS deliveries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS subcategories;
DROP TABLE IF EXISTS categories;

-- 테이블 생성
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE subcategories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subcategory_name VARCHAR(255) NOT NULL,
    parent_category_id BIGINT,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    zipcode VARCHAR(255),
    street_address VARCHAR(255),
    detail_address VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    role VARCHAR(50),
    social_id VARCHAR(255),
    refresh_token VARCHAR(255),
    enabled BOOLEAN DEFAULT false,
    token VARCHAR(255),
    expiry_date TIMESTAMP
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    subcategory_id BIGINT,
    pseudo_category_id BIGINT,
    price INT NOT NULL,
    discount_rate FLOAT,
    expiration_date DATE,
    stock INT,
    order_count INT,
    is_sold_out BOOLEAN NOT NULL DEFAULT false,
    created_at DATETIME,
    updated_at DATETIME,
    view_count INT DEFAULT 0,
    is_deleted BOOLEAN NOT NULL DEFAULT false,
    image_url VARCHAR(255),
    description_image_url VARCHAR(255),
    description TEXT,
    FOREIGN KEY (subcategory_id) REFERENCES subcategories(id)
);

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    image_url VARCHAR(255),
    product_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    deliveries_id BIGINT,
    order_date TIMESTAMP,
    order_number VARCHAR(255) NOT NULL,
    order_status VARCHAR(50),
    user_id BIGINT,
    total_all_price INT,
    FOREIGN KEY (deliveries_id) REFERENCES deliveries(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_item (
    order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_price INT NOT NULL,
    quantity INT NOT NULL,
    product_id BIGINT,
    order_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE deliveries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date DATETIME,
    arrival_date DATETIME,
    status VARCHAR(255) NOT NULL,
    order_id BIGINT,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    request VARCHAR(255),
    zipcode VARCHAR(255) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    detail_address VARCHAR(255) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

-- 외래 키 제약 조건 활성화
SET FOREIGN_KEY_CHECKS = 1;