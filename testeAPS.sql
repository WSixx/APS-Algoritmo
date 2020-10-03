CREATE DATABASE APS;
USE APS;

CREATE TABLE tempos (
id INT PRIMARY KEY auto_increment,
algoritmo varchar(14),
tempo double,
dataNow DateTime
);

select id, algoritmo, tempo as "tempo em Mili", dataNow from tempos;

drop table tempos;

CREATE TABLE focos (
id INT PRIMARY KEY auto_increment,
satelite varchar(10),
cidade varchar(32),
estado varchar(11),
diasSemChuva integer,
bioma VARCHAR(9)
);

drop table focos;
desc focos;
select id from focos;
select * from focos limit 0, 55000;
select id from focos ORDER BY rand() limit 0, 55000 ;
select id, bioma, cidade from focos where id=256;