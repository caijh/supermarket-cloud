CREATE TABLE "public"."t_country" (
  "id" int8 NOT NULL,
  "abbreviation" varchar(255) COLLATE "pg_catalog"."default",
  "en_name" varchar(255) COLLATE "pg_catalog"."default",
  "first_char" varchar(255) COLLATE "pg_catalog"."default",
  "full_en_name" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "t_country_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_country" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_area" (
  "code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "country_id" int8,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "parent_code" varchar(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "t_area_pkey" PRIMARY KEY ("code")
)
;

ALTER TABLE "public"."t_area" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_client_app" (
  "client_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "access_token_validity_seconds" int4,
  "authorities" jsonb,
  "authorized_grant_types" jsonb,
  "client_secret" varchar(255) COLLATE "pg_catalog"."default",
  "client_type" int4,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "redirect_uri" jsonb,
  "refresh_token_validity_seconds" int4,
  "resource_ids" jsonb,
  "scope" jsonb,
  CONSTRAINT "t_client_app_pkey" PRIMARY KEY ("client_id")
)
;

ALTER TABLE "public"."t_client_app" 
  OWNER TO "supermarket";

CREATE TABLE "t_role" (
  "id" int8 NOT NULL,
  "code" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "create_time" timestamp(6),
  "created_by" int8,
  "update_time" timestamp(6),
  "updated_by" int8,
  "is_sys_defined" bool DEFAULT false,
  PRIMARY KEY ("id")
);
COMMENT ON TABLE "t_role" IS '角色表';
COMMENT ON COLUMN "t_role"."code" IS '角色代号';
COMMENT ON COLUMN "t_role"."is_sys_defined" IS '是否是系统预先定义的角色';

CREATE TABLE "t_user" (
  "id" int8 NOT NULL,
  "account" varchar(255) NOT NULL,
  "password" varchar(255) NOT NULL,
  "nickname" varchar(255),
  "real_name" varchar(255),
  "head_img" varchar(255),
  "age" int4,
  "create_time" timestamp,
  "created_by" int8,
  "update_time" timestamp,
  "updated_by" int8,
  "status" int4,
  PRIMARY KEY ("id") ,
  CONSTRAINT "udx_account" UNIQUE ("account")
);
COMMENT ON TABLE "t_user" IS '用户表';
COMMENT ON COLUMN "t_user"."status" IS '0-正常,1-锁定,2-删除';

CREATE TABLE "t_user_role" (
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  PRIMARY KEY ("role_id", "user_id")
);
CREATE INDEX "idx_user_id" ON "t_user_role" ("user_id" ASC NULLS LAST);
CREATE INDEX "idx_role_id" ON "t_user_role" ("role_id" ASC NULLS LAST);
COMMENT ON TABLE "t_user_role" IS '用户与角色关系表';

CREATE TABLE "t_resource" (
  "id" int8 NOT NULL,
  "type" int4,
  "parent_id" int8,
  "display_name" varchar(255),
  "url" varchar(255),
  PRIMARY KEY ("id")
);
CREATE INDEX "idx_parent_id" ON "t_resource" ("parent_id" ASC NULLS LAST);
COMMENT ON TABLE "t_resource" IS '权限资源表';
COMMENT ON COLUMN "t_resource"."type" IS '1-菜单,2-按钮,Link';

CREATE TABLE "t_role_resource" (
"role_id" int8 NOT NULL,
"resource_id" int8 NOT NULL,
PRIMARY KEY ("resource_id", "role_id")
);
COMMENT ON TABLE "t_role_resource" IS '角色与权限关系表';

CREATE TABLE "public"."t_shop" (
  "id" int8 NOT NULL,
  "address" jsonb,
  "create_time" timestamp(6),
  "created_by" int8,
  "introduction" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "thumbnails" jsonb,
  "update_time" timestamp(6),
  "updated_by" int8,
  "user_id" int8,
  CONSTRAINT "t_shop_pkey" PRIMARY KEY ("id")
)
;

CREATE TABLE "public"."t_express" (
  "id" varchar(255) NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  CONSTRAINT "t_express_pkey" PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."t_express"."id" IS ' id';
COMMENT ON COLUMN "public"."t_express"."name" IS '快递商名称';

CREATE TABLE "public"."t_express_client" (
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "config" varchar(2000) NOT NULL,
  CONSTRAINT "t_express_client_pkey" PRIMARY KEY ("name")
)
;


CREATE TABLE "public"."t_express_client_setting" (
  "client" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "express_id" varchar(255) NOT NULL,
  "express_config" varchar(2000),
  CONSTRAINT "t_express_client_setting_pkey" PRIMARY KEY ("client", "express_id")
)
;

COMMENT ON COLUMN "public"."t_express_client_setting"."client" IS '对应client的名称';

COMMENT ON COLUMN "public"."t_express_client_setting"."express_id" IS '快递物流商id';

COMMENT ON COLUMN "public"."t_express_client_setting"."express_config" IS '在client的配置';