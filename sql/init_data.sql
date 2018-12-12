INSERT INTO "public"."t_country"("id", "abbreviation", "en_name", "first_char", "full_en_name", "name") VALUES (86, 'CN', 'China', 'C', '', '中国');
INSERT INTO "public"."t_area"("code", "country_id", "name", "parent_code") VALUES('440000', '86', '广东省', NULL);
INSERT INTO "public"."t_area"("code", "country_id", "name", "parent_code") VALUES('440100', '86', '广州市', 440000);

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


INSERT INTO "public"."t_express_client"("name", "config") VALUES ('cainiaoExpressClient', '{"appName":"HAIHUODIANZI", "appKey": "349CN6986sMiK5V0982P95K7C597q033", "appCode": "7db99129bd31b1e0a5317d3267658a97", "queryUrl": "http://link.cainiao.com/gateway/link.do"}');
INSERT INTO "public"."t_express_client"("name", "config") VALUES ('kdniaoExpressClient', '{"appKey": "1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "appCode": "1257230", "queryUrl": "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO "public"."t_express_client"("name", "config") VALUES ('kd100ExpressClient', '{"appKey": "aHnpSWdz8024", "appCode": "78FE308BCCD907818EF83CB2249667F7", "queryUrl": "http://poll.kuaidi100.com/poll/query.do", "subscribeUrl": "http://poll.kuaidi100.com/poll"}');


INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') 'EMS（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '中通国际（新西兰）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') 'TNT（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '云翔物流-末端派送EMS');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '速尔快递（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '德邦物流（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '中铁物流（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '顺丰快递（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64') '德邦黑猫宅急便（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '韵达快递（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '富腾达');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '圆通速递（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '蓝天快递（澳大利亚）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '富腾达国际货运代理公司（新西兰）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'FREAKYQUICK LOGISTICS（澳大利亚）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '合众兴物流（日本）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '法国EMS（法国）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '中通快递（国内）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '台湾邮政');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '4PX--转运四方现代物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '云翔物流，前端TNT');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '申通日本专线');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '德国-荷兰-韩国专模版');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '百世快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '易达通');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '天天快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '联邦快递 ( FedEx)-国际段');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '申通快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '香港邮政');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '全峰快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '韵达快递（香港）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '优速快递（香港）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '美西转运物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'AOI跨境转运');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '急速达');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '快捷快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '国通快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '中国邮政');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '快运通');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '安能物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '韵达(国际）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '拆单专用模版');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '天地华宇物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '日本专用模版');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '加中快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '快捷物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '新华快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '星云快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '宅急送');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '京东物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'track-parcel-中英直邮');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '程光快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '泰国BKK物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '家家通国际物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'EWE全球物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '日本EMS');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '远成快运');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳洲顺风快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '京广物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '一号线快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '海龟快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '信丰物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'S2C国际快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '品骏快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '优速快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳邮中国快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '泛捷国际速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '八达通快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '中邮速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳世速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'EWE国际快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '运通速运');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '货运皇');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '中泰金牌物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '方舟国际速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '极地快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'AOL国际物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '龙邦物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '王道快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '三象速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '环球速运');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '一号仓（香港）');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '中联快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳行国际物流快递（AOX)');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '捷安达国际速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '跨越速运');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '捷豹国际快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '天翼快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '长江国际速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '陆本快递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳邦国际物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '澳新物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '光线速递');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), '淘布斯国际物流');
INSERT INTO t_express(id, name) VALUES (encode('圆通速递（国内）', 'base64'), 'CNPEX中邮快递');


INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 1,'{"expressSupplierCode":"EMS"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 11,'{"expressSupplierCode":"YUNDA"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 13,'{"expressSupplierCode":"YTO"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 15, '{"expressSupplierCode":"ftd"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 19, '{"expressSupplierCode":"ZTO"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 20, '{"expressSupplierCode":"postserv"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 23, '{"expressSupplierCode":"STO"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 25, '{"expressSupplierCode":"HTKY"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 27, '{"expressSupplierCode":"TTKDEX"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 29,  '{"expressSupplierCode":"lianbangkuaidi"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 3,  '{"expressSupplierCode":"tnt"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 30, '{"expressSupplierCode":"STO"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 31, '{"expressSupplierCode":"FEDEX"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 33, '{"expressSupplierCode":"hkpost"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 34, '{"expressSupplierCode":"QFKD"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 35,'{"expressSupplierCode":"YUNDA"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 36, '{"expressSupplierCode":"UC"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 40, '{"cainiaoCpCode":"FAST"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 41,'{"shipperCode":"GTO"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 41, now(),now(), '{"expressName":"国通快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"guotongkuaidi","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 41, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"国通快递","cainiaoCpCode":"GTO"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 42, now(),now(),'{"expressName":"中国邮政", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"YD", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 42, now(),now(), '{"expressName":"中国邮政","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"youzhengguonei","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 42, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"中国邮政","cainiaoCpCode":"POSTB"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 44, now(),now(),'{"expressName":"安能物流", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"EMS", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 44, now(),now(), '{"expressName":"安能物流","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"annengwuliu","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 44, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"安能物流","cainiaoCpCode":"ANE56"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 45, now(),now(),'{"expressName":"韵达国际", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"YD", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 45, now(),now(), '{"expressName":"韵达国际","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"yunda","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 47, now(),now(),'{"expressName":"天地华宇物流", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"EMS", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 47, now(),now(), '{"expressName":"天地华宇物流","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"tiandihuayu","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 5, now(),now(),'{"expressName":"速尔快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"SURE", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 5, now(),now(), '{"expressName":"速尔快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"suer","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 5, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"速尔快递","cainiaoCpCode":"SURE"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 55, now(),now(),'{"expressName":"中英直邮快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"EMS", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 55, now(),now(), '{"expressName":"中英直邮快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"ems","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 56, now(),now(),'{"expressName":"程光快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"CGKD", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 56, now(),now(), '{"expressName":"程光快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"chengguangkuaidi","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 58, now(),now(),'{"expressName":"家家通国际物流", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"JJTKD", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 58, now(),now(), '{"expressName":"家家通国际物流","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"newsway","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 6, now(),now(),'{"expressName":"德邦物流", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"DBL", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 6, now(),now(), '{"expressName":"德邦物流","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"debangwuliu","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 6, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"德邦物流","cainiaoCpCode":"DBL"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 69, now(),now(),'{"expressName":"优速快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"EMS", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 69, now(),now(), '{"expressName":"优速快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"youshuwuliu","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('cainiaoExpressClient', 69, now(),now(),'{"cainiaoReqUrl":"http://link.cainiao.com/gateway/link.do","cainiaoAppName":"HAIHUODIANZI","cainiaoAppKey":"349CN6986sMiK5V0982P95K7C597q033","logisticProviderId":"7db99129bd31b1e0a5317d3267658a97","expressName":"优速快递","cainiaoCpCode":"UC"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 7, now(),now(),'{"expressName":"中铁快运", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"ZTKY", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 7, now(),now(), '{"expressName":"中铁快运","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"ztky","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 8, now(),now(),'{"expressName":"顺丰快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"SF", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 8, now(),now(), '{"expressName":"顺丰快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"shunfeng","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 87, now(),now(),'{"expressName":"中联快递", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 87, now(),now(), '{"expressName":"中联快递","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"auvanda","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');

INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kdniaoExpressClient', 90, now(),now(),'{"expressName":"跨越速运", "appKey":"1ce9806e-2905-45fd-b34b-0e9f3ede8ba4", "eBusinessID":1257230, "requestType":1002,"shipperCode":"KY", "reqUrl":"http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"}');
INSERT INTO t_express_client_setting(client,express_id,express_config) VALUES ('kd100ExpressClient', 90, now(),now(), '{"expressName":"跨越速运","100subsUrl":"http://poll.kuaidi100.com/poll","100Key":"aHnpSWdz8024","100ComCode":"78FE308BCCD907818EF83CB2249667F7","100shipCode":"kuayue","100queryUrl":"http://poll.kuaidi100.com/poll/query.do"}');
