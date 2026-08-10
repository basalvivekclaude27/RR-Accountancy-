-- V29: Capital Gain Tax module (reuses tax_returns' status vocabulary, restricted to the
-- 4 values that apply to CGT case tracking — no REFUND_ISSUED)
CREATE TABLE capital_gain_returns (
    id                 BIGSERIAL PRIMARY KEY,
    client_name        VARCHAR(150)  NOT NULL,
    utr                VARCHAR(10)   NOT NULL,
    client_type        VARCHAR(60)   NOT NULL,
    asset_type         VARCHAR(60)   NOT NULL,
    tax_year_start     INT           NOT NULL,
    date_of_disposal   DATE          NOT NULL,
    gain_or_loss       NUMERIC(12,2) NOT NULL,
    tax_payable        NUMERIC(12,2) NOT NULL,
    status              VARCHAR(30)   NOT NULL CHECK (status IN
                            ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED')),
    assigned_to        VARCHAR(100)  NOT NULL,
    last_updated       DATE          NOT NULL,
    created_at         TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_cgt_client ON capital_gain_returns (client_name);
CREATE INDEX idx_cgt_status ON capital_gain_returns (status);
CREATE INDEX idx_cgt_type ON capital_gain_returns (client_type);
CREATE INDEX idx_cgt_asset ON capital_gain_returns (asset_type);
CREATE INDEX idx_cgt_year ON capital_gain_returns (tax_year_start);
