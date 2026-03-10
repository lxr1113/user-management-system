-- 用户管理系统数据库表结构
-- 请在 MySQL 中执行此文件

USE user_management;

-- 增强 user 表
ALTER TABLE user 
ADD COLUMN IF NOT EXISTS status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用, 1-正常',
ADD COLUMN IF NOT EXISTS create_by VARCHAR(50) DEFAULT NULL,
ADD COLUMN IF NOT EXISTS update_by VARCHAR(50) DEFAULT NULL,
ADD COLUMN IF NOT EXISTS update_time DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
ADD COLUMN IF NOT EXISTS del_flag TINYINT DEFAULT 0 COMMENT '删除标志: 0-正常, 1-删除';

-- 角色表
CREATE TABLE IF NOT EXISTS role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '角色名称',
    code VARCHAR(50) NOT NULL COMMENT '角色编码',
    description VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用, 1-正常',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表
CREATE TABLE IF NOT EXISTS permission (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '权限名称',
    code VARCHAR(100) NOT NULL COMMENT '权限编码',
    type VARCHAR(20) DEFAULT 'button' COMMENT '类型: menu-菜单, button-按钮',
    url VARCHAR(255) DEFAULT NULL COMMENT '请求路径',
    parent_id INT DEFAULT 0 COMMENT '父权限ID',
    sort INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS user_role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL COMMENT '用户ID',
    role_id INT NOT NULL COMMENT '角色ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_role (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE IF NOT EXISTS role_permission (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_id INT NOT NULL COMMENT '角色ID',
    permission_id INT NOT NULL COMMENT '权限ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_role_perm (role_id, permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 插入默认管理员角色
INSERT INTO role (name, code, description, status) VALUES ('管理员', 'admin', '系统管理员', 1);
INSERT INTO role (name, code, description, status) VALUES ('普通用户', 'user', '普通用户', 1);
