create table animals(
    id serial primary key,
    name character varying(255),
    area text
);

insert into animals(name, area) values('Гризли', 'США');

update animals set name = 'Гризли';