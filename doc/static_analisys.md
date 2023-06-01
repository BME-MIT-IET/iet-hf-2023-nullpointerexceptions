**Github** 

A kód feltöltése és elrendezése a repository-ba. A projekt ezelőtt is github-on volt verzió kezelve, viszont pár dolgot ki kellett javítani a projekt szerkezetén. 

**SonarLint alapú elemzés** 

Az egész kódban átnéztem a hibákat. Párat hagytam csak ki melyeknek nem láttam szükségét, hogy kijavítsam.  

A projekt elején az alábbi hibákat adta ki a SonarLint: 

![](elotte.jpeg)

Miután az elemzést elvégeztem az alábbit kaptam: 

![](utana.jpeg)

Főbb hibák: logikailag hosszú függvények refaktorálása, kikommentelt kódok törlése, absztrakt osztályok bevezetése, override bevezetése, fölösleges függvények törlése,  

**SonarCloud alapú elemzés** 

Github Actions-be gradle segítségével automatikus ellenőrzésként beüzemeltem. Miután beüzemeltem észrevettem, hogy a SonarLint megfelelője, mely felhőt használ. Így végülis ez alapján nem végeztem el refaktorálást. 
