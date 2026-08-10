-- V17: Company Formation module — enquiries table
CREATE TABLE company_formation_enquiries (
    id              BIGSERIAL PRIMARY KEY,
    client_name     VARCHAR(150) NOT NULL,
    enquiry_ref     VARCHAR(30)  NOT NULL,
    company_name    VARCHAR(150) NOT NULL,
    company_type    VARCHAR(100) NOT NULL,
    jurisdiction    VARCHAR(50)  NOT NULL,
    stage           VARCHAR(30)  NOT NULL CHECK (stage IN
                        ('NAME_RESERVATION', 'INCORPORATION', 'DOCUMENTS', 'REVIEW', 'COMPLETED')),
    status          VARCHAR(30)  NOT NULL CHECK (status IN ('IN_PROGRESS', 'PENDING', 'COMPLETED')),
    source          VARCHAR(30)  NOT NULL CHECK (source IN ('WEBSITE', 'REFERRAL', 'ADVERTISEMENT', 'SOCIAL_MEDIA')),
    assigned_to     VARCHAR(100) NOT NULL,
    enquiry_date    DATE         NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE INDEX idx_cformation_client ON company_formation_enquiries (client_name);
CREATE INDEX idx_cformation_stage ON company_formation_enquiries (stage);
CREATE INDEX idx_cformation_status ON company_formation_enquiries (status);
CREATE INDEX idx_cformation_source ON company_formation_enquiries (source);
CREATE INDEX idx_cformation_jurisdiction ON company_formation_enquiries (jurisdiction);
CREATE INDEX idx_cformation_date ON company_formation_enquiries (enquiry_date);
