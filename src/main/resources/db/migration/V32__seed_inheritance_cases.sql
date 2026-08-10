-- V32: Seed Inheritance Tax cases — first 6 rows match Pics/16_InheritanceTax.png
-- exactly, remaining 32 are generated filler so pagination has real data to work with (total 38).

INSERT INTO inheritance_cases (client_name, utr, client_type, planning_type, tax_year_start, estate_value, nil_rate_band_available, tax_liability, status, assigned_to, last_updated) VALUES
('John Miller',  '1234567890', 'Individual', 'IHT Return',   2024, 1250000.00, 325000.00,  185000.00, 'FILED',             'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  '2345678901', 'Trustee',    'IHT Planning', 2024, 2450000.00, 650000.00,  540000.00, 'IN_REVIEW',         'James Wright', DATE '2025-08-07'),
('Amit Kumar',   '3456789012', 'Individual', 'IHT Return',   2024, 980000.00,  325000.00,  78000.00,  'PROCESSING',        'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', '4567890123', 'Executor',   'IHT Planning', 2024, 3200000.00, 975000.00,  667500.00, 'DOCUMENTS_PENDING', 'Tom Becker',   DATE '2025-08-05'),
('David Wilson', '5678901234', 'Individual', 'IHT Return',   2024, 1100000.00, 325000.00,  155000.00, 'FILED',             'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', '6789012345', 'Trustee',    'IHT Planning', 2024, 4100000.00, 1300000.00, 700000.00, 'IN_REVIEW',         'Sarah Khan',   DATE '2025-07-30');

INSERT INTO inheritance_cases (client_name, utr, client_type, planning_type, tax_year_start, estate_value, nil_rate_band_available, tax_liability, status, assigned_to, last_updated)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    LPAD((3000000000 + n * 151)::text, 10, '0'),
    (ARRAY['Individual', 'Trustee', 'Executor', 'Joint Estate'])[1 + (n % 4)],
    (ARRAY['IHT Return', 'IHT Planning'])[1 + (n % 2)],
    2023 + (n % 2),
    e.estate,
    (325000 + (n % 4) * 325000)::numeric(12,2),
    ROUND(e.estate * 0.16, 2),
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-08-01' - (n || ' days')::interval)::date
FROM generate_series(1, 32) AS n,
     LATERAL (SELECT (250000 + (n * 61300) % 3850000)::numeric(14,2) AS estate) e;
