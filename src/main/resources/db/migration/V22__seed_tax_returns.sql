-- V22: Seed Personal Income Tax returns — first 6 rows match Pics/11_PersonalIncomeTax.png
-- exactly, remaining 118 are generated filler so pagination has real data to work with (total 124).

INSERT INTO tax_returns (client_name, utr, client_type, tax_year_start, status, refund_amount, assigned_to, last_updated) VALUES
('John Miller',  '1234567890', 'Individual', 2024, 'FILED',             3250.00, 'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  '2345678901', 'Individual', 2024, 'IN_REVIEW',         2180.00, 'James Wright', DATE '2025-08-07'),
('Amit Kumar',   '3456789012', 'Individual', 2024, 'PROCESSING',        NULL,    'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', '4567890123', 'Individual', 2024, 'DOCUMENTS_PENDING', NULL,    'Tom Becker',   DATE '2025-08-05'),
('David Wilson', '5678901234', 'Individual', 2024, 'FILED',             1950.00, 'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', '6789012345', 'Individual', 2024, 'REFUND_ISSUED',     3780.00, 'Sarah Khan',   DATE '2025-07-30');

INSERT INTO tax_returns (client_name, utr, client_type, tax_year_start, status, refund_amount, assigned_to, last_updated)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    LPAD((1000000000 + n * 137)::text, 10, '0'),
    (ARRAY['Individual', 'Sole Trader', 'Director', 'Landlord'])[1 + (n % 4)],
    2023 + (n % 2),
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED', 'REFUND_ISSUED'])[1 + (n % 5)],
    CASE WHEN (n % 5) IN (0, 1) THEN NULL
         WHEN (n % 7) = 0 THEN -1 * (500 + (n * 97) % 4000)::numeric(12,2)
         ELSE (300 + (n * 173) % 5500)::numeric(12,2)
    END,
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-29' - (n || ' days')::interval)::date
FROM generate_series(1, 118) AS n;
