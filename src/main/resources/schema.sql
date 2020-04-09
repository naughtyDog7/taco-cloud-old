create table if not exists ingredients
(
    id   varchar(4)  not null default ('AAAA'),
    name varchar(25) not null,
    type varchar(12) not null,
    constraint ingredients_pk
        primary key (id)
);
create table if not exists tacos
(
    id        int AUTO_INCREMENT,
    name      varchar(50) not null,
    createdAt datetime   null,
    constraint tacos_pk
        primary key (id)
);
create table if not exists tacos_ingredients
(
    Taco_id       int     not null,
    ingredientsList_id varchar(4) not null
);
alter table tacos_ingredients
    add foreign key (taco_id) references tacos (id);
alter table tacos_ingredients
    add foreign key (ingredientsList_id) references ingredients (id);

create table if not exists orders
(
    id       int  AUTO_INCREMENT,
    name     varchar(50) not null,
    city     varchar(50) not null,
    street   varchar(50) not null,
    phoneNum varchar(13) not null,
    cardNum  varchar(16) not null,
    placedAt datetime not null,
    constraint orders_pk
        primary key (id)
);
create table if not exists orders_tacos(
    Order_id int not null,
    tacos_id  int not null
);
alter table orders_tacos
    add foreign key (Order_id) references orders (id);
alter table orders_tacos
    add foreign key (tacos_id) references tacos (id);


#
# create table ingredients
# (
#     id   varchar(4)  not null
#         primary key,
#     name varchar(25) not null,
#     type varchar(12) not null
# );
#
# create table orders
# (
#     id       int auto_increment
#         primary key,
#     name     varchar(50) null,
#     city     varchar(50) null,
#     street   varchar(50) null,
#     phoneNum varchar(13) null,
#     cardNum  varchar(16) null,
#     placedAt datetime    null
# );
#
# create table tacos
# (
#     id        int auto_increment
#         primary key,
#     name      varchar(50) not null,
#     createdAt datetime    null
# );
#
# create table taco_ingredients
# (
#     taco_id       int        not null,
#     ingredient_id varchar(4) not null,
#     constraint taco_ingredients_ibfk_1
#         foreign key (taco_id) references tacos (id),
#     constraint taco_ingredients_ibfk_2
#         foreign key (ingredient_id) references ingredients (id)
# );
#
# create index ingredient_id
#     on taco_ingredients (ingredient_id);
#
# create index taco_id
#     on taco_ingredients (taco_id);
#
# create table taco_orders
# (
#     order_id int not null,
#     taco_id  int not null,
#     constraint taco_orders_ibfk_1
#         foreign key (order_id) references orders (id),
#     constraint taco_orders_ibfk_2
#         foreign key (taco_id) references tacos (id)
# );
#
# create index order_id
#     on taco_orders (order_id);
#
# create index taco_id
#     on taco_orders (taco_id);




# create table ingredients
# (
#     id   varchar(4)   not null
#         primary key,
#     name varchar(255) null,
#     type varchar(255) null
# )
#     engine = MyISAM;
#
# create table orders
# (
#     id       int auto_increment
#         primary key,
#     cardNum  varchar(255) null,
#     city     varchar(255) null,
#     name     varchar(255) null,
#     phoneNum varchar(255) null,
#     placedAt datetime     null,
#     street   varchar(255) null
# )
#     engine = MyISAM;
#
# create table taco_ingredients
# (
#     ingredient_id varchar(4) not null,
#     taco_id       int        not null
# )
#     engine = MyISAM;
#
# alter table taco_ingredients
#     add foreign key (taco_id) references tacos (id);
# alter table taco_ingredients
#     add foreign key (ingredient_id) references ingredients (id);
#
# create table taco_orders
# (
#     taco_id  int not null,
#     order_id int not null
# )
#     engine = MyISAM;
#
# alter table taco_orders
#     add foreign key (order_id) references orders (id);
# alter table taco_orders
#     add foreign key (taco_id) references tacos (id);
#
# create table tacos
# (
#     id        int auto_increment
#         primary key,
#     createdAt datetime     null,
#     name      varchar(255) not null
# )
#     engine = MyISAM;