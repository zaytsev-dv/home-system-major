alter table keyboard_callback rename to msg_callback;

insert into msg_callback (value, type) VALUES ('Супер!!!Введи свой email', 'REGISTRATION');
insert into msg_callback (value, type) VALUES ('Супер! А теперь Номер телефона', 'REGISTRATION');
insert into msg_callback (value, type) VALUES ('Супер!!!Введи свой email", "Супер! А теперь Номер телефона', 'REGISTRATION');
insert into msg_callback (value, type) VALUES ('Какой товар тебя интересует?', 'REALM');
insert into msg_callback (value, type) VALUES ('Какой бюджет?', 'REALM');
