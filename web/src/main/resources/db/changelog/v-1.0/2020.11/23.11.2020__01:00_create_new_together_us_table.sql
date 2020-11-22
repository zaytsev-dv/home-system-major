CREATE TABLE realm
(
    id          bigserial not null primary key,
    value       text      not null,
    description text
);

CREATE TABLE company
(
    id          bigserial not null primary key,
    value       text      not null,
    description text,
    realm_id    bigint references realm(id) not null
);

CREATE TABLE product
(
    id          bigserial not null primary key,
    value       text      not null,
    description text,
    price       double precision,
    company_id    bigint references company(id) not null
);

insert into realm(id, value, description) values (1, 'Бытовая техника', null);
insert into realm(id, value, description) values (2, 'Цифровая техника', null);

insert into company(id, value, description, realm_id) values (1, 'Землекопы и ко', null, 1);
insert into company(id, value, description, realm_id) values (2, 'Петрович', null, 1);

insert into company(id, value, description, realm_id) values (3, 'Компания А', null, 2);
insert into company(id, value, description, realm_id) values (4, 'Компания Б', null, 2);

insert into product(value, description, price, company_id) values ('Пылесос 1', null, 1000, 1);
insert into product(value, description, price, company_id) values ('Телевизор 1', null, 5000, 3);