## **Sudoku**
Sovelluksen tarkoituksena on tehdä toimiva sudokupeli, jossa on muutama yleisesti haluttu ominaisuus, kuten siirtojen peruminen.

## **Dokumentaatio**
[Alustava vaatimusmäärittely](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/SamiP7/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## **Releaset**
[Viikko 5](https://github.com/SamiP7/ot-harjoitustyo/releases/tag/Viikko5)
[Viikko 6](https://github.com/SamiP7/ot-harjoitustyo/releases/tag/Viikko6)
[Loppupalautus](https://github.com/SamiP7/ot-harjoitustyo/releases/tag/Loppupalautus)

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

### **JavaDoc**

JavaDoc generoidaan komennolla

`mvn javadoc:javadoc`

JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*
