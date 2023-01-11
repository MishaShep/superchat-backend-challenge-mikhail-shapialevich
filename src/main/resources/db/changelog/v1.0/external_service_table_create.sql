create table external_service
(
    id           serial       not null primary key,
    service_name varchar(256) not null unique,
    url_code     varchar(256) not null,
    created_date timestamp    not null
)