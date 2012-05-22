package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge;


/**
 * Basisklasse für Subwerkzeuge, die ihr Kontextwerkzeug bei Änderungen
 * benachrichtigen möchten.
 * 
 * Diese Klasse implementiert die Schnittstelle, über die sich Beobachter an dem
 * Subwerkzeug registrieren können. In der Regel wird es genau ein beobachtendes
 * Kontextwerkzeug geben. In Ausnahmen koennen es auch mehrere sein, wenn
 * indirekte Kontextwerkzeuge ebenfalls beobachten. Diese Klasse erlaubt beides.
 * 
 * Erbende Klassen rufen die Methode {@link #informiereUeberAenderung()} auf, um
 * alle Beobachter zu benachrichtigen. Erbende Klassen müssen dokumentieren, in
 * welchen Fällen sie ihre Beobachter informieren.
 * 
 * Diese Klasse entspricht der Klasse "Beobachtbar" im Beobachter-Muster.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface SubWerkzeugBeobachter
{
    /**
     * Benachrichtigt diesen Beobachter bei einer Änderung in dem beobachteten
     * Subwerkzeug.
     */
    void informiereUeberAenderung();
}
