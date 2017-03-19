package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaAlleNolla() {
        varasto.lisaaVarastoon(-1);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaLiikaa() {
        varasto.lisaaVarastoon(15);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaAlleNolla() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaa() {
        varasto.lisaaVarastoon(5);
        double varastosta = varasto.otaVarastosta(6);

        assertEquals(5, varastosta, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenKonstruktori() {
        varasto = new Varasto(-1);
        double tilavuus = varasto.getTilavuus();

        assertEquals(0, tilavuus, vertailuTarkkuus);
    }
    
    @Test
    public void nollaKonstruktori() {
        varasto = new Varasto(0);
        double tilavuus = varasto.getTilavuus();

        assertEquals(0, tilavuus, vertailuTarkkuus);
    }
    
    @Test
    public void kaksiSamaaParametriaKonstruktorissa() {
        varasto = new Varasto(1, 1);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void isompiAlkusaldoKuinTilavuusKonstruktorissa() {
        varasto = new Varasto(1,2);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusKahdenKonstruktorissa() {
        varasto = new Varasto(-1,2);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisetParametritKonstruktorissa() {
        varasto = new Varasto(-1,-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusaldoKonstruktorissa() {
        varasto = new Varasto(1,-1);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto = new Varasto(10, 8);

        assertEquals("saldo = 8.0, vielä tilaa 2.0", "" + varasto);
    }
}