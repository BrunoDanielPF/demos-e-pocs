DROP TABLE IF EXISTS tb_customer;

CREATE TABLE tb_customer (
  id int(10),
  name varchar(30),
  PRIMARY KEY (id)
);

INSERT INTO tb_customer VALUES(1, 'name teste');