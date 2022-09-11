create table if not exists course(
 id varchar(64) primary key,
 name varchar(255) not null,
 created_at TIMESTAMP not null
);
