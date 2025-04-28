-- 회원 1명
INSERT INTO `member` (`id`, `password`, `role`, `tel`, `email`, `name`, `address`)
VALUES ('user-001', 'pass123!', 'ROLE_USER', '010-1234-5678', 'user1@example.com', '홍길동', '서울시 강남구');

INSERT INTO `member` (`id`, `password`, `role`, `tel`, `email`, `name`, `address`)
VALUES ('admin','{bcrypt}$2a$10$J35cAg1GvMQAFzM2pZDOaeqplOhpyc.gRBgyKb.DxkgT1.XU5e0CC', 'ROLE_ADMIN', '010-0000-0000', 'super@super.com', 'admin', 'admin_address');

-- 원두 메뉴
INSERT INTO `menu` (`name`, `amount`, `info`, `price`)
VALUES
    ('에티오피아 예가체프', 25, '플로럴 향과 밝은 산미', 12000),
    ('콜롬비아 수프리모', 40, '고소하고 균형 잡힌 맛', 11000),
    ('케냐 AA', 30, '강한 산미와 묵직한 바디감', 13000);

-- 메뉴 이미지
INSERT INTO `menu_img` (`original_name`, `rename_name`, `save_path`, `menu_id`)
VALUES
    ('yirgacheffe.jpg', 'bean_1001.jpg', 'img/beans/', 1000),
    ('colombia.jpg', 'bean_1002.jpg', 'img/beans/', 1001),
    ('kenya.jpg', 'bean_1003.jpg', 'img/beans/', 1002);

-- 주문 - 비회원
INSERT INTO `orders` (`user_name`, `user_address`, `user_email`)
VALUES ('이순신', '서울시 은평구', 'guest@example.com');

-- 주문 - 회원
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`)
VALUES ('user-001', '홍길동', '서울시 강남구', 'user1@example.com');

-- 주문 메뉴 연결
-- 비회원 주문: 주문 id = 10000
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`)
VALUES
    (10000, 1000, 1),
    (10000, 1002, 2);

-- 회원 주문: 주문 id = 10001
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`)
VALUES
    (10001, 1001, 1),
    (10001, 1002, 1);