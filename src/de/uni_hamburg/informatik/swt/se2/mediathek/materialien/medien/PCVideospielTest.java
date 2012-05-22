package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 */
public class PCVideospielTest extends AbstractMediumTest
{
    private static final String BEZEICHNUNG = "PCVideospiel";
    private static final String SYSTEM = "System";
    private PCVideospiel _videoSpiel;

    @Before
    public void setUp()
    {
        _videoSpiel = getMedium();
    }

    @Test
    @Override
    public void testBerechneMietgebuehr()
    {
        PCVideospiel medium = getMedium();
        Datum tag1 = new Datum(1, 1, 1);
        Datum tag7 = new Datum(7, 1, 1);
        Datum tag8 = new Datum(8, 1, 1);
        Datum tag12 = new Datum(12, 1, 1);
        Datum tag13 = new Datum(13, 1, 1);
        assertEquals(new Geldbetrag(200),
                medium.berechneMietgebuehr(tag1, tag1));
        assertEquals(new Geldbetrag(200),
                medium.berechneMietgebuehr(tag1, tag7));
        assertEquals(new Geldbetrag(700),
                medium.berechneMietgebuehr(tag1, tag8));
        assertEquals(new Geldbetrag(700),
                medium.berechneMietgebuehr(tag1, tag12));
        assertEquals(new Geldbetrag(1200),
                medium.berechneMietgebuehr(tag1, tag13));
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
    protected PCVideospiel getMedium()
    {
        return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
    }

}
