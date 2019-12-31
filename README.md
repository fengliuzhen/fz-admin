# fz-admin
基于Springboot+Vue+Element-UI 后台管理框架，Tab菜单


数据库脚本：
      -- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE fz;

--
-- Create table `sys_role_menu`
--
CREATE TABLE sys_role_menu (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增Id',
  roleid int(11) DEFAULT NULL COMMENT '角色id',
  menuid int(11) DEFAULT NULL COMMENT '菜单id',
  datastate int(11) DEFAULT NULL COMMENT '数据状态 0：无效   1：有效',
  adduid int(11) DEFAULT NULL COMMENT '创建人Id',
  addtime int(11) DEFAULT NULL COMMENT '创建日期 时间戳',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '角色菜单关联表';


--
-- Create table `sys_role`
--
CREATE TABLE sys_role (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增ID',
  rolename varchar(50) DEFAULT NULL COMMENT '部门名称',
  parentid int(11) DEFAULT 0 COMMENT '上级ID',
  orderno int(11) DEFAULT NULL COMMENT '排序号',
  datastate int(11) DEFAULT 1 COMMENT '数据状态  0：无效   1：有效',
  addtime int(11) DEFAULT NULL COMMENT '添加日期',
  adduid int(11) DEFAULT NULL COMMENT '添加人Id',
  lastedittime int(11) DEFAULT NULL COMMENT '最后一次修改日期',
  lastedituid int(11) DEFAULT NULL COMMENT '最后一次修改人Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '角色表';

--
-- Create table `sys_post`
--
CREATE TABLE sys_post (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增ID',
  postname varchar(50) DEFAULT NULL COMMENT '部门名称',
  parentid int(11) DEFAULT 0 COMMENT '父ID',
  orderno int(11) DEFAULT NULL COMMENT '排序号',
  datastate int(11) DEFAULT 1 COMMENT '数据状态  0：无效   1：有效',
  addtime int(11) DEFAULT NULL COMMENT '添加日期',
  adduid int(11) DEFAULT NULL COMMENT '添加人Id',
  lastedittime int(11) DEFAULT NULL COMMENT '最后一次修改日期',
  lastedituid int(11) DEFAULT NULL COMMENT '最后一次修改人Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '组织架构岗位表';

--
-- Create table `sys_menu`
--
CREATE TABLE sys_menu (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增ID',
  menuname varchar(50) DEFAULT NULL COMMENT '菜单名称',
  parentid int(11) DEFAULT 0 COMMENT '上级id',
  parentids varchar(100) DEFAULT NULL COMMENT '上级id集合',
  icon varchar(100) DEFAULT 'el-icon-menu' COMMENT '菜单icon',
  path varchar(50) DEFAULT NULL COMMENT '菜单路径',
  orderno int(11) DEFAULT 1 COMMENT '排序号',
  showtype int(11) DEFAULT 0 COMMENT '菜单展示类型 0：菜单功能  1：按钮功能',
  datastate int(11) DEFAULT 1 COMMENT '数据状态  0：无效   1：有效',
  addtime int(11) DEFAULT NULL COMMENT '添加日期',
  adduid int(11) DEFAULT NULL COMMENT '添加人Id',
  lastedittime int(11) DEFAULT NULL COMMENT '最后一次修改日期',
  lastedituid int(11) DEFAULT NULL COMMENT '最后一次修改人Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '菜单表';

--
-- Create table `sys_log`
--
CREATE TABLE sys_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  logtype int(11) DEFAULT NULL,
  mid int(11) DEFAULT NULL,
  content varchar(255) DEFAULT NULL,
  logtime datetime DEFAULT NULL,
  uid int(11) DEFAULT NULL,
  uname varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '系统日志';

--
-- Create table `sys_dept_post`
--
CREATE TABLE sys_dept_post (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增Id',
  deptid int(11) DEFAULT NULL COMMENT '部门id',
  postid int(11) DEFAULT NULL COMMENT '岗位id',
  datastate int(11) DEFAULT NULL COMMENT '数据状态 0：无效   1：有效',
  adduid int(11) DEFAULT NULL COMMENT '创建人Id',
  addtime int(11) DEFAULT NULL COMMENT '创建日期 时间戳',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '部门岗位关联表';


--
-- Create table `sys_department`
--
CREATE TABLE sys_department (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增ID',
  deptname varchar(50) DEFAULT NULL COMMENT '部门名称',
  parentid int(11) DEFAULT 0 COMMENT '父ID',
  orderno int(11) DEFAULT NULL COMMENT '排序号',
  datastate int(11) DEFAULT 1 COMMENT '数据状态  0：无效   1：有效',
  addtime int(11) DEFAULT NULL COMMENT '添加日期',
  adduid int(11) DEFAULT NULL COMMENT '添加人Id',
  lastedittime int(11) DEFAULT NULL COMMENT '最后一次修改日期',
  lastedituid int(11) DEFAULT NULL COMMENT '最后一次修改人Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '组织架构部门表';

--
-- Create table `sys_config`
--
CREATE TABLE sys_config (
  id int(11) NOT NULL AUTO_INCREMENT,
  itemkey varchar(50) DEFAULT NULL,
  itemval varchar(255) DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '系统配置表';

--
-- Create table `sys_admin_role`
--
CREATE TABLE sys_admin_role (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增Id',
  userid int(11) DEFAULT NULL COMMENT '用户id',
  roleid int(11) DEFAULT NULL COMMENT '角色id',
  datastate int(11) DEFAULT NULL COMMENT '数据状态 0：无效   1：有效',
  adduid int(11) DEFAULT NULL COMMENT '创建人Id',
  addtime int(11) DEFAULT NULL COMMENT '创建日期 时间戳',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '管理员角色关联表';

--
-- Create table `sys_admin_post`
--
CREATE TABLE sys_admin_post (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增Id',
  userid int(11) DEFAULT NULL COMMENT '用户id',
  postid int(11) DEFAULT NULL COMMENT '岗位id',
  datastate int(11) DEFAULT NULL COMMENT '数据状态 0：无效   1：有效',
  adduid int(11) DEFAULT NULL COMMENT '创建人Id',
  addtime int(11) DEFAULT NULL COMMENT '创建日期 时间戳',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '管理员岗位关联表';

--
-- Create table `sys_admin`
--
CREATE TABLE sys_admin (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增ID',
  username varchar(50) DEFAULT NULL COMMENT '用户名',
  password varchar(50) DEFAULT NULL COMMENT '密码',
  nickname varchar(50) DEFAULT NULL COMMENT '昵称',
  realname varchar(50) DEFAULT NULL COMMENT '真实姓名',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  mobile varchar(50) DEFAULT NULL COMMENT '手机',
  age int(11) DEFAULT 0 COMMENT '年龄',
  sex int(11) DEFAULT 0 COMMENT '性别  0:男  1:女',
  deptid int(11) DEFAULT 0 COMMENT '部门ID',
  islock int(11) DEFAULT 0 COMMENT '是否锁定  0:未锁定  1:锁定',
  datastate int(11) DEFAULT 1 COMMENT '数据状态  0：无效   1：有效',
  addtime int(11) DEFAULT NULL COMMENT '添加日期',
  adduid int(11) DEFAULT NULL COMMENT '添加人Id',
  lastedittime int(11) DEFAULT NULL COMMENT '最后一次修改日期',
  lastedituid int(11) DEFAULT NULL COMMENT '最后一次修改人Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT =1,
CHARACTER SET utf8,
COLLATE utf8_general_ci,
COMMENT = '管理员表';
  
