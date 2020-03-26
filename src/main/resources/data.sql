INSERT INTO TRAFFIC_HOUR (ID, name, RED_DURATION, GREEN_DURATION, AMBER_DURATION ) VALUES (1, 'OFF_PEAK', 10, 30, 10);
INSERT INTO TRAFFIC_HOUR (ID, name, RED_DURATION, GREEN_DURATION, AMBER_DURATION ) VALUES (2, 'NORMAL', 15, 30, 5);
INSERT INTO TRAFFIC_HOUR (ID, name, RED_DURATION, GREEN_DURATION, AMBER_DURATION ) VALUES (3, 'HEAVY', 15, 15, 2);
INSERT INTO TRAFFIC_HOUR (ID, name, RED_DURATION, GREEN_DURATION, AMBER_DURATION ) VALUES (4, 'MEDIUM', 20, 20, 5);

--SUNDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (101, 'SUNDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (102, 'SUNDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (103, 'SUNDAY', 1, 1900, 2359);

--MONDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (201, 'MONDAY', 1, 0000, 600);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (202, 'MONDAY', 3, 600, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (203, 'MONDAY', 1, 1100, 1600);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (204, 'MONDAY', 3, 1600, 2000);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (205, 'MONDAY', 2, 2000, 2359);

--TUESDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (301, 'TUESDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (302, 'TUESDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (303, 'TUESDAY', 3, 1900, 2359);

--WEDNESDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (401, 'WEDNESDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (402, 'WEDNESDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (403, 'WEDNESDAY', 3, 1900, 2359);

--THURSDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (501, 'THURSDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (502, 'THURSDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (503, 'THURSDAY', 3, 1900, 2359);


--FRIDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (601, 'FRIDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (602, 'FRIDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (603, 'FRIDAY', 3, 1900, 2359);

--SATURDAY Schedule
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (701, 'SATURDAY', 1, 0000, 1100);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (702, 'SATURDAY', 2, 1100, 1900);
INSERT INTO TRAFFIC_LIGHT_SCHEDULE (ID, day_of_week, traffic_hours_id, fromHour, endHour) VALUES (703, 'SATURDAY', 3, 1900, 2359);
