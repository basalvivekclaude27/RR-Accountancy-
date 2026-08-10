-- V4: Seed Bookkeeping jobs — first 6 rows match Pics/02_BookKeeping.png exactly,
-- remaining 30 are generated filler so pagination/filtering has real data to work with.

INSERT INTO bookkeeping_jobs (client_name, invoice_ref, period_date, category, status, assigned_to, last_updated) VALUES
('ABC Solutions Ltd.',      'INV-1005', DATE '2025-07-01', 'MANAGEMENT_ACCOUNTS', 'IN_PROGRESS',    'Sarah Khan',   DATE '2025-08-08'),
('XYZ Retail Ltd.',         'INV-1004', DATE '2025-07-01', 'BOOKKEEPING',         'IN_PROGRESS',    'James Wright', DATE '2025-08-07'),
('123 Construction Ltd.',   'INV-1003', DATE '2025-06-01', 'VAT_RETURNS',         'PENDING_REVIEW', 'Priya Patel',  DATE '2025-08-06'),
('DEF Services Ltd.',       'INV-1002', DATE '2025-06-01', 'PAYROLL',             'COMPLETED',      'Tom Becker',   DATE '2025-08-05'),
('GHI Enterprises Ltd.',    'INV-1001', DATE '2025-05-01', 'BOOKKEEPING',         'COMPLETED',      'Aisha Noor',   DATE '2025-08-02'),
('JKL Consulting Ltd.',     'INV-1000', DATE '2025-05-01', 'MANAGEMENT_ACCOUNTS', 'PENDING_INFO',   'Sarah Khan',   DATE '2025-08-01');

INSERT INTO bookkeeping_jobs (client_name, invoice_ref, period_date, category, status, assigned_to, last_updated)
SELECT
    (ARRAY['ABC Solutions Ltd.', 'XYZ Retail Ltd.', '123 Construction Ltd.', 'DEF Services Ltd.',
           'GHI Enterprises Ltd.', 'JKL Consulting Ltd.', 'Bright Future Ltd.', 'Silver Oak Traders',
           'Northgate Logistics', 'Maple & Co.', 'Coastal Retail Group', 'Vantage Partners LLP'])[1 + (n % 12)],
    'INV-' || LPAD((999 - n)::text, 4, '0'),
    (DATE '2025-08-01' - ((n % 6) * INTERVAL '1 month'))::date,
    (ARRAY['BOOKKEEPING', 'MANAGEMENT_ACCOUNTS', 'VAT_RETURNS', 'PAYROLL'])[1 + (n % 4)],
    (ARRAY['IN_PROGRESS', 'PENDING_REVIEW', 'COMPLETED', 'PENDING_INFO'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-31' - (n || ' days')::interval)::date
FROM generate_series(1, 30) AS n;
