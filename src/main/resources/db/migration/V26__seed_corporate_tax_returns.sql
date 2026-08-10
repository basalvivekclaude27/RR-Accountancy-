-- V26: Seed Corporate Tax returns — first 6 rows match Pics/13_CorporateTax.png exactly,
-- remaining 72 are generated filler so pagination has real data to work with (total 78).

INSERT INTO corporate_tax_returns (client_name, utr, client_type, industry, accounting_period_start, accounting_period_end, taxable_profit, tax_payable, status, due_date, assigned_to) VALUES
('John Miller Ltd',          '1234567890', 'Limited Company', 'Professional Services', DATE '2024-04-01', DATE '2025-03-31', 285600.00, 57120.00,  'FILED',             DATE '2025-08-01', 'Sarah Khan'),
('GreenBite Café Ltd',       '2345678901', 'Limited Company', 'Hospitality',           DATE '2024-04-01', DATE '2025-03-31', 142300.00, 28460.00,  'IN_REVIEW',         DATE '2025-07-31', 'James Wright'),
('AK Consultancy Ltd',       '3456789012', 'Limited Company', 'Professional Services', DATE '2024-04-01', DATE '2025-03-31', 512750.00, 102550.00, 'PROCESSING',        DATE '2025-10-31', 'Priya Patel'),
('Bright Learning Academy Ltd','4567890123','Limited Company', 'Retail & Wholesale',   DATE '2024-04-01', DATE '2025-03-31', 98600.00,  19720.00,  'DOCUMENTS_PENDING', DATE '2025-08-15', 'Tom Becker'),
('Wilson Trading Ltd',       '5678901234', 'Limited Company', 'Retail & Wholesale',    DATE '2024-04-01', DATE '2025-03-31', 233400.00, 46680.00,  'FILED',             DATE '2025-07-30', 'Aisha Noor'),
('Thakkar Events Ltd',       '6789012345', 'Limited Company', 'IT & Technology',       DATE '2024-04-01', DATE '2025-03-31', 76200.00,  15240.00,  'PROCESSING',        DATE '2025-09-30', 'Sarah Khan');

INSERT INTO corporate_tax_returns (client_name, utr, client_type, industry, accounting_period_start, accounting_period_end, taxable_profit, tax_payable, status, due_date, assigned_to)
SELECT
    (ARRAY['Bennett Designs Ltd', 'Chen Logistics Ltd', 'Rahman Imports Ltd', 'Osei Fitness Ltd', 'Kelly Interiors Ltd',
           'O''Connor Legal Ltd', 'Nair Wellness Ltd', 'Fletcher Media Ltd', 'Ahmed Retail Ltd', 'Palmer Consulting Ltd',
           'Robinson Bakery Ltd', 'Mehta Software Ltd'])[1 + (n % 12)],
    LPAD((3000000000 + n * 151)::text, 10, '0'),
    'Limited Company',
    (ARRAY['Professional Services', 'Retail & Wholesale', 'IT & Technology', 'Construction', 'Hospitality', 'Manufacturing'])[1 + (n % 6)],
    DATE '2024-04-01',
    DATE '2025-03-31',
    (30000 + (n * 4137) % 550000)::numeric(12,2),
    (6000 + (n * 827) % 110000)::numeric(12,2),
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED'])[1 + (n % 4)],
    (DATE '2025-07-25' + (n || ' days')::interval)::date,
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)]
FROM generate_series(1, 72) AS n;
