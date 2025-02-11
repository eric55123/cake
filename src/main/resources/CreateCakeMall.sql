DROP DATABASE cakemall;

CREATE DATABASE cakemall;

USE cakemall;

-- 創建管理員表格 (admin)
CREATE TABLE admin (
                       admin_id INT AUTO_INCREMENT COMMENT '管理員編號',
                       admin_account VARCHAR(50) UNIQUE NOT NULL COMMENT '帳號',
                       admin_password VARCHAR(64) NOT NULL COMMENT '密碼',
                       admin_mail VARCHAR(50) NOT NULL COMMENT '電子郵件',
                       admin_controller TINYINT NOT NULL DEFAULT 0 COMMENT '是否有控制權限 (0=無, 1=有)',
                       admin_status TINYINT NOT NULL DEFAULT 1 COMMENT '狀態 (0=停用, 1=啟用)',
                       admin_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
                       admin_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',
                       PRIMARY KEY (admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理員表格';

-- 插入 3 筆管理員假資料
INSERT INTO admin (admin_account, admin_password, admin_mail, admin_controller, admin_status)
VALUES
    ('admin1', 'password1_hash', 'admin1@example.com', 1, 1),
    ('admin2', 'password2_hash', 'admin2@example.com', 0, 1),
    ('admin3', 'password3_hash', 'admin3@example.com', 0, 0);

-- 創建會員表格 (member)
CREATE TABLE member (
                        member_no INT AUTO_INCREMENT COMMENT '會員編號',
                        member_account VARCHAR(20) UNIQUE NOT NULL COMMENT '帳號',
                        member_email VARCHAR(50) NOT NULL COMMENT '電子郵件',
                        member_pwd VARCHAR(64) NOT NULL COMMENT '密碼',
                        member_name VARCHAR(50) NOT NULL COMMENT '姓名',
                        birthday DATE NOT NULL COMMENT '生日',
                        member_phone VARCHAR(15) COMMENT '手機號碼',
                        address VARCHAR(200) COMMENT '地址',
                        member_status TINYINT NOT NULL DEFAULT 1 COMMENT '會員狀態 (0=停用, 1=啟用)',
                        member_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
                        member_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
                        PRIMARY KEY (member_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='會員表格';

-- 插入 5 筆會員假資料
INSERT INTO member (member_account, member_email, member_pwd, member_name, birthday, member_phone, address, member_status)
VALUES
    ('user1', 'user1@example.com', 'password1_hash', 'Alice', '1990-01-15', '0912345678', '台北市中正區第一街1號', 1),
    ('user2', 'user2@example.com', 'password2_hash', 'Bob', '1985-05-25', '0923456789', '台中市西屯區第二街2號', 1),
    ('user3', 'user3@example.com', 'password3_hash', 'Cindy', '1995-07-10', '0934567890', '高雄市左營區第三街3號', 1),
    ('user4', 'user4@example.com', 'password4_hash', 'David', '2000-03-20', '0945678901', '新北市板橋區第四街4號', 0),
    ('user5', 'user5@example.com', 'password5_hash', 'Eve', '1998-12-30', '0956789012', '桃園市中壢區第五街5號', 1);

-- 創建商品類別表格 (product_category)
CREATE TABLE product_category (
                                  product_category_id INT AUTO_INCREMENT COMMENT '列表編號',
                                  product_category_name VARCHAR(32) NOT NULL UNIQUE COMMENT '列表名稱',
                                  PRIMARY KEY (product_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品類別表格';

-- 創建商品表格 (product)
CREATE TABLE product (
                         product_no INT AUTO_INCREMENT COMMENT '商品編號',
                         product_desc TEXT COMMENT '商品敘述',
                         product_add_qty INT NOT NULL COMMENT '上架數量',
                         product_name VARCHAR(32) NOT NULL UNIQUE COMMENT '商品名稱',
                         remaining_qty INT NOT NULL COMMENT '剩餘數量',
                         product_add_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上架時間',
                         product_remove_time DATETIME COMMENT '下架時間',
                         product_status TINYINT NOT NULL DEFAULT 1 COMMENT '商品狀態 (0=下架, 1=上架)',
                         product_price VARCHAR(10) NOT NULL COMMENT '商品價格',
                         product_category_id INT COMMENT '商品類別編號',
                         PRIMARY KEY (product_no),
                         FOREIGN KEY (product_category_id) REFERENCES product_category (product_category_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表格';

-- 插入 3 筆商品類別假資料
INSERT INTO product_category (product_category_name)
VALUES ('生日蛋糕'), ('節日蛋糕'), ('客製化蛋糕');

-- 插入 5 筆商品假資料
INSERT INTO product (product_desc, product_add_qty, product_name, remaining_qty, product_status, product_category_id, product_price)
VALUES
    ('巧克力風味濃郁，搭配香濃奶油的完美組合', 10, '巧克力蛋糕', 10, 1, 1, '800'),
    ('莓果清香搭配濃郁起司，口感酸甜', 8, '莓果乳酪蛋糕', 8, 1, 2, '900'),
    ('柔軟的海綿蛋糕體搭配卡士達內餡', 12, '原味卡士達蛋糕', 12, 1, 1, '750'),
    ('經典的紅絲絨蛋糕，外層奶油乳酪裝飾', 5, '紅絲絨蛋糕', 5, 1, 3, '950'),
    ('榛果風味濃厚，口感細膩的慕斯蛋糕', 7, '榛果慕斯蛋糕', 7, 1, 2, '980');

-- 創建商品圖片表格 (product_img)
CREATE TABLE product_img (
                             product_img_no INT AUTO_INCREMENT COMMENT '圖片編號',
                             product_no INT NOT NULL COMMENT '商品編號',
                             product_img_url VARCHAR(255) NOT NULL COMMENT '圖片路徑',
                             PRIMARY KEY (product_img_no),
                             FOREIGN KEY (product_no) REFERENCES product (product_no) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品圖片表格';

-- 插入 5 筆商品圖片假資料
INSERT INTO product_img (product_no, product_img_url)
VALUES
    (1, 'https://example.com/img/chocolate_cake.jpg'),
    (2, 'https://example.com/img/berry_cheesecake.jpg'),
    (3, 'https://example.com/img/vanilla_custard_cake.jpg'),
    (4, 'https://example.com/img/red_velvet_cake.jpg'),
    (5, 'https://example.com/img/hazelnut_mousse_cake.jpg');


-- 創建常見問題表格 (qa_img)
CREATE TABLE qa_img (
                        qa_no INT AUTO_INCREMENT PRIMARY KEY COMMENT '圖片編號',
                        qa_img_url VARCHAR(255) NOT NULL COMMENT '圖片路徑'
) COMMENT='常見問題';

-- 插入 2 筆常見問題資料
INSERT INTO qa_img (qa_img_url)
VALUES
    ('https://example.com/img/qa_question1.jpg'),
    ('https://example.com/img/qa_question2.jpg');

-- 創建營運公告表格 (bulletin)
CREATE TABLE bulletin (
                          bulletin_no INT AUTO_INCREMENT PRIMARY KEY COMMENT '公告編號',
                          bulletin_title VARCHAR(50) NOT NULL COMMENT '公告標題',
                          bulletin_content VARCHAR(255) NOT NULL COMMENT '公告內容',
                          bulletiin_update DATETIME NOT NULL COMMENT '公告更新時間',
                          admin_id INT NOT NULL COMMENT '最後修改管理員',
                          FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
) COMMENT='營運公告';

-- 插入 2 筆營運公告資料
INSERT INTO bulletin (bulletin_title, bulletin_content, bulletiin_update, admin_id)
VALUES
    ('2024 新春營業時間公告', '2024 年春節期間，我們將調整營業時間：除夕休息，初一至初五正常營業，敬請知悉。', '2024-12-15 10:00:00', 1),
    ('新品上市通知', '我們即將推出全新口味的草莓奶油蛋糕，敬請期待！', '2024-12-20 14:30:00', 2);

-- 創建蛋糕使用說明表格 (cake_illustrate)
CREATE TABLE cake_illustrate (
                                 cake_illustrate INT AUTO_INCREMENT PRIMARY KEY COMMENT '編號',
                                 cake_img_url VARCHAR(255) NOT NULL COMMENT '蛋糕說明圖片'
) COMMENT='蛋糕使用說明';

-- 插入 2 筆蛋糕使用說明資料
INSERT INTO cake_illustrate (cake_img_url)
VALUES
    ('https://example.com/img/cake_storage_guide.jpg'),
    ('https://example.com/img/cake_serving_tips.jpg');

-- 創建付款訂單表格 (payment_order)
CREATE TABLE payment_order (
                               payment_order_no INT AUTO_INCREMENT COMMENT '付款訂單編號',
                               trade_no VARCHAR(50) NOT NULL COMMENT '交易編號',
                               total_amount VARCHAR(10) NOT NULL COMMENT '訂單總金額',
                               payment_status ENUM('Pending', 'Paid', 'Failed') NOT NULL COMMENT '付款狀態',
                               payment_time DATETIME COMMENT '付款時間',
                               PRIMARY KEY (payment_order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='付款訂單表格';


-- 插入假資料到付款訂單表格
INSERT INTO payment_order (trade_no, total_amount, payment_status, payment_time)
VALUES ('Order167590001', 2500.00, 'Pending', '2025-02-09 10:00:00'),
       ('Order167590002', 1800.50, 'Paid', '2025-02-08 22:00:00'),
       ('Order167590003', 900.00, 'Failed', '2025-02-08 23:30:00');


-- 建立訂單項目表格 (payment_order_item)
CREATE TABLE payment_order_item (
                                    payment_order_item_no INT AUTO_INCREMENT COMMENT '訂單項目編號',
                                    payment_order_no INT NOT NULL COMMENT '付款訂單編號',
                                    product_no INT NOT NULL COMMENT '商品編號',
                                    unit_price VARCHAR(10) NOT NULL COMMENT '商品單價',
                                    quantity INT NOT NULL COMMENT '商品數量',
                                    PRIMARY KEY (payment_order_item_no),
                                    FOREIGN KEY (payment_order_no) REFERENCES payment_order (payment_order_no) ON DELETE CASCADE ON UPDATE CASCADE,
                                    FOREIGN KEY (product_no) REFERENCES product (product_no) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='訂單項目表格';

-- 插入測試資料
INSERT INTO payment_order_item (payment_order_no, product_no, unit_price, quantity)
VALUES (1, 1, '1200.00', 2),
       (1, 2, '900.50', 1),
       (2, 3, '800.00', 1);

CREATE TABLE payment_callback_log (
                                      callback_log_id INT AUTO_INCREMENT COMMENT '回傳記錄編號',
                                      payment_order_no INT NOT NULL COMMENT '付款訂單編號',
                                      trade_no VARCHAR(50) NOT NULL COMMENT '綠界交易編號',
                                      rtn_code VARCHAR(10) NOT NULL COMMENT '回傳狀態碼',
                                      rtn_msg VARCHAR(255) COMMENT '回傳訊息',
                                      payment_date DATETIME COMMENT '付款完成時間',
                                      callback_data TEXT COMMENT '回傳的完整 JSON 資料',
                                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '記錄創建時間',
                                      PRIMARY KEY (callback_log_id),
                                      FOREIGN KEY (payment_order_no) REFERENCES payment_order (payment_order_no) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='付款回傳記錄表';

