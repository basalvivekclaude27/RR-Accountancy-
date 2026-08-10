-- V14: Seed Service Charge Accounts — first 6 rows match Pics/07_ServiceChargeAccount.png
-- exactly, remaining 36 are generated filler so pagination has real data to work with (total 42).

INSERT INTO service_charge_accounts (property_name, property_ref, units, financial_year_start, period_date, budgeted_amount, collected_amount, status, last_updated) VALUES
('Maple Court',          'MC-1001', 24, 2024, DATE '2025-07-01', 18600.00, 15680.00, 'ON_TRACK',  DATE '2025-08-08'),
('Oak Heights',          'OH-1002', 32, 2024, DATE '2025-07-01', 24800.00, 19360.00, 'ATTENTION', DATE '2025-08-07'),
('Riverside Apartments', 'RA-1003', 18, 2024, DATE '2025-07-01', 12450.00, 11230.00, 'ON_TRACK',  DATE '2025-08-06'),
('Sunset Gardens',       'SG-1004', 28, 2024, DATE '2025-07-01', 16200.00, 12050.00, 'BEHIND',    DATE '2025-08-05'),
('Willow Court',         'WC-1005', 16, 2024, DATE '2025-07-01', 10600.00, 9000.00,  'ON_TRACK',  DATE '2025-08-02'),
('Victoria House',       'VH-1006', 22, 2024, DATE '2025-07-01', 19450.00, 16000.00, 'ON_TRACK',  DATE '2025-07-31');

INSERT INTO service_charge_accounts (property_name, property_ref, units, financial_year_start, period_date, budgeted_amount, collected_amount, status, last_updated)
SELECT
    (ARRAY['Maple Court', 'Oak Heights', 'Riverside Apartments', 'Sunset Gardens', 'Willow Court',
           'Victoria House', 'Birchwood Place', 'Harbourview Residences', 'Elm Tree Court',
           'Kingsgate Mansions', 'Chapel Green', 'Meadowbank House'])[1 + (n % 12)],
    'PR-' || LPAD((1099 - n)::text, 4, '0'),
    10 + (n % 30),
    2023 + (n % 2),
    (DATE '2025-07-01' - ((n % 6) * INTERVAL '1 month'))::date,
    (10000 + (n * 733) % 20000)::numeric(12,2),
    (8000 + (n * 611) % 18000)::numeric(12,2),
    (ARRAY['ON_TRACK', 'ATTENTION', 'BEHIND'])[1 + (n % 3)],
    (DATE '2025-07-30' - (n || ' days')::interval)::date
FROM generate_series(1, 36) AS n;
