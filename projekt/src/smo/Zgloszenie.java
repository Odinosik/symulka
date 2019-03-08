package smo;

import smo.KoniecNiecierpliwienia;
import smo.Smo;
import smo.StartNiecierpliwienia;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 * 
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj
{
    double czasOdniesienia;
    static int nr=0;
    int tenNr;
    StartNiecierpliwienia startNiecierpliwienia;
    public KoniecNiecierpliwienia koniecNiecierpliwosci;
	public KoniecBlakania koniecBlakania;
	public StartBlakania startBlakania;
    public Smo smo;
    public Smo_2 smo2;
    int ktoresmo;
    public boolean usun = false;
    

	public Zgloszenie(double Czas, Smo smo,Smo_2 smo2,int ktore) throws SimControlException
    {
        czasOdniesienia = Czas;
        setTenNr();
        this.smo = smo;
        this.smo2 = smo2;
        this.ktoresmo = ktore;
        startNiecierpliwienia = new StartNiecierpliwienia(this);


    }
    public Zgloszenie()
    {
        setTenNr();
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

	public double getCzasOdniesienia() {

	    return czasOdniesienia;
    }

	public void setTenNr() {
		this.tenNr = nr++;
	}

	public int getTenNr() {
		return tenNr;
	}

}