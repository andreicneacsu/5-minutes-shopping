INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Andrei', 'Neacsu', PARSEDATETIME('01/07/2000', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('James', 'Bond', PARSEDATETIME('07/08/1973', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Stefan', 'Popescu', PARSEDATETIME('22/11/1989', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Dan', 'Negru', PARSEDATETIME('13/05/1975', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Ivan', 'Miskovich', PARSEDATETIME('25/12/1980', 'dd/MM/yyyy'), 0);

INSERT INTO shoppers(first_name, last_name, birth_date, bonus_points)
    VALUES ('Marius', 'Moise', PARSEDATETIME('12/07/1979', 'dd/MM/yyyy'), 0);

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (15.50, 'Cascaval Hochland', 'PIECES');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (3.90, 'Ciocolata Milka Capsuni', 'PIECES');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (10.99, 'Baton Proteic', 'PIECES');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (38.99, 'Fistic', 'GRAMS');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (4.50, 'Seminte de Floarea Soarelui', 'GRAMS');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (6.50, 'Cola Zero', 'LITTERS');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (49.50, 'Jack Daniels', 'LITTERS');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
VALUES (75.50, 'Whisky', 'LITTERS');

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (1, 1);

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (2, 1);

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (3, 1);

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (4, 1);

INSERT INTO product_favourites(product_id, shopper_id)
VALUES (5, 1);

INSERT INTO bonuspromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, bonus_points_earned)
VALUES(2, 1, PARSEDATETIME('17/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('24/09/2023', 'dd/MM/yyyy'), 'BONUS', 10);

INSERT INTO discountpromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, discount_percent)
VALUES(3, 2, PARSEDATETIME('20/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('21/09/2023', 'dd/MM/yyyy'), 'DISCOUNT', 20);