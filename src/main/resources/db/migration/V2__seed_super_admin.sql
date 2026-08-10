-- V2: Seed default Super Administrator account
-- username: admin  |  password: Basal123$  (BCrypt hashed, strength 10)
INSERT INTO users (username, email, password, full_name, role, enabled, account_non_locked)
VALUES (
    'admin',
    'admin@rraccountancy.com',
    '$2b$10$vd0BG8jadwPC3sSBd4UF8uMxXW7Xz2Rwcd8GuNrdK2lTDx.Dnwp7.',
    'Super Administrator',
    'ROLE_ADMIN',
    TRUE,
    TRUE
)
ON CONFLICT (username) DO NOTHING;
