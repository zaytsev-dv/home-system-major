CREATE TABLE telegram_users
(
    id          bigserial not null primary key,
    external_id bigint    not null,
    firstname   text,
    lastname    text,
    username    text
);

comment on column telegram_users.external_id is 'telegram user id (chatId)'