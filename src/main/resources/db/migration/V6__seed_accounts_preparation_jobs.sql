-- V6: Seed Accounts Preparation jobs — first 6 rows match Pics/03_AccountPreparation.png exactly,
-- remaining 36 are generated filler so pagination/filtering has real data to work with (total 42).

INSERT INTO accounts_preparation_jobs (client_name, job_ref, financial_year_start, account_type, status, assigned_to, last_updated) VALUES
('ABC Solutions Ltd.',      'ACC-1006', 2024, 'STATUTORY_ACCOUNTS',   'IN_PROGRESS',    'Sarah Khan',   DATE '2025-08-08'),
('XYZ Retail Ltd.',         'ACC-1005', 2024, 'MANAGEMENT_ACCOUNTS',  'IN_PROGRESS',    'James Wright', DATE '2025-08-07'),
('123 Construction Ltd.',   'ACC-1004', 2024, 'STATUTORY_ACCOUNTS',   'PENDING_REVIEW', 'Priya Patel',  DATE '2025-08-06'),
('DEF Services Ltd.',       'ACC-1003', 2024, 'MANAGEMENT_ACCOUNTS',  'DRAFT',          'Tom Becker',   DATE '2025-08-05'),
('GHI Enterprises Ltd.',    'ACC-1002', 2023, 'STATUTORY_ACCOUNTS',   'COMPLETED',      'Aisha Noor',   DATE '2025-08-02'),
('JKL Consulting Ltd.',     'ACC-1001', 2023, 'MANAGEMENT_ACCOUNTS',  'COMPLETED',      'Sarah Khan',   DATE '2025-07-31');

INSERT INTO accounts_preparation_jobs (client_name, job_ref, financial_year_start, account_type, status, assigned_to, last_updated)
SELECT
    (ARRAY['ABC Solutions Ltd.', 'XYZ Retail Ltd.', '123 Construction Ltd.', 'DEF Services Ltd.',
           'GHI Enterprises Ltd.', 'JKL Consulting Ltd.', 'Bright Future Ltd.', 'Silver Oak Traders',
           'Northgate Logistics', 'Maple & Co.', 'Coastal Retail Group', 'Vantage Partners LLP'])[1 + (n % 12)],
    'ACC-' || LPAD((999 - n)::text, 4, '0'),
    2022 + (n % 3),
    (ARRAY['STATUTORY_ACCOUNTS', 'MANAGEMENT_ACCOUNTS'])[1 + (n % 2)],
    (ARRAY['IN_PROGRESS', 'PENDING_REVIEW', 'DRAFT', 'COMPLETED'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-30' - (n || ' days')::interval)::date
FROM generate_series(1, 36) AS n;
