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
VALUES ('프리미엄 강아지 사료', 1, 1, 45500, 15, '2024-12-31', 50, 120, false, NOW(), NOW(), 1500, false, 'url', '설명1');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리 고기 강아지 사료', 1, 1, 32000, 25, '2024-12-31', 30, 85, false, NOW(), NOW(), 2300, false, 'url', '설명2');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 강아지 사료', 1, 1, 28300, 10, '2024-12-31', 45, 60, false, NOW(), NOW(), 800, false, 'url', '설명3');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어와 감자 강아지 사료', 1, 1, 54500, 35, '2024-12-31', 20, 190, false, NOW(), NOW(), 1600, false, 'url', '설명4');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨 맛 강아지 사료', 1, 1, 21000, 50, '2024-12-31', 75, 200, false, NOW(), NOW(), 700, false, 'url', '설명5');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 강아지 사료', 1, 1, 34000, 20, '2024-12-31', 15, 90, false, NOW(), NOW(), 1100, false, 'url', '설명6');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저지방 강아지 사료', 1, 1, 27300, 30, '2024-12-31', 5, 250, false, NOW(), NOW(), 1400, false, 'url', '설명7');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 강아지 사료', 1, 1, 40500, 18, '2024-12-31', 60, 30, false, NOW(), NOW(), 900, false, 'url', '설명8');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지용 식이섬유 사료', 1, 1, 22000, 40, '2024-12-31', 12, 140, false, NOW(), NOW(), 1300, false, 'url', '설명9');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 강아지 사료', 1, 1, 55700, 12, '2024-12-31', 80, 70, false, NOW(), NOW(), 1800, false, 'url', '설명10');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 1', 1, 1, 60000, 40, '2024-12-31', 60, 500, false, NOW(), NOW(), 1300, false, 'url', '설명181');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 2', 1, 1, 24000, 22, '2024-12-31', 15, 30, false, NOW(), NOW(), 1600, false, 'url', '설명182');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 3', 1, 1, 30000, 25, '2024-12-31', 1, 150, false, NOW(), NOW(), 700, false, 'url', '설명183');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 4', 1, 1, 20000, 35, '2024-12-31', 3, 200, false, NOW(), NOW(), 1700, false, 'url', '설명184');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 5', 1, 1, 32000, 30, '2024-12-31', 8, 100, false, NOW(), NOW(), 1900, false, 'url', '설명185');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 6', 1, 1, 50000, 10, '2024-12-31', 12, 300, false, NOW(), NOW(), 1100, false, 'url', '설명186');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 7', 1, 1, 25000, 45, '2024-12-31', 40, 20, false, NOW(), NOW(), 900, false, 'url', '설명187');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 8', 1, 1, 41000, 20, '2024-12-31', 80, 50, false, NOW(), NOW(), 1500, false, 'url', '설명188');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 9', 1, 1, 23000, 15, '2024-12-31', 90, 30, false, NOW(), NOW(), 1200, false, 'url', '설명189');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 10', 1, 1, 40000, 35, '2024-12-31', 1, 500, false, NOW(), NOW(), 1600, false, 'url', '설명190');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 11', 1, 1, 24000, 22, '2024-12-31', 2, 20, false, NOW(), NOW(), 1700, false, 'url', '설명191');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 12', 1, 1, 28000, 25, '2024-12-31', 5, 10, false, NOW(), NOW(), 700, false, 'url', '설명192');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 13', 1, 1, 18000, 35, '2024-12-31', 10, 30, false, NOW(), NOW(), 1900, false, 'url', '설명193');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 14', 1, 1, 38000, 30, '2024-12-31', 3, 100, false, NOW(), NOW(), 1100, false, 'url', '설명194');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 15', 1, 1, 48000, 10, '2024-12-31', 8, 52, false, NOW(), NOW(), 900, false, 'url', '설명195');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 16', 1, 1, 27000, 45, '2024-12-31', 12, 250, false, NOW(), NOW(), 1500, false, 'url', '설명196');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 17', 1, 1, 32000, 20, '2024-12-31', 12, 84, false, NOW(), NOW(), 1200, false, 'url', '설명197');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 18', 1, 1, 24000, 15, '2024-12-31', 13, 16, false, NOW(), NOW(), 1600, false, 'url', '설명198');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 19', 1, 1, 30000, 35, '2024-12-31', 1, 500, false, NOW(), NOW(), 1700, false, 'url', '설명199');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 사료 추가 20', 1, 1, 25000, 22, '2024-12-31', 1, 200, false, NOW(), NOW(), 700, false, 'url', '설명200');


-- 고양이 사료 (Subcategory 2)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 사료', 2, 2, 48200, 20, '2024-12-31', 60, 180, false, NOW(), NOW(), 1700, false, 'url', '설명61');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨 맛 고양이 사료', 2, 2, 31500, 25, '2024-12-31', 9, 90, false, NOW(), NOW(), 900, false, 'url', '설명62');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 사료', 2, 2, 27700, 15, '2024-12-31', 8, 75, false, NOW(), NOW(), 1300, false, 'url', '설명63');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어와 감자 고양이 사료', 2, 2, 53900, 30, '2024-12-31', 10, 37, false, NOW(), NOW(), 1400, false, 'url', '설명64');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 고양이 사료', 2, 2, 22500, 40, '2024-12-31', 8, 29, false, NOW(), NOW(), 1900, false, 'url', '설명65');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 사료', 2, 2, 37000, 22, '2024-12-31', 55, 145, false, NOW(), NOW(), 2000, false, 'url', '설명66');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저지방 고양이 사료', 2, 2, 25500, 35, '2024-12-31', 5, 70, false, NOW(), NOW(), 1100, false, 'url', '설명67');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 고양이 사료', 2, 2, 42000, 18, '2024-12-31', 3, 90, false, NOW(), NOW(), 1300, false, 'url', '설명68');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이용 식이섬유 사료', 2, 2, 23000, 50, '2024-12-31', 15, 190, false, NOW(), NOW(), 1500, false, 'url', '설명69');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 사료', 2, 2, 56300, 15, '2024-12-31', 19, 80, false, NOW(), NOW(), 1600, false, 'url', '설명70');


-- 강아지 간식 (Subcategory 3)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 강아지 간식', 3, 3, 15700, 25, '2024-12-31', 20, 150, false, NOW(), NOW(), 1300, false, 'url', '설명121');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨맛 강아지 간식', 3, 3, 12600, 30, '2024-12-31', 25, 100, false, NOW(), NOW(), 1100, false, 'url', '설명122');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어맛 강아지 간식', 3, 3, 18100, 15, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'url', '설명123');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 강아지 간식', 3, 3, 14900, 20, '2024-12-31', 50, 90, false, NOW(), NOW(), 1700, false, 'url', '설명124');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 강아지 간식', 3, 3, 16300, 35, '2024-12-31', 20, 10, false, NOW(), NOW(), 1500, false, 'url', '설명125');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 강아지 간식', 3, 3, 22000, 18, '2024-12-31', 10, 20, false, NOW(), NOW(), 1900, false, 'url', '설명126');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 강아지 간식', 3, 3, 11200, 40, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명127');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 강아지 간식', 3, 3, 13900, 22, '2024-12-31', 55, 70, false, NOW(), NOW(), 1100, false, 'url', '설명128');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 강아지 간식', 3, 3, 17000, 35, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'url', '설명129');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 강아지 간식', 3, 3, 19000, 15, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명130');


-- 고양이 간식 (Subcategory 4)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 간식', 4, 4, 16000, 20, '2024-12-31', 40, 130, false, NOW(), NOW(), 1200, false, 'url', '설명131');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('치킨맛 고양이 간식', 4, 4, 13000, 25, '2024-12-31', 20, 90, false, NOW(), NOW(), 1100, false, 'url', '설명132');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('연어맛 고양이 간식', 4, 4, 19000, 15, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'url', '설명133');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('오리고기 고양이 간식', 4, 4, 15000, 30, '2024-12-31', 50, 120, false, NOW(), NOW(), 1700, false, 'url', '설명134');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 간식', 4, 4, 17000, 35, '2024-12-31', 25, 300, false, NOW(), NOW(), 1500, false, 'url', '설명135');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소고기 고양이 간식', 4, 4, 21000, 18, '2024-12-31', 10, 100, false, NOW(), NOW(), 1900, false, 'url', '설명136');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 고양이 간식', 4, 4, 12000, 40, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명137');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 고양이 간식', 4, 4, 14000, 22, '2024-12-31', 55, 40, false, NOW(), NOW(), 1100, false, 'url', '설명138');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 간식', 4, 4, 18000, 35, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'url', '설명139');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 간식', 4, 4, 20000, 15, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명140');


-- 배변패드 (Subcategory 5)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 배변패드', 5, 5, 10000, 20, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'url', '설명141');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('흡수력 좋은 배변패드', 5, 5, 12000, 25, NULL, 6, 90, false, NOW(), NOW(), 1100, false, 'url', '설명142');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 배변패드', 5, 5, 15000, 15, NULL, 3, 90, false, NOW(), NOW(), 900, false, 'url', '설명143');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('대형 배변패드', 5, 5, 14000, 30, NULL, 11, 120, false, NOW(), NOW(), 1700, false, 'url', '설명144');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소형 배변패드', 5, 5, 9000, 35, NULL, 25, 300, false, NOW(), NOW(), 1500, false, 'url', '설명145');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고급 배변패드', 5, 5, 18000, 18, NULL, 10, 80, false, NOW(), NOW(), 1900, false, 'url', '설명146');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 배변패드', 5, 5, 11000, 40, NULL, 80, 50, false, NOW(), NOW(), 1400, false, 'url', '설명147');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 배변패드', 5, 5, 13000, 22, NULL, 27, 80, false, NOW(), NOW(), 1100, false, 'url', '설명148');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 배변패드', 5, 5, 17000, 35, NULL, 29, 90, false, NOW(), NOW(), 1800, false, 'url', '설명149');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 배변패드', 5, 5, 19000, 15, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명150');


-- 고양이 모래 (Subcategory 6)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('프리미엄 고양이 모래', 6, 6, 18000, 25, NULL, 4, 150, false, NOW(), NOW(), 1300, false, 'url', '설명151');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('흡수력 좋은 고양이 모래', 6, 6, 22000, 30, NULL, 35, 107, false, NOW(), NOW(), 1100, false, 'url', '설명152');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저알러지 고양이 모래', 6, 6, 17000, 15, NULL, 5, 98, false, NOW(), NOW(), 900, false, 'url', '설명153');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('대형 고양이 모래', 6, 6, 24000, 20, NULL, 8, 90, false, NOW(), NOW(), 1700, false, 'url', '설명154');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('소형 고양이 모래', 6, 6, 15000, 35, NULL, 30, 53, false, NOW(), NOW(), 1500, false, 'url', '설명155');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고급 고양이 모래', 6, 6, 25000, 18, NULL, 5, 60, false, NOW(), NOW(), 1900, false, 'url', '설명156');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('저칼로리 고양이 모래', 6, 6, 14000, 40, NULL, 15, 50, false, NOW(), NOW(), 1400, false, 'url', '설명157');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고구마 고양이 모래', 6, 6, 20000, 22, NULL, 17, 200, false, NOW(), NOW(), 1100, false, 'url', '설명158');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('통곡물 고양이 모래', 6, 6, 21000, 35, NULL, 27, 90, false, NOW(), NOW(), 1800, false, 'url', '설명159');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('유기농 고양이 모래', 6, 6, 23000, 15, NULL, 29, 70, false, NOW(), NOW(), 1600, false, 'url', '설명160');


-- 의류 (Subcategory 7)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 겨울옷', 7, 7, 32000, 20, NULL, 17, 150, false, NOW(), NOW(), 1200, false, 'url', '설명161');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 여름옷', 7, 7, 15000, 25, NULL, 2, 90, false, NOW(), NOW(), 1100, false, 'url', '설명162');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 레인코트', 7, 7, 27000, 15, NULL, 30, 80, false, NOW(), NOW(), 900, false, 'url', '설명163');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 니트', 7, 7, 18000, 30, NULL, 8, 57, false, NOW(), NOW(), 1700, false, 'url', '설명164');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 패딩', 7, 7, 50000, 35, NULL, 1, 20, false, NOW(), NOW(), 1500, false, 'url', '설명165');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 털옷', 7, 7, 45000, 18, NULL, 1, 11, false, NOW(), NOW(), 1900, false, 'url', '설명166');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 운동복', 7, 7, 29000, 40, NULL, 1, 80, false, NOW(), NOW(), 1400, false, 'url', '설명167');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 스웨터', 7, 7, 23000, 22, NULL, 8, 200, false, NOW(), NOW(), 1100, false, 'url', '설명168');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 발열조끼', 7, 7, 37000, 35, NULL, 9, 90, false, NOW(), NOW(), 1800, false, 'url', '설명169');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 반팔', 7, 7, 25000, 15, NULL, 17, 70, false, NOW(), NOW(), 1600, false, 'url', '설명170');


-- 넥카라 (Subcategory 8)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 소형', 8, 8, 12000, 20, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'url', '설명171');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 소형', 8, 8, 11000, 25, NULL, 20, 90, false, NOW(), NOW(), 1100, false, 'url', '설명172');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 중형', 8, 8, 15000, 15, NULL, 17, 70, false, NOW(), NOW(), 900, false, 'url', '설명173');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 중형', 8, 8, 14000, 30, NULL, 18, 10, false, NOW(), NOW(), 1700, false, 'url', '설명174');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 넥카라 대형', 8, 8, 18000, 35, NULL, 16, 90, false, NOW(), NOW(), 1500, false, 'url', '설명175');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 넥카라 대형', 8, 8, 17000, 18, NULL, 10, 100, false, NOW(), NOW(), 1900, false, 'url', '설명176');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 실리콘 넥카라', 8, 8, 22000, 40, NULL, 20, 50, false, NOW(), NOW(), 1400, false, 'url', '설명177');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 실리콘 넥카라', 8, 8, 21000, 22, NULL, 9, 87, false, NOW(), NOW(), 1100, false, 'url', '설명178');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('강아지 보호용 넥카라', 8, 8, 25000, 35, NULL, 16, 90, false, NOW(), NOW(), 1800, false, 'url', '설명179');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description)
VALUES ('고양이 보호용 넥카라', 8, 8, 24000, 15, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'url', '설명180');
