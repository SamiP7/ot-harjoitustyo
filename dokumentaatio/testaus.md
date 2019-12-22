## Testausdokumentti
Ohjelmaa on testattu käsin tehdyin ja automatisoiduilla testeillä JUnitilla.

### Automatisoidut testit
**Table luokka**

Luokkaa testaa [TableTest](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logictests/TableTest.java) jonka suorittamat testit tutkivat pelin erilaisia ominaisuuksia. Eli saammeko esim. lisätä luvun kohtaan, joka on pelin valmiiksi annettu kohta, taikka onnistuuko pelin tekeminen.

**Hiscores luokka**

[Luokassa](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logictests/HiscoresTest.java) teemme uuden tietokannan testien suoritukseen, jotta alkuperäinen data ei ole muutoksen vaarassa. Luokka testaa onko palautetut ajat oikeita ja ovatko ne oikeassa järjestyksessä. Testien jälkeen tilapainen tietokanta poistetaan luomalla tästä tiedosto ja poistamalla se.

**Testauskattavuus**

Sovelluksen testien rivikattavuus on 82% ja haaraumakattavuus vain 67%.
![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png)

Alhaiseen rivi-ja haaraumakattavuuteen syynä on tilanteiden testaus koodissa, joita ei ole pahemmin mahdollista edes testata. Esim. metodi checkIfCorrect() voi palauttaa kymmenessä eri kohdassa false, kun se tarkistaa onko sudoku oikein. Metodin tarkoitus on lopulta vain tarkistaa onko tehty ratkaisu oikein ja se tarvitsee tähän hyvin monta erilaista haaraa kun sen täytyy tutkia kaikki 3x3 alueet. Jos mikään näiden alueiden ehdoista ei toteudu, on ratkaisu oikein. Sama pätee periaatteessa kanssa checkIfCorrectTemp() metodiin. Koska näillä metodeilla on niin paljon haaroja, laskee se testien rivi- ja haaraumakattavuutta roimasta, varsinkin kun kyseessä olevat metodit ovat yhdessä lähes puolet luokasta.

### Manuaaliset testit

**Asennus ja pelaaminen**

Sovellus on haettu ja testattu [käyttöohjeen](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Windows- OSX- ja Linux-ympäristössä. Ohjelma ja sen testit toimivat kaikissa näissä, vaikka Linux-ympäristössä on ohjelman käyttöliittymä hyvin huonolaatuinen verrattuna esim. Windows-ympäristöön.

### Sovellukseen jääneitä ongelmia

Sovellukseen jäi paljon copy-paste koodia, sillä tämän poistoon ei jäänyt tarpeeksi aikaa. Sovelluksen luokkarakenteessa on parantamisen varaa, sillä tällä hetkellä vain yksi luokka tekee lähes kaiken peliin liittyvät metodit. Käyttöliittymää ei saatu poistettua checkstyle tarkastelun alta, sillä ohjelma antoi aina virheilmoituksia tätä tehdessä.
