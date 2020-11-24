CREATE TABLE user_app
(
    id           bigserial not null primary key,
    username     text      not null,
    password     text      not null,
    firstname    text      not null,
    lastname     text      not null,
    email        text      not null,
    is_activated boolean default false
);

CREATE TABLE authority
(
    name text not null primary key
);

create table user_authority
(
    user_id        bigint not null,
    authority_name text   not null,
    primary key (user_id, authority_name)
);

insert into user_app (id, username, password, firstname, lastname, email)
VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin_f', 'admin_l',
        'zaytsev.dmitry9228@gmail.com');

insert into user_app (id, username, password, firstname, lastname, email)
VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'admin_f', 'admin_l',
        'zaytsev.dmitry9228@gmail.com');

insert into authority(name)
values ('ROLE_ADMIN');
insert into authority(name)
values ('ROLE_USER');

insert into user_authority(user_id, authority_name)
VALUES (1, 'ROLE_ADMIN');

insert into user_authority(user_id, authority_name)
VALUES (2, 'ROLE_USER');