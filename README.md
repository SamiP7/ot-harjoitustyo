## **Sudoku**
Sovelluksen tarkoituksena on tehdä toimiva sudokupeli, jossa on muutama yleisesti haluttu ominaisuus, kuten siirtojen peruminen.

## **Dokumentaatio**
[Alustava vaatimusmäärittely](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## **Releaset**
[Viikko 5](https://github.com/SamiP7/ot-harjoitustyo/releases)

## **Komentorivitoiminnot**

### **Testaus**

Testit suoritetaan komennolla

`mvn test`

Testtikattavuusraportti luodaan komennolla

`mvn test jacoco:report`

Raporttia voidaan tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*

### **Suoritettavan jarin generointi**

Komento

`mvn package`

### **Checkstyle**

Checkstyle tarkistukset voidaan suorittaa komennolla

`mvn jxr:jxr checkstyle:checkstyle`

Virheilmoitukset voidaan löytää tiedostosta *target/site/checkstyle.html*

**HUOM**

Tällä hetkellä ohjelma saattaa vaatia heap sizen kasvatusta, sillä toimivaa sudokua on vaikea saada tehtyä ilman vaativaa rekursiota.
