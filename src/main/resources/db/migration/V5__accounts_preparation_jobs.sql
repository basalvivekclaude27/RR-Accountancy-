-- V5: Accounts Preparation module — jobs table
CREATE TABLE accounts_preparation_jobs (
    id                     BIGSERIAL PRIMARY KEY,
    client_name            VARCHAR(150) NOT NULL,
    job_ref                VARCHAR(30)  NOT NULL,
    financial_year_start   INT          NOT NULL,
    account_type           VARCHAR(30)  NOT NULL CHECK (account_type IN ('STATUTORY_ACCOUNTS', 'MANAGEMENT_ACCOUNTS')),
    status                 VARCHAR(30)  NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING_REVIEW', 'DRAFT', 'COMPLETED')),
    assigned_to            VARCHAR(100) NOT NULL,
    last_updated           DATE         NOT NULL,
    created_at             TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at             TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_apjobs_client ON accounts_preparation_jobs (client_name);
CREATE INDEX idx_apjobs_status ON accounts_preparation_jobs (status);
CREATE INDEX idx_apjobs_type ON accounts_preparation_jobs (account_type);
CREATE INDEX idx_apjobs_assigned ON accounts_preparation_jobs (assigned_to);
CREATE INDEX idx_apjobs_fy ON accounts_preparation_jobs (financial_year_start);
