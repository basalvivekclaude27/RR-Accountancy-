-- V20: Seed Business Plans — first 6 rows match Pics/10_BusinessPlans.png exactly,
-- remaining 46 are generated filler so pagination has real data to work with (total 52).

INSERT INTO business_plans (client_name, plan_ref, plan_name, plan_type, industry, status, revenue_potential, last_updated) VALUES
('John Miller',  'BP-1001', '5 Year Growth Plan',    'Growth Plan',      'Technology',   'IN_PROGRESS',    450000.00, DATE '2025-08-08'),
('Sarah Patel',  'BP-1002', 'Startup Business Plan',  'Startup Plan',     'Retail',       'IN_PROGRESS',    250000.00, DATE '2025-08-07'),
('Amit Kumar',   'BP-1003', 'Expansion Plan',         'Expansion Plan',   'Healthcare',   'COMPLETED',      680000.00, DATE '2025-08-06'),
('Lisa Roberts', 'BP-1004', 'Investor Pitch Deck',    'Pitch Deck',       'Construction', 'IN_PROGRESS',    150000.00, DATE '2025-08-05'),
('David Wilson', 'BP-1005', 'Operational Plan',       'Operational Plan', 'Technology',   'PENDING_REVIEW', 220000.00, DATE '2025-08-02'),
('Neha Thakkar', 'BP-1006', 'Turnaround Plan',        'Turnaround Plan',  'Retail',       'COMPLETED',      700000.00, DATE '2025-07-30');

INSERT INTO business_plans (client_name, plan_ref, plan_name, plan_type, industry, status, revenue_potential, last_updated)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    'BP-' || LPAD((1006 + n)::text, 4, '0'),
    (ARRAY['5 Year Growth Plan', 'Startup Business Plan', 'Expansion Plan', 'Investor Pitch Deck',
           'Operational Plan', 'Turnaround Plan', 'Marketing Plan', 'Funding Proposal'])[1 + (n % 8)],
    (ARRAY['Growth Plan', 'Startup Plan', 'Expansion Plan', 'Pitch Deck', 'Operational Plan', 'Turnaround Plan'])[1 + (n % 6)],
    (ARRAY['Technology', 'Retail', 'Healthcare', 'Construction', 'Hospitality', 'Manufacturing'])[1 + (n % 6)],
    (ARRAY['NOT_STARTED', 'IN_PROGRESS', 'PENDING_REVIEW', 'ON_HOLD', 'COMPLETED'])[1 + (n % 5)],
    (80000 + (n * 9137) % 620000)::numeric(14,2),
    (DATE '2025-07-29' - (n || ' days')::interval)::date
FROM generate_series(1, 46) AS n;
