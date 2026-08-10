-- V28: Seed VAT returns — first 6 rows match Pics/14_ValueAddedTax.png exactly,
-- remaining 62 are generated filler so pagination has real data to work with (total 68).

INSERT INTO vat_returns (client_name, vat_number, scheme_type, client_type, period_start, sales_ex_vat, purchases_ex_vat, vat_amount, status, filed_on, assigned_to) VALUES
('John Miller Ltd',           'GB 123 4567 89', 'Standard Rate',    'Limited Company', DATE '2025-04-01', 152450.00, 98120.00,  10866.00, 'FILED',             DATE '2025-08-05', 'Sarah Khan'),
('GreenBite Café Ltd',        'GB 234 5678 90', 'Standard Rate',    'Limited Company', DATE '2025-04-01', 86320.00,  64250.00,  4415.00,  'IN_REVIEW',         NULL,              'James Wright'),
('AK Consultancy Ltd',        'GB 345 6789 01', 'Flat Rate Scheme', 'Limited Company', DATE '2025-04-01', 41800.00,  NULL,      2090.00,  'PROCESSING',        NULL,              'Priya Patel'),
('Bright Learning Academy Ltd','GB 456 7890 12','Standard Rate',    'Limited Company', DATE '2025-04-01', 73650.00,  71320.00,  465.00,   'DOCUMENTS_PENDING', NULL,              'Tom Becker'),
('Wilson Trading Ltd',        'GB 567 8901 23', 'Standard Rate',    'Limited Company', DATE '2025-04-01', 210500.00, 154600.00, 11182.00, 'FILED',             DATE '2025-08-02', 'Aisha Noor'),
('Thakkar Events Ltd',        'GB 678 9012 34', 'Flat Rate Scheme', 'Limited Company', DATE '2025-04-01', 29780.00,  NULL,      1489.00,  'REFUND_ISSUED',     DATE '2025-07-29', 'Sarah Khan');

INSERT INTO vat_returns (client_name, vat_number, scheme_type, client_type, period_start, sales_ex_vat, purchases_ex_vat, vat_amount, status, filed_on, assigned_to)
SELECT
    (ARRAY['Bennett Designs Ltd', 'Chen Logistics Ltd', 'Rahman Imports Ltd', 'Osei Fitness Ltd', 'Kelly Interiors Ltd',
           'O''Connor Legal Ltd', 'Nair Wellness Ltd', 'Fletcher Media Ltd', 'Ahmed Retail Ltd', 'Palmer Consulting Ltd',
           'Robinson Bakery Ltd', 'Mehta Software Ltd'])[1 + (n % 12)],
    'GB ' || LPAD((100 + n)::text, 3, '0') || ' ' || LPAD((1000 + n * 7)::text, 4, '0') || ' ' || LPAD((n % 100)::text, 2, '0'),
    (ARRAY['Standard Rate', 'Standard Rate', 'Flat Rate Scheme'])[1 + (n % 3)],
    'Limited Company',
    (DATE '2025-04-01' - ((n % 4) * INTERVAL '3 months'))::date,
    CASE WHEN (n % 9) = 0 THEN NULL ELSE (15000 + (n * 917) % 220000)::numeric(12,2) END,
    CASE WHEN (n % 3) = 2 THEN NULL ELSE (10000 + (n * 613) % 160000)::numeric(12,2) END,
    CASE WHEN (n % 9) = 0 THEN NULL
         WHEN (n % 6) = 0 THEN -1 * (500 + (n * 71) % 6000)::numeric(12,2)
         ELSE (200 + (n * 233) % 13000)::numeric(12,2)
    END,
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED'])[1 + (n % 5)],
    CASE WHEN (n % 4) = 0 THEN NULL ELSE (DATE '2025-08-05' - (n || ' days')::interval)::date END,
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)]
FROM generate_series(1, 62) AS n;
