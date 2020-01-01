DELETE FROM sys_user_role 
WHERE
    user_id = 1;
DELETE FROM sys_role 
WHERE
    id >= 1 AND id <= 4;
-- INSERT ROLES
insert into sys_role(id,role_name,role_desc,create_time)
select 1,'ROLE_ADMIN','administrator',NOW()
UNION
select 2,'ROLE_CONTRIBUTOR','log configuer',NOW()
UNION
select 3,'ROLE_VIEWER','viewer',NOW()
UNION
select 4,'ROLE_ANONYMOUS','anyone',NOW();

DELETE FROM sys_user 
WHERE
    id = 1;
insert sys_user(id,user_name,user_pass,create_time) values(1,'admin','$2a$10$6tznn0nBDJZRKuTN4pvnle81pqpj9eEPlVVE4fLumonL8Bxm6t4Le',NOW());

DELETE FROM sys_user_role 
WHERE
    user_id = 1;
insert into sys_user_role(id,user_id,role_id)
select UUID_SHORT(),1,1
UNION
select UUID_SHORT(),1,2
UNION
select UUID_SHORT(),1,3
;

DELETE FROM b_meta_productline 
WHERE
    1 = 1;
DELETE FROM b_meta_project 
WHERE
    1 = 1;
-- INSERT META DATA PROJECT
INSERT INTO b_meta_project(id,category,prj_id,prj_name)
SELECT UUID_SHORT(),'CN','01','TEST_PROJ_1'
UNION
SELECT UUID_SHORT(),'CN','02','TEST_PROJ_2'
;

-- INSERT META DATA PRODUCTLINE
INSERT INTO b_meta_productline(id,category,prj_id,pl_id,pl_name)
SELECT UUID_SHORT(),'CN','01','01','TEST_PROD_1'
UNION
SELECT UUID_SHORT(),'CN','01','02','TEST_PROD_2'
;

DELETE FROM b_meta_release 
WHERE
    1 = 1;
INSERT INTO b_meta_release(id,name,ordinal,xml_tag)
SELECT UUID_SHORT(),'APP',0,'app'
UNION
SELECT UUID_SHORT(),'MCU',1,'mcu'
;