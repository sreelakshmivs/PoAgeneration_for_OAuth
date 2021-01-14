drop database projectdb;
drop user sree;
create user sree with password 'password';
create database projectdb with template=template0 owner=sree;
\connect projectdb;
alter default privileges grant all on tables to sree;
alter default privileges grant all on sequences to sree;

create table et_poa(
user_id integer primary key not null,
resourceServerId varchar(30) not null,
metadata varchar(200) not null,
clientId varchar(50) not null,
password text not null
);
create table et_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(50) not null
);
alter table et_categories add constraint cat_users_fk
foreign key (user_id) references et_poa(user_id);

create table et_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(50) not null,
transaction_date bigint not null
);

alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories(category_id);
alter table et_transactions add constraint trans_users_fk
foreign key (user_id) references et_poa(user_id);

create sequence et_poa_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;
