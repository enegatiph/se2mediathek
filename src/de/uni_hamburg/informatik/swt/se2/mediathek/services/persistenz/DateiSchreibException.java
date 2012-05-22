package de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz;

import java.io.IOException;

/**
 * Eine DateiSchreibException signalisiert, dass das Schreiben in eine Datei
 * nicht geklappt hat.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public class DateiSchreibException extends IOException
{

    private static final long serialVersionUID = 1L;

    /**
     * Initilaisert eine neue Exception mit der übergebenen Fehlermeldung.
     * 
     * @param message
     *            Eine Fehlerbeschreibung.
     */
    public DateiSchreibException(String message)
    {
        super(message);
    }
}
