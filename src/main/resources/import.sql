insert into ADRESSES (code_postal, rue, ville) values ('29000', '3, rue des lilas', 'Quimper');
insert into ADRESSES (code_postal, rue, ville) values ('56000', '74, rue Hubert Ben Kemoun', 'Vannes');
insert into ADRESSES (code_postal, rue, ville) values ('44000', '10, rue Michel Renard', 'Nantes');
insert into ADRESSES (code_postal, rue, ville) values ('56000', '8, place aux Nouilles', 'Vannes');

insert into CLIENTS (no_adresse, no_telephone, email, nom, prenom) values (3, '0505050505', 'michel.pineau@eni.fr', 'Pineau', 'Michel');
insert into CLIENTS (no_adresse, no_telephone, email, nom, prenom) values (2, '0202020202', 'mireille.alji@eni.fr', 'Alji', 'Mireille');
insert into CLIENTS (no_adresse, no_telephone, email, nom, prenom) values (4, '0404040404', 'roseline.piacro@eni.fr', 'Piacro', 'Roseline');

insert into GENRES (libelle) values ('plateau');
insert into GENRES (libelle) values ('strategie');
insert into GENRES (libelle) values ('famille');
insert into GENRES (libelle) values ('deck-building');
insert into GENRES (libelle) values ('cartes');

insert into JEUX (titre,reference,age_min,description,duree,tarif_jour) values ('Domino','DOM-001',7,'Le jeu du domino dans toute sa splendeur',45,2.5);

insert into JEUX (titre,reference,age_min,description,duree,tarif_jour) values ('Echec','ECH-001',4,'Qui ne connait pas ce jeu ?',45,1.5);

insert into JEUX (titre,reference,age_min,description,duree,tarif_jour) values ('Magic: The Gathering','MTG-001',12,'Un bon jeu',45,6.5);

insert into JEUX_GENRES (no_jeu, no_genre) values (1, 1);
insert into JEUX_GENRES (no_jeu, no_genre) values (1, 2);

insert into JEUX_GENRES (no_jeu, no_genre) values (2, 1);
insert into JEUX_GENRES (no_jeu, no_genre) values (2, 2);

insert into JEUX_GENRES (no_jeu, no_genre) values (3, 2);
insert into JEUX_GENRES (no_jeu, no_genre) values (3, 3);
insert into JEUX_GENRES (no_jeu, no_genre) values (3, 4);
insert into JEUX_GENRES (no_jeu, no_genre) values (3, 5);

insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 1, '0000000000001');

insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 2, '1000000000001');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (0, 2, '1000000000002');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 2, '1000000000003');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (0, 2, '1000000000004');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 2, '1000000000005');

insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000001');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000002');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000003');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000004');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000005');
insert into EXEMPLAIRES (louable, no_jeu, codebarre) values (1, 3, '2000000000006');

insert into LOCATIONS (date_debut, date_retour, no_client, no_exemplaire) values ('18/06/2026', '19/06/2026', 1, 7);
insert into LOCATIONS (date_debut, date_retour, no_client, no_exemplaire) values ('18/06/2026', '20/06/2026', 1, 8);
insert into LOCATIONS (date_debut, date_retour, no_client, no_exemplaire) values ('18/06/2026', '21/06/2026', 1, 9);
insert into LOCATIONS (date_debut, date_retour, no_client, no_exemplaire) values ('18/06/2026', '22/06/2026', 1, 10);
insert into LOCATIONS (date_debut, date_retour, no_client, no_exemplaire) values ('18/06/2026', null, 1, 11);

INSERT INTO roles (no_role, libelle) values (1,'EMPLOYE"');
INSERT INTO utilisateurs (login, password) values ('michel','$2a$10$z3e2QxSZjXrGpugKeMoCxeTxGHzf6g.7d3q7jLcvuuJpDMhU9Cdwi');
INSERT INTO utilisateurs_roles (no_role, no_utilisateur) values (1,1);