--------------------------------------------------------
--  File created - Saturday-September-03-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PRODUCT" 
   (	"PRODUCT_ID" NUMBER, 
	"Product_Category" VARCHAR2(32 CHAR), 
	"PRODUCT_DESCRIPTION" VARCHAR2(32 CHAR), 
	"PRODUCT_NAME" VARCHAR2(20 CHAR), 
	"PRODUCT_STOCK" NUMBER
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index PRODUCT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."PRODUCT_PK" ON "SYSTEM"."PRODUCT" ("PRODUCT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PRODUCT" ADD CONSTRAINT "CHK_STOCK" CHECK (PRODUCT_STOCK >= 0) ENABLE;
  ALTER TABLE "SYSTEM"."PRODUCT" MODIFY ("PRODUCT_ID" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."PRODUCT" ADD CONSTRAINT "PRODUCT_PK" PRIMARY KEY ("PRODUCT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  
--------------------------------------------------------
--  DDL for Sequence PRODUCT_ID_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "SYSTEM"."PRODUCT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
