package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 */
public class KonsolenVideospielTest extends AbstractMediumTest
{

    private static final String BEZEICHNUNG = "KonsolenVideospiel";
    private static final String SYSTEM = "System";
    private KonsolenVideospiel _videoSpiel;

    @Before
    public void setUp()
    {
        _videoSpiel = getMedium();
    }

    @Test
    @Override
    public void testBerechneMietgebuehr()
    {
        KonsolenVideospiel medium = getMedium();
        Datum tag1 = new Datum(1, 1, 1);
        Datum tag3 = new Datum(3, 1, 1);
        Datum tag5 = new Datum(5, 1, 1);
        Datum tag7 = new Datum(7, 1, 1);
        assertEquals(new Geldbetrag(200),
                medium.berechneMietgebuehr(tag1, tag1));
        assertEquals(new Geldbetrag(900),
                medium.berechneMietgebuehr(tag1, tag3));
        assertEquals(new Geldbetrag(900),
                medium.berechneMietgebuehr(tag1, tag5));
        assertEquals(new Geldbetrag(1600),
                medium.berechneMietgebuehr(tag1, tag7));
    }

    @Test
    public void testeKonstruktoren()
    {
        assertEquals(SYSTEM, _videoSpiel.getSystem());
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        String videospielBezeichnung = BEZEICHNUNG;
        assertEquals(videospielBezeichnung, _videoSpiel.getMedienBezeichnung());
    }

    @Override
    protected KonsolenVideospiel getMedium()
    {
        return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
