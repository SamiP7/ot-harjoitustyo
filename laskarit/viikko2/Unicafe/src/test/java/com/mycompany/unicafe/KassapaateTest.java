package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Maksukortti kortti;
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        this.kortti = new Maksukortti(1000);
        this.kassa = new Kassapaate();
    }
    
    @Test
    public void kassaOlemassa() {
        assertTrue(kassa!=null);
    }
    
    @Test
    public void rahaaOikeaMaara() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lounaitaMyytyOikeaMaara() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoEdullinen() {
        kassa.syoEdullisesti(10);
        kassa.syoEdullisesti(-100);
        kassa.syoEdullisesti(240);
        kassa.syoEdullisesti(1000);
        assertEquals(100480, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoMaukas() {
        kassa.syoMaukkaasti(-20);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisOsto() {
        kassa.syoMaukkaasti(1000);
        kassa.syoEdullisesti(200);
        kassa.syoMaukkaasti(-300);
        kassa.syoEdullisesti(300);
        kassa.syoMaukkaasti(400);
        assertEquals(101040, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullistenAterioidenOstoKortilla() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(3, kassa.edullisiaLounaitaMyyty());
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(4, kassa.edullisiaLounaitaMyyty());
        assertEquals(40, kortti.saldo());
    }
    
    @Test
    public void maukkaidenAterioidenOstoKortilla() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortille() {
        kassa.lataaRahaaKortille(kortti, -10);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void muutamanAterianOstoKortilla() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(360, kortti.saldo());
    }

}
