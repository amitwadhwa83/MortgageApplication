create table reservation (
id integer NOT NULL AUTO_INCREMENT,
user_id integer NOT NULL,
party_size integer NOT NULL,
date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
restaurant_id integer NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO reservation(user_id,party_size,date,restaurant_id) VALUES (100,2,NOW(),800);
INSERT INTO reservation(user_id,party_size,date,restaurant_id) VALUES (101,3,NOW(),800);
INSERT INTO reservation(user_id,party_size,date,restaurant_id) VALUES (102,5,NOW(),800);