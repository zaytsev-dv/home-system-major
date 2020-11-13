CREATE TABLE telegram_question
(
    id          bigserial not null primary key,
    external_id bigint    not null,
    value       text,
    type        text,
    sub_type    text,
    created_at  timestamp with time zone,
    is_answered bool
);