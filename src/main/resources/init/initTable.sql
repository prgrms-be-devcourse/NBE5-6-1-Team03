-- 회원 테이블 (id는 문자열)
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
                          `id` VARCHAR(36) NOT NULL,
                          `password` VARCHAR(70) NOT NULL,
                          `role` CHAR(50) NOT NULL DEFAULT 'ROLE_USER',
                          `tel` VARCHAR(15),
                          `email` VARCHAR(50) NOT NULL,
                          `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
                          `name` VARCHAR(36) NOT NULL,
                          `address` VARCHAR(255) NOT NULL,
                          PRIMARY KEY (`id`)
);

-- 메뉴 테이블 (id: 자동 증가, 시작값 1000)
DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(100) NOT NULL,
                        `amount` INT NOT NULL DEFAULT 0,
                        `info` VARCHAR(100),
                        `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
                        `price` INT NOT NULL,
                        `is_deleted` BOOLEAN DEFAULT FALSE,
                        PRIMARY KEY (`id`)
) AUTO_INCREMENT = 1000;

-- 메뉴 이미지 테이블 (id: 자동 증가, 시작값 100)
DROP TABLE IF EXISTS `menu_img`;

CREATE TABLE `menu_img` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
                            `original_name` VARCHAR(100) NOT NULL,
                            `rename_name` VARCHAR(100) NOT NULL,
                            `save_path` VARCHAR(100) NOT NULL,
                            `menu_id` INT NOT NULL,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`menu_id`) REFERENCES `menu`(`id`)
) AUTO_INCREMENT = 100;

-- 주문 테이블 (id: 자동 증가, 시작값 10000)
DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `user_id` VARCHAR(36),
                          `user_name` VARCHAR(36),
                          `user_address` VARCHAR(255) NOT NULL,
                          `user_email` VARCHAR(50) NOT NULL,
                          `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
                          `status` CHAR(50) NOT NULL DEFAULT 'ORDER_ACCEPT',
                          PRIMARY KEY (`id`),
                          FOREIGN KEY (`user_id`) REFERENCES `member`(`id`)
) AUTO_INCREMENT = 10000;

-- 주문-메뉴 연결 테이블 (복합 PK)
DROP TABLE IF EXISTS `ordered_menu`;

CREATE TABLE `ordered_menu` (
                                `order_id` INT NOT NULL,
                                `menu_id` INT NOT NULL,
                                `quantity` INT NOT NULL DEFAULT 1,
                                PRIMARY KEY (`order_id`, `menu_id`),
                                FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
                                FOREIGN KEY (`menu_id`) REFERENCES `menu`(`id`)
);
