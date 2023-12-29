create table n_account
(
    id         bigserial
        primary key,
    username   varchar(64)           not null,
    password   varchar(64)           not null,
    nickname   varchar(64)           not null,
    email      varchar(64),
    secret     varchar(64)           not null,
    create_at  timestamp             not null,
    update_at  timestamp,
    is_deleted boolean default false not null

);

create table n_third_account
(
    id         bigserial
        primary key,
    account_id integer               not null,
    third_id   varchar(128),
    type       varchar(64),
    create_at  timestamp             not null,
    update_at  timestamp,
    is_deleted boolean default false not null
);

create table n_music
(
    id           bigserial
        primary key,
    name         varchar(64)           not null
        unique,
    trainer      varchar(64),
    trainer_link varchar(128),
    create_at    timestamp             not null,
    update_at    timestamp,
    is_deleted   boolean default false not null
);

create table n_album
(
    id         bigserial
        primary key,
    name       varchar(64) not null
        unique,
    create_at  timestamp   not null,
    update_at  timestamp,
    is_deleted boolean default false
);

create table n_music_album
(
    id         bigserial,
    music_id   bigint    not null,
    album_id   bigint    not null,
    create_at  timestamp not null,
    update_at  timestamp,
    is_deleted integer
);