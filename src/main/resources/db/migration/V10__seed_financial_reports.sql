-- V10: Seed Financial & Management reports — first 6 rows match Pics/05_FinanceManagementAccount.png
-- exactly, remaining 72 are generated filler so pagination has real data to work with (total 78).

INSERT INTO financial_reports (client_name, report_type, period_date, financial_year_start, status, generated_on) VALUES
('ABC Solutions Ltd.',      'MANAGEMENT_ACCOUNTS',    DATE '2025-07-01', 2024, 'COMPLETED',      DATE '2025-08-08'),
('XYZ Retail Ltd.',         'FINANCIAL_STATEMENTS',   DATE '2025-07-01', 2024, 'IN_PROGRESS',    DATE '2025-08-07'),
('123 Construction Ltd.',   'MANAGEMENT_ACCOUNTS',    DATE '2025-07-01', 2024, 'PENDING_REVIEW', DATE '2025-08-06'),
('DEF Services Ltd.',       'CASH_FLOW_STATEMENT',    DATE '2025-06-01', 2024, 'COMPLETED',      DATE '2025-08-05'),
('GHI Enterprises Ltd.',    'PROFIT_LOSS_STATEMENT',  DATE '2025-06-01', 2024, 'COMPLETED',      DATE '2025-08-02'),
('JKL Consulting Ltd.',     'BALANCE_SHEET',          DATE '2025-06-01', 2024, 'DRAFT',          DATE '2025-07-31');

INSERT INTO financial_reports (client_name, report_type, period_date, financial_year_start, status, generated_on)
SELECT
    (ARRAY['ABC Solutions Ltd.', 'XYZ Retail Ltd.', '123 Construction Ltd.', 'DEF Services Ltd.',
           'GHI Enterprises Ltd.', 'JKL Consulting Ltd.', 'Bright Future Ltd.', 'Silver Oak Traders',
           'Northgate Logistics', 'Maple & Co.', 'Coastal Retail Group', 'Vantage Partners LLP'])[1 + (n % 12)],
    (ARRAY['MANAGEMENT_ACCOUNTS', 'FINANCIAL_STATEMENTS', 'CASH_FLOW_STATEMENT',
           'PROFIT_LOSS_STATEMENT', 'BALANCE_SHEET'])[1 + (n % 5)],
    (DATE '2025-07-01' - ((n % 12) * INTERVAL '1 month'))::date,
    2023 + (n % 2),
    (ARRAY['IN_PROGRESS', 'PENDING_REVIEW', 'DRAFT', 'COMPLETED'])[1 + (n % 4)],
    (DATE '2025-07-30' - (n || ' days')::interval)::date
FROM generate_series(1, 72) AS n;
