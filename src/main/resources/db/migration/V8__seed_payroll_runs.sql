-- V8: Seed Payroll runs — first 6 rows match Pics/04_PayrollAdviceNServices.png exactly,
-- remaining 6 are generated filler so pagination has a real second page (total 12).

INSERT INTO payroll_runs (client_name, payroll_month, frequency, employees, net_pay, status, assigned_to, last_updated) VALUES
('ABC Solutions Ltd.',      DATE '2025-07-01', 'MONTHLY', 18, 8450.00,  'COMPLETED',        'Sarah Khan',   DATE '2025-08-08'),
('XYZ Retail Ltd.',         DATE '2025-07-01', 'MONTHLY', 22, 10850.00, 'IN_PROGRESS',      'James Wright', DATE '2025-08-07'),
('123 Construction Ltd.',   DATE '2025-07-01', 'MONTHLY', 15, 6230.00,  'PENDING_APPROVAL', 'Priya Patel',  DATE '2025-08-06'),
('DEF Services Ltd.',       DATE '2025-06-01', 'MONTHLY', 12, 5120.00,  'COMPLETED',        'Tom Becker',   DATE '2025-07-05'),
('GHI Enterprises Ltd.',    DATE '2025-06-01', 'MONTHLY', 8,  3840.00,  'COMPLETED',        'Aisha Noor',   DATE '2025-07-02'),
('JKL Consulting Ltd.',     DATE '2025-06-01', 'MONTHLY', 9,  4050.00,  'PAID',             'Sarah Khan',   DATE '2025-06-30');

INSERT INTO payroll_runs (client_name, payroll_month, frequency, employees, net_pay, status, assigned_to, last_updated)
SELECT
    (ARRAY['Bright Future Ltd.', 'Silver Oak Traders', 'Northgate Logistics',
           'Maple & Co.', 'Coastal Retail Group', 'Vantage Partners LLP'])[1 + (n % 6)],
    (DATE '2025-06-01' - ((n % 4) * INTERVAL '1 month'))::date,
    'MONTHLY',
    6 + (n % 20),
    (2500 + (n * 137) % 9000)::numeric(12,2),
    (ARRAY['IN_PROGRESS', 'PENDING_APPROVAL', 'COMPLETED', 'PAID'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-06-28' - (n || ' days')::interval)::date
FROM generate_series(1, 6) AS n;
