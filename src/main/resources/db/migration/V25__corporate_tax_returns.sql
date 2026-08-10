-- V25: Corporate Tax module (reuses tax_returns' status vocabulary, minus REFUND_ISSUED
-- which doesn't apply to corporation tax)
CREATE TABLE corporate_tax_returns (
    id                          BIGSERIAL PRIMARY KEY,
    client_name                 VARCHAR(150)  NOT NULL,
    utr                         VARCHAR(10)   NOT NULL,
    client_type                 VARCHAR(60)   NOT NULL,
    industry                    VARCHAR(100)  NOT NULL,
    accounting_period_start     DATE          NOT NULL,
    accounting_period_end       DATE          NOT NULL,
    taxable_profit              NUMERIC(12,2) NOT NULL,
    tax_payable                 NUMERIC(12,2) NOT NULL,
    status                      VARCHAR(30)   NOT NULL CHECK (status IN
                                    ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED')),
    due_date                    DATE          NOT NULL,
    assigned_to                 VARCHAR(100)  NOT NULL,
    created_at                  TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at                  TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_corptax_client ON corporate_tax_returns (client_name);
CREATE INDEX idx_corptax_status ON corporate_tax_returns (status);
CREATE INDEX idx_corptax_type ON corporate_tax_returns (client_type);
CREATE INDEX idx_corptax_industry ON corporate_tax_returns (industry);
CREATE INDEX idx_corptax_due ON corporate_tax_returns (due_date);
