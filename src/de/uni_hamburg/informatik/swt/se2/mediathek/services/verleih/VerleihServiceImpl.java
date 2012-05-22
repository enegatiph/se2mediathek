package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

//TODO test anpassen
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.VormerkKarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.AbstractBeobachtbarerService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;

/**
 * Diese Klasse implementiert das Interface VerleihService. Siehe dortiger
 * Kommentar.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public class VerleihServiceImpl extends AbstractBeobachtbarerService implements
        VerleihService
{
    /**
     * Die Menge mit den Verleihkarten.
     */
    private Map<Medium, Verleihkarte> _verleihkarten;

    /**
     * Die Menge mit den Verleihkarten.
     */
    private Map<Medium, VormerkKarte> _vormerkKarten;
    
    /**
     * Der Medienbestand.
     */
    private MedienbestandService _medienbestand;

    /**
     * Der Kundenstamm.
     */
    private KundenstammService _kundenstamm;

    /**
     * Der Protokollierer für die Verleihvorgänge.
     */
    private Verleihprotokollierer _protokollierer;

    /**
     * Konstruktor. Erzeugt einen neuen {@link VerleihServiceImpl}.
     * 
     * @param kundenstamm
     *            Der {@link KundenstammService}.
     * @param medienbestand
     *            Der {@link MedienbestandService}.
     * @param initialBestand
     *            Der initiale Bestand.
     * 
     * @require kundenstamm != null
     * @require medienbestand != null
     * @require initialBestand != null
     */
    public VerleihServiceImpl(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm  != null";
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand  != null";
        assert initialBestand != null : "Vorbedingung verletzt: initialBestand  != null";
        _verleihkarten = erzeugeVerleihkartenBestand(initialBestand);
        _vormerkKarten = new HashMap<Medium, VormerkKarte>();
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
        _protokollierer = new Verleihprotokollierer();
        
        for (Medium medium : medienbestand.getMedien())
            _vormerkKarten.put(medium, new VormerkKarte( medium));
    }

    /**
     * Erzeugt eine neue HashMap aus dem Initialbestand.
     */
    private HashMap<Medium, Verleihkarte> erzeugeVerleihkartenBestand(
            List<Verleihkarte> initialBestand)
    {
        HashMap<Medium, Verleihkarte> result = new HashMap<Medium, Verleihkarte>();
        for (Verleihkarte verleihkarte : initialBestand)
        {
            result.put(verleihkarte.getMedium(), verleihkarte);
        }
        return result;
    }
    
    @Override
    public List<Verleihkarte> getVerleihkarten()
    {
        return new ArrayList<Verleihkarte>(_verleihkarten.values());
    }

    @Override
    public boolean istVerliehen(Medium medium)
    {
        assert mediumImBestand(medium) : "Vorbedingung verletzt: mediumExistiert(medium)";
        return _verleihkarten.get(medium) != null;
    }

    //wurde editiert
    @Override
    public boolean istVerleihenMoeglich(List<Medium> medien, Kunde kunde)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        //--
        for ( Medium medium : medien)
        {
            if( !_vormerkKarten.get(medium).getVormerker().remove().equals( kunde) ) //stil..
                return false;
        }
        
        return sindAlleNichtVerliehen(medien);
    }

    @Override
    public void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException
    {
        assert sindAlleVerliehen(medien) : "Vorbedingung verletzt: sindVerliehen(medien)";
        assert rueckgabeDatum != null : "Vorbedingung verletzt: rueckgabeDatum != null";

              
        for (Medium medium : medien)
        {
            Verleihkarte verleihkarte = _verleihkarten.get(medium);
            _verleihkarten.remove(medium);
            _protokollierer.protokolliere(EREIGNIS_RUECKGABE, verleihkarte);
        }

        informiereUeberAenderung();
    }

    @Override
    public boolean sindAlleNichtVerliehen(List<Medium> medien)
    {
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienExistieren(medien)";
        boolean result = true;
        for (Medium medium : medien)
        {
            if (istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }                           

    
    @Override
    public boolean sindAlleVerliehen(List<Medium> medien)
    {
        assert medienImBestand(medien) : "Vorbedingung verletzt: medienExistieren(medien)";
        boolean result = true;
        for (Medium medium : medien)
        {
            if (!istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }

    //wurde editiert
    @Override
    public void verleiheAn(Kunde kunde, List<Medium>     medien, Datum ausleihDatum)
            throws ProtokollierException
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert sindAlleNichtVerliehen(medien) : "Vorbedingung verletzt: sindNichtVerliehen(medien) ";
        assert ausleihDatum != null : "Vorbedingung verletzt: ausleihDatum != null";
        assert istVerleihenMoeglich(medien, kunde) : "Vorbedingung verletzt:  istVerleihenMoeglich(medien, kunde)";

        for (Medium medium : medien)
        {
            //--
            getVormerkkarteFuer(medium).removeErstenVormerker();
            
            Verleihkarte verleihkarte = new Verleihkarte(kunde, medium,
                    ausleihDatum);
            _verleihkarten.put(medium, verleihkarte);
            _protokollierer.protokolliere(EREIGNIS_AUSLEIHE, verleihkarte);
        }

        informiereUeberAenderung();
    }

    @Override
    public boolean kundeImBestand(Kunde kunde)
    {
        return _kundenstamm.enthaeltKunden(kunde);
    }

    @Override
    public boolean mediumImBestand(Medium medium)
    {
        return _medienbestand.enthaeltMedium(medium);
    }

    @Override
    public boolean medienImBestand(List<Medium> medien)
    {
        assert medien != null : "Vorbedingung verletzt: medien != null";
        assert !medien.isEmpty() : "Vorbedingung verletzt: !medien.isEmpty()";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!mediumImBestand(medium))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Medium> getAusgelieheneMedienFuer(Kunde kunde)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Medium> result = new ArrayList<Medium>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher().equals(kunde))
            {
                result.add(verleihkarte.getMedium());
            }
        }
        return result;
    }

    @Override
    public Kunde getEntleiherFuer(Medium medium)
    {
        assert istVerliehen(medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        Verleihkarte verleihkarte = _verleihkarten.get(medium);
        return verleihkarte.getEntleiher();
    }

    @Override
    public Verleihkarte getVerleihkarteFuer(Medium medium)
    {
        assert istVerliehen(medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        return _verleihkarten.get(medium);
    }

    @Override
    public List<Verleihkarte> getVerleihkartenFuer(Kunde kunde)
    {
        assert kundeImBestand(kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher().equals(kunde))
            {
                result.add(verleihkarte);
            }
        }
        return result;
    }
    
    /******* VerleihServiceImplVormerker**********/

    //alternativer konstruktor
    public VerleihServiceImpl(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand,
            List<VormerkKarte> initialBestandVormerkKarten)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm  != null";
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand  != null";
        assert initialBestand != null : "Vorbedingung verletzt: initialBestand  != null";
        _verleihkarten = erzeugeVerleihkartenBestand(initialBestand);
        _vormerkKarten = erzeugeVormerkKartenBestand(initialBestandVormerkKarten);
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
        _protokollierer = new Verleihprotokollierer();
    }

    
    
    /**
     * Erzeugt eine neue HashMap aus dem Initialbestand für VormerkKarten
     */
    private HashMap<Medium, VormerkKarte> erzeugeVormerkKartenBestand(
            List<VormerkKarte> initialBestand)
    {
        HashMap<Medium, VormerkKarte> result = new HashMap<Medium, VormerkKarte>();
        for (VormerkKarte vormerkkarte : initialBestand)
        {
            result.put( vormerkkarte.getMedium(), vormerkkarte);
        }
        return result;
    }
    
    
    
    public void merkeVor(Kunde k, List<Medium> medien)
    {
        for ( Medium medium : medien)
        {
            if ( _vormerkKarten.get(medium).istVormerkenMoeglich(k))
            {
                _vormerkKarten.get(medium).addVormerker(k);
            }
        }
        informiereUeberAenderung();
    }
    
    
    public Queue<Kunde> getVormerkerFuer(Medium medium)
    {
        return _vormerkKarten.get(medium).getVormerker();
        
    }
    
    
    public VormerkKarte getVormerkkarteFuer(Medium medium)
    {
        return _vormerkKarten.get(medium);
    }

    
    public boolean istVormerkenMoeglich(List<Medium> medien, Kunde kunde)
    {
        for ( Medium medium : medien)
        {
            if( _vormerkKarten.get(medium).istVormerkenMoeglich(kunde) == false)
                return false;
        }
        
        return true;
    }
    
    public Kunde getErstenVormerkerFuer(Medium medium)
    {
        return ( getVormerkerFuer(medium) != null ) ? getVormerkerFuer(medium).remove() : null;
        
    }
}