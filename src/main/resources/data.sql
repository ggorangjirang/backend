-- 외래 키 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 삭제
TRUNCATE TABLE products;
TRUNCATE TABLE subcategories;
TRUNCATE TABLE categories;
TRUNCATE TABLE orders;
TRUNCATE TABLE order_item;
TRUNCATE TABLE deliveries;
TRUNCATE TABLE users;

-- 외래 키 제약 조건 활성화
SET FOREIGN_KEY_CHECKS = 1;

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
-- 강아지 사료 (Subcategory 1)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 강아지 사료', 1, 1, 45500, 20, '2024-12-31', 50, 120, false, NOW(), NOW(), 1500, false, 'url', '설명1');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리 고기 강아지 사료', 1, 1, 32000, 30, '2024-12-31', 30, 85, false, NOW(), NOW(), 2300, false, 'url', '설명2');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 강아지 사료', 1, 1, 28300, 10, '2024-12-31', 45, 60, false, NOW(), NOW(), 800, false, 'url', '설명3');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어와 감자 강아지 사료', 1, 1, 54500, 40, '2024-12-31', 20, 190, false, NOW(), NOW(), 1600, false, 'url', '설명4');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨 맛 강아지 사료', 1, 1, 21000, 40, '2024-12-31', 75, 200, false, NOW(), NOW(), 700, false, 'url', '설명5');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 강아지 사료', 1, 1, 34000, 30, '2024-12-31', 15, 90, false, NOW(), NOW(), 1100, false, 'url', '설명6');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저지방 강아지 사료', 1, 1, 27300, 30, '2024-12-31', 5, 250, false, NOW(), NOW(), 1400, false, 'url', '설명7');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 강아지 사료', 1, 1, 40500, 0, '2024-12-31', 60, 30, false, NOW(), NOW(), 900, false, 'url', '설명8');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지용 식이섬유 사료', 1, 1, 22000, 40, '2024-12-31', 12, 140, false, NOW(), NOW(), 1300, false, 'url', '설명9');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 강아지 사료', 1, 1, 55700, 0, '2024-12-31', 80, 70, false, NOW(), NOW(), 1800, false, 'url', '설명10');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 1', 1, 1, 60000, 40, '2024-12-31', 60, 500, false, NOW(), NOW(), 1300, false, 'url', '설명11');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 2', 1, 1, 24000, 0, '2024-12-31', 15, 30, false, NOW(), NOW(), 1600, false, 'url', '설명12');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 3', 1, 1, 30000, 0, '2024-12-31', 1, 150, false, NOW(), NOW(), 700, false, 'url', '설명13');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 4', 1, 1, 20000, 50, '2024-12-31', 3, 200, false, NOW(), NOW(), 1700, false, 'url', '설명14');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 5', 1, 1, 32000, 30, '2024-12-31', 8, 100, false, NOW(), NOW(), 1900, false, 'url', '설명15');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 6', 1, 1, 50000, 0, '2024-12-31', 12, 300, false, NOW(), NOW(), 1100, false, 'url', '설명16');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 7', 1, 1, 25000, 50, '2024-12-31', 40, 20, false, NOW(), NOW(), 900, false, 'url', '설명17');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 8', 1, 1, 41000, 0, '2024-12-31', 80, 50, false, NOW(), NOW(), 1500, false, 'url', '설명18');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 9', 1, 1, 23000, 0, '2024-12-31', 90, 30, false, NOW(), NOW(), 1200, false, 'url', '설명19');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 10', 1, 1, 40000, 40, '2024-12-31', 1, 500, false, NOW(), NOW(), 1600, false, 'url', '설명20');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 11', 1, 1, 24000, 20, '2024-12-31', 2, 20, false, NOW(), NOW(), 1700, false, 'url', '설명21');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 12', 1, 1, 28000, 30, '2024-12-31', 5, 10, false, NOW(), NOW(), 700, false, 'url', '설명22');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 13', 1, 1, 18000, 30, '2024-12-31', 10, 30, false, NOW(), NOW(), 1900, false, 'url', '설명23');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 14', 1, 1, 38000, 0, '2024-12-31', 3, 100, false, NOW(), NOW(), 1100, false, 'url', '설명24');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 15', 1, 1, 48000, 0, '2024-12-31', 8, 52, false, NOW(), NOW(), 900, false, 'url', '설명25');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 16', 1, 1, 27000, 40, '2024-12-31', 12, 250, false, NOW(), NOW(), 1500, false, 'url', '설명26');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 17', 1, 1, 32000, 0, '2024-12-31', 12, 84, false, NOW(), NOW(), 1200, false, 'url', '설명27');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 18', 1, 1, 24000, 0, '2024-12-31', 13, 16, false, NOW(), NOW(), 1600, false, 'url', '설명28');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 19', 1, 1, 30000, 40, '2024-12-31', 1, 500, false, NOW(), NOW(), 1700, false, 'url', '설명29');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 20', 1, 1, 25000, 0, '2024-12-31', 1, 200, false, NOW(), NOW(), 700, false, 'url', '설명30');


-- 고양이 사료 (Subcategory 2)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 사료', 2, 2, 48200, 40, '2024-12-31', 60, 180, false, NOW(), NOW(), 1700, false, 'url', '설명31');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨 맛 고양이 사료', 2, 2, 31500, 30, '2024-12-31', 9, 90, false, NOW(), NOW(), 900, false, 'url', '설명32');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 사료', 2, 2, 27700, 0, '2024-12-31', 8, 75, false, NOW(), NOW(), 1300, false, 'url', '설명33');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어와 감자 고양이 사료', 2, 2, 53900, 30, '2024-12-31', 10, 37, false, NOW(), NOW(), 1400, false, 'url', '설명34');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 고양이 사료', 2, 2, 22500, 40, '2024-12-31', 8, 29, false, NOW(), NOW(), 1900, false, 'url', '설명35');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 사료', 2, 2, 37000, 0, '2024-12-31', 55, 145, false, NOW(), NOW(), 2000, false, 'url', '설명36');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저지방 고양이 사료', 2, 2, 25500, 40, '2024-12-31', 5, 70, false, NOW(), NOW(), 1100, false, 'url', '설명37');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 고양이 사료', 2, 2, 42000, 50, '2024-12-31', 3, 90, false, NOW(), NOW(), 1300, false, 'url', '설명38');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이용 식이섬유 사료', 2, 2, 23000, 50, '2024-12-31', 15, 190, false, NOW(), NOW(), 1500, false, 'url', '설명39');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 사료', 2, 2, 56300, 0, '2024-12-31', 19, 80, false, NOW(), NOW(), 1600, false, 'url', '설명40');


-- 강아지 간식 (Subcategory 3)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 강아지 간식', 3, 3, 15700, 0, '2024-12-31', 20, 150, false, NOW(), NOW(), 1300, false, 'url', '설명41');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨맛 강아지 간식', 3, 3, 12600, 0, '2024-12-31', 25, 100, false, NOW(), NOW(), 1100, false, 'url', '설명42');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어맛 강아지 간식', 3, 3, 18100, 0, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'url', '설명43');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 강아지 간식', 3, 3, 14900, 0, '2024-12-31', 50, 90, false, NOW(), NOW(), 1700, false, 'url', '설명44');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 강아지 간식', 3, 3, 16300, 0, '2024-12-31', 20, 10, false, NOW(), NOW(), 1500, false, 'url', '설명45');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 강아지 간식', 3, 3, 22000, 40, '2024-12-31', 10, 20, false, NOW(), NOW(), 1900, false, 'url', '설명46');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 강아지 간식', 3, 3, 11200, 0, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명47');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 강아지 간식', 3, 3, 13900, 0, '2024-12-31', 55, 70, false, NOW(), NOW(), 1100, false, 'url', '설명48');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 강아지 간식', 3, 3, 17000, 40, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'url', '설명49');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 강아지 간식', 3, 3, 19000, 0, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명50');


-- 고양이 간식 (Subcategory 4)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 간식', 4, 4, 16000, 0, '2024-12-31', 40, 130, false, NOW(), NOW(), 1200, false, 'url', '설명51');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨맛 고양이 간식', 4, 4, 13000, 0, '2024-12-31', 20, 90, false, NOW(), NOW(), 1100, false, 'url', '설명52');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어맛 고양이 간식', 4, 4, 19000, 40, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'url', '설명53');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 고양이 간식', 4, 4, 15000, 30, '2024-12-31', 50, 120, false, NOW(), NOW(), 1700, false, 'url', '설명54');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 간식', 4, 4, 17000, 40, '2024-12-31', 25, 300, false, NOW(), NOW(), 1500, false, 'url', '설명55');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 고양이 간식', 4, 4, 21000, 0, '2024-12-31', 10, 100, false, NOW(), NOW(), 1900, false, 'url', '설명56');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 고양이 간식', 4, 4, 12000, 40, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명57');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 고양이 간식', 4, 4, 14000, 40, '2024-12-31', 55, 40, false, NOW(), NOW(), 1100, false, 'url', '설명58');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 간식', 4, 4, 18000, 40, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'url', '설명59');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 간식', 4, 4, 20000, 0, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명60');


-- 배변패드 (Subcategory 5)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 배변패드', 5, 5, 10000, 0, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'url', '설명61');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('흡수력 좋은 배변패드', 5, 5, 12000, 30, NULL, 6, 90, false, NOW(), NOW(), 1100, false, 'url', '설명62');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 배변패드', 5, 5, 15000, 20, NULL, 3, 90, false, NOW(), NOW(), 900, false, 'url', '설명63');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('대형 배변패드', 5, 5, 14000, 40, NULL, 11, 120, false, NOW(), NOW(), 1700, false, 'url', '설명64');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소형 배변패드', 5, 5, 29000, 40, NULL, 25, 300, false, NOW(), NOW(), 1500, false, 'url', '설명65');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고급 배변패드', 5, 5, 18000, 0, NULL, 10, 80, false, NOW(), NOW(), 1900, false, 'url', '설명66');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 배변패드', 5, 5, 11000, 40, NULL, 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명67');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 배변패드', 5, 5, 13000, 0, NULL, 27, 80, false, NOW(), NOW(), 1100, false, 'url', '설명68');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 배변패드', 5, 5, 17000, 40, NULL, 29, 90, false, NOW(), NOW(), 1800, false, 'url', '설명69');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 배변패드', 5, 5, 19000, 20, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명70');


-- 고양이 모래 (Subcategory 6)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 모래', 6, 6, 18000, 20, NULL, 4, 150, false, NOW(), NOW(), 1300, false, 'url', '설명71');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('흡수력 좋은 고양이 모래', 6, 6, 22000, 30, NULL, 35, 107, false, NOW(), NOW(), 1100, false, 'url', '설명72');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 모래', 6, 6, 17000, 20, NULL, 5, 98, false, NOW(), NOW(), 900, false, 'url', '설명73');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('대형 고양이 모래', 6, 6, 24000, 0, NULL, 8, 90, false, NOW(), NOW(), 1700, false, 'url', '설명74');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소형 고양이 모래', 6, 6, 15000, 40, NULL, 30, 53, false, NOW(), NOW(), 1500, false, 'url', '설명75');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고급 고양이 모래', 6, 6, 25000, 40, NULL, 5, 60, false, NOW(), NOW(), 1900, false, 'url', '설명76');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 고양이 모래', 6, 6, 14000, 40, NULL, 15, 50, false, NOW(), NOW(), 1400, false, 'url', '설명77');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 고양이 모래', 6, 6, 20000, 0, NULL, 17, 200, false, NOW(), NOW(), 1100, false, 'url', '설명78');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 모래', 6, 6, 21000, 40, NULL, 27, 90, false, NOW(), NOW(), 1800, false, 'url', '설명79');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 모래', 6, 6, 23000, 0, NULL, 29, 70, false, NOW(), NOW(), 1600, false, 'url', '설명80');


-- 의류 (Subcategory 7)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 겨울옷', 7, 7, 32000, 40, NULL, 17, 150, false, NOW(), NOW(), 1200, false, 'url', '설명81');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 여름옷', 7, 7, 15000, 0, NULL, 2, 90, false, NOW(), NOW(), 1100, false, 'url', '설명82');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 레인코트', 7, 7, 27000, 0, NULL, 30, 80, false, NOW(), NOW(), 900, false, 'url', '설명83');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 니트', 7, 7, 18000, 0, NULL, 8, 57, false, NOW(), NOW(), 1700, false, 'url', '설명84');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 패딩', 7, 7, 50000, 40, NULL, 1, 20, false, NOW(), NOW(), 1500, false, 'url', '설명85');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 털옷', 7, 7, 45000, 0, NULL, 1, 11, false, NOW(), NOW(), 1900, false, 'url', '설명86');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 운동복', 7, 7, 29000, 50, NULL, 1, 80, false, NOW(), NOW(), 1400, false, 'url', '설명87');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 스웨터', 7, 7, 23000, 0, NULL, 8, 200, false, NOW(), NOW(), 1100, false, 'url', '설명88');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 발열조끼', 7, 7, 37000, 40, NULL, 9, 90, false, NOW(), NOW(), 1800, false, 'url', '설명89');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 반팔', 7, 7, 25000, 40, NULL, 17, 70, false, NOW(), NOW(), 1600, false, 'url', '설명90');


-- 넥카라 (Subcategory 8)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 소형', 8, 8, 12000, 40, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'url', '설명91');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 소형', 8, 8, 11000, 0, NULL, 20, 90, false, NOW(), NOW(), 1100, false, 'url', '설명92');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 중형', 8, 8, 15000, 20, NULL, 17, 70, false, NOW(), NOW(), 900, false, 'url', '설명93');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 중형', 8, 8, 14000, 0, NULL, 18, 10, false, NOW(), NOW(), 1700, false, 'url', '설명94');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 대형', 8, 8, 18000, 40, NULL, 16, 90, false, NOW(), NOW(), 1500, false, 'url', '설명95');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 대형', 8, 8, 17000, 40, NULL, 10, 100, false, NOW(), NOW(), 1900, false, 'url', '설명96');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 실리콘 넥카라', 8, 8, 22000, 40, NULL, 20, 50, false, NOW(), NOW(), 1400, false, 'url', '설명97');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 실리콘 넥카라', 8, 8, 21000, 0, NULL, 9, 87, false, NOW(), NOW(), 1100, false, 'url', '설명98');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 보호용 넥카라', 8, 8, 25000, 40, NULL, 16, 90, false, NOW(), NOW(), 1800, false, 'url', '설명99');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 보호용 넥카라', 8, 8, 24000, 0, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명100');


-- 추가 데이터
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가1', 2, 2, 56000, 0, '2024-12-31', 19, 180, false, NOW(), NOW(), 1600, false, 'url', '추가1');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가2', 2, 2, 47000, 50, '2024-12-31', 17, 80, false, NOW(), NOW(), 1600, false, 'url', '추가2');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가3', 2, 2, 47500, 60, '2024-12-31', 16, 280, false, NOW(), NOW(), 1600, false, 'url', '추가3');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가4', 2, 2, 39500, 40, '2024-12-31', 17, 80, false, NOW(), NOW(), 1600, false, 'url', '추가4');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가5', 2, 2, 37000, 40, '2024-12-31', 21, 480, false, NOW(), NOW(), 1600, false, 'url', '추가5');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가6', 2, 2, 53200, 40, '2024-12-31', 5, 80, false, NOW(), NOW(), 1600, false, 'url', '추가6');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가7', 2, 2, 21100, 40, '2024-12-31', 9, 80, false, NOW(), NOW(), 1600, false, 'url', '추가7');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가8', 2, 2, 36200, 40, '2024-12-31', 40, 580, false, NOW(), NOW(), 1600, false, 'url', '추가8');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가9', 2, 2, 27700, 40, '2024-12-31', 3, 80, false, NOW(), NOW(), 1600, false, 'url', '추가9');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 사료 추가10', 2, 2, 25600, 40, '2024-12-31', 1, 180, false, NOW(), NOW(), 1600, false, 'url', '추가10');

-- 주소
INSERT INTO deliveries (zipcode, street_address, detail_address, status, request, arrival_date, phone_number, name)
VALUE('12345', '123 test St', 'May 101', 'Pending', 'Testing~', '2024-06-01', 1234567890, 'Test');

INSERT INTO deliveries (zipcode, street_address, detail_address, status, request, arrival_date, phone_number, name)
VALUE('12345', '123 test St', 'May 102', 'Pending', 'Testing~', '2024-06-01', 1234567890, 'Test2');

-- 유저
INSERT INTO users (name, password, email) VALUES ('test','1234','test@test.com');

-- 주문
INSERT INTO orders (deliveries_id, order_date, order_number, order_status, user_id, total_all_price)
VALUES (1, '2024-05-01T10:00:00', 'abcd1234', 'ORDER', 1, 3000);

INSERT INTO orders (deliveries_id, order_date, order_number, order_status, user_id, total_all_price)
VALUES (2, '2024-05-01T10:00:00', 'fead2342', 'ORDER', 1, 4000);


-- 주문 아이템
INSERT INTO order_item (order_price, quantity, order_id, product_id)
VALUES (100,10,1,8);

INSERT INTO order_item (order_price, quantity, order_id, product_id)
VALUES (40,10,1,9);

INSERT INTO order_item (order_price, quantity, order_id, product_id)
VALUES (500,10,2,3);
