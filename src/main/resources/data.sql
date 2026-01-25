INSERT INTO "PUBLIC"."ESTADO" VALUES
(1, 'Sao Paulo', 'SP');

INSERT INTO "PUBLIC"."CIDADE" VALUES
(1, 'Botucatu', 'SP');

INSERT INTO "PUBLIC"."USUARIO" VALUES
(1, TRUE, 'lucio.souza@cadshotel.com', 'RECEPCIONISTA', '12345678'),
(2, TRUE, 'catiussia.lima@gmail.com', 'HOSPEDE', '12345678');

INSERT INTO "PUBLIC"."FUNCIONARIO" VALUES
(1, 'RECEPCIONISTA', '558.716.150-15', 'Lucio Souza', 1);            

INSERT INTO "PUBLIC"."HOSPEDE" VALUES
(1, '43627306805', DATE '1995-01-20', 'Vinicius Dionysio', '(14) 99677-9702', 1, NULL),
(2, '827.378.530-01', DATE '1993-01-01', 'Catiussia Lima', '(15) 99999-9999', 1, 2);

INSERT INTO "PUBLIC"."COMODIDADE" VALUES
(1, 'Ar condicionado 9000 btus', 'Ar Condicionado'),
(2, 'Frigobar 45 L', 'Frigobar 45 L'),
(3, 'Banheira de hidromassagem', 'Banheira de hidromassagem');

INSERT INTO "PUBLIC"."CATEGORIA_QUARTO" VALUES
(1, 'Quarto Luxo Casal', 2, 'Luxo Casal', 'EXTERNA', 1200.00);

INSERT INTO "PUBLIC"."CATEGORIA_QUARTO_COMODIDADES" VALUES
(1, 1),
(1, 2),
(1, 3);
