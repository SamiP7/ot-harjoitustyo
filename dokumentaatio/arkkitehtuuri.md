
# **Arkkitehtuurikuvaus**

## **Rakenne**

Ohjelma koostuu kahdesta paketista, joista yksi hallitsee käyttöliittymää ja toinen sovelluslogiikkaa. 

![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/PakkausRakenne.png)

Käyttöliittymä on toteutettu *JavaFx:llä*, mikä hieman vaikeuttaa näiden kahden välistä tietojen hallintaa, jonka vuoksi käyttöliittymä joutuu pitämään myös joitakin arvoja itse tiedossa.


## **Käyttöliittymä**

Käyttöliittymä on itse peli, joka on toteutettu isona BorderPane-oliona, joka sisältää kaikki pelin nappulat ja itse laudan.

Käyttöliittymää on pyritty eristämään mahdollisimman paljon sovelluslogiikasta, mutta tämä joutuu kuitenkin pitämään joitakin tietoja itse yllä, sillä sovelluslogiikka ei tähän pysty luokkien olioiden erilaisuuksien vuoksi. Suurin osa ohjelman tärkeistä toiminnallisuuksista saa käyttöliittymä kummiskin sovelluslogiikalta, tai vähintään siltä saamia tietoja joita se pitää muistissa.

Kun pelissä tehdään siirtoja, ohjelma tarkastaa että onko siirto oikein ja onko taulu täynnä. Jos siirto ei ole oikein, niin merkkaa se ruudun punaisella, ja jatkaa tätä niin pitkään kunnes kaikki virhesiirron jälkeen olleet siirrot on poistettu ja myös itse virhesiirto. Kun taulu on täynnä, ilmoittaa ohjelma onko ratkaisu oikein taikka väärin.

## **Sovelluslogiikka**

Sovelluksen tärkeät metodit suoritetaan kaikki luokassa Table. Tässä esim. luodaan taulukko, tarkistetaan sen oikeellisuus ja lisätään alkioita siihen. Näitä metodeja ovat esim.

* createAnswer()
* createSudokuFromAnswer()
* checkIfCorrect(int[][] sudokuTable)
* addNumber(int y, int x, int number)

Seuraava kaavio pyrkii avaamaan sudokun luomista käyttöliittymää varten:

![](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Sekvenssikaavio.png)

Tämä on ohjelman tärkein toiminnallisuus ja myöhemmin näitä muita toiminnallisuuksia lisätään myös tänne.

## **Heikkouksia**

Ohjelman toimminnallisuudet sijaitsevat vain kahdessa eri luokassa, joten näitä pitäisi pystyä eriyttämään hieman. Varsinkin itse Table luokkaa pitää vielä hajauttaa, sillä se on vastuussa turhan monesta ohjelman toiminnallisuudesta.

Ohjelmassa on paljon toisteista koodia if-else-lauseiden sisällä, mutta tätä on erittäin hankala välttää kun ottaa huomioon ohjelman tarkoituksen. Toisteisuus on siis melkeinpä välttämätöntä, mutta pyrin korjaamaan tätä ainakin hieman.
