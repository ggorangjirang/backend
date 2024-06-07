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
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('프리미엄 강아지 사료', 1, 1, 45500, 20, '2024-12-31', 50, 120, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('오리 고기 강아지 사료', 1, 1, 32000, 30, '2024-12-31', 30, 85, false, NOW(), NOW(), 2300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('저알러지 강아지 사료', 1, 1, 28300, 10, '2024-12-31', 45, 60, false, NOW(), NOW(), 800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('연어와 감자 강아지 사료', 1, 1, 54500, 40, '2024-12-31', 20, 190, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('치킨 맛 강아지 사료', 1, 1, 21000, 40, '2024-12-31', 75, 200, false, NOW(), NOW(), 700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('통곡물 강아지 사료', 1, 1, 34000, 30, '2024-12-31', 15, 90, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('저지방 강아지 사료', 1, 1, 27300, 30, '2024-12-31', 5, 250, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('소고기 강아지 사료', 1, 1, 40500, 0, '2024-12-31', 60, 30, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지용 식이섬유 사료', 1, 1, 22000, 40, '2024-12-31', 12, 140, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('유기농 강아지 사료', 1, 1, 55700, 0, '2024-12-31', 80, 70, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('건강한 멍멍이', 1, 1, 60000, 40, '2024-12-31', 60, 500, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('행복한 강아지 밥', 1, 1, 24000, 0, '2024-12-31', 15, 30, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('멍멍이 맛집', 1, 1, 30000, 0, '2024-12-31', 1, 150, false, NOW(), NOW(), 700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('튼튼 강아지를 위한 사료', 1, 1, 20000, 50, '2024-12-31', 3, 200, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('맛좋은 댕댕밥', 1, 1, 32000, 30, '2024-12-31', 8, 100, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('영양가득 강아지 사료', 1, 1, 50000, 0, '2024-12-31', 12, 300, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('순수한 강아지 사료', 1, 1, 25000, 50, '2024-12-31', 40, 20, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('식사 시간이 제일 즐거워', 1, 1, 41000, 0, '2024-12-31', 80, 50, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지의 기쁨 사료', 1, 1, 23000, 0, '2024-12-31', 90, 30, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('활력 충전 사료', 1, 1, 40000, 40, '2024-12-31', 1, 500, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('사랑 가득 강아지밥', 1, 1, 24000, 20, '2024-12-31', 2, 20, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 활력식', 1, 1, 28000, 30, '2024-12-31', 5, 10, false, NOW(), NOW(), 700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('멍멍이 건강밥', 1, 1, 18000, 30, '2024-12-31', 10, 30, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 건강 한끼', 1, 1, 38000, 0, '2024-12-31', 3, 100, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 튼튼 식사', 1, 1, 48000, 0, '2024-12-31', 8, 52, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('사랑과 정성 듬뿍 강아지 사료', 1, 1, 27000, 40, '2024-12-31', 12, 250, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('튼튼찬 해피독', 1, 1, 32000, 0, '2024-12-31', 12, 84, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냠냠찬 해피독', 1, 1, 24000, 0, '2024-12-31', 13, 16, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 행복식단', 1, 1, 30000, 40, '2024-12-31', 1, 500, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b8820b2-31a0-4c08-b0b1-c084786e7bca-product-sample1.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('멍멍이 바삭 하우스', 1, 1, 25000, 0, '2024-12-31', 1, 200, false, NOW(), NOW(), 700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/07ce63c5-d95b-431c-83c9-2bb106ec421f-product-sample2.jpg', 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/descriptions/2f7b3d18-312a-41ea-aa49-237aead06521-description-sample2.jpg', '제품 상세 설명');


-- 고양이 사료 (Subcategory 2)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('프리미엄 고양이 사료', 2, 2, 48200, 40, '2024-12-31', 60, 180, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('치킨 맛 고양이 사료', 2, 2, 31500, 30, '2024-12-31', 9, 90, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('저알러지 고양이 사료', 2, 2, 27700, 0, '2024-12-31', 8, 75, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('연어와 감자 고양이 사료', 2, 2, 53900, 30, '2024-12-31', 10, 37, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('오리고기 고양이 사료', 2, 2, 22500, 40, '2024-12-31', 8, 29, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('통곡물 고양이 사료', 2, 2, 37000, 0, '2024-12-31', 55, 145, false, NOW(), NOW(), 2000, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('저지방 고양이 사료', 2, 2, 25500, 40, '2024-12-31', 5, 70, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('소고기 고양이 사료', 2, 2, 42000, 50, '2024-12-31', 3, 90, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이용 식이섬유 사료', 2, 2, 23000, 50, '2024-12-31', 15, 190, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('유기농 고양이 사료', 2, 2, 56300, 0, '2024-12-31', 19, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 강아지 간식 (Subcategory 3)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('알록달록 강아지 간식', 3, 3, 15700, 0, '2024-12-31', 20, 150, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/2ef9e8c0-6819-48fb-9ff3-ba7fd0d52011-product-sample5.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('바우와우 간식', 3, 3, 12600, 0, '2024-12-31', 25, 100, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/2ef9e8c0-6819-48fb-9ff3-ba7fd0d52011-product-sample5.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고구마맛 강아지 간식', 3, 3, 18100, 0, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/d4bffeaa-882a-44b9-85ef-7a9101e95d8e-product-sample6.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('식이섬유 가득 강아지 간식', 3, 3, 14900, 0, '2024-12-31', 50, 90, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/d4bffeaa-882a-44b9-85ef-7a9101e95d8e-product-sample6.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('닭가슴살 강아지 간식', 3, 3, 16300, 0, '2024-12-31', 20, 10, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/460c55d0-76a2-4639-93d3-a06b0e085c61-product-sample7.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('단백질 가득 강아지 간식', 3, 3, 22000, 40, '2024-12-31', 10, 20, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/460c55d0-76a2-4639-93d3-a06b0e085c61-product-sample7.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('스탠바이미 안심통살', 3, 3, 11200, 0, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5a83b079-191d-4cfc-9b61-db9b059125ba-product-sample8.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('닭안심과 가다랑어 간식', 3, 3, 13900, 0, '2024-12-31', 55, 70, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5a83b079-191d-4cfc-9b61-db9b059125ba-product-sample8.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('닭고기 통조림 간식', 3, 3, 17000, 40, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e995a459-c9dd-4eee-832b-cd58d62a99f1-product-sample12.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('치킨젤리 통조림', 3, 3, 19000, 0, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e995a459-c9dd-4eee-832b-cd58d62a99f1-product-sample12.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 고양이 간식 (Subcategory 4)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('프리미엄 고양이 간식', 4, 4, 16000, 0, '2024-12-31', 40, 130, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/144e8918-da9b-4125-bf3e-33d966e538e0-product-sample10.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('연어맛 고양이 간식', 4, 4, 13000, 0, '2024-12-31', 20, 90, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/144e8918-da9b-4125-bf3e-33d966e538e0-product-sample10.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('생선살 고양이 간식', 4, 4, 19000, 40, '2024-12-31', 60, 200, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/144e8918-da9b-4125-bf3e-33d966e538e0-product-sample10.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고단백 저칼로리 고양이 영양 간식', 4, 4, 15000, 30, '2024-12-31', 50, 120, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/b51c3b6a-daaa-4f66-8d8f-3f8a3b71765d-product-sample11.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('미아오꼬꼬', 4, 4, 17000, 40, '2024-12-31', 25, 300, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/b51c3b6a-daaa-4f66-8d8f-3f8a3b71765d-product-sample11.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('닭가슴살 고양이 간식', 4, 4, 21000, 0, '2024-12-31', 10, 100, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/b51c3b6a-daaa-4f66-8d8f-3f8a3b71765d-product-sample11.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('저칼로리 고양이 간식', 4, 4, 12000, 40, '2024-12-31', 80, 50, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/6741312f-1755-4743-ba98-560820afeb76-product-sample12.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('기력회복 오리고기 고양이 간식', 4, 4, 14000, 40, '2024-12-31', 55, 40, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/6741312f-1755-4743-ba98-560820afeb76-product-sample12.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고단백 큐브 고양이 간식', 4, 4, 18000, 40, '2024-12-31', 45, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e95222aa-119b-45fc-91ff-015e061f44e6-product-sample13.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('FD 트릿츠 치킨리버 고양이용', 4, 4, 20000, 0, '2024-12-31', 30, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e95222aa-119b-45fc-91ff-015e061f44e6-product-sample13.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 배변패드 (Subcategory 5)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('매너가드 애견 기저귀', 5, 5, 10000, 0, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca17a74d-bda2-48ed-92a4-2f9ddd95f2b2-product-sample14.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('수컷용 중형견용 기저귀', 5, 5, 12000, 30, NULL, 6, 90, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca17a74d-bda2-48ed-92a4-2f9ddd95f2b2-product-sample14.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('루나 홀리스틱 기저귀 사이즈 M', 5, 5, 15000, 20, NULL, 3, 90, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca17a74d-bda2-48ed-92a4-2f9ddd95f2b2-product-sample14.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('슈슈패드', 5, 5, 14000, 40, NULL, 11, 120, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/19de87b9-0279-4c83-baa0-a4a00399dc50-product-sample15.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('대형 배변패드', 5, 5, 29000, 40, NULL, 25, 300, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/19de87b9-0279-4c83-baa0-a4a00399dc50-product-sample15.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('네이쳐펫 청결한 배변패드', 5, 5, 18000, 0, NULL, 10, 80, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/19de87b9-0279-4c83-baa0-a4a00399dc50-product-sample15.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('유린 오프 냄새 제거제', 5, 5, 11000, 40, NULL, 80, 50, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/dc499fbf-8ac5-455e-81df-559f641d5df5-product-sample16.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('반려견용 냄새 제거제', 5, 5, 13000, 0, NULL, 27, 80, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/dc499fbf-8ac5-455e-81df-559f641d5df5-product-sample16.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('쓱쓱싹싹 냄새제거 스프레이', 5, 5, 17000, 40, NULL, 29, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/6974d565-b122-4efd-980e-e687b24ec1df-product-sample17.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('부들부들 애견 데오도란트 라벤더향', 5, 5, 19000, 20, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/6974d565-b122-4efd-980e-e687b24ec1df-product-sample17.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 고양이 모래 (Subcategory 6)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('프리미엄 참숯 매쉬패드', 6, 6, 18000, 20, NULL, 4, 150, false, NOW(), NOW(), 1300, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/92515a38-1181-434d-bfe1-2c6b7edc5dab-product-sample18.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('흡수력 좋은 고양이 배변패드', 6, 6, 22000, 30, NULL, 35, 107, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/92515a38-1181-434d-bfe1-2c6b7edc5dab-product-sample18.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냄새 잡는 고양이 배변패드', 6, 6, 17000, 20, NULL, 5, 98, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/92515a38-1181-434d-bfe1-2c6b7edc5dab-product-sample18.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('위시캣 고양이 모래', 6, 6, 24000, 0, NULL, 8, 90, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/a090cefe-81ad-41c0-ad4d-8afa3c99d9f6-product-sample19.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('프리미엄 고양이 모래', 6, 6, 15000, 40, NULL, 30, 53, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/a090cefe-81ad-41c0-ad4d-8afa3c99d9f6-product-sample19.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고급 고양이 모래', 6, 6, 25000, 40, NULL, 5, 60, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/a090cefe-81ad-41c0-ad4d-8afa3c99d9f6-product-sample19.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('캐네디안 샌드 엑스트라', 6, 6, 14000, 40, NULL, 15, 50, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/bf14c220-11d5-4a68-ba35-f5a8e7267fa5-product-sample20.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('야스퍼 논트랙킹 캣샌드', 6, 6, 20000, 0, NULL, 17, 200, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/bf14c220-11d5-4a68-ba35-f5a8e7267fa5-product-sample20.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('앤샌드 베이직', 6, 6, 21000, 40, NULL, 27, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/eb838cd2-3de9-46c4-a2cd-50069eeee2f9-product-sample21.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('청결한 고양이 모래', 6, 6, 23000, 0, NULL, 29, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/eb838cd2-3de9-46c4-a2cd-50069eeee2f9-product-sample21.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 의류 (Subcategory 7)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('보솜보솜 귀요미 옷', 7, 7, 32000, 40, NULL, 17, 150, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b5aee8e-586d-473c-a2c9-af8ea3d8058e-product-sample22.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('반짝반짝 강아지 옷', 7, 7, 15000, 0, NULL, 2, 90, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b5aee8e-586d-473c-a2c9-af8ea3d8058e-product-sample22.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('옐로우 스트레이프 플라워', 7, 7, 27000, 0, NULL, 30, 80, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/0b5aee8e-586d-473c-a2c9-af8ea3d8058e-product-sample22.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('블루 스트레이프 플라워', 7, 7, 18000, 0, NULL, 8, 57, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/105d042d-f998-4deb-ba3e-2346e2f439ba-product-sample23.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('부들부들 귀요미 옷', 7, 7, 50000, 40, NULL, 1, 20, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/105d042d-f998-4deb-ba3e-2346e2f439ba-product-sample23.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('시원한 쿨톤 강아지 옷', 7, 7, 45000, 0, NULL, 1, 11, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/105d042d-f998-4deb-ba3e-2346e2f439ba-product-sample23.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('꿀벌 옷', 7, 7, 29000, 50, NULL, 1, 80, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5607c70e-fef1-4ce2-88f0-2d8c72987022-product-sample24.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('귀여운 강아지 꿀벌 조끼', 7, 7, 23000, 0, NULL, 8, 200, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5607c70e-fef1-4ce2-88f0-2d8c72987022-product-sample24.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('허니비 재킷', 7, 7, 37000, 40, NULL, 9, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5607c70e-fef1-4ce2-88f0-2d8c72987022-product-sample24.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('꿀벌 천사 조끼', 7, 7, 25000, 40, NULL, 17, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/5607c70e-fef1-4ce2-88f0-2d8c72987022-product-sample24.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 넥카라 (Subcategory 8)
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 넥카라 소형', 8, 8, 12000, 40, NULL, 40, 60, false, NOW(), NOW(), 1200, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca0e2c51-827b-44b6-b3cb-b3bfe06daa20-product-sample25.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 넥카라 소형', 8, 8, 11000, 0, NULL, 20, 90, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca0e2c51-827b-44b6-b3cb-b3bfe06daa20-product-sample25.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 넥카라 중형', 8, 8, 15000, 20, NULL, 17, 70, false, NOW(), NOW(), 900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/ca0e2c51-827b-44b6-b3cb-b3bfe06daa20-product-sample25.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 넥카라 중형', 8, 8, 14000, 0, NULL, 18, 10, false, NOW(), NOW(), 1700, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/66f6abc6-0952-4597-8dbf-68d93c7327ae-product-sample26.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 넥카라 대형', 8, 8, 18000, 40, NULL, 16, 90, false, NOW(), NOW(), 1500, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/66f6abc6-0952-4597-8dbf-68d93c7327ae-product-sample26.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 넥카라 소형', 8, 8, 17000, 40, NULL, 10, 100, false, NOW(), NOW(), 1900, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/66f6abc6-0952-4597-8dbf-68d93c7327ae-product-sample26.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('스트레이프 베이지톤 넥카라', 8, 8, 22000, 40, NULL, 20, 50, false, NOW(), NOW(), 1400, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e7791c66-25de-4d5e-82c8-50ea6fe7fc1c-product-sample27.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('소형견용 푹신한 줄무늬 넥카라', 8, 8, 21000, 0, NULL, 9, 87, false, NOW(), NOW(), 1100, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e7791c66-25de-4d5e-82c8-50ea6fe7fc1c-product-sample27.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('강아지 보호용 넥카라', 8, 8, 25000, 40, NULL, 16, 90, false, NOW(), NOW(), 1800, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e7791c66-25de-4d5e-82c8-50ea6fe7fc1c-product-sample27.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 보호용 넥카라', 8, 8, 24000, 0, NULL, 30, 70, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/e7791c66-25de-4d5e-82c8-50ea6fe7fc1c-product-sample27.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 추가 데이터
INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냥냥이 꿈밥', 2, 2, 56000, 0, '2024-12-31', 19, 180, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 별빛밥상', 2, 2, 47000, 50, '2024-12-31', 17, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냥이 행복식탁', 2, 2, 47500, 60, '2024-12-31', 16, 280, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 달빛 하루', 2, 2, 39500, 40, '2024-12-31', 17, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('캣 에너지 스푼', 2, 2, 37000, 40, '2024-12-31', 21, 480, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/efa13d5a-7199-49f9-a4f4-1398affe89e5-product-sample3.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('고양이 미식만찬', 2, 2, 53200, 40, '2024-12-31', 5, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냥냥이 황금그릇', 2, 2, 21100, 40, '2024-12-31', 9, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냥냥이 쥐포밥', 2, 2, 36200, 40, '2024-12-31', 40, 580, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('냥냥이 바삭바삭', 2, 2, 27700, 40, '2024-12-31', 3, 80, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');

INSERT INTO products (name, subcategory_id, pseudo_category_id, price, discount_rate, expiration_date, stock, order_count, is_sold_out, created_at, updated_at, view_count, is_deleted, image_url, description_image_url, description)
VALUES ('영양가득 고양이 사료', 2, 2, 25600, 40, '2024-12-31', 1, 180, false, NOW(), NOW(), 1600, false, 'https://ggorangjirang-s3.s3.ap-northeast-2.amazonaws.com/images/products/65ad053a-bcf8-41ba-860e-ed6310e832e7-product-sample4.jpg', 'https://ggorangjirang-s3.s3.amazonaws.com/images/descriptions/0cd60135-65ea-4073-8a4c-87347ac20088-description-sample.jpg', '제품 상세 설명');


-- 주소
--INSERT INTO deliveries (zipcode, street_address, detail_address, status, request, arrival_date, phone_number, name)
--VALUE('12345', '123 test St', 'May 101', 'Pending', 'Testing~', '2024-06-01', 1234567890, 'Test');
--
--INSERT INTO deliveries (zipcode, street_address, detail_address, status, request, arrival_date, phone_number, name)
--VALUE('12345', '123 test St', 'May 102', 'Pending', 'Testing~', '2024-06-01', 1234567890, 'Test2');
--
--INSERT INTO deliveries (zipcode, street_address, detail_address, status, request, arrival_date, phone_number, name)
--VALUE('12345', '123 test St', 'May 102', 'Pending', 'Testing~', '2024-06-01', 1234567890, 'Test3');


-- 유저
--INSERT INTO users (name, password, email) VALUES ('test','1234','test@test.com');


-- 주문
--INSERT INTO orders (deliveries_id, order_date,  order_status, order_number,user_id, total_all_price)
--VALUES (1, '2024-05-01T10:00:00','ORDER','adf123', 1, 3000);
--
--INSERT INTO orders (deliveries_id, order_date,  order_status,order_number, user_id, total_all_price)
--VALUES (2, '2024-05-01T10:00:00','ORDER','bef234', 1, 4000);




-- 주문 아이템
--INSERT INTO order_item (order_price, quantity, order_id, product_id) VALUES (100,10,1,8);
--INSERT INTO order_item (order_price, quantity, order_id, product_id) VALUES (40,10,1,9);
--INSERT INTO order_item (order_price, quantity, order_id, product_id) VALUES (500,10,2,3);
--

-- 리뷰
-- 이미지를 업로드한 리뷰
--INSERT INTO reviews (title, content, image_url, product_id, user_id, created_at)
--VALUES ('만족', '좋네요. 잘 쓸게요.', 'url', 1, 1, NOW());

-- 이미지를 업로드하지 않은 리뷰
--INSERT INTO reviews (title, content, product_id, user_id, created_at)
--VALUES ('흠', '좀 더 써보고 판단할게요.', 1, 1, NOW());

-- 카트 데이터 삽입
--INSERT INTO carts (user_id) VALUES (1);

-- 카트 아이템 데이터 삽입
--INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (1, 1, 2);

