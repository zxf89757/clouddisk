CREATE TABLE  "userinfo" 
   (	"user_id" NUMBER(6,0), 
	"user_loginname" VARCHAR2(30), 
	"user_pwd" VARCHAR2(30), 
	"user_email" VARCHAR2(30), 
	"user_name" NVARCHAR2(10), 
	"user_age" NUMBER(3,0), 
	"user_gender" NVARCHAR2(1), 
	"user_createdata" NVARCHAR2(20), 
	"user_pubusedsize" NUMBER(6,3) DEFAULT 0, 
	"user_perusedsize" NUMBER(6,3) DEFAULT 0, 
	 CONSTRAINT "USERINFO_CON" PRIMARY KEY ("user_id") ENABLE
   )
/
CREATE TABLE  "folder" 
   (	"folder_id" NUMBER(8,0), 
	"parent_id" NUMBER(8,0), 
	"folder_name" VARCHAR2(100), 
	"folder_data" NVARCHAR2(20), 
	"islock" NUMBER(1,0) DEFAULT 1, 
	"user_id" NUMBER(6,0), 
	 CONSTRAINT "FOLDER_CON" PRIMARY KEY ("folder_id") ENABLE, 
	 CONSTRAINT "FOLDER_CON1" FOREIGN KEY ("parent_id")
	  REFERENCES  "folder" ("folder_id") ON DELETE CASCADE ENABLE, 
	 CONSTRAINT "FOLDER_CON2" FOREIGN KEY ("user_id")
	  REFERENCES  "userinfo" ("user_id") ON DELETE CASCADE ENABLE
   )
/
CREATE TABLE  "resourse" 
   (	"res_id" NUMBER(10,0), 
	"res_userid" NUMBER(6,0), 
	"res_name" VARCHAR2(100), 
	"res_typeid" NUMBER(2,0), 
	"res_url" VARCHAR2(200), 
	"res_createdata" NVARCHAR2(20), 
	"res_describe" VARCHAR2(500), 
	"res_size" NUMBER(6,3) NOT NULL ENABLE, 
	"res_savedcount" NUMBER(6,0) DEFAULT 1, 
	"res_share" NUMBER(1,0) DEFAULT 0, 
	 CONSTRAINT "RESOURSE_CON" PRIMARY KEY ("res_id") ENABLE, 
	 CONSTRAINT "RESOURSE_CON1" FOREIGN KEY ("res_userid")
	  REFERENCES  "userinfo" ("user_id") ENABLE
   )
/
CREATE TABLE  "type" 
   (	"res_typeid" NUMBER(2,0), 
	"type" VARCHAR2(20), 
	 CONSTRAINT "TYPE_CON" PRIMARY KEY ("res_typeid") ENABLE
   )
/
CREATE TABLE  "userres" 
   (	"res_id" NUMBER(10,0), 
	"parent_id" NUMBER(8,0), 
	"userres_name" VARCHAR2(100), 
	"userres_updatetime" NVARCHAR2(20), 
	"islock" NUMBER(1,0) DEFAULT 0, 
	 CONSTRAINT "USERRES_CON" PRIMARY KEY ("res_id", "parent_id") ENABLE, 
	 CONSTRAINT "USERRES_CON1" FOREIGN KEY ("res_id")
	  REFERENCES  "resourse" ("res_id") ON DELETE CASCADE ENABLE, 
	 CONSTRAINT "USERRES_CON2" FOREIGN KEY ("parent_id")
	  REFERENCES  "folder" ("folder_id") ON DELETE CASCADE ENABLE
   )
/
CREATE UNIQUE INDEX  "USERRES_CON" ON  "userres" ("res_id", "parent_id")
/
CREATE UNIQUE INDEX  "USERINFO_CON" ON  "userinfo" ("user_id")
/
CREATE UNIQUE INDEX  "TYPE_CON" ON  "type" ("res_typeid")
/
CREATE UNIQUE INDEX  "RESOURSE_CON" ON  "resourse" ("res_id")
/
CREATE UNIQUE INDEX  "FOLDER_CON" ON  "folder" ("folder_id")
/
 CREATE SEQUENCE   "user_id_seq"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 10020 CACHE 20 NOORDER  NOCYCLE
/
 CREATE SEQUENCE   "res_typeid_seq"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
 CREATE SEQUENCE   "res_id_seq"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 10040 CACHE 20 NOORDER  NOCYCLE
/
 CREATE SEQUENCE   "folder_id_seq"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 10114 CACHE 20 NOORDER  NOCYCLE
/
CREATE OR REPLACE FORCE VIEW  "userres_info" ("res_id", "parent_id", "userres_name", "userres_updatetime", "islock", "res_userid", "res_name", "res_typeid", "res_url", "res_createdata", "res_describe", "res_size", "res_savedcount", "res_share") AS 
  select 
"e"."res_id",
"e"."parent_id",
"e"."userres_name",
"e"."userres_updatetime",
"e"."islock",
"d"."res_userid",
"d"."res_name",
"d"."res_typeid",
"d"."res_url",
"d"."res_createdata",
"d"."res_describe",
"d"."res_size",
"d"."res_savedcount",
"d"."res_share"
from "userres" "e" join "resourse" "d"
ON "e"."res_id"="d"."res_id"
/