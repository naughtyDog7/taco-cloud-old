create table if not exists ingredients
(
    id   varchar(4)  not null default ('AAAA'),
    name varchar(25) not null,
    type varchar(12) not null,
    price double not null,
    constraint ingredients_pk
        primary key (id)
);
create table if not exists tacos
(
    id        int AUTO_INCREMENT,
    name      varchar(50) not null,
    createdAt datetime    null,
    constraint tacos_pk
        primary key (id)
);
create table if not exists tacos_ingredients
(
    Taco_id            int        not null,
    ingredientsList_id varchar(4) not null
);
alter table tacos_ingredients
    add foreign key (taco_id) references tacos (id);
alter table tacos_ingredients
    add foreign key (ingredientsList_id) references ingredients (id);

create table if not exists orders
(
    id       int AUTO_INCREMENT,
    name     varchar(50) not null,
    city     varchar(50) not null,
    street   varchar(50) not null,
    phoneNum varchar(17) not null,
    cardNum varchar(16),
    user_id bigint not null,
    placedAt datetime    not null,
    ordered boolean,
    constraint orders_pk
        primary key (id)
);


create table if not exists orders_tacos
(
    Order_id int not null,
    tacos_id int not null
);
alter table orders_tacos
    add foreign key (Order_id) references orders (id);
alter table orders_tacos
    add foreign key (tacos_id) references tacos (id);

create table if not exists users
(
    id       bigint AUTO_INCREMENT,
    username varchar(50),
    password varchar(255),
    fullName varchar(50),
    city     varchar(50),
    street   varchar(50),
    phoneNum varchar(17),
    constraint users_pk
        primary key (id)
);
alter table orders
    add foreign key (user_id) references users (id);