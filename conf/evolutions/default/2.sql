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

CREATE TABLE todolist (
  id       BIGINT       NOT NULL,
  content VARCHAR(255) NOT NULL,
  status int NOT NULL ,
  note VARCHAR (255),
  account_id BIGINT       NOT NULL,
  category VARCHAR(255) ,
  create_date DATETIME     NOT NULL,
  CONSTRAINT pk_todolist PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);




insert into account values (1, "tringchen@gmail.com", "E10ADC3949BA59ABBE56E057F20F883E", "tring");
insert into account_detail values (1, "tringchen", "8.8.8.8", "2014-01-01", "2014-01-01", 10, "nothing");

# --- !Downs

DROP TABLE IF EXISTS todolist;
DROP TABLE IF EXISTS account_detail;
DROP TABLE IF EXISTS account;