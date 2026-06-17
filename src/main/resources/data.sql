insert into GENRE (libelle) values ('plateau');
insert into GENRE (libelle) values ('strategie');
insert into GENRE (libelle) values ('famille');

insert into JEU (
    titre,
    reference,
    age_min,
    description,
    duree,
    tarif_jour
)
values (
           'Domino',
           'DOM-001',
           7,
           'Le jeu du domino dans toute sa splendeur',
           45,
           2.5
       );

insert into JEU_GENRE (no_jeu, no_genre) values (1, 1);
insert into JEU_GENRE (no_jeu, no_genre) values (1, 2);