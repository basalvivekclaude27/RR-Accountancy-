-- V18: Seed Company Formation enquiries — first 6 rows match Pics/09_CompanyFormation.png
-- exactly, remaining 62 are generated filler so pagination has real data to work with (total 68).

INSERT INTO company_formation_enquiries (client_name, enquiry_ref, company_name, company_type, jurisdiction, stage, status, source, assigned_to, enquiry_date) VALUES
('John Miller',  'ENQ-1001', 'Miller Tech Solutions Ltd',   'Private Limited', 'UK', 'INCORPORATION',    'IN_PROGRESS', 'WEBSITE',       'Sarah Khan',   DATE '2025-08-08'),
('Sarah Patel',  'ENQ-1002', 'GreenBite Café Ltd',          'Private Limited', 'UK', 'NAME_RESERVATION', 'IN_PROGRESS', 'REFERRAL',      'James Wright', DATE '2025-08-07'),
('Amit Kumar',   'ENQ-1003', 'AK Consultancy Ltd.',         'Private Limited', 'UK', 'DOCUMENTS',        'IN_PROGRESS', 'WEBSITE',       'Priya Patel',  DATE '2025-08-06'),
('Lisa Roberts', 'ENQ-1004', 'Bright Learning Academy Ltd', 'Private Limited', 'UK', 'REVIEW',           'PENDING',     'ADVERTISEMENT', 'Tom Becker',   DATE '2025-08-05'),
('David Wilson', 'ENQ-1005', 'Wilson Trading Ltd',          'Private Limited', 'UK', 'INCORPORATION',    'COMPLETED',   'SOCIAL_MEDIA',  'Aisha Noor',   DATE '2025-08-02'),
('Neha Thakkar', 'ENQ-1006', 'Thakkar Events Ltd',          'Private Limited', 'UK', 'COMPLETED',        'COMPLETED',   'REFERRAL',      'Sarah Khan',   DATE '2025-07-30');

INSERT INTO company_formation_enquiries (client_name, enquiry_ref, company_name, company_type, jurisdiction, stage, status, source, assigned_to, enquiry_date)
SELECT
    (ARRAY['Olivia Bennett', 'Marcus Chen', 'Fatima Rahman', 'Daniel Osei', 'Grace Kelly',
           'Ryan O''Connor', 'Meera Nair', 'Tom Fletcher', 'Zara Ahmed', 'Chris Palmer',
           'Ella Robinson', 'Kunal Mehta'])[1 + (n % 12)],
    'ENQ-' || LPAD((999 - n)::text, 4, '0'),
    (ARRAY['Bennett Designs Ltd', 'Chen Logistics Ltd', 'Rahman Imports Ltd', 'Osei Fitness Ltd', 'Kelly Interiors Ltd',
           'O''Connor Legal LLP', 'Nair Wellness Ltd', 'Fletcher Media Ltd', 'Ahmed Retail Ltd', 'Palmer Consulting Ltd',
           'Robinson Bakery Ltd', 'Mehta Software Ltd'])[1 + (n % 12)],
    (ARRAY['Private Limited', 'Private Limited', 'Private Limited', 'Limited by Guarantee', 'LLP', 'Public Limited'])[1 + (n % 6)],
    (ARRAY['UK', 'UK', 'UK', 'UK', 'UK', 'UK', 'UK', 'Ireland', 'USA', 'UAE', 'Ireland', 'Others'])[1 + (n % 12)],
    (ARRAY['NAME_RESERVATION', 'INCORPORATION', 'DOCUMENTS', 'REVIEW', 'COMPLETED'])[1 + (n % 5)],
    (ARRAY['IN_PROGRESS', 'PENDING', 'COMPLETED'])[1 + (n % 3)],
    (ARRAY['WEBSITE', 'REFERRAL', 'ADVERTISEMENT', 'SOCIAL_MEDIA'])[1 + (n % 4)],
    (ARRAY['Sarah Khan', 'James Wright', 'Priya Patel', 'Tom Becker', 'Aisha Noor'])[1 + (n % 5)],
    (DATE '2025-07-29' - (n || ' days')::interval)::date
FROM generate_series(1, 62) AS n;
