-- V27: Value Added Tax (VAT) module (reuses tax_returns' full status vocabulary)
CREATE TABLE vat_returns (
    id                  BIGSERIAL PRIMARY KEY,
    client_name         VARCHAR(150)  NOT NULL,
    vat_number          VARCHAR(20)   NOT NULL,
    scheme_type         VARCHAR(60)   NOT NULL,
    client_type         VARCHAR(60)   NOT NULL,
    period_start        DATE          NOT NULL,
    sales_ex_vat        NUMERIC(12,2),
    purchases_ex_vat    NUMERIC(12,2),
    vat_amount          NUMERIC(12,2),
    status              VARCHAR(30)   NOT NULL CHECK (status IN
                            ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED')),
    filed_on            DATE,
    assigned_to         VARCHAR(100)  NOT NULL,
    created_at          TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_vat_client ON vat_returns (client_name);
CREATE INDEX idx_vat_status ON vat_returns (status);
CREATE INDEX idx_vat_scheme ON vat_returns (scheme_type);
CREATE INDEX idx_vat_type ON vat_returns (client_type);
CREATE INDEX idx_vat_period ON vat_returns (period_start);
