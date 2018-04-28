
insert into user_role(role, description) VALUES ("ROLE_USER", "Default role for user");
insert into user_role(role, description) VALUES ("ROLE_ADMIN", "Role for admininstrators");



insert into user (email, first_name, last_name, password, username) values ("adi@mail.com", "Adrian", "Dyjeciński", "123", "Adi");
insert into user_roles (user_id, roles_id) values (1, 2);
insert into user_roles (user_id, roles_id) values (1, 1);



insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdańsk", "Kołobrzeska 77", "", "przy Ogólnokształcących Szkołach Sportowych", "", "58 557-17-41", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdańsk", "Marcina Dragana 2", "", "przy Szkole Podstawowej nr 8", "", "58 300-48-75", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdańsk", "Tadeusza Kościuszki 8B", "", "przy Gimnazjum nr 25", "", "58 341-49-15", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdańsk", "Orłowska 13", "", "przy Zespole Kształcenia Podstawowego i Gimnazjalnego nr 7", "", "58 556-29-00", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdańsk", "Subisława 22", "", "przy Zespole Szkół Sportowych i Ogólnokształcących", "", "58 557-94-45", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Sopot", "Subisława 22", "", "przy Zespole Szkół Sportowych i Ogólnokształcących", "", "58 557-94-45", "");

insert into field (city, street, email, next_to, opening_hours, phone_number, url)
VALUES ("Gdynia", "Subisława 22", "", "przy Zespole Szkół Sportowych i Ogólnokształcących", "", "58 557-94-45", "");



insert into event (description, posted_at, title, type, user_id) values ("Opis do meczu jakis tam", "2017-10-20", "Meczyk", "FOOTBALL", "1");
insert into event (description, posted_at, title, type, user_id) values ("Opis do drugiego meczu jakis tam", "2017-12-20", "Meczyk drugi", "FOOTBALL", "1");
insert into event (description, posted_at, title, type, user_id) values ("Opis do meczu trzeciego jakis tam", "2018-03-20", "Meczyk trzeci", "FOOTBALL", "1");