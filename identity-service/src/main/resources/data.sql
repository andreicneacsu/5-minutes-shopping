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

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (15.50, 'Cascaval Hochland', 'PIECES');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (3.90, 'Ciocolata Milka Capsuni', 'PIECES');

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (1, 1);

INSERT INTO bonuspromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, bonus_points_earned)
VALUES(2, 1, PARSEDATETIME('17/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('24/07/2023', 'dd/MM/yyyy'), 'BONUS', 10);

INSERT INTO discountpromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, discount_percent)
VALUES(3, 2, PARSEDATETIME('20/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('01/08/2023', 'dd/MM/yyyy'), 'DISCOUNT', 20);