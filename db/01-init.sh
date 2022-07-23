#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;
        CREATE TABLE IF NOT EXISTS itsd_customer (
        id VARCHAR(100) UNIQUE NOT NULL,
        name VARCHAR(100),
        email VARCHAR(100),
        PRIMARY KEY(id)
        );

        CREATE TABLE IF NOT EXISTS itsd_user (
        id VARCHAR(100) UNIQUE NOT NULL,
        name VARCHAR(100),
        email VARCHAR(100),
        password VARCHAR(100),
        customer_id VARCHAR(100),
        role VARCHAR(100),
        PRIMARY KEY(id),
        CONSTRAINT customer_user_constraint
        FOREIGN KEY(customer_id)
        REFERENCES itsd_customer(id));

        CREATE TABLE IF NOT EXISTS request (
        id CHAR(100) UNIQUE NOT NULL,
        assignee_name VARCHAR(100),
        status VARCHAR(100),
        title VARCHAR(100),
        description VARCHAR(100),
        created_at VARCHAR(100),
        updated_at VARCHAR(100),
        user_id VARCHAR(100),
        PRIMARY KEY(id),
        CONSTRAINT user_request_constraint
        FOREIGN KEY(user_id)
        REFERENCES itsd_user(id));

        CREATE TABLE IF NOT EXISTS comment (
        id VARCHAR(100) UNIQUE NOT NULL,
        description VARCHAR(100),
        created_at VARCHAR(100),
        user_name VARCHAR(100),
        request_id VARCHAR(100),
        CONSTRAINT request_comment_constraint
        FOREIGN KEY(request_id)
        REFERENCES request(id));

        CREATE TABLE IF NOT EXISTS responder (
            id VARCHAR(100) UNIQUE NOT NULL,
            name VARCHAR(100),
            email VARCHAR(100),
            password VARCHAR(100),
            PRIMARY KEY(id));

  COMMIT;
EOSQL