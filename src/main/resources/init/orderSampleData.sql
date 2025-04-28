-- menu 더미 데이터 (커피 원두만)
INSERT INTO `menu` (`name`, `amount`, `info`, `created_at`, `price`) VALUES
                                                                         ('에티오피아 예가체프', 50, '꽃향과 과일향이 어우러진 원두', NOW(), 15000),
                                                                         ('콜롬비아 수프리모', 40, '밸런스가 좋고 고소한 맛', NOW(), 14000),
                                                                         ('케냐 AA', 30, '산미와 단맛이 뛰어난 프리미엄 원두', NOW(), 16000),
                                                                         ('과테말라 안티구아', 25, '초콜릿 향이 감도는 부드러운 원두', NOW(), 14500),
                                                                         ('인도네시아 만델링', 35, '묵직하고 스모키한 맛의 원두', NOW(), 15500);

-- menu_img 더미 데이터 (커피 원두 이미지)
INSERT INTO `menu_img` (`original_name`, `rename_name`, `save_path`, `menu_id`, `created_at`) VALUES
                                                                                                  ('yirgacheffe.jpg', 'bean_1001.jpg', 'img/beans/', 1000, NOW()),
                                                                                                  ('colombia.jpg', 'bean_1002.jpg', 'img/beans/', 1001, NOW()),
                                                                                                  ('kenya.jpg', 'bean_1003.jpg', 'img/beans/', 1002, NOW()),
                                                                                                  ('guatemala.jpg', 'bean_1004.jpg', 'img/beans/', 1003, NOW()),
                                                                                                  ('mandheling.jpg', 'bean_1005.jpg', 'img/beans/', 1004, NOW());



-- 추가 회원 더미 데이터 (더 다양한 주문자를 위해)
INSERT INTO `member` (`id`, `password`, `role`, `tel`, `email`, `created_at`, `name`, `address`) VALUES
                                                                                                     ('user-001', 'pass1234', 'ROLE_USER', '010-1234-5678', 'user1@example.com', NOW(), '홍길동', '서울시 강남구'),
                                                                                                     ('user-002', 'pass1234', 'ROLE_USER', '010-2345-6789', 'user2@example.com', NOW(), '이순신', '서울시 서초구'),
                                                                                                     ('user-003', 'pass1234', 'ROLE_ADMIN', '010-3456-7890', 'admin@example.com', NOW(), '김유신', '부산시 해운대구'),
                                                                                                     ('user-004', 'pass1234', 'ROLE_USER', NULL, 'user4@example.com', NOW(), '강감찬', '대전시 유성구'),
                                                                                                     ('user-005', 'pass1234', 'ROLE_USER', '010-5678-1234', 'user5@example.com', NOW(), '신사임당', '광주시 북구'),
                                                                                                     ('user-006', 'pass1234', 'ROLE_USER', '010-6789-2345', 'user6@example.com', NOW(), '김철수', '서울시 송파구'),
                                                                                                     ('user-007', 'pass1234', 'ROLE_USER', '010-7890-3456', 'user7@example.com', NOW(), '박영희', '인천시 연수구'),
                                                                                                     ('user-008', 'pass1234', 'ROLE_USER', '010-8901-4567', 'user8@example.com', NOW(), '정민준', '대구시 수성구'),
                                                                                                     ('user-009', 'pass1234', 'ROLE_USER', '010-9012-5678', 'user9@example.com', NOW(), '윤지영', '경기도 성남시'),
                                                                                                     ('user-010', 'pass1234', 'ROLE_USER', '010-0123-6789', 'user10@example.com', NOW(), '이하은', '부산시 동래구'),
                                                                                                     ('admin','{bcrypt}$2a$10$J35cAg1GvMQAFzM2pZDOaeqplOhpyc.gRBgyKb.DxkgT1.XU5e0CC', 'ROLE_ADMIN', '010-0000-0000', 'super@super.com', NOW(), '관리자', 'admin_address');

-- 지난 일주일간의 주문 데이터 추가 (2025-04-21 ~ 2025-04-27, 일별 5건)

-- 2025-04-21 주문 데이터 (다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-001', '홍길동', '서울시 강남구', 'user1@example.com', '2025-04-21 08:15:00', 'DELIVERED'),
                                                                                                        ('user-002', '이순신', '서울시 서초구', 'user2@example.com', '2025-04-21 10:45:00', 'EXCHANGE'),
                                                                                                        ('user-006', '김철수', '서울시 송파구', 'user6@example.com', '2025-04-21 13:30:00', 'DELIVERED'),
                                                                                                        ('user-007', '박영희', '인천시 연수구', 'user7@example.com', '2025-04-21 17:20:00', 'RETURN'),
                                                                                                        (NULL, '최지훈', '경기도 수원시', 'guest3@example.com', '2025-04-21 20:10:00', 'DELIVERED');

-- 2025-04-21 주문 메뉴
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10000, 1000, 2), (10000, 1001, 1),
                                                                   (10001, 1002, 1), (10001, 1004, 2),
                                                                   (10002, 1000, 1), (10002, 1003, 1), (10002, 1004, 1),
                                                                   (10003, 1001, 3),
                                                                   (10004, 1002, 2), (10004, 1003, 1);

-- 2025-04-22 주문 데이터 (다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-003', '김유신', '부산시 해운대구', 'admin@example.com', '2025-04-22 09:05:00', 'DELIVERED'),
                                                                                                        ('user-008', '정민준', '대구시 수성구', 'user8@example.com', '2025-04-22 11:30:00', 'DELIVERED'),
                                                                                                        ('user-009', '윤지영', '경기도 성남시', 'user9@example.com', '2025-04-22 14:45:00', 'ERROR'),
                                                                                                        (NULL, '박서준', '서울시 마포구', 'guest4@example.com', '2025-04-22 16:50:00', 'DELIVERED'),
                                                                                                        ('user-005', '신사임당', '광주시 북구', 'user5@example.com', '2025-04-22 19:25:00', 'REJECTED');

-- 2025-04-22 주문 메뉴
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10005, 1003, 2), (10005, 1004, 1),
                                                                   (10006, 1000, 2), (10006, 1002, 1),
                                                                   (10007, 1001, 1), (10007, 1004, 1),
                                                                   (10008, 1000, 1), (10008, 1003, 2),
                                                                   (10009, 1002, 2), (10009, 1004, 2);

-- 2025-04-23 주문 데이터 (다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-010', '이하은', '부산시 동래구', 'user10@example.com', '2025-04-23 08:40:00', 'PAID'),
                                                                                                        ('user-004', '강감찬', '대전시 유성구', 'user4@example.com', '2025-04-23 12:15:00', 'ON_THE_WAY'),
                                                                                                        (NULL, '김민지', '서울시 강서구', 'guest5@example.com', '2025-04-23 15:30:00', 'DELIVERED'),
                                                                                                        ('user-001', '홍길동', '서울시 강남구', 'user1@example.com', '2025-04-23 18:20:00', 'EXCHANGE'),
                                                                                                        ('user-006', '김철수', '서울시 송파구', 'user6@example.com', '2025-04-23 21:05:00', 'DELIVERED');

-- 2025-04-23 주문 메뉴
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10010, 1001, 1), (10010, 1004, 1),
                                                                   (10011, 1000, 3),
                                                                   (10012, 1002, 1), (10012, 1003, 1),
                                                                   (10013, 1003, 2), (10013, 1004, 1),
                                                                   (10014, 1000, 1), (10014, 1001, 2);

-- 2025-04-24 주문 데이터 (다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-007', '박영희', '인천시 연수구', 'user7@example.com', '2025-04-24 09:50:00', 'DELIVERED'),
                                                                                                        ('user-003', '김유신', '부산시 해운대구', 'admin@example.com', '2025-04-24 11:45:00', 'RETURN'),
                                                                                                        ('user-008', '정민준', '대구시 수성구', 'user8@example.com', '2025-04-24 14:30:00', 'ON_THE_WAY'),
                                                                                                        (NULL, '이서연', '경기도 용인시', 'guest6@example.com', '2025-04-24 17:15:00', 'ERROR'),
                                                                                                        ('user-002', '이순신', '서울시 서초구', 'user2@example.com', '2025-04-24 20:40:00', 'DELIVERED');

-- 2025-04-24 주문 메뉴
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10015, 1002, 2), (10015, 1004, 1),
                                                                   (10016, 1000, 1), (10016, 1001, 1), (10016, 1003, 1),
                                                                   (10017, 1001, 2), (10017, 1002, 1),
                                                                   (10018, 1003, 1), (10018, 1004, 2),
                                                                   (10019, 1000, 2), (10019, 1002, 2);

-- 2025-04-25 주문 데이터 (기존 데이터 외 추가, 다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-009', '윤지영', '경기도 성남시', 'user9@example.com', '2025-04-25 08:30:00', 'DELIVERED'),
                                                                                                        ('user-005', '신사임당', '광주시 북구', 'user5@example.com', '2025-04-25 14:45:00', 'RELEASE'),
                                                                                                        ('user-010', '이하은', '부산시 동래구', 'user10@example.com', '2025-04-25 19:50:00', 'ON_THE_WAY');

-- 2025-04-25 주문 메뉴 (추가)
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10020, 1001, 1), (10020, 1003, 2),
                                                                   (10021, 1000, 2), (10021, 1004, 1),
                                                                   (10022, 1002, 3);

-- 2025-04-26 주문 데이터 (기존 데이터 외 추가, 다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-004', '강감찬', '대전시 유성구', 'user4@example.com', '2025-04-26 10:15:00', 'PAID'),
                                                                                                        ('user-007', '박영희', '인천시 연수구', 'user7@example.com', '2025-04-26 16:40:00', 'RELEASE'),
                                                                                                        (NULL, '정도윤', '경기도 고양시', 'guest7@example.com', '2025-04-26 19:20:00', 'PAID');

-- 2025-04-26 주문 메뉴 (추가)
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10023, 1002, 1), (10023, 1003, 1), (10023, 1004, 1),
                                                                   (10024, 1000, 2), (10024, 1001, 1),
                                                                   (10025, 1001, 1), (10025, 1004, 2);

-- 2025-04-27 (오늘) 주문 데이터 (다양한 상태로 수정)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-001', '홍길동', '서울시 강남구', 'user1@example.com', '2025-04-27 08:10:00', 'ACCEPTED'),
                                                                                                        ('user-006', '김철수', '서울시 송파구', 'user6@example.com', '2025-04-27 10:30:00', 'PAID'),
                                                                                                        ('user-008', '정민준', '대구시 수성구', 'user8@example.com', '2025-04-27 12:45:00', 'ACCEPTED'),
                                                                                                        (NULL, '한수민', '서울시 중구', 'guest8@example.com', '2025-04-27 15:20:00', 'REJECTED'),
                                                                                                        ('user-010', '이하은', '부산시 동래구', 'user10@example.com', '2025-04-27 17:05:00', 'PAID');

-- 2025-04-27 주문 메뉴
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10026, 1000, 1), (10026, 1002, 2),
                                                                   (10027, 1001, 2), (10027, 1003, 1),
                                                                   (10028, 1002, 1), (10028, 1004, 2),
                                                                   (10029, 1000, 1), (10029, 1001, 1), (10029, 1003, 1),
                                                                   (10030, 1001, 1), (10030, 1002, 1), (10030, 1004, 1);