-- 电商系统数据库表结构
-- 请在 MySQL 中执行此文件

USE user_management;

-- ==================== 用户相关表（扩展现有user表）====================
ALTER TABLE user ADD COLUMN IF NOT EXISTS phone VARCHAR(20);
ALTER TABLE user ADD COLUMN IF NOT EXISTS avatar VARCHAR(255);
ALTER TABLE user ADD COLUMN IF NOT EXISTS nickname VARCHAR(50);

-- 收货地址表
CREATE TABLE IF NOT EXISTS address (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL COMMENT '用户ID',
    receiver_name VARCHAR(50) NOT NULL COMMENT '收货人',
    phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    province VARCHAR(50) COMMENT '省份',
    city VARCHAR(50) COMMENT '城市',
    district VARCHAR(50) COMMENT '区县',
    detail_address VARCHAR(255) COMMENT '详细地址',
    is_default TINYINT DEFAULT 0 COMMENT '是否默认: 0-否, 1-是',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- ==================== 商品相关表 ====================
-- 商品分类表
CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    parent_id INT DEFAULT 0 COMMENT '父分类ID',
    level INT DEFAULT 1 COMMENT '层级',
    sort INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 品牌表
CREATE TABLE IF NOT EXISTS brand (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '品牌名称',
    logo VARCHAR(255) COMMENT '品牌LOGO',
    description TEXT COMMENT '品牌描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌表';

-- 商品表
CREATE TABLE IF NOT EXISTS product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    category_id INT COMMENT '分类ID',
    brand_id INT COMMENT '品牌ID',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    original_price DECIMAL(10,2) COMMENT '原价',
    stock INT DEFAULT 0 COMMENT '库存',
    sales INT DEFAULT 0 COMMENT '销量',
    description VARCHAR(500) COMMENT '简短描述',
    detail TEXT COMMENT '商品详情',
    image VARCHAR(500) COMMENT '主图',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-下架, 1-上架',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 商品图片表
CREATE TABLE IF NOT EXISTS product_image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL COMMENT '商品ID',
    url VARCHAR(255) NOT NULL COMMENT '图片URL',
    sort INT DEFAULT 0 COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

-- 商品SKU表（规格）
CREATE TABLE IF NOT EXISTS product_sku (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL COMMENT '商品ID',
    sku_name VARCHAR(100) COMMENT 'SKU名称(如: 红色-128G)',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    original_price DECIMAL(10,2) COMMENT '原价',
    stock INT DEFAULT 0 COMMENT '库存',
    image VARCHAR(255) COMMENT 'SKU图片',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SKU表';

-- ==================== 购物车表 ====================
CREATE TABLE IF NOT EXISTS cart (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL COMMENT '用户ID',
    product_id INT NOT NULL COMMENT '商品ID',
    sku_id INT COMMENT 'SKU ID',
    quantity INT DEFAULT 1 COMMENT '数量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_product (user_id, product_id, sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ==================== 订单相关表 ====================
-- 订单表
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(50) NOT NULL COMMENT '订单编号',
    user_id INT NOT NULL COMMENT '用户ID',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总价',
    pay_amount DECIMAL(10,2) COMMENT '实付金额',
    freight DECIMAL(10,2) DEFAULT 0.00 COMMENT '运费',
    coupon_amount DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠金额',
    coupon_id INT COMMENT '优惠券ID',
    status TINYINT DEFAULT 0 COMMENT '状态: 0-待付款, 1-待发货, 2-待收货, 3-已完成, 4-已取消',
    receiver_name VARCHAR(50) COMMENT '收货人',
    phone VARCHAR(20) COMMENT '联系电话',
    province VARCHAR(50) COMMENT '省份',
    city VARCHAR(50) COMMENT '城市',
    district VARCHAR(50) COMMENT '区县',
    detail_address VARCHAR(255) COMMENT '详细地址',
    remark VARCHAR(255) COMMENT '备注',
    pay_time DATETIME COMMENT '支付时间',
    deliver_time DATETIME COMMENT '发货时间',
    receive_time DATETIME COMMENT '收货时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_order_no (order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单项表
CREATE TABLE IF NOT EXISTS order_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL COMMENT '订单ID',
    product_id INT NOT NULL COMMENT '商品ID',
    sku_id INT COMMENT 'SKU ID',
    product_name VARCHAR(100) COMMENT '商品名称',
    product_image VARCHAR(255) COMMENT '商品图片',
    sku_name VARCHAR(100) COMMENT 'SKU规格',
    price DECIMAL(10,2) COMMENT '单价',
    quantity INT COMMENT '数量',
    total_price DECIMAL(10,2) COMMENT '小计'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项表';

-- ==================== 评价相关表 ====================
CREATE TABLE IF NOT EXISTS review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL COMMENT '商品ID',
    user_id INT NOT NULL COMMENT '用户ID',
    order_id INT COMMENT '订单ID',
    score INT DEFAULT 5 COMMENT '评分(1-5)',
    content TEXT COMMENT '评价内容',
    images VARCHAR(1000) COMMENT '评价图片(多张逗号分隔)',
    reply VARCHAR(500) COMMENT '商家回复',
    reply_time DATETIME COMMENT '回复时间',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-隐藏, 1-显示',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价表';

-- ==================== 优惠券相关表 ====================
CREATE TABLE IF NOT EXISTS coupon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '优惠券名称',
    type TINYINT DEFAULT 0 COMMENT '类型: 0-满减券, 1-折扣券',
    amount DECIMAL(10,2) COMMENT '优惠金额/折扣',
    min_amount DECIMAL(10,2) COMMENT '最低消费金额',
    total_count INT COMMENT '发放总量',
    remain_count INT COMMENT '剩余数量',
    limit_count INT DEFAULT 1 COMMENT '每人限领数量',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

CREATE TABLE IF NOT EXISTS user_coupon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL COMMENT '用户ID',
    coupon_id INT NOT NULL COMMENT '优惠券ID',
    status TINYINT DEFAULT 0 COMMENT '状态: 0-未使用, 1-已使用, 2-已过期',
    order_id INT COMMENT '使用的订单ID',
    use_time DATETIME COMMENT '使用时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

-- ==================== 收藏表 ====================
CREATE TABLE IF NOT EXISTS favorite (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL COMMENT '用户ID',
    product_id INT NOT NULL COMMENT '商品ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_product (user_id, product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ==================== 初始化数据 ====================
-- 初始化分类
INSERT INTO category (name, parent_id, level, sort) VALUES 
('手机数码', 0, 1, 1),
('电脑办公', 0, 1, 2),
('家用电器', 0, 1, 3),
('服装鞋包', 0, 1, 4),
('食品生鲜', 0, 1, 5);

INSERT INTO category (name, parent_id, level, sort) VALUES 
('手机', 1, 2, 1),
('智能穿戴', 1, 2, 2),
('笔记本', 2, 2, 1),
('平板电脑', 2, 2, 2);

-- 初始化品牌
INSERT INTO brand (name, logo, description) VALUES 
('Apple', 'https://img.yzcdn.cn/vant/logo.png', '苹果公司'),
('华为', 'https://img.yzcdn.cn/vant/logo.png', '华为技术有限公司'),
('小米', 'https://img.yzcdn.cn/vant/logo.png', '小米科技'),
('OPPO', 'https://img.yzcdn.cn/vant/logo.png', 'OPPO广东移动通信');

-- 初始化商品
INSERT INTO product (name, category_id, brand_id, price, original_price, stock, sales, description, image, status) VALUES 
('iPhone 15 Pro', 5, 1, 7999.00, 8999.00, 100, 500, '苹果最新款智能手机', 'https://img.yzcdn.cn/vant/ipad.jpeg', 1),
('华为 Mate 60', 5, 2, 5999.00, 6999.00, 80, 300, '华为旗舰手机', 'https://img.yzcdn.cn/vant/ipad.jpeg', 1),
('小米 14 Pro', 5, 3, 4999.00, 5999.00, 120, 200, '小米旗舰手机', 'https://img.yzcdn.cn/vant/ipad.jpeg', 1);

-- 初始化优惠券
INSERT INTO coupon (name, type, amount, min_amount, total_count, remain_count, limit_count, start_time, end_time) VALUES 
('新人专享券', 0, 50.00, 200.00, 1000, 980, 1, '2026-01-01', '2026-12-31'),
('满100减10', 0, 10.00, 100.00, 500, 450, 2, '2026-01-01', '2026-12-31'),
('95折优惠券', 1, 5.00, 300.00, 200, 180, 1, '2026-01-01', '2026-12-31');
