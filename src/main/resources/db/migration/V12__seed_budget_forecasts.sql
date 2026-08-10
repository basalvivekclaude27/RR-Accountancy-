-- V12: Seed Budgets & Forecasts — first 6 rows match Pics/06_BudgetForecasting.png exactly,
-- remaining 18 are generated filler so pagination has real data to work with (total 24).

INSERT INTO budget_forecasts (client_name, type, financial_year_start, period, budgeted_amount, status, last_updated) VALUES
('ABC Solutions Ltd.',      'ANNUAL_BUDGET',   2024, 'YEARLY', 245000.00, 'APPROVED',       DATE '2025-08-08'),
('XYZ Retail Ltd.',         'FORECAST',        2024, 'Q2',     180500.00, 'IN_PROGRESS',    DATE '2025-08-07'),
('123 Construction Ltd.',   'ANNUAL_BUDGET',   2024, 'YEARLY', 320000.00, 'PENDING_REVIEW', DATE '2025-08-06'),
('DEF Services Ltd.',       'FORECAST',        2024, 'Q3',     150000.00, 'IN_PROGRESS',    DATE '2025-08-05'),
('GHI Enterprises Ltd.',    'BUDGET_REVISION', 2024, 'Q2',     210300.00, 'APPROVED',       DATE '2025-08-02'),
('JKL Consulting Ltd.',     'FORECAST',        2024, 'Q1',     148000.00, 'COMPLETED',      DATE '2025-07-31');

INSERT INTO budget_forecasts (client_name, type, financial_year_start, period, budgeted_amount, status, last_updated)
SELECT
    (ARRAY['ABC Solutions Ltd.', 'XYZ Retail Ltd.', '123 Construction Ltd.', 'DEF Services Ltd.',
           'GHI Enterprises Ltd.', 'JKL Consulting Ltd.', 'Bright Future Ltd.', 'Silver Oak Traders',
           'Northgate Logistics', 'Maple & Co.', 'Coastal Retail Group', 'Vantage Partners LLP'])[1 + (n % 12)],
    (ARRAY['ANNUAL_BUDGET', 'FORECAST', 'BUDGET_REVISION'])[1 + (n % 3)],
    2023 + (n % 2),
    (ARRAY['YEARLY', 'Q1', 'Q2', 'Q3', 'Q4'])[1 + (n % 5)],
    (80000 + (n * 5137) % 250000)::numeric(14,2),
    (ARRAY['IN_PROGRESS', 'PENDING_REVIEW', 'APPROVED', 'COMPLETED'])[1 + (n % 4)],
    (DATE '2025-07-30' - (n || ' days')::interval)::date
FROM generate_series(1, 18) AS n;
