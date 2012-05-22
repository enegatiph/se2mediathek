package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

//Die in den Testfällen verwendeten assert-Anweisungen werden über
//einen sogenannten statischen Import bereitgestellt, zum Beispiel:
//import static org.junit.Assert.assertEquals;
//
//Um die Annotationen @Test und @Before verwenden zu können, müssen diese
//importiert werden, zum Beispiel:
//import org.junit.Test;

public class CDTest extends AbstractMediumTest
{

    private static final String CD_BEZEICHNUNG = "CD";
    private static final String INTERPRET = "CD Interpret";
    private static final int LAENGE = 100;
    private CD _cd1;
    private CD _cd2;

    @Before
    // Die setUp()-Methode wird nicht mehr anhand ihres Namens erkannt, sondern
    // anhand der Annotation @Before.
    public void setUp()
    {
        _cd1 = getMedium();
        _cd2 = getMedium();
    }

    @Override
    @Test
    public void testGetMedienBezeichnung()
    {
        String cdBezeichnung = CD_BEZEICHNUNG;
        assertEquals(cdBezeichnung, _cd1.getMedienBezeichnung());
    }

    @Test
    // Alle Testmethoden erhalten die Annotation @Test. Dafür müssen diese nicht
    // mehr mit test im Namen beginnen. Dies wird jedoch aus Gewohnheit
    // oft weiter verwendet.
    public void testKonstruktor()
    {
        assertEquals(LAENGE, _cd1.getSpiellaenge());
        assertEquals(INTERPRET, _cd1.getInterpret());
    }

    @Test
    /*
     * Von ein und der selben CD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden können.
     */
    public void testEquals()
    {
        assertFalse("Mehrere Exemplare der gleichen CD sind gleich",
                _cd1.equals(_cd2));
        assertTrue("Mehrere Exemplare der gleichen CD sind ungleich",
                _cd1.equals(_cd1));
    }

    @Override
    protected CD getMedium()
    {
        return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
    }

}
