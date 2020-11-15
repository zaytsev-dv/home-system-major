CREATE TABLE notes
(
    id               bigserial not null primary key,
    value            text      not null,
    description      text,
    telegram_user_id bigint REFERENCES telegram_users (id)
);