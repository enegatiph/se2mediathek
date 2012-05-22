package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;

/**
 * Ein Verleihprotokollierer schreibt alle Verleihvorgänge in eine Datei.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
class Verleihprotokollierer
{

    /**
     * Schreibt eine übergebene Verleihkarte ins Protokoll.
     * 
     * @param ereignis
     *            Ein Verleihereignis, mögliche Texte sind in
     *            {@link VerleihService} als Konstanten deklariert.
     * @param verleihkarte
     *            eine Verleihkarte, die das Verleihereignis betrifft.
     * 
     * @throws ProtokollierException
     *             wenn das Protokollieren nicht geklappt hat.
     */
    public void protokolliere(String ereignis, Verleihkarte verleihkarte)
            throws ProtokollierException
    {
        FileWriter writer = null;
        try
        {
            File protokollDatei = new File("./verleihProtokoll.txt");
            if (!protokollDatei.exists())
            {
                if (!protokollDatei.createNewFile())
                {
                    throw new ProtokollierException(
                            "Protokoll-Datei konnte nicht erzeugt werden: "
                                    + protokollDatei.getAbsolutePath());
                }
            }
            writer = new FileWriter(protokollDatei, true);
            String eintrag = Calendar.getInstance().getTime().toString() + ": "
                    + ereignis + "\n" + verleihkarte.getFormatiertenString();
            writer.write(eintrag);
        }
        catch (IOException e)
        {
            throw new ProtokollierException(
                    "Beim Schreiben des Verleihprotokolls ist ein Fehler aufgetreten.");
        }
        finally
        {
            // Abschließend wird die Datei immer geschlossen.
            if (writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    throw new ProtokollierException(
                            "Beim Schreiben des Verleihprotokolls ist ein Fehler aufgetreten.");
                }
            }
        }
    }
}
