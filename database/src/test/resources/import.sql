/* fleet 1 */
insert into fleet(id, fleetname) values ('f1ee7001-56e9-43ee-9cc8-15937e9a841b','Fleet One');
insert into cmlogin(id, login, fleet_id) values ('aaaaaaaa-56e9-43ee-9cc8-15937e9a8499','cmlogin1', 'f1ee7001-56e9-43ee-9cc8-15937e9a841b');

insert into device(id, devname, cmlogin_id, fleet_id) values ('de11ce00-56e9-43ee-9cc8-15937e9a0001','iPhone8', 'aaaaaaaa-56e9-43ee-9cc8-15937e9a8499', 'f1ee7001-56e9-43ee-9cc8-15937e9a841b');
insert into device(id, devname, cmlogin_id, fleet_id) values ('de11ce00-56e9-43ee-9cc8-15937e9a0002','GooglePixel', 'aaaaaaaa-56e9-43ee-9cc8-15937e9a8499', 'f1ee7001-56e9-43ee-9cc8-15937e9a841b');
insert into cmlogin(id, login, fleet_id) values ('bbbbbbbb-56e9-43ee-9cc8-15937e9a8499','cmlogin2', 'f1ee7001-56e9-43ee-9cc8-15937e9a841b');
insert into device(id, devname, cmlogin_id, fleet_id) values ('de11ce22-2222-2222-2222-15937e9a0001','iPhone8', 'bbbbbbbb-56e9-43ee-9cc8-15937e9a8499', 'f1ee7001-56e9-43ee-9cc8-15937e9a841b');

/* fleet 2 */
insert into fleet(id, fleetname) values ('89619fd4-56e9-43ee-9cc8-15937e9a841c','Fleet 2');

