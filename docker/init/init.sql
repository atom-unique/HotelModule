-- Drop schema
DROP SCHEMA IF EXISTS hotel CASCADE;

-- Drop user
DROP USER IF EXISTS test_user;

-- Create new database user
CREATE USER test_user WITH PASSWORD 'user';

--Create new schema for user 'hotel'
CREATE SCHEMA IF NOT EXISTS hotel AUTHORIZATION test_user;

-- Grant privileges for 'test_user' on 'hotel' (for develop only)
GRANT ALL ON SCHEMA hotel TO test_user;