package de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;

/**
 * Ein {@link Medium} definiert Eigenschaften, die alle Medien unserer Mediathek
 * gemeinsam haben. Der Titel eines {@link Medium}s dient als eindeutige
 * Identifikation. Ein {@link Medium} kann ausgeliehen und zurückgegeben werden.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface Medium
{

    /**
     * Berechnet die Mietgebühr in Euro, für den Fall, dass das Medium am Datum
     * "von" ausgeliehen wird und am Datum "bis" zurückgegeben wird. Für ein
     * Medium, das an einem Tag ausgeliehen wird und am nächsten Tag
     * zurückgegeben wird, werden 2 Miettage berechnet.
     * 
     * @param von
     *            Das Ausleihdatum
     * @param bis
     *            Das Rückgabedatum
     * @return Die Mietgebühr in Euro als {@link Geldbetrag}
     * 
     * @require von != null
     * @require bis != null
     * @require von.compareTo(bis) <= 0
     * 
     * @ensure result != null
     */
    Geldbetrag berechneMietgebuehr(Datum von, Datum bis);

    /**
     * Gibt einen formatierten Text mit allen Eigenschaften des Mediums zurück.
     * Jedes Attribute steht in einer eigenen Zeile mit der Form "Attributename:
     * Attributwert". Hinweis: Ein Zeilenumbruch wird durch den Character '\n'
     * dargestellt.
     * 
     * @return Eine Textrepräsentation des Mediums.
     * 
     * @ensure result != null
     */
    String getFormatiertenString();

    /**
     * Gibt den Kommentar zu diesem Medium zurück.
     * 
     * @return Den Kommentar zu diesem Medium.
     * 
     * @ensure result != null
     */
    String getKommentar();
    
    /**
     * Ändert den Kommentar des Mediums
     *  
     * @param kommentar 
     *              Ein Kommentar zu diesem Medium
     * 
     * @require kommentar != null
     * @ensure getKommentar() == kommentar
     */
     public void setKommentar(String kommentar);


    /**
     * Gibt die Bezeichnung für die Medienart zurück.
     * 
     * @return Die Bezeichnung für die Medienart.
     * 
     * @ensure result != null
     */
    String getMedienBezeichnung();

    /**
     * Gibt den Titel des Mediums zurück.
     * 
     * @return Den Titel des Mediums
     * 
     * @ensure result != null
     */
    String getTitel();
    
    /**
     * Ändert den Titel des Mediums.
     *  
     * @param titel
     *            Der Titel des Mediums
     * 
     * @require titel != null
     * @ensure getTitel() == titel
     */
     public void setTitel(String titel);

}
