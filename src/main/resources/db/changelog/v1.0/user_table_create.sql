create table users
(
    id           serial       not null primary key,
    first_name   varchar(256) not null,
    last_name    varchar(256) not null,
    nickname     varchar(256) not null unique,
    email        varchar(256) not null unique,
    password     varchar(256) not null,
    created_date timestamp    not null,
    role         varchar(256) not null
)