package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.vormerken;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import java.util.Queue;
import java.util.LinkedList;;


public class VormerkKarte
{
    private Queue<Kunde> _kunden;
    private Medium _m;
    
    public VormerkKarte()
    {
        _kunden = new LinkedList<Kunde>();
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
        _kunden.add(k);
    }
    public Medium getMedium()
    {
        return _m;
    }
    public boolean istVormerkenMoeglich(Kunde k)
    {
        return true;
    }
    public Kunde removeErstenVormerker()
    {
        return _kunden.poll();
    }
}