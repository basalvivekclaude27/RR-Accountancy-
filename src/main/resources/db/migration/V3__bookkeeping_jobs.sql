-- V3: Bookkeeping module — jobs table
CREATE TABLE bookkeeping_jobs (
    id              BIGSERIAL PRIMARY KEY,
    client_name     VARCHAR(150) NOT NULL,
    invoice_ref     VARCHAR(30)  NOT NULL,
    period_date     DATE         NOT NULL,
    category        VARCHAR(30)  NOT NULL CHECK (category IN ('BOOKKEEPING', 'MANAGEMENT_ACCOUNTS', 'VAT_RETURNS', 'PAYROLL')),
    status          VARCHAR(30)  NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING_REVIEW', 'COMPLETED', 'PENDING_INFO')),
    assigned_to     VARCHAR(100) NOT NULL,
    last_updated    DATE         NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_bkjobs_client ON bookkeeping_jobs (client_name);
CREATE INDEX idx_bkjobs_status ON bookkeeping_jobs (status);
CREATE INDEX idx_bkjobs_category ON bookkeeping_jobs (category);
CREATE INDEX idx_bkjobs_assigned ON bookkeeping_jobs (assigned_to);
CREATE INDEX idx_bkjobs_period ON bookkeeping_jobs (period_date);
