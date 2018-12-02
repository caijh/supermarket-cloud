CREATE TABLE "public"."t_country" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "en_name" varchar(255) COLLATE "pg_catalog"."default",
  "full_en_name" varchar(255) COLLATE "pg_catalog"."default",
  "first_char" varchar(255) COLLATE "pg_catalog"."default",
  "abbreviation" varchar(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "t_country_pkey" PRIMARY KEY ("id")
);

COMMENT ON TABLE "public"."t_country" IS '国家';

CREATE TABLE "public"."t_area" (
  "code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_code" varchar(255) COLLATE "pg_catalog"."default",
  "country_id" int8 NOT NULL,
  CONSTRAINT "t_area_pkey" PRIMARY KEY ("code")
);

COMMENT ON COLUMN "public"."t_area"."code" IS '地区码';

COMMENT ON COLUMN "public"."t_area"."name" IS '名称';

COMMENT ON COLUMN "public"."t_area"."parent_code" IS '上一级地区';

COMMENT ON COLUMN "public"."t_area"."country_id" IS '所属国家id';


INSERT INTO "public"."t_country"("id", "abbreviation", "en_name", "first_char", "full_en_name", "name") VALUES (86, 'CN', 'China', 'C', '', '中国');

CREATE TABLE "public"."t_shop" (
  "id" int8 NOT NULL,
  "user_id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "address" jsonb,
  "thumbnails" jsonb,
  "introduction" text COLLATE "pg_catalog"."default",
  "status" int4 NOT NULL,
  "create_time" date NOT NULL,
  "created_by" int8,
  "update_time" date,
  "updated_by" int8,
  CONSTRAINT "t_shop_pkey" PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."t_shop"."status" IS '0-在用,1-删除';

COMMENT ON COLUMN "public"."t_shop"."user_id" IS '所属uid';