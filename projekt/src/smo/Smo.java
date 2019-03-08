package smo;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Description: Klasa gniazda obsługi obiektów - zgłoszeń 
 */

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



public class Smo extends BasicSimObj
{
    private LinkedList <Zgloszenie> kolejka;
	private boolean wolne1 = true;
    private boolean wolne2 = true;

    private LinkedList <Zgloszenie> kolejka_blakan;
    


    public RozpocznijObsluge rozpocznijObsluge;
    public ZakonczObsluge zakonczObsluge;
    public RozpocznijObsluge rozpocznijObsluge2;
    public ZakonczObsluge zakonczObsluge2;
    final public int size = 5;


    private int num_zgl = -1;
    private int num_zgl2 = -1;

    public MonitoredVar MVczasy_obslugi; //



    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;



	
    /** Creates a new instance of Smo 
     * @throws SimControlException */
    public Smo() throws SimControlException
    {
        // Utworzenie wewnętrznej listy w kolejce
        kolejka = new LinkedList <Zgloszenie>();
        kolejka_blakan = new LinkedList<Zgloszenie>();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVczasy_obslugi = new MonitoredVar();
    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj(Zgloszenie zgl)
    {


        kolejka.add(zgl);
        MVdlKolejki.setValue(this.kolejka.size(),simTime());
        System.out.println("dlugosc kolejki" + kolejka.size());
        return kolejka.size();
    }

    public void dodaj_blak(Zgloszenie zgl)
    {


        kolejka_blakan.add(zgl);


    }

    public void usun_blak(Zgloszenie zgl)
    {


        kolejka_blakan.remove(zgl);


    }




    // Pobranie zgłoszenia z kolejki
    public Zgloszenie usun()
    {
    	Zgloszenie zgl = (Zgloszenie) kolejka.removeLast();

        return zgl;
    }

    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Zgloszenie zgl)
    {
    	Boolean b= kolejka.remove(zgl);

        return b;
    }

    public void usunAll() throws SimControlException {
        for (int i=0; i<kolejka_blakan.size(); i++) {

            kolejka_blakan.get(i).koniecBlakania.interrupt();
        }

        for (int i=0; i<kolejka.size(); i++) {
            if (kolejka.get(i).koniecNiecierpliwosci !=null)
            kolejka.get(i).koniecNiecierpliwosci.interrupt();
        }
        if (zakonczObsluge != null){
            zakonczObsluge.interrupt();
        }
        if (zakonczObsluge2 != null){
            zakonczObsluge2.interrupt();
        }
        kolejka.clear();


    }
    
    public int liczbaZgl()
    {
        return kolejka.size();
    }

	public boolean isWolne() {
		return wolne1;
	}
    public void setWolne(boolean wolne) {
        this.wolne1 = wolne;

    }

	public void setWolne(boolean wolne,int num_zgl) {
		this.wolne1 = wolne;
		this.num_zgl = num_zgl;
	}


	public int getnum_zgl() {
        return num_zgl ;
    }

    public void setNum_zgl(int num) {
        this.num_zgl = num;
    }

    public boolean isWolne2() {
        return wolne2;
    }

    public void setWolne2(boolean wolne) {
        this.wolne2 = wolne;
    }
    public void setWolne2(boolean wolne, int num_zgl) {
        this.wolne2 = wolne;
        this.num_zgl2 = num_zgl;
    }
    public int getnum_zgl2() {
        return num_zgl2 ;
    }
    public void setNum_zgl2(int num) {
        this.num_zgl2 = num;
}

    public int getOstatni() {

        return kolejka.get(kolejka.size() - 1).getTenNr();
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