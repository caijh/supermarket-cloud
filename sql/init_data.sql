INSERT INTO "public"."t_country"("id", "abbreviation", "en_name", "first_char", "full_en_name", "name") VALUES (86, 'CN', 'China', 'C', '', '中国');
INSERT INTO "public"."t_area"("code", "country_id", "name", "parent_code") VALUES('440000', '86', '广东省', NULL);
INSERT INTO "public"."t_area"("code", "country_id", "name", "parent_code") VALUES('440100', '86', '广州市', '440000');
INSERT INTO "public"."t_area"("code", "country_id", "name", "parent_code") VALUES ('440106', '86', '天河区', '440100');

INSERT INTO t_role (id, code, name, created_by, create_time, updated_by, update_time, is_sys_defined)
VALUES (10000, 'ROLE_ADMIN', '管理员角色', NULL, NULL, NULL, NULL, TRUE);

INSERT INTO t_user (id,
                    account,
                    password,
                    nickname,
                    real_name,
                    head_img,
                    birthday,
                    created_by,
                    create_time,
                    updated_by,
                    update_time,
                    status)
VALUES (10000, 'admin', md5('123456'), '管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

INSERT INTO t_user_role (user_id, role_id)
VALUES (10000, 10000);

INSERT INTO t_resource (id, type, parent_id, display_name, url)
VALUES (10000000, 1, NULL, '系统管理', NULL);

INSERT INTO t_resource (id, type, parent_id, display_name, url)
VALUES (10010000, 1, 10000000, '角色管理', '/admin/role/list'),
       (10010100, 2, 10010000, '新增', '/admin/role/add'),
       (10010200, 2, 10010000, '修改', '/admin/role/update'),
       (10010300, 2, 10010000, '查看', '/admin/role/detail'),
       (10010400, 2, 10010000, '删除', '/admin/role/delete');

INSERT INTO t_resource (id, type, parent_id, display_name, url)
VALUES (10020000, 1, 10000000, '用户管理', '/admin/user/list'),
       (10020100, 2, 10020000, '新增', '/admin/user/add'),
       (10020200, 2, 10020000, '修改', '/admin/user/update'),
       (10020300, 2, 10020000, '查看', '/admin/user/detail'),
       (10020400, 2, 10020000, '删除', '/admin/user/delete');


INSERT INTO t_role_resource (role_id, resource_id)
VALUES (10000, 10000000);

INSERT INTO t_role_resource (role_id, resource_id)
VALUES (10000, 10010000),
       (10000, 10010100),
       (10000, 10010200),
       (10000, 10010300),
       (10000, 10010400);

INSERT INTO t_role_resource (role_id, resource_id)
VALUES (10000, 10020000),
       (10000, 10020100),
       (10000, 10020200),
       (10000, 10020300),
       (10000, 10020400);

INSERT INTO "public"."t_shop"("id", "address", "create_time", "created_by", "introduction", "name", "status", "thumbnails", "update_time", "updated_by", "user_id") VALUES (1000000, '[{"id": "440000", "code": "440000", "name": "广东省", "countryId": 86, "parentCode": null}, {"id": "440100", "code": "440100", "name": "广州市", "countryId": 86, "parentCode": "440000"}, {"id": "440106", "code": "440106", "name": "天河区", "countryId": 86, "parentCode": "440100"}]', '2018-12-20 17:00:09.353', 10000, '俊辉的自家店', '自营门店', 0, '[]', NULL, NULL, 10000);

INSERT INTO "public"."t_express"("id", "name") VALUES (encode('圆通速递(国内)', 'base64'), '圆通速递(国内)');


INSERT INTO "public"."t_express_client_setting"("client", "express_id", "express_config") VALUES ('cainiaoExpressClient', encode('圆通速递(国内)', 'base64'), '{"expressSupplierCode":"YTO"}');

