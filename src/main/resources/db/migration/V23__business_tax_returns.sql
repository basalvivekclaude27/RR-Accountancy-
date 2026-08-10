-- V23: Business Income Tax module (reuses tax_returns' status vocabulary: PROCESSING/
-- DOCUMENTS_PENDING/IN_REVIEW/FILED/REFUND_ISSUED)
CREATE TABLE business_tax_returns (
    id                  BIGSERIAL PRIMARY KEY,
    client_name         VARCHAR(150)  NOT NULL,
    utr                 VARCHAR(10)   NOT NULL,
    business_type       VARCHAR(60)   NOT NULL,
    industry            VARCHAR(100)  NOT NULL,
    tax_year_start      INT           NOT NULL,
    profit_before_tax   NUMERIC(12,2) NOT NULL,
    tax_payable         NUMERIC(12,2) NOT NULL,
    status              VARCHAR(30)   NOT NULL CHECK (status IN
                            ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED')),
    assigned_to         VARCHAR(100)  NOT NULL,
    last_updated        DATE          NOT NULL,
    created_at          TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_biztax_client ON business_tax_returns (client_name);
CREATE INDEX idx_biztax_status ON business_tax_returns (status);
CREATE INDEX idx_biztax_type ON business_tax_returns (business_type);
CREATE INDEX idx_biztax_industry ON business_tax_returns (industry);
CREATE INDEX idx_biztax_year ON business_tax_returns (tax_year_start);
