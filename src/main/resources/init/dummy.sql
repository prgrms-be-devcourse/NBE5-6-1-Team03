-- member 더미 데이터
INSERT INTO `member` (`id`, `password`, `role`, `tel`, `email`, `created_at`, `name`, `address`) VALUES
                                                                                                     ('user-001', 'pass1234', 'ROLE_USER', '010-1234-5678', 'user1@example.com', NOW(), '홍길동', '서울시 강남구'),
                                                                                                     ('user-002', 'pass1234', 'ROLE_USER', '010-2345-6789', 'user2@example.com', NOW(), '이순신', '서울시 서초구'),
                                                                                                     ('user-003', 'pass1234', 'ROLE_ADMIN', '010-3456-7890', 'admin@example.com', NOW(), '김유신', '부산시 해운대구'),
                                                                                                     ('user-004', 'pass1234', 'ROLE_USER', NULL, 'user4@example.com', NOW(), '강감찬', '대전시 유성구'),
                                                                                                     ('user-005', 'pass1234', 'ROLE_USER', '010-5678-1234', 'user5@example.com', NOW(), '신사임당', '광주시 북구');


-- menu 더미 데이터 (커피 원두만)
INSERT INTO `menu` (`name`, `amount`, `info`, `created_at`, `price`) VALUES
                                                                         ('에티오피아 예가체프', 50, '꽃향과 과일향이 어우러진 원두', NOW(), 15000),
                                                                         ('콜롬비아 수프리모', 40, '밸런스가 좋고 고소한 맛', NOW(), 14000),
                                                                         ('케냐 AA', 30, '산미와 단맛이 뛰어난 프리미엄 원두', NOW(), 16000),
                                                                         ('과테말라 안티구아', 25, '초콜릿 향이 감도는 부드러운 원두', NOW(), 14500),
                                                                         ('인도네시아 만델링', 35, '묵직하고 스모키한 맛의 원두', NOW(), 15500);

-- menu_img 더미 데이터 (커피 원두 이미지)
INSERT INTO `menu_img` (`original_name`, `rename_name`, `save_path`, `menu_id`, `created_at`) VALUES
                                                                                                  ('yirgacheffe.jpg', 'yirgacheffe_001.jpg', '/images/menu/', 1000, NOW()),
                                                                                                  ('colombia.jpg', 'colombia_001.jpg', '/images/menu/', 1001, NOW()),
                                                                                                  ('kenya.jpg', 'kenya_001.jpg', '/images/menu/', 1002, NOW()),
                                                                                                  ('guatemala.jpg', 'guatemala_001.jpg', '/images/menu/', 1003, NOW()),
                                                                                                  ('mandheling.jpg', 'mandheling_001.jpg', '/images/menu/', 1004, NOW());

-- orders 더미 데이터 (회원과 비회원 주문 혼합)
INSERT INTO `orders` (`user_id`, `user_name`, `user_address`, `user_email`, `created_at`, `status`) VALUES
                                                                                                        ('user-001', '홍길동', '서울시 강남구', 'user1@example.com', '2025-04-25 13:00:00', 'ACCEPTED'),
                                                                                                        (NULL, NULL, '부산시 중구', 'guest1@example.com', '2025-04-25 18:00:00', 'RELEASE'),
                                                                                                        ('user-002', '이순신', '서울시 서초구', 'user2@example.com', '2025-04-25 22:00:00', 'ACCEPTED'),
                                                                                                        (NULL, NULL, '대구시 달서구', 'guest2@example.com', '2025-04-26 13:00:00', 'DELIVERED'),
                                                                                                        ('user-003', '이순신', '서울시 서초구', 'user2@example.com', '2025-04-26 15:00:00', 'ACCEPTED');
-- ordered_menu 더미 데이터
INSERT INTO `ordered_menu` (`order_id`, `menu_id`, `quantity`) VALUES
                                                                   (10000, 1000, 2),
                                                                   (10000, 1002, 1),
                                                                   (10001, 1004, 1),
                                                                   (10001, 1003, 2),
                                                                   (10002, 1001, 1),
                                                                   (10002, 1002, 1),
                                                                   (10003, 1000, 1),
                                                                   (10003, 1003, 1),
                                                                   (10004, 1001, 2),
                                                                   (10004, 1002, 2);
-- admin 계정
INSERT INTO `member` (`id`, `password`, `role`, `tel`, `email`, `name`, `address`)
VALUES ('admin','{bcrypt}$2a$10$J35cAg1GvMQAFzM2pZDOaeqplOhpyc.gRBgyKb.DxkgT1.XU5e0CC', 'ROLE_ADMIN', '010-0000-0000', 'super@super.com', 'admin', 'admin_address');