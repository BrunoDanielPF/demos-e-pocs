DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user (
    id int not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

INSERT INTO tb_user VALUES(1, "name teste")