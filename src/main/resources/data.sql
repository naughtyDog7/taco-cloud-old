delete from orders_tacos;
delete from tacos_ingredients;
delete from tacos;
delete from orders;
delete from ingredients;
insert into ingredients (id, name, type, price)
values ('FLTO', 'Flour Tortilla', 'WRAP', 2.5);
insert into ingredients (id, name, type, price)
values ('COTO', 'Corn Tortilla', 'WRAP', 2.6);
insert into ingredients (id, name, type, price)
values ('GRBF', 'Ground Beef', 'PROTEIN', 4.2);
insert into ingredients (id, name, type, price)
values ('CARN', 'Carnitas', 'PROTEIN', 3.6);
insert into ingredients (id, name, type, price)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES', 0.6);
insert into ingredients (id, name, type, price)
values ('LETC', 'Lettuce', 'VEGGIES', 0.4);
insert into ingredients (id, name, type, price)
values ('CHED', 'Cheddar', 'CHEESE', 2.2);
insert into ingredients (id, name, type, price)
values ('JACK', 'Monterrey Jack', 'CHEESE', 2.5);
insert into ingredients (id, name, type, price)
values ('SLSA', 'Salsa', 'SAUCE', 2.0);
insert into ingredients (id, name, type, price)
values ('SRCR', 'Sour Cream', 'SAUCE', 2.0);