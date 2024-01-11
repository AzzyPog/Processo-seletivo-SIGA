INSERT INTO SituacaoMatricula(id, nome, codigo) VALUES
                                                    (1, 'Ativo', 'A01'),
                                                    (2, 'Trancado', 'T01'),
                                                    (3, 'Formado', 'F01'),
                                                    (4, 'Cancelado', 'C01');


INSERT INTO Aluno (nome, matricula, hobbies, situacaoMatricula_id) VALUES
                                                 ('Renato', '123456789', 'instrumentos', 1),
                                                 ('Alberto', '000000001', 'Jogar Monopoly, ouvir Samba e tocar clarinete.', 2),
                                                 ('Bianca', '000000002', 'Jogar War, ouvir Rock e tocar guitarra', 3),
                                                 ('Carlos', '000000003', 'Jogar Banco Imobiliário, ouvir MPB e tocar violão', 4),
                                                 ('Daniela', '000000004', 'Jogar Xadrez, ouvir Jazz e tocar piano', 1),
                                                 ('Eduardo', '000000005', 'Jogar Gamão, ouvir Blues e tocar gaita', 4);