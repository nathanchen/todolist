# --- !Ups

CREATE TABLE account (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE account_detail (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  full_name    VARCHAR(255) ,
  ip_address      VARCHAR(20),
  enrolled_date   DATETIME     NOT NULL,
  last_login_date DATETIME     NOT NULL,
  status int NOT NULL ,
  note VARCHAR (255),
  CONSTRAINT pk_account PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);

CREATE TABLE list_format (
  id BIGINT NOT NULL  AUTO_INCREMENT,
  list_name VARCHAR (100),
  csv_attributes VARCHAR (500),
  CONSTRAINT pk_list_format PRIMARY KEY (id)
);

CREATE TABLE category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  account_id BIGINT       NOT NULL,
  category_name VARCHAR(255) NOT NULL ,
  list_format_id BIGINT NOT NULL ,
  status int NOT NULL ,
  create_date DATETIME     NOT NULL,
  CONSTRAINT pk_category PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  FOREIGN KEY (list_format_id) REFERENCES list_format (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


CREATE TABLE tb_entry (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  content VARCHAR(5000) NOT NULL,
  status int NOT NULL ,
  note VARCHAR (255),
  account_id BIGINT       NOT NULL,
  category_id BIGINT ,
  create_date DATETIME     NOT NULL,
  CONSTRAINT pk_tb_entry PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  FOREIGN KEY (category_id) REFERENCES category (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


insert into account values (1, 'tringchen@gmail.com', 'E10ADC3949BA59ABBE56E057F20F883E', 'tring');
insert into account values (2, '22@gmail.com', 'E10ADC3949BA59ABBE56E057F20F883E', '22');

insert into account_detail values (1, 'tringchen', '8.8.8.8', '2014-01-01', '2014-01-01', 10, 'nothing');
insert into account_detail values (2, '22', '2.2.2.2', '2014-01-01', '2014-01-01', 10, '22');

INSERT INTO list_format VALUES (1, 'todolist', 'status,creator_id,create_date,modifier_id,note,start_date,end_date,location,content,buddies_names');
INSERT INTO list_format VALUES (2, 'shoppinglist', 'status,creator_id,create_date,modifier_id,modify_date,item_name,quantity,item_price,total_price,toWhom,purchase_location,purchase_price,gprs_date,purchase_date,note,buddies_names');

INSERT INTO category VALUES (1, 1, 'Japan', 1, 10, '2014-01-01');
INSERT INTO category VALUES (2, 1, 'Asia', 2, 10, '2014-01-01');

insert into tb_entry values (1, '{"status":10,"creator_id":1,"create_date":1391780066474,"modifier_id":null,"modify_date":null,"note":"","start_date":1391184000000,"end_date":1393603200000,"location":"","content":"work","buddies_names":""}
', 10, 'no note', 1, 1, '2014-01-01');
insert into tb_entry values (2, '{"status":20,"creator_id":1,"create_date":1391780066474,"modifier_id":null,"modify_date":null,"note":"","start_date":1391780066474,"end_date":1393603200000,"location":"Japan","content":"beofroe","buddies_names":""}
', 10, 'no note', 1, 1, '2014-01-01');
insert into tb_entry values (3, '{"status":10,"creator_id":1,"create_date":1391780066474,"modifier_id":null,"modify_date":null,"note":"","start_date":1391184000000,"end_date":1393603200000,"location":"Nanjing","content":"after","buddies_names":"trinddi"}
', 10, 'no note', 1, 1, '2014-01-01');
insert into tb_entry values (4, '{"status":10,"creator_id":1,"create_date":1391780838673,"modifier_id":null,"modify_date":null,"item_name":"cabbage","quantity":2,"item_price":20.25,"total_price":40.5,"toWhom":"Stallar","purchase_location":"66# zhongyang Road","purchase_place":"Safeway","gprs_data":"","purchase_date":1393171200000,"note":"","buddies_names":""}
', 10, 'no note', 1, 2, '2014-01-01');


# --- !Downs

DROP TABLE IF EXISTS tb_entry;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS list_format;
DROP TABLE IF EXISTS account_detail;
DROP TABLE IF EXISTS account;