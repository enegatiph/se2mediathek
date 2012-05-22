package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * Ein {@link AbstractVideospiel} bietet seine Standardimplemntation für
 * Videospiele an.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
abstract class AbstractVideospiel extends AbstractMedium
{
    /**
     * Basispreis eines Videospiels in Cent
     */
    protected final int _basispreis;

    /**
     * Das System, auf dem das Spiel lauffähig ist
     */
    private String _system;

    /**
     * Initialisiert ein neues Videospiel.
     * 
     * @param titel
     *            Der Titel des Spiels
     * @param system
     *            Die Bezeichnung des System
     * @param kommentar
     *            Ein Kommentar zum Spiel
     * 
     * @require titel != null
     * @require kommentar != null
     * @require system != null
     * 
     * @ensure getTitel() == titel
     * @ensure getKommentar() == kommentar
     * @ensure getSystem() == system
     */
    public AbstractVideospiel(String titel, String kommentar, String system)
    {
        super(titel, kommentar);

        assert system != null : "Vorbedingung verletzt: system != null";
        _system = system;
        _basispreis = 200;
    }

    @Override
    public Geldbetrag berechneMietgebuehr(Datum von, Datum bis)
    {
        return new Geldbetrag(_basispreis
                + getPreisNachTagen(bis.tageSeit(von) + 1));
    }

    @Override
    public String getFormatiertenString()
    {
        return super.getFormatiertenString() + SPACE + "System: " + getSystem()
                + "\n";
    }

    /**
     * Gibt das System zurück, auf dem das Spiel lauffähig ist.
     * 
     * @return Das System, auf dem das Spiel ausgeführt werden kann.
     * 
     * @ensure result != null
     */
    public String getSystem()
    {
        assert _system != null : "Nachbedingung verletzt: result != null";
        return _system;
    }

    /**
     * Berechnet den Preis für ein Videospiel mit einer Verleihdauer von tage.
     * 
     * @param tage
     *            Verleihdauer des Videospiels in Tagen.
     */
    protected abstract int getPreisNachTagen(int tage);

}
