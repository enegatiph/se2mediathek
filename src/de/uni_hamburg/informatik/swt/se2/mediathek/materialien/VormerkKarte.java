package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

//TODO: vertragsmodell....
// WORKING vertragsmodell

import  de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import java.util.Queue;
import java.util.LinkedList;;

/**
 * Eine VormerkKarte enthält Informationen darüber welche Kunden ein 
 * bestimmtes Medium vorgemerkt haben. Es gibt max. 1 Vormerkkarte pro Medium.
 * Es können maximal 3 Kunden ein Medium vormerken.
 * Weitere Bedingunen unter denen vormerken möglich ist bei
 * {@link VerleihService}
 * 
 * 
 */
public class VormerkKarte
{
  private Queue<Kunde> _kunden;
  private Medium _medium;
  /**
   * MAX_VORMERKER ist die Anzahl der maximal möglichen Vormerker pro Vormerkkarte.
   * Momentan ist die Anzahl auf >3< beschränkt
   */
  private final int MAX_VORMERKER = 3;
  
  /**
   * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
   * 
   * @param m
   *     Medium für welches eine Vormerkkarte erstellt werden soll.
   * @require m != null
   * @ensure getVormerker() != null
   * @ensure getMedium() == m
   */
  public VormerkKarte(Medium m)
  {
      assert m != null : "Fehler, kein Medium ausgewaehlt";
      
      _kunden = new LinkedList<Kunde>();
      _medium = m;
  }

  /**
   * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
   * 
   * @param m
   *         Medium für welches eine Vormerkkarte erstellt werden soll.
   * @param k
   *         Kunde welcher dieses Medium vormerken möchte
   * 
   * @require k != null
   * @require m != null
   * @ensure getVormerker() == k
   * @ensure getMedium() == m
   */
  public VormerkKarte(Medium m, Kunde k)
  {
      this(m);
      addVormerker(k);
  }

  /**
   * Gibt eine Liste der Vormerker aus (als Queue<Kunde>).
   * 
   * @return Liste der Vormerker (als Queue<Kunde>)
   * 
   * @ensure result != null
   */
  public Queue<Kunde> getVormerker()
  {
      Queue<Kunde> newKunden = new LinkedList<Kunde>();
      for ( Kunde k : _kunden)
          newKunden.add(k);
      
      return newKunden;
  }
  
  /**
   * Registriert einen weiteren Vormerker fuers zugehörige Medium.
   * 
   * @param k
   *         Kunde welcher eine Vormerkung vornehmen möchte
   * @require istVormerkenMoeglich(k)
   * @ensure getVormerker().contains(k) == true
   */
  public void addVormerker(Kunde k)
  {
      assert istVormerkenMoeglich(k) : "Dieser Kunde kann dieses Medium nicht vormerken"; 
      
      _kunden.add(k);
  }
  
  /**
   * Gibt an zu welchen Medium diese Vormerkkarte gehört.
   *  
   * @return Medium zu welchen diese Vormerkkarte gehört.
   * @ensure result != null
   */
  public Medium getMedium()
  {
      return _medium;
  }
  
  // TODO comment const MAX_VORMERKER
   
  /** 
   * Überprüft ob der angegebene Kunden eine VOrmerkung vornehmen kann.
   * Dies ist möglich solange es weniger als {@link MAX_VORMERKER} Vormerker gibt 
   * und der Kunde dieses Medium nicht bereits vorgemerkt hat. 
   * @param k
   *         Kunde welcher eine Vormerkung vornehmen möchte
   * @return Wahrheits obs möglich ist (Ja/Nein bzw true/false)
   * @require k != null
   * @ensure result != null
   */
  public boolean istVormerkenMoeglich(Kunde k)
  {
      assert k != null : "Kein Kunde ausgewaehlt";
      
      return _kunden.size() < MAX_VORMERKER && !_kunden.contains(k);
  }
  
  /**
   * Entfernt den ersten Vormerker aus der liste und gibt eine referenz auf diesen 
   * zurueck
   * 
   * @return Der Erste Vormerker
   * @ensure result can be null
   */
  public Kunde removeErstenVormerker()
  {
      return _kunden.poll();
  }
}
