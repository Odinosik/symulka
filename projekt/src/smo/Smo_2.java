package smo;

import java.util.LinkedList;
import smo.RozpocznijObsluge;
import smo.ZakonczObsluge;
import smo.Zgloszenie;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimControlException;



public class Smo_2 extends BasicSimObj
{
    private LinkedList <Zgloszenie> kolejka;
    private boolean wolne = true;
    public RozpocznijObsluge_2 rozpocznijObsluge;
    public ZakonczObsluge_2 zakonczObsluge;

    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;





    /** Creates a new instance of Smo
     * @throws SimControlException */
    public Smo_2() throws SimControlException
    {
        // Utworzenie wewnętrznej listy w kolejce
        kolejka = new LinkedList <Zgloszenie>();
        System.out.println("zyje");

        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVczasy_obslugi = new MonitoredVar();

    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj(Zgloszenie zgl)
    {
        kolejka.add(zgl);
        System.out.println("Liczba zgl w smo2" + this.kolejka.size());
        return kolejka.size();
    }
    public void usunAll() throws SimControlException {
        for (int i=0; i<kolejka.size(); i++) {
            kolejka.get(i).koniecNiecierpliwosci.interrupt();
        }
        if (zakonczObsluge != null) {
            zakonczObsluge.interrupt();
        }
        kolejka.clear();

    }


    // Pobranie zgłoszenia z kolejki
    public Zgloszenie usun()
    {

        Zgloszenie zgl = (Zgloszenie) kolejka.removeFirst();
        return zgl;
    }

    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Zgloszenie zgl)
    {
        Boolean b= kolejka.remove(zgl);
        return b;
    }

    public int liczbaZgl()
    {
        return kolejka.size();
    }

    public boolean isWolne() {
        return wolne;
    }

    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub
        return false;
    }
}