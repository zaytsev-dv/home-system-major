alter table product add column realm_id bigint;

alter table product
    add constraint product_realm FOREIGN KEY (realm_id)
        REFERENCES realm (id);