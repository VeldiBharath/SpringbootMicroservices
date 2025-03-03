

create sequence product_id_seq start with 1 increment by 50;

create table products
(
--     auto generator for db id, always use new migration scripts to change anything in database or else conflicts may occur
    id bigint default nextval('product_id_seq') not null,
    code        text not null unique,
    name        text not null,
    description text,
    image_url   text,
    price       numeric not null,
    primary key (id)
);