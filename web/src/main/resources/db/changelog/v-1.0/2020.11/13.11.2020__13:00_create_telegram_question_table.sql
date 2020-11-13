CREATE TABLE telegram_question
(
    id          bigserial not null primary key,
    external_id bigint    not null,
    value       text
);