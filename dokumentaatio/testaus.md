## Testausdokumentti
Ohjelmaa on testattu käsin tehdyin ja automatisoiduilla testeillä JUnitilla.

### Automatisoidut testit
**Table luokka**

Luokkaa testaa [TableTest](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logictests/TableTest.java) jonka suorittamat testit tutkivat pelin erilaisia ominaisuuksia. Eli saammeko esim. lisätä luvun kohtaan, joka on pelin valmiiksi annettu kohta, taikka onnistuuko pelin tekeminen.

**Hiscores luokka**

[Luokassa](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logictests/HiscoresTest.java) teemme uuden tietokannan testien suoritukseen, jotta alkuperäinen data ei ole muutoksen vaarassa. Luokka testaa onko palautetut ajat oikeita ja ovatko ne oikeassa järjestyksessä. Testien jälkeen tilapainen tietokanta poistetaan luomalla tästä tiedosto ja poistamalla se.

**Testauskattavuus**
