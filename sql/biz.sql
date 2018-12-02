CREATE TABLE "public"."t_brand" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "logo" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "country_id" int8 NOT NULL,
  "status" int4 NOT NULL,
  CONSTRAINT "t_brand_pkey" PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."t_brand"."status" IS '0-在用,1-删除';