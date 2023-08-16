-- Generated by Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   at:        2023-05-03 11:26:12 MYT
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c
--Group Name : T1_Group04
-- Group Members : Mark Manlagit, Heng Zi Ying, Chong Jin Yao

-- Capture run of script in file called cui_schema_output.txt 
set echo on
SPOOL cui_schema_output.txt



DROP TABLE authorised_driver CASCADE CONSTRAINTS;

DROP TABLE authority CASCADE CONSTRAINTS;

DROP TABLE bin CASCADE CONSTRAINTS;

DROP TABLE bin_collection_record CASCADE CONSTRAINTS;

DROP TABLE bin_cost CASCADE CONSTRAINTS;

DROP TABLE bin_type CASCADE CONSTRAINTS;

DROP TABLE collection CASCADE CONSTRAINTS;

DROP TABLE contract CASCADE CONSTRAINTS;

DROP TABLE contract_detail CASCADE CONSTRAINTS;

DROP TABLE driver CASCADE CONSTRAINTS;

DROP TABLE owner CASCADE CONSTRAINTS;

DROP TABLE ownerline CASCADE CONSTRAINTS;

DROP TABLE property CASCADE CONSTRAINTS;

DROP TABLE street CASCADE CONSTRAINTS;

DROP TABLE truck CASCADE CONSTRAINTS;

DROP TABLE waste_type CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE authorised_driver (
    truck_vin        VARCHAR2(50) NOT NULL,
    driver_no        NUMBER NOT NULL,
    ad_approval_date DATE
);

COMMENT ON COLUMN authorised_driver.truck_vin IS
    'Unique Vehicle Identification Number assigned to each truck';

COMMENT ON COLUMN authorised_driver.driver_no IS
    'Unique identification number assigned to Drivers by CUI';

COMMENT ON COLUMN authorised_driver.ad_approval_date IS
    'Date in which the Company Approved the Driver to drive a particular truck';

ALTER TABLE authorised_driver ADD CONSTRAINT authorised_driver_pk PRIMARY KEY ( truck_vin
,
                                                                                driver_no
                                                                                );

CREATE TABLE authority (
    auth_name       VARCHAR2(50) NOT NULL,
    auth_ceo_gname  VARCHAR2(50) NOT NULL,
    auth_ceo_fname  VARCHAR2(50) NOT NULL,
    auth_phone      CHAR(10) NOT NULL,
    auth_total_area NUMBER(8) NOT NULL,
    auth_type       VARCHAR2(16) NOT NULL
);

ALTER TABLE authority
    ADD CONSTRAINT chk_auth_type CHECK ( auth_type IN ( 'Borough', 'City', 'District Council'
    , 'Shire', 'Town' ) );

COMMENT ON COLUMN authority.auth_name IS
    'Given Authority''s name';

COMMENT ON COLUMN authority.auth_ceo_gname IS
    'Given Name of the Chief Executive Officer of the given Authority Instance';

COMMENT ON COLUMN authority.auth_ceo_fname IS
    'First Name of the Chief Executive Officer of the given Authority Instance';

COMMENT ON COLUMN authority.auth_phone IS
    'Phone Number of the Given Authority Instance';

COMMENT ON COLUMN authority.auth_total_area IS
    'Area under control by the given Authority Instance';

COMMENT ON COLUMN authority.auth_type IS
    'Type of the given Authority Instance';

ALTER TABLE authority ADD CONSTRAINT authority_pk PRIMARY KEY ( auth_name );

CREATE TABLE bin (
    bin_rfid               VARCHAR2(16) NOT NULL,
    bin_replacement_reason CHAR(50),
    bin_supply_date        DATE,
    bin_type_size          NUMBER NOT NULL,
    waste_type_id          NUMBER(7) NOT NULL,
    property_id            VARCHAR2(10) NOT NULL
);

ALTER TABLE bin
    ADD CONSTRAINT chk_replacement_reason CHECK ( bin_replacement_reason IN ( 'Bin Failure'
    , 'Damaged by owner', 'Damaged during pickup of waste', 'Stolen' ) );

COMMENT ON COLUMN bin.bin_rfid IS
    'Unique Hexadecimal RFID Number for each Bin';

COMMENT ON COLUMN bin.bin_replacement_reason IS
    'Reason for the bins replacement';

COMMENT ON COLUMN bin.bin_supply_date IS
    'Date the Bin was supplied';

COMMENT ON COLUMN bin.bin_type_size IS
    'Size/Capacity of a given bin ( In Litres )';

COMMENT ON COLUMN bin.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN bin.property_id IS
    'The ID of the property';

ALTER TABLE bin ADD CONSTRAINT bin_pk PRIMARY KEY ( bin_rfid );

CREATE TABLE bin_collection_record (
    bin_rfid                     VARCHAR2(16) NOT NULL,
    collection_date              DATE NOT NULL,
    waste_type_id                NUMBER(7) NOT NULL,
    contract_no                  NUMBER(7) NOT NULL,
    bin_collection_collected_kgs NUMBER,
    bin_collection_overweight    CHAR(1)
);

ALTER TABLE bin_collection_record
    ADD CONSTRAINT chk_overweight CHECK ( bin_collection_overweight IN ( 'N', 'Y' ) )
    ;

COMMENT ON COLUMN bin_collection_record.bin_rfid IS
    'Unique Hexadecimal RFID Number for each Bin';

COMMENT ON COLUMN bin_collection_record.collection_date IS
    'Date in which the Waste Collection occured';

COMMENT ON COLUMN bin_collection_record.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN bin_collection_record.contract_no IS
    'The contract''s unique id';

COMMENT ON COLUMN bin_collection_record.bin_collection_collected_kgs IS
    'Weight of waste collected from each Bin';

COMMENT ON COLUMN bin_collection_record.bin_collection_overweight IS
    'Boolean to record whether weight of waste during collection was too heavy or not'
    ;

ALTER TABLE bin_collection_record
    ADD CONSTRAINT bin_collection_record_pk PRIMARY KEY ( bin_rfid,
                                                          collection_date,
                                                          waste_type_id,
                                                          contract_no );

CREATE TABLE bin_cost (
    bin_type_size   NUMBER NOT NULL,
    waste_type_id   NUMBER(7) NOT NULL,
    contract_no     NUMBER(7) NOT NULL,
    bin_supply_cost NUMBER(5, 2) NOT NULL
);

COMMENT ON COLUMN bin_cost.bin_type_size IS
    'Size/Capacity of a given bin ( In Litres )';

COMMENT ON COLUMN bin_cost.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN bin_cost.contract_no IS
    'The contract''s unique id';

COMMENT ON COLUMN bin_cost.bin_supply_cost IS
    'The supply cost of a bin type required, which differs from the standard bin supply cost'
    ;

ALTER TABLE bin_cost
    ADD CONSTRAINT bin_cost_pk PRIMARY KEY ( bin_type_size,
                                             waste_type_id,
                                             contract_no );

CREATE TABLE bin_type (
    bin_type_size        NUMBER NOT NULL,
    waste_type_id        NUMBER(7) NOT NULL,
    bin_type_supply_cost NUMBER(10)
);

COMMENT ON COLUMN bin_type.bin_type_size IS
    'Size/Capacity of a given bin ( In Litres )';

COMMENT ON COLUMN bin_type.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN bin_type.bin_type_supply_cost IS
    'Standard Supply Cost for the specific Bin Type';

ALTER TABLE bin_type ADD CONSTRAINT bin_type_pk PRIMARY KEY ( bin_type_size,
                                                              waste_type_id );

CREATE TABLE collection (
    collection_date DATE NOT NULL,
    truck_vin       VARCHAR2(30) NOT NULL,
    driver_no       NUMBER NOT NULL,
    waste_type_id   NUMBER(7) NOT NULL,
    contract_no     NUMBER(7) NOT NULL
);

COMMENT ON COLUMN collection.collection_date IS
    'Date in which the Waste Collection occured';

COMMENT ON COLUMN collection.truck_vin IS
    'Unique Vehicle Identification Number assigned to each truck';

COMMENT ON COLUMN collection.driver_no IS
    'Unique identification number assigned to Drivers by CUI';

COMMENT ON COLUMN collection.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN collection.contract_no IS
    'The contract''s unique id';

ALTER TABLE collection
    ADD CONSTRAINT collection_pk PRIMARY KEY ( collection_date,
                                               waste_type_id,
                                               contract_no );

CREATE TABLE contract (
    contract_no         NUMBER(7) NOT NULL,
    contract_start_date DATE NOT NULL,
    contract_end_date   DATE NOT NULL,
    auth_name           VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN contract.contract_no IS
    'The contract''s unique id';

COMMENT ON COLUMN contract.contract_start_date IS
    'The contract''s start date';

COMMENT ON COLUMN contract.contract_end_date IS
    'The contract''s end date';

COMMENT ON COLUMN contract.auth_name IS
    'Given Authority''s name';

ALTER TABLE contract ADD CONSTRAINT contract_pk PRIMARY KEY ( contract_no );

CREATE TABLE contract_detail (
    waste_type_id       NUMBER(7) NOT NULL,
    contract_no         NUMBER(7) NOT NULL,
    c_d_collection_cost NUMBER,
    c_d_collection_freq CHAR(50)
);

ALTER TABLE contract_detail
    ADD CONSTRAINT chk_contract_det_col_freq CHECK ( c_d_collection_freq IN ( 'Fortnightly'
    , 'Monthly', 'Weekly' ) );

COMMENT ON COLUMN contract_detail.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN contract_detail.contract_no IS
    'The contract''s unique id';

COMMENT ON COLUMN contract_detail.c_d_collection_cost IS
    'Fixed collection cost per kilogram for waste types being collected  by the contract, specified by the contract'
    ;

COMMENT ON COLUMN contract_detail.c_d_collection_freq IS
    'Contract detail specifying fixed intervals for waste collection';

ALTER TABLE contract_detail ADD CONSTRAINT contract_cost_pk PRIMARY KEY ( waste_type_id
,
                                                                          contract_no
                                                                          );

CREATE TABLE driver (
    driver_no         NUMBER NOT NULL,
    driver_license_no NUMBER,
    driver_name       CHAR(50),
    driver_dob        DATE,
    driver_tax_no     NUMBER,
    driver_contact    NUMBER
);

COMMENT ON COLUMN driver.driver_no IS
    'Unique identification number assigned to Drivers by CUI';

COMMENT ON COLUMN driver.driver_license_no IS
    'License Number for a given Truck Driver';

COMMENT ON COLUMN driver.driver_name IS
    'Name of a given Truck Driver';

COMMENT ON COLUMN driver.driver_dob IS
    'Date of Birth of a given truck driver';

COMMENT ON COLUMN driver.driver_tax_no IS
    'Taxfile number of a given truck driver';

COMMENT ON COLUMN driver.driver_contact IS
    'Phone number of a given truck driver';

ALTER TABLE driver ADD CONSTRAINT driver_pk PRIMARY KEY ( driver_no );

CREATE TABLE owner (
    owner_id    NUMBER(7) NOT NULL,
    owner_name  VARCHAR2(50) NOT NULL,
    owner_email VARCHAR2(50) NOT NULL,
    owner_phone CHAR(10) NOT NULL
);

COMMENT ON COLUMN owner.owner_id IS
    'The owner''s unique id';

COMMENT ON COLUMN owner.owner_name IS
    'The owner''s name';

COMMENT ON COLUMN owner.owner_email IS
    'The owner''s email';

COMMENT ON COLUMN owner.owner_phone IS
    'The owner''s phone';

ALTER TABLE owner ADD CONSTRAINT owner_pk PRIMARY KEY ( owner_id );

CREATE TABLE ownerline (
    owner_id    NUMBER(7) NOT NULL,
    property_id VARCHAR2(10) NOT NULL
);

COMMENT ON COLUMN ownerline.owner_id IS
    'The owner''s unique id';

COMMENT ON COLUMN ownerline.property_id IS
    'The ID of the property';

ALTER TABLE ownerline ADD CONSTRAINT ownerline_pk PRIMARY KEY ( owner_id,
                                                                property_id );

CREATE TABLE property (
    property_id        VARCHAR2(10) NOT NULL,
    property_street_no NUMBER(7) NOT NULL,
    street_id          NUMBER(7) NOT NULL,
    auth_name          VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN property.property_id IS
    'The ID of the property';

COMMENT ON COLUMN property.property_street_no IS
    'Given street number of a property';

COMMENT ON COLUMN property.street_id IS
    'The street''s unique id (within the local authority only)';

COMMENT ON COLUMN property.auth_name IS
    'Given Authority''s name';

ALTER TABLE property ADD CONSTRAINT property_pk PRIMARY KEY ( property_id );

ALTER TABLE property
    ADD CONSTRAINT property_nk UNIQUE ( property_street_no,
                                        street_id,
                                        auth_name );

CREATE TABLE street (
    street_id           NUMBER(7) NOT NULL,
    auth_name           VARCHAR2(50) NOT NULL,
    street_name         VARCHAR2(50) NOT NULL,
    street_length       NUMBER(7) NOT NULL,
    street_surface_type CHAR(50) NOT NULL,
    street_lanes_no     NUMBER(2) NOT NULL
);

ALTER TABLE street
    ADD CONSTRAINT chk_street_surface_type CHECK ( street_surface_type IN ( 'Asphalt'
    , 'Concrete', 'Unsealed' ) );

COMMENT ON COLUMN street.street_id IS
    'The street''s unique id (within the local authority only)';

COMMENT ON COLUMN street.auth_name IS
    'Given Authority''s name';

COMMENT ON COLUMN street.street_name IS
    'The name of the street';

COMMENT ON COLUMN street.street_length IS
    'Street length (in meters)';

COMMENT ON COLUMN street.street_surface_type IS
    'The surface type of the street';

COMMENT ON COLUMN street.street_lanes_no IS
    'The number of lanes of the street';

ALTER TABLE street ADD CONSTRAINT street_pk PRIMARY KEY ( street_id,
                                                          auth_name );

CREATE TABLE truck (
    truck_vin     VARCHAR2(50) NOT NULL,
    truck_rego_no VARCHAR2(50),
    truck_make    VARCHAR2(50),
    truck_model   VARCHAR2(50),
    truck_year    DATE
);

COMMENT ON COLUMN truck.truck_vin IS
    'Unique Vehicle Identification Number assigned to each truck';

COMMENT ON COLUMN truck.truck_rego_no IS
    'Registration number for the truck';

COMMENT ON COLUMN truck.truck_make IS
    'Given truck''s brand/manufacturer';

COMMENT ON COLUMN truck.truck_model IS
    'Given truck''s specific model';

COMMENT ON COLUMN truck.truck_year IS
    'Date in which the truck was manufactured';

ALTER TABLE truck ADD CONSTRAINT truck_pk PRIMARY KEY ( truck_vin );

CREATE TABLE waste_type (
    waste_type_id          NUMBER(7) NOT NULL,
    waste_type_description CHAR(50)
);

ALTER TABLE waste_type
    ADD CONSTRAINT chk_waste_type_desc CHECK ( waste_type_description IN ( 'Glass', 'Green Waste'
    , 'Landfill', 'Recycle', 'Standard' ) );

COMMENT ON COLUMN waste_type.waste_type_id IS
    'Given waste type''s unique ID';

COMMENT ON COLUMN waste_type.waste_type_description IS
    'Description of the given waste type';

ALTER TABLE waste_type ADD CONSTRAINT waste_type_pk PRIMARY KEY ( waste_type_id );

ALTER TABLE contract
    ADD CONSTRAINT authority_contract FOREIGN KEY ( auth_name )
        REFERENCES authority ( auth_name );

ALTER TABLE street
    ADD CONSTRAINT authority_street FOREIGN KEY ( auth_name )
        REFERENCES authority ( auth_name );

ALTER TABLE bin_collection_record
    ADD CONSTRAINT bin_collection_record_bin FOREIGN KEY ( bin_rfid )
        REFERENCES bin ( bin_rfid );

ALTER TABLE bin
    ADD CONSTRAINT bin_type_bin FOREIGN KEY ( bin_type_size,
                                              waste_type_id )
        REFERENCES bin_type ( bin_type_size,
                              waste_type_id );

ALTER TABLE bin_cost
    ADD CONSTRAINT bin_type_bin_cost FOREIGN KEY ( bin_type_size,
                                                   waste_type_id )
        REFERENCES bin_type ( bin_type_size,
                              waste_type_id );

ALTER TABLE bin_collection_record
    ADD CONSTRAINT col_bin_col_rec FOREIGN KEY ( collection_date,
                                                 waste_type_id,
                                                 contract_no )
        REFERENCES collection ( collection_date,
                                waste_type_id,
                                contract_no );

ALTER TABLE collection
    ADD CONSTRAINT collection_authorised_driver FOREIGN KEY ( truck_vin,
                                                              driver_no )
        REFERENCES authorised_driver ( truck_vin,
                                       driver_no );

ALTER TABLE bin_cost
    ADD CONSTRAINT contact_bin_cost FOREIGN KEY ( contract_no )
        REFERENCES contract ( contract_no );

ALTER TABLE contract_detail
    ADD CONSTRAINT contract_contract_cost FOREIGN KEY ( contract_no )
        REFERENCES contract ( contract_no );

ALTER TABLE collection
    ADD CONSTRAINT contract_detail_collection FOREIGN KEY ( waste_type_id,
                                                            contract_no )
        REFERENCES contract_detail ( waste_type_id,
                                     contract_no );

ALTER TABLE authorised_driver
    ADD CONSTRAINT driver_authorised_driver FOREIGN KEY ( driver_no )
        REFERENCES driver ( driver_no );

ALTER TABLE ownerline
    ADD CONSTRAINT owner_ownerline FOREIGN KEY ( owner_id )
        REFERENCES owner ( owner_id );

ALTER TABLE bin
    ADD CONSTRAINT property_bin FOREIGN KEY ( property_id )
        REFERENCES property ( property_id );

ALTER TABLE ownerline
    ADD CONSTRAINT property_ownerline FOREIGN KEY ( property_id )
        REFERENCES property ( property_id );

ALTER TABLE property
    ADD CONSTRAINT street_property FOREIGN KEY ( street_id,
                                                 auth_name )
        REFERENCES street ( street_id,
                            auth_name );

ALTER TABLE authorised_driver
    ADD CONSTRAINT truck_authorised_driver FOREIGN KEY ( truck_vin )
        REFERENCES truck ( truck_vin );

ALTER TABLE bin_type
    ADD CONSTRAINT waste_type_bin_type FOREIGN KEY ( waste_type_id )
        REFERENCES waste_type ( waste_type_id );

ALTER TABLE contract_detail
    ADD CONSTRAINT waste_type_contract_cost FOREIGN KEY ( waste_type_id )
        REFERENCES waste_type ( waste_type_id );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            16
-- CREATE INDEX                             0
-- ALTER TABLE                             41
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0