-- V15: Business Startup module — enquiries table
CREATE TABLE startup_enquiries (
    id              BIGSERIAL PRIMARY KEY,
    client_name     VARCHAR(150) NOT NULL,
    enquiry_ref     VARCHAR(30)  NOT NULL,
    business_name   VARCHAR(150) NOT NULL,
    business_type   VARCHAR(100) NOT NULL,
    stage           VARCHAR(30)  NOT NULL CHECK (stage IN
                        ('BUSINESS_PLAN', 'COMPANY_FORMATION', 'REGISTRATION', 'DOCUMENT_SETUP', 'LAUNCHED')),
    status          VARCHAR(30)  NOT NULL CHECK (status IN ('NEW', 'IN_PROGRESS', 'AWAITING_INFO', 'COMPLETED')),
    source          VARCHAR(30)  NOT NULL CHECK (source IN ('WEBSITE', 'REFERRAL', 'ADVERTISEMENT', 'SOCIAL_MEDIA')),
    assigned_to     VARCHAR(100) NOT NULL,
    enquiry_date    DATE         NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_startup_client ON startup_enquiries (client_name);
CREATE INDEX idx_startup_stage ON startup_enquiries (stage);
CREATE INDEX idx_startup_status ON startup_enquiries (status);
CREATE INDEX idx_startup_source ON startup_enquiries (source);
CREATE INDEX idx_startup_type ON startup_enquiries (business_type);
CREATE INDEX idx_startup_date ON startup_enquiries (enquiry_date);
