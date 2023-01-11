create table notifications
(
    id                bigint       not null primary key,
    notification_type varchar(256) not null,
    created_date      varchar(256) not null,
    content           text         not null
)