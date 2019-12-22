
# **Arkkitehtuurikuvaus**

## **Rakenne**

Ohjelma koostuu kahdesta paketista, joista yksi hallitsee käyttöliittymää ja toinen sovelluslogiikkaa. Kumpikin näistä sijaitsee paketin sudokuapp alaisuudessa, jota alla oleva kuva ei ilmoita.

![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/PakkausRakenne.png)

Käyttöliittymä on toteutettu *JavaFx:llä*, mikä hieman vaikeuttaa näiden kahden välistä tietojen hallintaa, jonka vuoksi käyttöliittymä joutuu pitämään myös joitakin arvoja itse tiedossa.


## **Käyttöliittymä**

Käyttöliittymä koostuu kolmesta näkymästä

  * päävalikko
  * parhaat ajat
  * peli

Jokainen näistä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html) olionaan. Yksi näistä on aina sijoitettuna ohjelman [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Näistä peli on rakennettu luokassa [sudokuapp.ui.SudokuInterface.](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudokuapp/ui/SudokuInterface.java) sen laajuuden vuoksi ja muut luokassa [sudokuapp.ui.MainMenu.](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudokuapp/ui/MainMenu.java) Sovellus käynnistyy luokasta [sudokuapp.ui.Main.](https://github.com/SamiP7/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudokuapp/ui/Main.java), missä se ensin näyttää päävalikon.

Käyttöliittymää on pyritty eristämään mahdollisimman paljon sovelluslogiikasta, mutta tämä joutuu kuitenkin pitämään joitakin tietoja itse yllä, sillä sovelluslogiikka ei tähän pysty luokkien olioiden erilaisuuksien vuoksi. Suurin osa ohjelman tärkeistä toiminnallisuuksista saa käyttöliittymä kummiskin sovelluslogiikalta, tai vähintään siltä saamia tietoja, joita se pitää muistissa.

Kun pelissä tehdään siirtoja, ohjelma tarkastaa että onko siirto oikein ja onko taulu täynnä. Jos siirto ei ole oikein, niin merkkaa se ruudun punaisella, ja jatkaa tätä niin pitkään kunnes kaikki virhesiirron jälkeen olleet siirrot on poistettu ja myös itse virhesiirto. Kun taulu on täynnä, ilmoittaa ohjelma onko ratkaisu oikein taikka väärin. Jos ratkaisu on oikein, tallettaa peli siihen käytetyn ajan tietokantaan ja pistää kaikki muut napit paitsi paluun päänäkymään pois käytöstä. Aina jos jotain lukua on lisätty 9 taulukkoon, pistää peli sen nappulan pois käytöstä, jotta ei vahingossakaan lisäisi lukuja liikaa. Tämä myös hyvin näyttää pelaajalle, jos on kaikki mahdolliset luvut tästä jo lisännyt. 

## **Sovelluslogiikka**

Sovelluksen tärkeät metodit suoritetaan kaikki luokassa Table. Tässä esim. luodaan taulukko, tarkistetaan sen oikeellisuus ja lisätään alkioita siihen. Näitä metodeja ovat esim.

* createAnswer()
* createSudokuFromAnswer()
* checkIfCorrect(int[][] sudokuTable)
* addNumber(int y, int x, int number)

Luokassa Hiscores alustetaan tietokanta ja lisätään tuloksia siihen. Se myös palauttaa 10 parasta aikaa hakemalla ne tietokannasta. Tiedon haku ei noudata DAO-mallia, sillä kerättävä tieto on niin vähäistä ettei tuntunut järkevältä alkaa tekemään tätä. Sovellus ei kuitenkaan suoraan käytä tietokanna tietoa vaan palauttaa sen aina erillisenä oliona.

Sovelluksen käynnistyttyä luo tämä sille uuden tietokannan projektikansioon johon se kerää näitä suoritus aikoja.

## **Päätoiminnalisuudet**

Kuvataan muutamia sovelluksen päätoimintoja.

**sudokun luonti**

Kun päävalikossa painetaan nappia *New game* alkaa luokka *Table* suorittamaan metodia *createAnswer()* kaavio mukaisesti. Table kutsuu tätä metodia rekursiivisesti niin pitkään, kunnes *checkIfCorrect()* palauttaa true. Kun vastaus on saatu, poistetaan tästä vastauksesta satunnainen määrä lukuja, jonka jälkeen meillä on valmis sudoku pulma.

![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Sekvenssikaavio.png)

Kun olemme luoneet sudokun, määrittää luokka *SudokuInterface* sille scene olion joka sijoitetaan luokan *MainMenu* stage olioon.


**luvun lisäys sudokuun**

Oletetaan, että meillä on jo luokka Table. Kaavio näyttää mitä eri muuttujia kutsutaan/muutetaan luokassa kun lisätään luku sudokuun.

![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/addNumber.png)

Pelin käyttöliittymässä tämä toimii aivan samalla tavalla, paitsi sen täytyy pitää kirjaa itse aiemmista siirroista tallentamalla nappuloiden aikaisempia arvoja ja värejä listaan. Tämän lisäksi tarkastetaan onko luvun lisäyksen jälkeen sudoku valmis. Jos on, ilmoitetaan onko ratkaisu oikein vai väärin.

**siirron peruminen**

Kun perumme siirron, tarkastamme ensin onko lista *previousMove* tyhjä. Jos ei ole, otetaan listasta kolme ensimmäistä arvoa ja sidotaan ne muuttujiin x, y ja value. Tämän jälkeen kasvatamme mapin *amountLeft* kohtaa sudokuTable[y][x] yhdellä. Sitten muutamme täksi arvoksi value ja vähennämme mapista *amountLeft* kohtaa value arvolla 1.

Tämäkin toimii käyttöliittymässä samalla tavalla, paitsi meidän vain täytyy pitää kirjalla myös nappuloita joihin siirtoja on tehty, niiden arvoja ja värejä.

## **Heikkouksia**

Ohjelman päätoimminnallisuudet sijaitsevat vain yhdessä luokassa, joten tätä pitäisi pystyä eriyttämään hieman, jotta ohjelmaa olisi helpompi laajentaa. Tämä myös hankoitti huomattavasti erilaisten metodien kuvausta. Datan säilöminen erilaisista siirroista olisi voinut toteuttaa paremmin, vaikka kuvaamalla jokaista siirtoa omana olionaan. Pelin käyttöliittymän olisi voinut toteuttaa eri tavalla, sillä nykyisellä on tietoa hankala säilöä, jonka vuoksi oli turhan hankalaa esim. toteuttaa mahdollisuus aiemman pelin jatkoon sovelluksen suljettua.

Ohjelmassa on jäänyt paljon toisteista koodia if-else-lauseiden sisällä, mutta tätä on ollut hankala välttää ottaen huomioon sovelluksen tarkoituksen. Tätä olisi voinut kuitenkin pyrkiä korjaamaan metodilla, joka korvaisi copy-paste koodin.
