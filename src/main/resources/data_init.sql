Insert into DIPBA.RISC (ID,DESCRIPCIO) values ('1','Vulnerabilitat');
Insert into DIPBA.RISC (ID,DESCRIPCIO) values ('2','Risc');
Insert into DIPBA.RISC (ID,DESCRIPCIO) values ('3','Alt Risc');
Insert into DIPBA.FREQUENCIA (ID,DESCRIPCIO) values ('1','Ocasional');
Insert into DIPBA.FREQUENCIA (ID,DESCRIPCIO) values ('2','Freq�ent');
Insert into DIPBA.FREQUENCIA (ID,DESCRIPCIO) values ('3','Continua');
Insert into DIPBA.AMBIT (ID,DESCRIPCIO) values ('1','Autonomia');
Insert into DIPBA.GRAVETAT (ID,DESCRIPCIO) values ('1','Baixa');
Insert into DIPBA.GRAVETAT (ID,DESCRIPCIO) values ('2','Moderada');
Insert into DIPBA.GRAVETAT (ID,DESCRIPCIO) values ('3','Alta');
Insert into DIPBA.FACTOR (ID,DESCRIPCIO) values ('1','La malaltia causa del d�ficit d''autonomia est� diagnosticada i en tractament');
Insert into DIPBA.FACTOR_GRAVETAT (ID,GRAVETAT,FACTOR,AMBIT) values ('1','1','1','1');
Insert into DIPBA.SITUACIO_SOCIAL (ID,SOCIAL,DEFINICIO,AMBIT) values ('1','Manca d�autonomia personal a les  activitats b�siques de la vida diaria  (A.1)','Situaci� en qu� la persona no pot desenvolupar les activitats b�siques de la vida diaria (alimentaci�, higiene, seguiment m�dic i farmacol�gic, mobilitat personal, descans, etc.), independentment de la causa que la motiva: malaltia f�sica o ps�quica, trastorn mental, deteriorament cognitiu, discapacitat f�sica, ps�quica,  intel�lectual o sensorial, etc.','1');
Insert into DIPBA.FREQUENCIA_GRAVETAT (ID,SITUACIO_SOCIAL,EVIDENCIA,GRAVETAT) values ('1','1','Necessita suport/atenci�/supervisi� en algun moment puntual de la setmana/dia','1');
Insert into DIPBA.CRITERI (ID,EVIDENCIA,FREQUENCIA, RISC,FREQUENCIA_GRAVETAT) values ('1','P�rdua sobtada per causes sobrevingudes i possiblitats de millora','1','1','1');
Insert into DIPBA.CRITERI (ID,EVIDENCIA,FREQUENCIA, RISC,FREQUENCIA_GRAVETAT) 
					values ('2','Cr�nica, sense possiblitats de millora','2','3','1');