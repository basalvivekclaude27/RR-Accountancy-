-- V19: Business Plans module
CREATE TABLE business_plans (
    id                  BIGSERIAL PRIMARY KEY,
    client_name         VARCHAR(150)  NOT NULL,
    plan_ref            VARCHAR(30)   NOT NULL,
    plan_name           VARCHAR(150)  NOT NULL,
    plan_type           VARCHAR(100)  NOT NULL,
    industry            VARCHAR(100)  NOT NULL,
    status              VARCHAR(30)   NOT NULL CHECK (status IN
                            ('NOT_STARTED', 'IN_PROGRESS', 'PENDING_REVIEW', 'ON_HOLD', 'COMPLETED')),
    revenue_potential   NUMERIC(14,2) NOT NULL,
    last_updated        DATE          NOT NULL,
    created_at          TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_bizplan_client ON business_plans (client_name);
CREATE INDEX idx_bizplan_status ON business_plans (status);
CREATE INDEX idx_bizplan_type ON business_plans (plan_type);
CREATE INDEX idx_bizplan_industry ON business_plans (industry);
CREATE INDEX idx_bizplan_date ON business_plans (last_updated);
