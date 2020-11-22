CREATE TABLE notes_category
(
    id          bigserial not null primary key,
    value       text      not null,
    description text
);

insert into notes_category (value, description) values ('DOCKER', 'Докер');
insert into notes_category (value, description) values ('JAVA', 'Java');

alter table notes
    add column notes_category_id bigint;


alter table notes
    add constraint notes_notes_category FOREIGN KEY (notes_category_id)
        REFERENCES notes_category (id);