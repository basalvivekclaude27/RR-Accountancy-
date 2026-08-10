-- V7: Payroll Advice & Service module — payroll runs table
CREATE TABLE payroll_runs (
    id              BIGSERIAL PRIMARY KEY,
    client_name     VARCHAR(150)   NOT NULL,
    payroll_month   DATE           NOT NULL,
    frequency       VARCHAR(30)    NOT NULL CHECK (frequency IN ('WEEKLY', 'FORTNIGHTLY', 'FOUR_WEEKLY', 'MONTHLY')),
    employees       INT            NOT NULL,
    net_pay         NUMERIC(12,2)  NOT NULL,
    status          VARCHAR(30)    NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING_APPROVAL', 'COMPLETED', 'PAID')),
    assigned_to     VARCHAR(100)   NOT NULL,
    last_updated    DATE           NOT NULL,
    created_at      TIMESTAMP      NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP      NOT NULL DEFAULT now()
);

CREATE INDEX idx_payroll_client ON payroll_runs (client_name);
CREATE INDEX idx_payroll_status ON payroll_runs (status);
CREATE INDEX idx_payroll_frequency ON payroll_runs (frequency);
CREATE INDEX idx_payroll_assigned ON payroll_runs (assigned_to);
CREATE INDEX idx_payroll_month ON payroll_runs (payroll_month);
