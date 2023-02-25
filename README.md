# CaseStudy

Spring Boot aplikace s použitím Javy, která do inmemory databáze (H2) ukládá uchazeče a k nim vázané technologie. Každá vazba obsahuje úroveň a poznámku.
API používá formát JSON. Aplikace běží pod JRE 17.

Funkcionalita ( url jsou popsány pro případ lokálního použití ):

	Správa uchazečů:

	- seznam
        GET http://localhost:8080/candidateList

        - detail se seznamem technologií (včetně poznámky a úrovně znalosti)
        GET http://localhost:8080/candidate/<Jméno Uchazeče>

        - přidání
        POST http://localhost:8080/addCandidate/<Jméno Uchazeče>

        - odebrání
        DELETE http://localhost:8080/deleteCandidate/<Jméno Uchazeče>

        - úprava    (vzhledem k tomu, že uchazeč neobsahuje kromě jména žádná data, je implementováno jako přejmenování)
        PUT http://localhost:8080/modifyCandidate/<Jméno Uchazeče>/<Nové Jméno Uchazeče>

	Správa technologií:
	- seznam
	GET http://localhost:8080/techlist

	- detail
	GET http://localhost:8080/getTech/<id technologie>

	- přidání
	POST http://localhost:8080/addTech/<Jméno Uchazeče>/<Jméno Technologie>/<Úroveň>/<Poznámka>

	- odebrání
	DELETE http://localhost:8080/deleteTech/<Jméno Uchazeče>/<Jméno Technologie>

	- úprava  ( pouze úprava, pro přidání nové technologie je třeba použít přidání )
	PUT http://localhost:8080/modifyTech/<Jméno Uchazeče>/<Jméno Technologie>/<Úroveň>/<Poznámka>

	Bonus:
	- vyhledávání uchazečů dle technologie a minimální úrovně
	GET http://localhost:8080/findcandidates/<Jméno Technologie>/<Minimální úroveň>

	Bonus 2:
	- pro otestování možnost zaplnění databáze
	GET http://localhost:8080/populate
