package de.uni_hamburg.informatik.swt.se2.mediathek.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine abstrakt Implementation des {@link BeobachtbarerService} Interfaces, die
 * die Verwaltung und Benachrichtigung der Beobachter bereitstellt.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public abstract class AbstractBeobachtbarerService implements
        BeobachtbarerService
{

    /**
     * Die Liste der registrierten Beobachter.
     */
    private List<ServiceBeobachter> _beobachterListe;

    /**
     * Initialisiert einen neuen {@link AbstractBeobachtbarerService}.
     */
    public AbstractBeobachtbarerService()
    {
        _beobachterListe = new ArrayList<ServiceBeobachter>();
    }

    @Override
    public void registriereBeobachter(ServiceBeobachter beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        if (!_beobachterListe.contains(beobachter))
        {
            _beobachterListe.add(beobachter);
        }
    }

    @Override
    public void entferneBeobachter(ServiceBeobachter beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _beobachterListe.remove(beobachter);
    }

    /**
     * Informiert alle angemeldeten Beobachter dass eine relevante Änderung
     * eingetreten ist.
     */
    protected void informiereUeberAenderung()
    {
        for (ServiceBeobachter beobachter : _beobachterListe)
        {
            beobachter.informiereUeberAenderung();
        }
    }
}
