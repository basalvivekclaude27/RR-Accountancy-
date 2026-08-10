-- This directory is mounted into the Postgres container's
-- /docker-entrypoint-initdb.d, so it only runs ONCE, the very first time
-- the data volume is created. It runs before Flyway (which owns the real
-- schema/migrations under src/main/resources/db/migration).
--
-- POSTGRES_DB already creates the "DataEngineering" database on first boot,
-- so nothing further is required here. Kept as a placeholder / extension
-- point (e.g. CREATE EXTENSION IF NOT EXISTS "pgcrypto";) if ever needed.
SELECT 1;
