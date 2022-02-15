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
        id CHAR(100) UNIQUE NOT NULL,
        name CHAR(100),
        email CHAR(100),
        PRIMARY KEY(id)
        );

        CREATE TABLE IF NOT EXISTS itsd_user (
        id CHAR(100) UNIQUE NOT NULL,
        name CHAR(100),
        email CHAR(100),
        password CHAR(100),
        customer_id CHAR(100),
        role CHAR(100),
        PRIMARY KEY(id),
        CONSTRAINT customer_user_constraint
        FOREIGN KEY(customer_id)
        REFERENCES itsd_customer(id));

        CREATE TABLE IF NOT EXISTS request (
        id CHAR(100) UNIQUE NOT NULL,
        assignee_name CHAR(100),
        status CHAR(100),
        title CHAR(100),
        description CHAR(100),
        created_at CHAR(100),
        updated_at CHAR(100),
        user_id CHAR(100),
        PRIMARY KEY(id),
        CONSTRAINT user_request_constraint
        FOREIGN KEY(user_id)
        REFERENCES itsd_user(id));

        CREATE TABLE IF NOT EXISTS comment (
        id CHAR(100) UNIQUE NOT NULL,
        description CHAR(100),
        created_at CHAR(100),
        user_id CHAR(100),
        request_id CHAR(100),
        CONSTRAINT request_comment_constraint
        FOREIGN KEY(request_id)
        REFERENCES request(id));

        CREATE TABLE IF NOT EXISTS responder (
            id CHAR(100) UNIQUE NOT NULL,
            name CHAR(100),
            email CHAR(100),
            password CHAR(100),
            PRIMARY KEY(id));

  COMMIT;
EOSQL