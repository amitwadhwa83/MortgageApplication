CREATE TABLE rate (
id integer NOT NULL AUTO_INCREMENT,
maturity_period integer NOT NULL,
interest_rate decimal(5,2) NOT NULL,
last_update timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id)
);
INSERT INTO rate(maturity_period,interest_rate,last_update) VALUES (12,2.5,NOW());
INSERT INTO rate(maturity_period,interest_rate,last_update) VALUES (24,1.5,NOW());
INSERT INTO rate(maturity_period,interest_rate,last_update) VALUES (36,1.0,NOW());