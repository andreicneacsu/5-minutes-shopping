INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Andrei', 'Neacsu', PARSEDATETIME('01/07/2000', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('James', 'Bond', PARSEDATETIME('07/08/1973', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Maria', 'Popescu', PARSEDATETIME('22/11/1989', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Dan', 'Negru', PARSEDATETIME('13/05/1975', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Ivan', 'Miskovich', PARSEDATETIME('25/12/1980', 'dd/MM/yyyy'), 0);

INSERT INTO product_favourites(product_id, shopper_id)
    VALUES (1, 1);
