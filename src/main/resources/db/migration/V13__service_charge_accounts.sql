-- V13: Service Charge Accounts module
CREATE TABLE service_charge_accounts (
    id                     BIGSERIAL PRIMARY KEY,
    property_name          VARCHAR(150)  NOT NULL,
    property_ref           VARCHAR(30)   NOT NULL,
    units                  INT           NOT NULL,
    financial_year_start   INT           NOT NULL,
    period_date            DATE          NOT NULL,
    budgeted_amount        NUMERIC(12,2) NOT NULL,
    collected_amount       NUMERIC(12,2) NOT NULL,
    status                 VARCHAR(20)   NOT NULL CHECK (status IN ('ON_TRACK', 'ATTENTION', 'BEHIND')),
    last_updated           DATE          NOT NULL,
    created_at             TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at             TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_svcchg_property ON service_charge_accounts (property_name);
CREATE INDEX idx_svcchg_status ON service_charge_accounts (status);
CREATE INDEX idx_svcchg_fy ON service_charge_accounts (financial_year_start);
CREATE INDEX idx_svcchg_period ON service_charge_accounts (period_date);
