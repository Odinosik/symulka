package smo;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

public class RozpocznijObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;
	private Smo_2 smoParent_2;

	private SimGenerator generator;

    public RozpocznijObsluge(Smo parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.smoParent = parent;
    }

    public RozpocznijObsluge(Smo parent, Smo_2 smoparent_2) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.smoParent = parent;
        this.smoParent_2 = smoparent_2;
    }
    
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub


		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
        if (smoParent.liczbaZgl() > 0)
        {
        	// Zablokuj gniazdo

        	// Pobierz zgłoszenie
        	Zgloszenie zgl = smoParent.usun();

        	System.out.println("ZGLOSZENIE nr: " + zgl.tenNr + " Bedzie obslugiwane");



			if (smoParent.isWolne() && (smoParent.getnum_zgl() == zgl.tenNr )) {

				System.out.println(smoParent.getnum_zgl());
				System.out.println("kasa 1 obsuguje zgloszenie: " + zgl.tenNr);
				smoParent.setWolne(false,zgl.tenNr);

				System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Początek obsługi zgl. nr: " + zgl.getTenNr());
				zgl.koniecNiecierpliwosci.interrupt();

				double czasObslugi = generator.normal(9.0, 1.0);

				//monitor tutaj
                //smo.parent.Mvczasy_oczekiwania.setValue(
                // simTime(), - zgl.getCzasOdniesienia(), simTime(), przy ponownym wejsciu zresetowac)



				smoParent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia(), simTime());


				smoParent.MVczasy_obslugi.setValue(czasObslugi,simTime());






				smoParent.zakonczObsluge = new ZakonczObsluge(smoParent, czasObslugi, zgl, smoParent_2);

			}
			if (smoParent.isWolne2() && (smoParent.getnum_zgl2() == zgl.tenNr))
			{
				System.out.println("kasa 2 obsluguje zgloszenie: " + smoParent.getnum_zgl2());
				smoParent.setWolne2(false,zgl.tenNr);

				zgl.koniecNiecierpliwosci.interrupt();

				double czasObslugi = generator.normal(9.0, 1.0);

				System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Początek obsługi zgl. nr: " + zgl.getTenNr());

				smoParent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia(), simTime());
				smoParent.MVczasy_obslugi.setValue(czasObslugi,simTime());
				smoParent.zakonczObsluge2 = new ZakonczObsluge(smoParent, czasObslugi, zgl, smoParent_2);
			}


        }
		
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}