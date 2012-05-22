package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.BeobachtbarerService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceBeobachter;

/**
 * Der {@link VerleihService} erlaubt es, Medien auszuleihen und zurückzugeben.
 * 
 * Für jedes ausgeliehene Medium wird eine neue Verleihkarte angelegt. Wird das
 * Medium wieder zurückgegeben, so wird diese Verleihkarte wieder gelöscht.
 * 
 * {@link VerleihService} ist ein {@link BeobachtbarerService}, als solcher
 * bietet er die Möglichkeit über Verleihvorgänge zu informieren. Beobachter
 * müssen das Interface {@link ServiceBeobachter} implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface VerleihService extends BeobachtbarerService
{

    /**
     * Textrepräsentation für das Ereignis Ausleihe.
     */
    public static final String EREIGNIS_AUSLEIHE = "Ausleihe";

    /**
     * Textrepräsentation für das Ereignis Rückgabe.
     */
    public static final String EREIGNIS_RUECKGABE = "Rückgabe";

    /**
     * Verleiht Medien an einen Kunden. Dabei wird für jedes {@link Medium} eine
     * neue {@link Verleihkarte} angelegt.
     * 
     * @param kunde
     *            Ein {@link Kunde}, an den ein {@link Medium} verliehen werden
     *            soll
     * @param medien
     *            Die Medien, die verliehen werden sollen
     * @param ausleihDatum
     *            Der erste Ausleihtag
     * 
     * @throws ProtokollierException
     *             Wenn beim Protokollieren des Verleihvorgangs ein Fehler
     *             auftritt.
     * 
     * @require kundeImBestand(kunde)
     * @require sindAlleNichtVerliehen(medien)
     * @require ausleihDatum != null
     * 
     * @ensure sindAlleVerliehen(medien)
     */
    void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
            throws ProtokollierException;

    /**
     * Prüft ob die ausgewählten {@link Medium} für den {@link Kunde} ausleihbar
     * sind
     * 
     * @param medien
     *            Die medien
     * @param kunde
     *            Der {@link Kunde} für den geprüft werden soll
     * 
     * 
     * @return true, wenn das entleihen für diesen Kunden möglich ist, sonst
     *         false
     * 
     * @require medienImBestand(medien)
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    boolean istVerleihenMoeglich(List<Medium> medien, Kunde kunde);

    /**
     * Liefert den Entleiher des angegebenen Mediums.
     * 
     * @param medium
     *            Das Medium.
     * 
     * @return Den Entleiher des Mediums.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure result != null
     */
    Kunde getEntleiherFuer(Medium medium);

    /**
     * Liefert alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     * 
     * @param kunde
     *            Der Kunde.
     * @return Alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     *         Liefert eine leere Liste wenn der Kunde aktuell nichts
     *         ausgeliehen hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Medium> getAusgelieheneMedienFuer(Kunde kunde);

    /**
     * Liefert eine Listenkopie aller Verleihkarten. Für jedes ausgeliehene
     * Medium existiert eine Verleihkarte.
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkarten();

    /**
     * Nimmt zuvor ausgeliehene Medien zurück. Die entsprechenden Verleihkarten
     * werden gelöscht.
     * 
     * 
     * @param medien
     *            Die Medien.
     * @param rueckgabeDatum
     *            Das Rückgabedatum.
     * 
     * @require sindAlleVerliehen(medien)
     * @require rueckgabeDatum != null
     * 
     * @ensure sindAlleNichtVerliehen(medien)
     */
    void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException;

    /**
     * Prüft ob das angegebene Medium verliehen ist.
     * 
     * @param medium
     *            Ein Medium, für das geprüft werden soll ob es verliehen ist.
     * @return true, wenn das gegebene medium verliehen ist, sonst false.
     * 
     * @require mediumImBestand(medium)
     */
    boolean istVerliehen(Medium medium);

    /**
     * Prüft ob alle angegebenen Medien nicht verliehen sind.
     * 
     * @param medien
     *            Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien nicht verliehen sind, sonst
     *         false (auch wenn die Liste leer ist).
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleNichtVerliehen(List<Medium> medien);

    /**
     * Prüft ob alle angegebenen Medien verliehen sind.
     * 
     * @param medien
     *            Eine Liste von Medien.
     * 
     * @return true, wenn alle gegebenen Medien verliehen sind, sonst false
     *         (auch wenn die Liste leer ist).
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleVerliehen(List<Medium> medien);

    /**
     * Prüft ob der angebene Kunde existiert. Ein Kunde existiert, wenn er im
     * Kundenstamm enthalten ist.
     * 
     * @param kunde
     *            Ein Kunde.
     * @return true wenn der Kunde existiert, sonst false.
     * 
     * @require kunde != null
     */
    boolean kundeImBestand(Kunde kunde);

    /**
     * Prüft ob das angebene Medium existiert. Ein Medium existiert, wenn es im
     * Medienbestand enthalten ist.
     * 
     * @param medium
     *            Ein Medium.
     * @return true wenn das Medium existiert, sonst false.
     * 
     * @require medium != null
     */
    boolean mediumImBestand(Medium medium);

    /**
     * Prüft ob die angebenen Medien existierien. Ein Medium existiert, wenn es
     * im Medienbestand enthalten ist.
     * 
     * @param medien
     *            Eine Liste von Medien.
     * @return true wenn die Medien existieren, sonst false.
     * 
     * @require medien != null
     * @require !medien.isEmpty()
     */
    boolean medienImBestand(List<Medium> medien);

    /**
     * Gibt alle Verleihkarten für den angegebenen Kunden zurück.
     * 
     * @param kunde
     *            Ein Kunde.
     * @return Alle Verleihkarten des angebenen Kunden.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkartenFuer(Kunde kunde);

    /**
     * Gibt die Verleihkarte für das angegebene Medium zurück, oder null wenn
     * das Medium nicht verliehen ist.
     * 
     * @param medium
     *            Ein Medium.
     * @return Die Verleihkarte für das angegebene Medium.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure (result != null)
     */
    Verleihkarte getVerleihkarteFuer(Medium medium);

}
