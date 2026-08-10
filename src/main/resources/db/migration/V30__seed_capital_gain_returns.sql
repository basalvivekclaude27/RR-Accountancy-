-- V30: Seed Capital Gain Tax cases — first 6 rows match Pics/15_CapitalGainTax.png
-- exactly, remaining 36 are generated filler so pagination has real data to work with (total 42).

INSERT INTO capital_gain_returns (client_name, utr, client_type, asset_type, tax_year_start, date_of_disposal, gain_or_loss, tax_payable, status, assigned_to, last_updated) VALUES
('John Miller',  '1234567890', 'Individual', 'Residential Property', 2024, DATE '2025-06-15', 185650.00,  37130.00, 'FILED',             'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  '2345678901', 'Individual', 'Shares',                2024, DATE '2025-05-28', 72400.00,   14480.00, 'IN_REVIEW',         'James Wright', DATE '2025-08-07'),
('Amit Kumar',   '3456789012', 'Landlord',   'Buy to Let Property',   2024, DATE '2025-04-10', 215300.00,  43060.00, 'PROCESSING',        'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', '4567890123', 'Individual', 'Shares',                2024, DATE '2025-06-30', -12750.00,  0.00,     'DOCUMENTS_PENDING', 'Tom Becker',   DATE '2025-08-05'),
('David Wilson', '5678901234', 'Landlord',   'Residential Property',  2024, DATE '2025-06-12', 146800.00,  29360.00, 'FILED',             'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', '6789012345', 'Individual', 'Crypto Assets',         2024, DATE '2025-07-20', 64120.00,   12820.00, 'IN_REVIEW',         'Sarah Khan',   DATE '2025-07-30');

INSERT INTO capital_gain_returns (client_name, utr, client_type, asset_type, tax_year_start, date_of_disposal, gain_or_loss, tax_payable, status, assigned_to, last_updated)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    LPAD((2000000000 + n * 149)::text, 10, '0'),
    (ARRAY['Individual', 'Sole Trader', 'Director', 'Landlord'])[1 + (n % 4)],
    (ARRAY['Residential Property', 'Shares', 'Buy to Let Property', 'Crypto Assets'])[1 + (n % 4)],
    2023 + (n % 2),
    (DATE '2025-07-25' - (n * 4 || ' days')::interval)::date,
    gain,
    CASE WHEN gain > 0 THEN ROUND(gain * 0.20, 2) ELSE 0.00 END,
    (ARRAY['PROCESSING', 'DOCUMENTS_PENDING', 'IN_REVIEW', 'FILED'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-08-01' - (n || ' days')::interval)::date
FROM generate_series(1, 36) AS n,
     LATERAL (SELECT CASE WHEN (n % 6) = 0 THEN -1 * (1000 + (n * 83) % 25000)::numeric(12,2)
                           ELSE (5000 + (n * 941) % 220000)::numeric(12,2)
                      END AS gain) g;
