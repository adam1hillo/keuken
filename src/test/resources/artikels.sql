insert into artikels (naam, aankoopprijs, verkoopprijs, soort, houdbaarheid, artikelgroepId)
values ('test', 10, 11, 'F', 3, (select id from artikelgroepen where naam = 'test'));