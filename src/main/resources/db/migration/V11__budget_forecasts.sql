-- V11: Budgeting & Forecasting module — budgets/forecasts table
CREATE TABLE budget_forecasts (
    id                     BIGSERIAL PRIMARY KEY,
    client_name            VARCHAR(150)  NOT NULL,
    type                   VARCHAR(30)   NOT NULL CHECK (type IN ('ANNUAL_BUDGET', 'FORECAST', 'BUDGET_REVISION')),
    financial_year_start   INT           NOT NULL,
    period                 VARCHAR(10)   NOT NULL CHECK (period IN ('YEARLY', 'Q1', 'Q2', 'Q3', 'Q4')),
    budgeted_amount        NUMERIC(14,2) NOT NULL,
    status                 VARCHAR(30)   NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING_REVIEW', 'APPROVED', 'COMPLETED')),
    last_updated           DATE          NOT NULL,
    created_at             TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at             TIMESTAMP     NOT NULL DEFAULT now()
);

CREATE INDEX idx_budget_client ON budget_forecasts (client_name);
CREATE INDEX idx_budget_status ON budget_forecasts (status);
CREATE INDEX idx_budget_type ON budget_forecasts (type);
CREATE INDEX idx_budget_fy ON budget_forecasts (financial_year_start);
CREATE INDEX idx_budget_period ON budget_forecasts (period);
