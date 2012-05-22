package de.uni_hamburg.informatik.swt.se2.mediathek.services;

/**
 * Interface für Beobachter, die sich für Änderungen eines
 * {@link BeobachtbarerService} interessieren.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface ServiceBeobachter
{

    /**
     * Diese Operation wird aufgerufen, sobald sich an an dem beobachteten
     * Service etwas relevantes geändert hat.
     * 
     * Implementierende Klassen müssen in dieser Operation auf die Aenderung
     * reagieren.
     */
    void informiereUeberAenderung();
}
