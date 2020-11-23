CREATE TABLE last_keyboard_select
(
    id          bigserial not null primary key,
    value       text      not null,
    type        text      not null,
    created_at  timestamp with time zone,
    external_id bigint    not null
);

alter table telegram_question add column last_keyboard_select_id bigint;

alter table telegram_question
    add constraint telegram_last_keyboard_select FOREIGN KEY (last_keyboard_select_id)
        REFERENCES last_keyboard_select (id);