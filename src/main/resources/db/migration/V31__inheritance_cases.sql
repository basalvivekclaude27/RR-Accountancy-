-- V31: Inheritance Tax module (reuses tax_returns' status vocabulary, restricted to the
-- 4 values that apply to IHT case tracking — no REFUND_ISSUED)
CREATE TABLE inheritance_cases (
    id                        BIGSERIAL PRIMARY KEY,
    client_name               VARCHAR(150)  NOT NULL,
    utr                       VARCHAR(10)   NOT NULL,
    client_type               VARCHAR(60)   NOT NULL,
    planning_type             VARCHAR(60)   NOT NULL,
    tax_year_start            INT           NOT NULL,
    estate_value              NUMERIC(14,2) NOT NULL,
    nil_rate_band_available   NUMERIC(12,2) NOT NULL,
    tax_liability             NUMERIC(12,2) NOT NULL,
    status                    VARCHAR(30)   NOT NULL CHECK (status IN
                                  ('PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED')),
    assigned_to               VARCHAR(100)  NOT NULL,
    last_updated              DATE          NOT NULL,
    created_at                TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at                TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_iht_client ON inheritance_cases (client_name);
CREATE INDEX idx_iht_status ON inheritance_cases (status);
CREATE INDEX idx_iht_type ON inheritance_cases (client_type);
CREATE INDEX idx_iht_planning ON inheritance_cases (planning_type);
CREATE INDEX idx_iht_year ON inheritance_cases (tax_year_start);
