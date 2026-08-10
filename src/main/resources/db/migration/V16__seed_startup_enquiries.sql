-- V16: Seed Business Startup enquiries — first 6 rows match Pics/08_BusinessStartup.png
-- exactly, remaining 18 are generated filler so pagination has real data to work with (total 24).

INSERT INTO startup_enquiries (client_name, enquiry_ref, business_name, business_type, stage, status, source, assigned_to, enquiry_date) VALUES
('John Miller',  'ENQ-1001', 'Miller Tech Solutions',   'IT Services',      'BUSINESS_PLAN',      'IN_PROGRESS',  'WEBSITE',       'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  'ENQ-1002', 'GreenBite Café',          'Food & Beverage',  'COMPANY_FORMATION',  'IN_PROGRESS',  'REFERRAL',      'James Wright', DATE '2025-08-07'),
('Amit Kumar',   'ENQ-1003', 'AK Consultancy Ltd.',     'Consulting',       'REGISTRATION',       'IN_PROGRESS',  'WEBSITE',       'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', 'ENQ-1004', 'Bright Learning Academy', 'Education',        'DOCUMENT_SETUP',     'AWAITING_INFO', 'ADVERTISEMENT', 'Tom Becker',   DATE '2025-08-05'),
('David Wilson', 'ENQ-1005', 'Wilson Trading',          'Retail',           'BUSINESS_PLAN',       'NEW',          'SOCIAL_MEDIA',  'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', 'ENQ-1006', 'Thakkar Events',          'Event Management', 'LAUNCHED',            'COMPLETED',    'REFERRAL',      'Sarah Khan',   DATE '2025-07-30');

INSERT INTO startup_enquiries (client_name, enquiry_ref, business_name, business_type, stage, status, source, assigned_to, enquiry_date)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    'ENQ-' || LPAD((999 - n)::text, 4, '0'),
    (ARRAY['Bennett Designs', 'Chen Logistics', 'Rahman Imports', 'Osei Fitness', 'Kelly Interiors',
           'O''Connor Legal', 'Nair Wellness', 'Fletcher Media', 'Ahmed Retail', 'Palmer Consulting',
           'Robinson Bakery', 'Mehta Software'])[1 + (n % 12)],
    (ARRAY['IT Services', 'Food & Beverage', 'Consulting', 'Education', 'Retail', 'Event Management',
           'Health & Fitness', 'Legal Services', 'Media & Marketing', 'Manufacturing'])[1 + (n % 10)],
    (ARRAY['BUSINESS_PLAN', 'COMPANY_FORMATION', 'REGISTRATION', 'DOCUMENT_SETUP', 'LAUNCHED'])[1 + (n % 5)],
    (ARRAY['NEW', 'IN_PROGRESS', 'AWAITING_INFO', 'COMPLETED'])[1 + (n % 4)],
    (ARRAY['WEBSITE', 'REFERRAL', 'ADVERTISEMENT', 'SOCIAL_MEDIA'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-29' - (n || ' days')::interval)::date
FROM generate_series(1, 18) AS n;
