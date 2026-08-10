-- V9: Financial & Management Accounts module — reports table
CREATE TABLE financial_reports (
    id                     BIGSERIAL PRIMARY KEY,
    client_name            VARCHAR(150) NOT NULL,
    report_type            VARCHAR(30)  NOT NULL CHECK (report_type IN
                                ('MANAGEMENT_ACCOUNTS', 'FINANCIAL_STATEMENTS', 'CASH_FLOW_STATEMENT',
                                 'PROFIT_LOSS_STATEMENT', 'BALANCE_SHEET')),
    period_date            DATE         NOT NULL,
    financial_year_start   INT          NOT NULL,
    status                 VARCHAR(30)  NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING_REVIEW', 'DRAFT', 'COMPLETED')),
    generated_on           DATE         NOT NULL,
    created_at             TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at             TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_finrep_client ON financial_reports (client_name);
CREATE INDEX idx_finrep_status ON financial_reports (status);
CREATE INDEX idx_finrep_type ON financial_reports (report_type);
CREATE INDEX idx_finrep_fy ON financial_reports (financial_year_start);
CREATE INDEX idx_finrep_period ON financial_reports (period_date);
