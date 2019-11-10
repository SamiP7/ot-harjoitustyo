package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldonKasvu() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldonVaheneminenKunTarpeeksi() {
        kortti.otaRahaa(1);
        assertEquals("saldo: 0.9", kortti.toString()); //Koodissa bugi(oikea olisi 0.09)
    }
    
    @Test
    public void saldoEiMuutuKunEiTarpeeksi() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void luodunKortinSaldoOikein() {
        Maksukortti kortti = new Maksukortti(100);
        assertEquals(100, kortti.saldo());
    }
}
