package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

//TODO: vertragsmodell....

import  de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import java.util.Queue;
import java.util.LinkedList;;


public class VormerkKarte
{
  private Queue<Kunde> _kunden;
  private Medium _medium;
  private final int MAX_VORMERKER = 3;
  
  /**
   * 
   * @param m
   *     Medium welches vorgemerkt werden soll
   * @require m != null
   */
  public VormerkKarte(Medium m)
  {
      _kunden = new LinkedList<Kunde>();
      _medium = m;
  }

  
  public VormerkKarte(Medium m, Kunde k)
  {
      this(m);
      addVormerker(k);
  }

  
  public Queue<Kunde> getVormerker()
  {
      Queue<Kunde> newKunden = new LinkedList<Kunde>();
      for ( Kunde k : _kunden)
          newKunden.add(k);
      
      return newKunden;
  }
  public void addVormerker(Kunde k)
  {
      if (  _kunden.size() < MAX_VORMERKER && _kunden.contains(k) ) //TODO Vertraagsmodell
      {
          _kunden.add(k);
      }
  }
  public Medium getMedium()
  {
      return _medium;
  }
  public boolean istVormerkenMoeglich(Kunde k)
  {
      return _kunden.size() < MAX_VORMERKER && _kunden.contains(k);
  }
  
  public Kunde removeErstenVormerker()
  {
      return _kunden.poll();
  }
}
