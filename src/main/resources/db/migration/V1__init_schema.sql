-- V1: Initial schema for R & R Accountancy Services
CREATE TABLE users (
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(100)  NOT NULL UNIQUE,
    email           VARCHAR(150)  NOT NULL UNIQUE,
    password        VARCHAR(100)  NOT NULL,
    full_name       VARCHAR(150)  NOT NULL,
    role            VARCHAR(20)   NOT NULL CHECK (role IN ('ROLE_ADMIN', 'ROLE_CLIENT')),
    enabled         BOOLEAN       NOT NULL DEFAULT TRUE,
    account_non_locked BOOLEAN    NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_role ON users (role);
