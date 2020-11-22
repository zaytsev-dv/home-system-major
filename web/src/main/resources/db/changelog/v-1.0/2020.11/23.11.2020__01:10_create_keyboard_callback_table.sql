CREATE TABLE keyboard_callback
(
    id          bigserial not null primary key,
    value       text      not null,
    type        text      not null
);

insert into keyboard_callback (value, type) VALUES ('/register_confirm_yes', 'REGISTRATION');
insert into keyboard_callback (value, type) VALUES ('/register_confirm_no', 'REGISTRATION');
insert into keyboard_callback (value, type) VALUES ('/register_confirm_final_yes', 'REGISTRATION');
insert into keyboard_callback (value, type) VALUES ('/register_confirm_final_no', 'REGISTRATION');

insert into keyboard_callback (value, type) VALUES ('/Бытовая техника', 'REALM');
insert into keyboard_callback (value, type) VALUES ('/Цифровая техника', 'REGISTRATION');
