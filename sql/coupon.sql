CREATE TABLE "public"."t_coupon" (
  "id" int8 NOT NULL,
  "amount" int4,
  "amount_use_limit" int4,
  "apply_to" jsonb,
  "create_time" timestamp(6),
  "created_by" int8,
  "days_after_receive" int4,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "end_time" timestamp(6),
  "start_time" timestamp(6),
  "status" int4,
  "superposition_use" bool,
  "update_time" timestamp(6),
  "updated_by" int8,
  "use_time_type" int4,
  CONSTRAINT "t_coupon_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_coupon" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_coupon_entry" (
  "id" int8 NOT NULL,
  "coupon_id" int8,
  "coupon_img" varchar(255) COLLATE "pg_catalog"."default",
  "end_time" timestamp(6),
  "start_time" timestamp(6),
  "stock" int4,
  "total" int4,
  "user_obtain_type" int4,
  CONSTRAINT "t_coupon_entry_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_coupon_entry" 
  OWNER TO "supermarket";

CREATE TABLE "public"."t_user_coupon" (
  "id" int8 NOT NULL,
  "coupons_id" int8,
  "create_time" timestamp(6),
  "status" int4,
  "update_time" timestamp(6),
  "user_id" int8,
  CONSTRAINT "t_user_coupon_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."t_user_coupon" 
  OWNER TO "supermarket";