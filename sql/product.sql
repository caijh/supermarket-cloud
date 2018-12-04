CREATE TABLE "public"."t_brand" (
  "id" int8 NOT NULL,
  "country_id" int8,
  "create_time" timestamp(6),
  "created_by" int8,
  "logo" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "update_time" timestamp(6),
  "updated_by" int8,
  CONSTRAINT "t_brand_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_brand" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product" (
  "id" int8 NOT NULL,
  "brand_id" int8,
  "briefs" varchar(255) COLLATE "pg_catalog"."default",
  "category_id" int8,
  "create_time" timestamp(6),
  "created_by" int8,
  "description" text COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "shop_id" int8,
  "status" int4,
  "tax_rate" float4,
  "thumbnails" jsonb,
  "update_time" timestamp(6),
  "updated_by" int8,
  CONSTRAINT "t_product_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_product" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product_attr_label" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "label" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "t_product_attr_label_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_product_attr_label" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product_category" (
  "id" int8 NOT NULL,
  "create_time" timestamp(6),
  "created_by" int8,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "parent_id" int8,
  "status" int4,
  "update_time" timestamp(6),
  "updated_by" int8,
  "weight" int4,
  CONSTRAINT "t_product_category_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_product_category" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product_favorite" (
  "id" int8 NOT NULL,
  "create_time" timestamp(6),
  "product_id" int8,
  "user_id" int8,
  CONSTRAINT "t_product_favorite_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_product_favorite" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product_sku" (
  "id" int8 NOT NULL,
  "barcode" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "created_by" int8,
  "origin" jsonb,
  "price" int4,
  "product_id" int8,
  "refer_price" int4,
  "sku_attrs" jsonb,
  "status" int4,
  "thumbnail" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "updated_by" int8,
  CONSTRAINT "t_product_sku_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_product_sku" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_product_sku_ext" (
  "product_sku_id" int8 NOT NULL,
  "frozen_num" int4,
  "sold_num" int4,
  "stock_num" int4,
  CONSTRAINT "t_product_sku_ext_pkey" PRIMARY KEY ("product_sku_id")
)
;

ALTER TABLE "public"."t_product_sku_ext" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_shopping_cart_sku" (
  "id" int8 NOT NULL,
  "create_time" timestamp(6),
  "num" int4,
  "product_sku_id" int8,
  "update_time" timestamp(6),
  "user_id" int8,
  CONSTRAINT "t_shopping_cart_sku_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_shopping_cart_sku" 
  OWNER TO "supermarket";