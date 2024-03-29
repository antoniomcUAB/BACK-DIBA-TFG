-- Generado por Oracle SQL Developer Data Modeler 4.1.0.881
--   en:        2018-12-10 16:28:39 CET
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




DROP TABLE AMBIT CASCADE CONSTRAINTS ;

DROP TABLE CONTEXTUALITZACIO CASCADE CONSTRAINTS ;

DROP TABLE CONTEXT_DIAGNOSTIC CASCADE CONSTRAINTS ;

DROP TABLE CRITERI CASCADE CONSTRAINTS ;

DROP TABLE DIAGNOSTIC CASCADE CONSTRAINTS ;

DROP TABLE ECONOMIA CASCADE CONSTRAINTS ;

DROP TABLE EXPEDIENT CASCADE CONSTRAINTS ;

DROP TABLE EX_REFERENCIA CASCADE CONSTRAINTS ;

DROP TABLE FACTOR CASCADE CONSTRAINTS ;

DROP TABLE FACTOR_CONTEXT CASCADE CONSTRAINTS ;

DROP TABLE FACTOR_GRAVETAT CASCADE CONSTRAINTS ;

DROP TABLE FREQUENCIA CASCADE CONSTRAINTS ;

DROP TABLE FREQUENCIA_GRAVETAT CASCADE CONSTRAINTS ;

DROP TABLE GRAVETAT CASCADE CONSTRAINTS ;

DROP TABLE MODALITAT_DIAGNOSTIC CASCADE CONSTRAINTS ;

DROP TABLE PERSONA CASCADE CONSTRAINTS ;

DROP TABLE QUESTIO CASCADE CONSTRAINTS ;

DROP TABLE RISC CASCADE CONSTRAINTS ;

DROP TABLE SITUACIO_SOCIAL CASCADE CONSTRAINTS ;

DROP TABLE TIPUS_PERSONA CASCADE CONSTRAINTS ;

CREATE TABLE AMBIT
  ( id NUMBER NOT NULL , descripcio VARCHAR2 (150)
  ) ;
ALTER TABLE AMBIT ADD CONSTRAINT AMBIT_PK PRIMARY KEY ( id ) ;

CREATE TABLE CONTEXTUALITZACIO
  (
    id          NUMBER NOT NULL ,
    expedient   NUMBER NOT NULL ,
    context     NUMBER NOT NULL ,
    membre_unic CHAR (1) ,
    questio     VARCHAR2 (100) NOT NULL ,
    mes_UC      CHAR (1) ,
    persona     NUMBER NOT NULL
  ) ;
ALTER TABLE CONTEXTUALITZACIO ADD CONSTRAINT CONTEXTUALITZACIO_PK PRIMARY KEY ( id ) ;

CREATE TABLE CONTEXT_DIAGNOSTIC
  (
    id         NUMBER NOT NULL ,
    descripcio VARCHAR2 (250) ,
    ambit      VARCHAR2 (50)
  ) ;
ALTER TABLE CONTEXT_DIAGNOSTIC ADD CONSTRAINT CONTEXT_DIAGNOSTIC_PK PRIMARY KEY ( id ) ;

CREATE TABLE CRITERI
  (
    id                 NUMBER NOT NULL ,
    evidencia          VARCHAR2 (500) ,
    frequencia         NUMBER NOT NULL ,
    risc               NUMBER NOT NULL ,
    frequenciaGravetat VARCHAR2 (32) NOT NULL
  ) ;
ALTER TABLE CRITERI ADD CONSTRAINT CRITERI_PK PRIMARY KEY ( id ) ;

CREATE TABLE DIAGNOSTIC
  (
    id         NUMBER NOT NULL ,
    expedient  NUMBER NOT NULL ,
    modalitat  VARCHAR2 (32) NOT NULL ,
    questio    VARCHAR2 (32) NOT NULL ,
    apl        CHAR (1) ,
    persona    NUMBER NOT NULL ,
    risc       NUMBER NOT NULL ,
    gravetat   NUMBER NOT NULL ,
    frequencia NUMBER NOT NULL
  ) ;
ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_PKv1 PRIMARY KEY ( id ) ;

CREATE TABLE ECONOMIA
  (
    id_expedient NUMBER NOT NULL ,
    valor        CHAR (1) ,
    factor       NUMBER NOT NULL
  ) ;
ALTER TABLE ECONOMIA ADD CONSTRAINT ECONOMIA_PK PRIMARY KEY ( id_expedient ) ;

CREATE TABLE EXPEDIENT
  (
    id           NUMBER NOT NULL ,
    expedient    VARCHAR2 (25) NOT NULL ,
    professional VARCHAR2 (100) ,
    nom          VARCHAR2 (100) ,
    data         TIMESTAMP WITH LOCAL TIME ZONE ,
    observacions VARCHAR2 (250)
  ) ;
ALTER TABLE EXPEDIENT ADD CONSTRAINT EXPEDIENT_PK PRIMARY KEY ( id ) ;

CREATE TABLE EX_REFERENCIA
  (
    id_expedient NUMBER NOT NULL ,
    persona      VARCHAR2 (32) NOT NULL
  ) ;
ALTER TABLE EX_REFERENCIA ADD CONSTRAINT EX_REFERENCIA_PK PRIMARY KEY ( id_expedient ) ;

CREATE TABLE FACTOR
  ( id NUMBER NOT NULL , descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE FACTOR ADD CONSTRAINT FACTOR_PK PRIMARY KEY ( id ) ;

CREATE TABLE FACTOR_CONTEXT
  (
    id     VARCHAR2 (32) NOT NULL ,
    apl    CHAR (1) ,
    factor NUMBER NOT NULL ,
    ambit  NUMBER NOT NULL
  ) ;
ALTER TABLE FACTOR_CONTEXT ADD CONSTRAINT FACTOR_CONTEXT_PK PRIMARY KEY ( id ) ;

CREATE TABLE FACTOR_GRAVETAT
  (
    id       NUMBER NOT NULL ,
    gravetat NUMBER NOT NULL ,
    factor   NUMBER NOT NULL ,
    ambit    NUMBER NOT NULL
  ) ;
ALTER TABLE FACTOR_GRAVETAT ADD CONSTRAINT FACTOR_GRAVETAT_PK PRIMARY KEY ( id ) ;

CREATE TABLE FREQUENCIA
  ( id NUMBER NOT NULL , descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE FREQUENCIA ADD CONSTRAINT FREQUENCIA_PK PRIMARY KEY ( id ) ;

CREATE TABLE FREQUENCIA_GRAVETAT
  (
    id              VARCHAR2 (32) NOT NULL ,
    situacio_social VARCHAR2 (32) NOT NULL ,
    evidencia       VARCHAR2 (500) NOT NULL ,
    gravetat        NUMBER NOT NULL
  ) ;
ALTER TABLE FREQUENCIA_GRAVETAT ADD CONSTRAINT MODEL_PK PRIMARY KEY ( id ) ;

CREATE TABLE GRAVETAT
  ( id NUMBER NOT NULL , descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE GRAVETAT ADD CONSTRAINT GRAVETAT_PK PRIMARY KEY ( id ) ;

CREATE TABLE MODALITAT_DIAGNOSTIC
  (
    id         VARCHAR2 (32) NOT NULL ,
    descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE MODALITAT_DIAGNOSTIC ADD CONSTRAINT MODALITAT_DIAGNOSTIC_PK PRIMARY KEY ( id ) ;

CREATE TABLE PERSONA
  (
    id      VARCHAR2 (32) NOT NULL ,
    nom     VARCHAR2 (50) ,
    cognom1 VARCHAR2 (50) ,
    cognom2 VARCHAR2 (50)
  ) ;
ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_PK PRIMARY KEY ( id ) ;

CREATE TABLE QUESTIO
  (
    id         VARCHAR2 (32) NOT NULL ,
    codi       VARCHAR2 (12) ,
    descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE QUESTIO ADD CONSTRAINT QUESTIO_PK PRIMARY KEY ( id ) ;

CREATE TABLE RISC
  ( id NUMBER NOT NULL , descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE RISC ADD CONSTRAINT RISC_PK PRIMARY KEY ( id ) ;

CREATE TABLE SITUACIO_SOCIAL
  (
    id        VARCHAR2 (32) NOT NULL ,
    social    VARCHAR2 (500) ,
    definicio VARCHAR2 (500) ,
    ambit     NUMBER NOT NULL
  ) ;
ALTER TABLE SITUACIO_SOCIAL ADD CONSTRAINT SITUACIO_SOCIAL_PK PRIMARY KEY ( id ) ;

CREATE TABLE TIPUS_PERSONA
  (
    id         NUMBER NOT NULL ,
    descripcio VARCHAR2 (100)
  ) ;
ALTER TABLE TIPUS_PERSONA ADD CONSTRAINT TIPUS_PERSONA_PK PRIMARY KEY ( id ) ;

ALTER TABLE CONTEXTUALITZACIO ADD CONSTRAINT CONTX_CONTX_DIAG_FK FOREIGN KEY ( context ) REFERENCES CONTEXT_DIAGNOSTIC ( id ) ;

ALTER TABLE CONTEXTUALITZACIO ADD CONSTRAINT CONTX_EXPEDIENT_FK FOREIGN KEY ( expedient ) REFERENCES EXPEDIENT ( id ) ;

ALTER TABLE CONTEXTUALITZACIO ADD CONSTRAINT CONTX_TIPUS_PERSONA_FKv1 FOREIGN KEY ( persona ) REFERENCES TIPUS_PERSONA ( id ) ;

ALTER TABLE CRITERI ADD CONSTRAINT CRITERI_FREQUENCIA_FK FOREIGN KEY ( frequencia ) REFERENCES FREQUENCIA ( id ) ;

ALTER TABLE CRITERI ADD CONSTRAINT CRITERI_FREQUENCIA_GRAVETAT_FK FOREIGN KEY ( frequenciaGravetat ) REFERENCES FREQUENCIA_GRAVETAT ( id ) ;

ALTER TABLE CRITERI ADD CONSTRAINT CRITERI_RISC_FK FOREIGN KEY ( risc ) REFERENCES RISC ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_EXPEDIENT_FK FOREIGN KEY ( expedient ) REFERENCES EXPEDIENT ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_FREQUENCIA_FK FOREIGN KEY ( frequencia ) REFERENCES FREQUENCIA ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_GRAVETAT_FK FOREIGN KEY ( gravetat ) REFERENCES GRAVETAT ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_QUESTIO_FK FOREIGN KEY ( questio ) REFERENCES QUESTIO ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_RISC_FK FOREIGN KEY ( risc ) REFERENCES RISC ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAGNOSTIC_TIPUS_PERSONA_FK FOREIGN KEY ( persona ) REFERENCES TIPUS_PERSONA ( id ) ;

ALTER TABLE DIAGNOSTIC ADD CONSTRAINT DIAG_MOD_DIAG_FK FOREIGN KEY ( modalitat ) REFERENCES MODALITAT_DIAGNOSTIC ( id ) ;

ALTER TABLE ECONOMIA ADD CONSTRAINT ECONOMIA_EXPEDIENT_FK FOREIGN KEY ( id_expedient ) REFERENCES EXPEDIENT ( id ) ;

ALTER TABLE ECONOMIA ADD CONSTRAINT ECONOMIA_FACTOR_FK FOREIGN KEY ( factor ) REFERENCES FACTOR ( id ) ;

ALTER TABLE EX_REFERENCIA ADD CONSTRAINT EX_REFERENCIA_EXPEDIENT_FK FOREIGN KEY ( id_expedient ) REFERENCES EXPEDIENT ( id ) ;

ALTER TABLE EX_REFERENCIA ADD CONSTRAINT EX_REFERENCIA_PERSONA_FK FOREIGN KEY ( persona ) REFERENCES PERSONA ( id ) ;

ALTER TABLE FACTOR_CONTEXT ADD CONSTRAINT FACTOR_CONTEXT_AMBIT_FKv1 FOREIGN KEY ( ambit ) REFERENCES AMBIT ( id ) ;

ALTER TABLE FACTOR_CONTEXT ADD CONSTRAINT FACTOR_CONTEXT_FACTOR_FK FOREIGN KEY ( factor ) REFERENCES FACTOR ( id ) ;

ALTER TABLE FACTOR_GRAVETAT ADD CONSTRAINT FACTOR_GRAVETAT_AMBIT_FK FOREIGN KEY ( ambit ) REFERENCES AMBIT ( id ) ;

ALTER TABLE FACTOR_GRAVETAT ADD CONSTRAINT FACTOR_GRAVETAT_FACTOR_FK FOREIGN KEY ( factor ) REFERENCES FACTOR ( id ) ;

ALTER TABLE FACTOR_GRAVETAT ADD CONSTRAINT FACTOR_GRAVETAT_GRAVETAT_FK FOREIGN KEY ( gravetat ) REFERENCES GRAVETAT ( id ) ;

ALTER TABLE FREQUENCIA_GRAVETAT ADD CONSTRAINT FREQ_GRAVETAT_FK FOREIGN KEY ( gravetat ) REFERENCES GRAVETAT ( id ) ;

ALTER TABLE FREQUENCIA_GRAVETAT ADD CONSTRAINT FREQ_SITUACIO_SOCIAL_FK FOREIGN KEY ( situacio_social ) REFERENCES SITUACIO_SOCIAL ( id ) ;

ALTER TABLE SITUACIO_SOCIAL ADD CONSTRAINT SITUACIO_SOCIAL_AMBIT_FK FOREIGN KEY ( ambit ) REFERENCES AMBIT ( id ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            20
-- CREATE INDEX                             0
-- ALTER TABLE                             45
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
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
