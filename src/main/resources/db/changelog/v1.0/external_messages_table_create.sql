create table external_messages
(
    id           serial       not null primary key,
    text         varchar(256) not null,
    message_from varchar(256) not null,
    message_to   bigint       not null,
    created_date timestamp    not null
)