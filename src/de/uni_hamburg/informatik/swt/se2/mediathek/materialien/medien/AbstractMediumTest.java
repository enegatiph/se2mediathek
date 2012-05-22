package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * Testklasse für AbstractMediumTest. Testklassen für konkrete 
 * Medienklassen (Beispielsweise DVDTest) sollen von dieser Testklasse 
 * erben. So werden für jede konkrete Medienklasse die hier definieren
 * Tests ausgeführt.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public abstract class AbstractMediumTest
{

    protected static final String KOMMENTAR = "Kommentar";

    protected static final int MIETGEBUEHR_PRO_TAG = 300;

    protected static final String TITEL = "Titel";

    @Test
    public final void testAbstractMedium()
    {
        Medium medium = getMedium();
        assertEquals(TITEL, medium.getTitel());
        assertEquals(KOMMENTAR, medium.getKommentar());
    }

    @Test
    public void testBerechneMietgebuehr()
    {
        Medium medium = getMedium();
        Datum tag1 = new Datum(1, 1, 1);
        Datum tag2 = new Datum(2, 1, 1);
        Datum tag7 = new Datum(7, 1, 1);
        assertEquals(new Geldbetrag(MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(tag1, tag1));
        assertEquals(new Geldbetrag(2 * MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(tag1, tag2));
        assertEquals(new Geldbetrag(7 * MIETGEBUEHR_PRO_TAG),
                medium.berechneMietgebuehr(tag1, tag7));
    }

    @Test
    public void testEquals()
    {
        Medium medium = getMedium();
        Medium medium2 = getMedium();
        assertFalse(medium.equals(medium2));
    }

    @Test
    public final void testGetFormatiertenString()
    {
        Medium medium = getMedium();
        assertNotNull(medium.getFormatiertenString());
    }
    
    @Test
    public final void testSetKommentar()
    {
        Medium medium = getMedium();
        medium.setKommentar("Kommentar2");
        assertEquals(medium.getKommentar(), "Kommentar2");
    }
    
    @Test
    public final void testSetTitel()
    {
        Medium medium = getMedium();
        medium.setTitel("Titel2");
        assertEquals(medium.getTitel(), "Titel2");
    }

    @Test
    public abstract void testGetMedienBezeichnung();

    /**
     * Gibt ein neues konkretes Medium-Objekt zurück.
     * Beispielsweise eine DVD in DVDTest
     */
    protected abstract Medium getMedium();
}
