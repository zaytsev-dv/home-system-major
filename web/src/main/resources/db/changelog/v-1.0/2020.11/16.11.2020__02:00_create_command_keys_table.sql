CREATE TABLE telegram_command_keys
(
    id    bigserial not null primary key,
    value text      not null,
    name  text      not null,
    type  text      not null,
    msg   text
);

insert into telegram_command_keys (value, name, type, msg) values ('/register_confirm_yes', '/register_confirm_yes', 'REGISTRATION_KEYBOARD', 'Супер!!!Введи свой email');
insert into telegram_command_keys (value, name, type, msg) values ('/register_confirm_no', '/register_confirm_no', 'REGISTRATION_KEYBOARD', 'Что ж очень жаль. Тогда до новых встреч и удачи тебе!');
insert into telegram_command_keys (value, name, type, msg) values ('/register_confirm_final_yes', '/register_confirm_final_yes', 'REGISTRATION_KEYBOARD', null);
insert into telegram_command_keys (value, name, type, msg) values ('/register_confirm_final_no', '/register_confirm_final_no', 'REGISTRATION_KEYBOARD', '/start');