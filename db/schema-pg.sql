create table n_account
(
    id         bigserial
        primary key,
    username   varchar(64)           not null,
    password   varchar(64)           not null,
    nickname   varchar(64)           not null,
    create_at  timestamp             not null,
    update_at  timestamp,
    is_deleted boolean default false not null,
    email      varchar(64),
    secret     varchar(64)           not null
);