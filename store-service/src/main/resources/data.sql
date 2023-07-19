INSERT INTO stores(name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES ('Mega Image Shop&Go Ferdinand 162', 'BUCHAREST', 'Bulevardul Ferdinand I 162, București 021398', 1, PARSEDATETIME('24/06/2015', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 50, 1);

INSERT INTO stores(name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES ('Auchan Titan', 'BUCHAREST', 'Bulevardul 1 Decembrie 1918 33A', 2, PARSEDATETIME('03/12/2019', 'dd/MM/yyyy'), true, 'ENTRY_EXIT_PASS', 0, 100, 1);

INSERT INTO stores(name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES ('Cora Lujerului', 'BUCHAREST', 'Bulevardul Iuliu Maniu 19, București 061106', 3, PARSEDATETIME('23/07/2018', 'dd/MM/yyyy'), true, 'EXIT_PASS', 0, 40, 2);

INSERT INTO stores(name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES ('Kaufland Colentina', 'BUCHAREST', 'Șoseaua Colentina 6, București 021173', 4, PARSEDATETIME('10/12/2020', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 150, 2);

INSERT INTO stores(name, city, address, retailer_id, launch_date, active_status, store_tag, current_capacity, max_capacity, district_number) VALUES ('Carrefour Veranda', 'BUCHAREST', 'Strada Ziduri Moși 23, București 021203', 5, PARSEDATETIME('17/03/2015', 'dd/MM/yyyy'), true, 'ENTRY_PASS', 0, 200, 2);

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