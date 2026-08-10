-- V21: Personal Income Tax module
CREATE TABLE tax_returns (
    id              BIGSERIAL PRIMARY KEY,
    client_name     VARCHAR(150)  NOT NULL,
    utr             VARCHAR(10)   NOT NULL,
    client_type     VARCHAR(60)   NOT NULL,
    tax_year_start  INT           NOT NULL,
    status          VARCHAR(30)   NOT NULL CHECK (status IN
                        ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED')),
    refund_amount   NUMERIC(12,2),
    assigned_to     VARCHAR(100)  NOT NULL,
    last_updated    DATE          NOT NULL,
    created_at      TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_taxret_client ON tax_returns (client_name);
CREATE INDEX idx_taxret_status ON tax_returns (status);
CREATE INDEX idx_taxret_type ON tax_returns (client_type);
CREATE INDEX idx_taxret_year ON tax_returns (tax_year_start);
CREATE INDEX idx_taxret_utr ON tax_returns (utr);
