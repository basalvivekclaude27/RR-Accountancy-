-- V24: Seed Business Income Tax returns — first 6 rows match Pics/12_BusinessIncomeTax.png
-- exactly, remaining 90 are generated filler so pagination has real data to work with (total 96).

INSERT INTO business_tax_returns (client_name, utr, business_type, industry, tax_year_start, profit_before_tax, tax_payable, status, assigned_to, last_updated) VALUES
('John Miller',  '1234567890', 'Sole Trader',      'Retail',        2024, 86250.00,  17280.00, 'FILED',             'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  '2345678901', 'Partnership',       'Construction',  2024, 124500.00, 28650.00, 'IN_REVIEW',         'James Wright', DATE '2025-08-07'),
('Amit Kumar',   '3456789012', 'Limited Company',   'IT & Services', 2024, 215800.00, 46280.00, 'PROCESSING',        'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', '4567890123', 'Sole Trader',       'Consultancy',   2024, 52300.00,  8940.00,  'DOCUMENTS_PENDING', 'Tom Becker',   DATE '2025-08-05'),
('David Wilson', '5678901234', 'Limited Company',   'Healthcare',    2024, 178600.00, 37620.00, 'FILED',             'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', '6789012345', 'Partnership',       'Retail',        2024, 92450.00,  18510.00, 'REFUND_ISSUED',     'Sarah Khan',   DATE '2025-07-30');

INSERT INTO business_tax_returns (client_name, utr, business_type, industry, tax_year_start, profit_before_tax, tax_payable, status, assigned_to, last_updated)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    LPAD((2000000000 + n * 149)::text, 10, '0'),
    (ARRAY['Sole Trader', 'Sole Trader', 'Limited Company', 'Limited Company', 'Partnership', 'LLP'])[1 + (n % 6)],
    (ARRAY['Retail', 'Construction', 'IT & Services', 'Consultancy', 'Healthcare', 'Manufacturing'])[1 + (n % 6)],
    2023 + (n % 2),
    (20000 + (n * 3137) % 280000)::numeric(12,2),
    (3000 + (n * 719) % 60000)::numeric(12,2),
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED'])[1 + (n % 5)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-29' - (n || ' days')::interval)::date
FROM generate_series(1, 90) AS n;
