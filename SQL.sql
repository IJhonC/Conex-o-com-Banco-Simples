create database dbTecDes_CassinoOnline_JoaoCristofolini;

use dbTecDes_CassinoOnline_JoaoCristofolini;

create table cliente(
codigo 	int(4) 			auto_increment,
nome 	varchar(30)	 	not null,
fone 	varchar(30) 	not null,
email 	varchar(30) 	not null,
ende 	varchar(100) 		not null,
primary key		(codigo)
);

create table produto(
codigo 	int(4) 			auto_increment,
descr 	varchar(30)	 	not null,
unidade 	varchar(30) 	not null,
qnt 	float	not null,
preco 	float		not null,
primary key		(codigo)
);

create table fornecedor(
codigo 	int(4) 			auto_increment,
empresa 	varchar(30)	 	not null,
contato 	varchar(30) 	not null,
fone 	varchar(30)	not null,
email 	varchar(30)	not null,
primary key		(codigo)
);

create table tbuser(
id_user int(4) auto_increment,
nm_user varchar(30) not null,
pw_user varchar(40) not null,
tp_user varchar(40) not null,
pv_user varchar(10) not null,
primary key(id_user)
);

insert into tbuser(
id_user,
nm_user,
pw_user,
tp_user,
pv_user)
values(null, 'gersonmt','abc@123','administrator',1);

insert into tbuser(
id_user,
nm_user,
pw_user,
tp_user,
pv_user)
values(null, 'joaoc','qwer1234','administrator',2);

insert into tbuser(
id_user,
nm_user,
pw_user,
tp_user,
pv_user)
values(null, 'pedro','12345','user',2);


select * from tbuser;
select * from cliente;