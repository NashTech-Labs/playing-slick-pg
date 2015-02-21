# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "COMPANY" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR(254) NOT NULL,"created" timestamp NOT NULL,"updated" timestamp NOT NULL);

# --- !Downs

drop table "COMPANY";

