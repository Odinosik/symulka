package smo;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.concurrent.TimeUnit;


public class Otoczenie extends BasicSimObj {
    public Zglaszaj zglaszaj;

    public Smo smo;
    public Smo_2 smo_2;
    public Pozar pozar;
    public KoniecPozar koniecpozar;
    public Boolean poz;

    public Otoczenie(Smo smo, Smo_2 smo_2) throws SimControlException {
        // Powołanie instancji pierwszego zdarzenia
		this.poz = true;
    	zglaszaj = new Zglaszaj(this, 0.0);

        this.smo = smo;
        this.smo_2 = smo_2;
        							//TO JEST
        this.pozar = new Pozar(this,500.0);



	}
	public void Zatrzymaj() throws SimControlException{

		//this.poz = false;
		smo.usunAll();
		smo_2.usunAll();

		zglaszaj.terminate();

	}

	public void Wznow() throws SimControlException {

    	zglaszaj = new Zglaszaj(this,0.0);
    	this.smo.setWolne(true);
    	this.smo.setWolne2(true);
    	//this.poz = true;
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
