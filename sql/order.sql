CREATE TABLE "public"."t_order" (
  "id" int8 NOT NULL,
  "amount" int4,
  "create_time" timestamp(6),
  "no" varchar(255) COLLATE "pg_catalog"."default",
  "pay_amount" int4,
  "status" int4,
  "update_time" timestamp(6),
  "user_id" int8,
  CONSTRAINT "t_order_pkey" PRIMARY KEY ("id"),
  CONSTRAINT "uk_f9xeho5maithd49rewa74mq8l" UNIQUE ("no")
)
;

ALTER TABLE "public"."t_order" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_order_product_sku" (
  "id" int8 NOT NULL,
  "order_id" int8,
  "product" jsonb,
  "product_sku" jsonb,
  CONSTRAINT "t_order_product_sku_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_order_product_sku" 
  OWNER TO "supermarket";