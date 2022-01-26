#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;
    CREATE TABLE IF NOT EXISTS request (
    id CHAR(100) UNIQUE NOT NULL PRIMARY KEY,
    assignee_name CHAR(100),
    status CHAR(100),
    title CHAR(100),
    description CHAR(100),
    created_at CHAR(100),
    updated_at CHAR(100),
    customer CHAR(100));

    CREATE TABLE IF NOT EXISTS customer (
    id CHAR(100) UNIQUE NOT NULL PRIMARY KEY,
    name CHAR(100),
    email CHAR(100)
    );
  COMMIT;
EOSQL