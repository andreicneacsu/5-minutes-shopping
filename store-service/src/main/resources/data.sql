INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (100, 'Mega Image Shop&Go Ferdinand 162', 'BUCHAREST', 'Bulevardul Ferdinand I 162, București 021398', 1, PARSEDATETIME('24/06/2015', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 50, 1);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (100, 'Auchan Titan', 'BUCHAREST', 'Bulevardul 1 Decembrie 1918 33A', 2, PARSEDATETIME('03/12/2019', 'dd/MM/yyyy'), true, 'ENTRY_EXIT_PASS', 0, 100, 1);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (20, 'Cora Lujerului', 'BUCHAREST', 'Bulevardul Iuliu Maniu 19, București 061106', 3, PARSEDATETIME('23/07/2018', 'dd/MM/yyyy'), true, 'EXIT_PASS', 0, 40, 2);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (50, 'Kaufland Colentina', 'BUCHAREST', 'Șoseaua Colentina 6, București 021173', 4, PARSEDATETIME('10/12/2020', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 150, 2);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (90, 'Carrefour Veranda', 'BUCHAREST', 'Strada Ziduri Moși 23, București 021203', 5, PARSEDATETIME('17/03/2015', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 200, 2);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (90, 'Lidl Baicului', 'BUCHAREST', 'Strada Baicului 1, București 021270', 5, PARSEDATETIME('17/03/2015', 'dd/MM/yyyy'), false, 'ENTRY_PASS', 0, 200, 2);

INSERT INTO stores(bonus_points_discount, name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES (90, 'Profi Militari', 'BUCHAREST', 'Strada Egalitatii 1, București 021272', 5, PARSEDATETIME('17/03/2015', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 200, 200, 2);

INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
    INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'SATURDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (1, 'SUNDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'SATURDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (7, 'SUNDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));


INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'SATURDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (2, 'SUNDAY', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));


INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (3, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (3, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (3, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (3, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (3, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));


INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (4, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (4, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (4, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (4, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (4, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));


INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (5, 'MONDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (5, 'TUESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (5, 'WEDNESDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (5, 'THURSDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));
INSERT INTO store_schedules(store_id, day_of_week, start_hour, end_hour)
VALUES
    (5, 'FRIDAY', PARSEDATETIME('08:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'));