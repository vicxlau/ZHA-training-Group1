drop database if exists shop;
/*创建数据库,并设置编码*/
create database shop default character set utf8;

use shop;

drop table if exists category;
drop table if exists product;

/*=============================*/
/* Table: 商品类别表结构 		   */
/*=============================*/
create table category
(
   /* 类别编号,自动增长 */
   id                  int not null auto_increment,
   /* 类别名称 */
   type                varchar(20),
   /* 类别是否为热点类别,热点类别才有可能显示在首页 */
   hot                 bool default false,
   /* 设置类别编号为主键 */
   primary key (id)
);

/*=============================*/
/* Table: 商品表结构	 		   */
/*=============================*/
create table product
(
   /* 商品编号,自动增长 */
   id                  int not null auto_increment,
   /* 商品名称 */
   name                varchar(20),
   /* 商品价格 */
   price               decimal(8,2),
   /* 商品图片 */
   pic                 varchar(200),
   /* 商品简单介绍 */
   remark              longtext,
   primary key (id)
);



INSERT INTO category (type,hot) VALUES ('男士休闲',true);
INSERT INTO category (type,hot) VALUES ('女士休闲',true);
INSERT INTO category (type,hot) VALUES ('儿童休闲',true);
INSERT INTO category (type,hot) VALUES ('老人休闲',false);


/* 商品测试用例 */
INSERT INTO product (name,price,pic,remark) VALUES ('圣得西服',2999.00,'test.jpg','这里是简单介绍');

select * from category;
select * from product;

