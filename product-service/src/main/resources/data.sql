INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
    VALUES (15.50, 'Cascaval Hochland', 'PIECES');

INSERT INTO products(price_per_measure_unit, name, unity_of_measure)
    VALUES (3.90, 'Ciocolata Milka Capsuni', 'PIECES');

INSERT INTO bonuspromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, bonus_points_earned)
    VALUES(2, 1, PARSEDATETIME('17/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('24/07/2023', 'dd/MM/yyyy'), 'BONUS', 10);

INSERT INTO discountpromotions(min_quantity, product_id, starts_on, expires_on, promotion_type, discount_percent)
    VALUES(3, 2, PARSEDATETIME('20/07/2023', 'dd/MM/yyyy'), PARSEDATETIME('01/08/2023', 'dd/MM/yyyy'), 'DISCOUNT', 20);
