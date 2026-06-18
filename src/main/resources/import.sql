insert into ADRESSE (code_postal, rue, ville) values ('29000', '3, rue des lilas', 'Quimper');
insert into ADRESSE (code_postal, rue, ville) values ('56000', '74, rue Hubert Ben Kemoun', 'Vannes');
insert into ADRESSE (code_postal, rue, ville) values ('44000', '10, rue Michel Renard', 'Nantes');
insert into ADRESSE (code_postal, rue, ville) values ('56000', '8, place aux Nouilles', 'Vannes');

insert into CLIENT (no_adresse, no_telephone, email, nom, prenom) values (3, '0505050505', 'michel.pineau@eni.fr', 'Pineau', 'Michel');
insert into CLIENT (no_adresse, no_telephone, email, nom, prenom) values (2, '0202020202', 'mireille.alji@eni.fr', 'Alji', 'Mireille');
insert into CLIENT (no_adresse, no_telephone, email, nom, prenom) values (4, '0404040404', 'roseline.piacro@eni.fr', 'Piacro', 'Roseline');

insert into GENRE (libelle) values ('plateau');
insert into GENRE (libelle) values ('strategie');
insert into GENRE (libelle) values ('famille');

insert into JEU (titre,reference,age_min,description,duree,tarif_jour) values ('Domino','DOM-001',7,'Le jeu du domino dans toute sa splendeur',45,2.5);

insert into JEU_GENRE (no_jeu, no_genre) values (1, 1);
insert into JEU_GENRE (no_jeu, no_genre) values (1, 2);