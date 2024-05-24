-- 카테고리 데이터 삽입
INSERT INTO categories (category_name) VALUES ('사료');
INSERT INTO categories (category_name) VALUES ('간식');
INSERT INTO categories (category_name) VALUES ('배변/위생');
INSERT INTO categories (category_name) VALUES ('패션');

-- 서브카테고리 데이터 삽입
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('강아지 사료', 1, 1);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('고양이 사료', 1, 1);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('강아지 간식', 2, 2);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('고양이 간식', 2, 2);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('배변패드', 3, 3);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('고양이 모래', 3, 3);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('의류', 4, 4);
INSERT INTO subcategories (subcategory_name, parent_category_id, category_id) VALUES ('넥카라', 4, 4);

-- 제품 데이터 삽입
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('맛 좋은 강아지 사료', 1, 1, 25000, 30, '2024-05-28', 120, 0, false, NOW(), NOW(), 0, false, 'url', '설명1');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('건강한 고양이 간식', 4, 4, 3500, 17, '2024-05-28', 30, 0, false, NOW(), NOW(), 0, false, 'url', '설명2');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('청결한 배변패드', 5, 5, 15000, 25, '2024-05-28', 48, 0, false, NOW(), NOW(), 0, false, 'url', '설명3');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저렴한 고양이 모래', 6, 6, 9000, 20, '2024-05-28', 95, 0, false, NOW(), NOW(), 0, false, 'url', '설명4');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('꿀벌 후드티', 7, 7, 20000, 9, '2024-05-28', 10, 0, false, NOW(), NOW(), 0, false, 'url', '설명5');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('토끼귀 모자', 7, 7, 9000, 31, '2024-05-28', 23, 0, false, NOW(), NOW(), 0, false, 'url', '설명6');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('화려하고 튼튼한 넥카라', 8, 8, 13200, 60, '2024-05-28', 77, 0, false, NOW(), NOW(), 0, false, 'url', '설명7');
