insert into USER(name, email, password) values('Leandro', 'le@email.com', '$2a$10$2x2/yGrsk0hSAMpkHhYoP.Foc3BA8i.zLkt.Ofrhi8.d7SxG8P44y');
insert into USER(name, email, password) values('Robson', 'rob@email.com', '$2a$10$2x2/yGrsk0hSAMpkHhYoP.Foc3BA8i.zLkt.Ofrhi8.d7SxG8P44y');

insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Beta', 30, 1.0, 22.5, true, true, 22.0, 7.1, true, '2020-01-01 21:00', 'ONLINE', 1);
insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Oscar', 60, 0.95, 23.7, true, true, 22.0, 7.2, false, '2020-01-04 20:30', 'ONLINE', 1);
insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Dourado', 25, 0.93, 21.3, false, false, 21.0, 6.9, true, '2020-02-15 20:00', 'ONLINE', 1);
insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Beta', 30, 0.98, 22.6, true, false, 22.0, 6.9, false, '2020-02-03 21:00', 'ONLINE', 2);
insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Palhaço', 25, 0.95, 24.8, true, false, 24.0, 7.2, true, '2020-03-06 20:30', 'ONLINE', 2);
insert into AQUARIUM(name, volume, water_level, temperature, control_active, temp_control_active, set_point_temp, ph, ph_monit_active, creation_date, status, user_id) values('Colisa', 10, 1.1, 25.1, false, true, 24.0, 7.0, false, '2020-03-16 20:00', 'ONLINE', 2);

