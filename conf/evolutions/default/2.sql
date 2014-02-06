# --- !Ups

CREATE TABLE account (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE account_detail (
  id       BIGINT       NOT NULL,
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

CREATE TABLE category (
  id BIGINT NOT NULL,
  account_id BIGINT       NOT NULL,
  category_name VARCHAR(255) NOT NULL ,
  status int NOT NULL ,
  create_date DATETIME     NOT NULL,
  CONSTRAINT pk_category PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);


CREATE TABLE todolist (
  id       BIGINT       NOT NULL,
  content VARCHAR(255) NOT NULL,
  status int NOT NULL ,
  note VARCHAR (255),
  account_id BIGINT       NOT NULL,
  category_id BIGINT ,
  create_date DATETIME     NOT NULL,
  CONSTRAINT pk_todolist PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  FOREIGN KEY (category_id) REFERENCES category (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);




insert into account values (1, "tringchen@gmail.com", "E10ADC3949BA59ABBE56E057F20F883E", "tring");
insert into account values (2, "22@gmail.com", "E10ADC3949BA59ABBE56E057F20F883E", "22");

insert into account_detail values (1, "tringchen", "8.8.8.8", "2014-01-01", "2014-01-01", 10, "nothing");
insert into account_detail values (2, "22", "2.2.2.2", "2014-01-01", "2014-01-01", 10, "22");

INSERT INTO category VALUES (1, 1, "Japan", 10, "2014-01-01");
INSERT INTO category VALUES (2, 1, "Asia", 10, "2014-01-01");

insert into todolist values (1, "tringchen's first cat. first entry 1", 10, "no note", 1, 1, "2014-01-01");
insert into todolist values (2, "tringchen's first cat. second entry 2", 10, "no note", 1, 1, "2014-01-01");
insert into todolist values (3, "tringchen's first cat. third entry 3", 10, "no note", 1, 1, "2014-01-01");
insert into todolist values (4, "tringchen's second cat. first entry 1", 10, "no note", 1, 2, "2014-01-01");


# --- !Downs

DROP TABLE IF EXISTS todolist;
DROP TABLE IF EXISTS category;

DROP TABLE IF EXISTS account_detail;
DROP TABLE IF EXISTS account;