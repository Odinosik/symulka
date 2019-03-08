package smo;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.Random;

public class ZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;
    private Smo_2 smoParent_2;


    public ZakonczObsluge(Smo parent, double delay, Zgloszenie zgl, Smo_2 parent_2) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
        this.smoParent_2 = parent_2;
    }

    public ZakonczObsluge(Smo parent, SimEventSemaphore semafor, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
    }
    
	@Override
	protected void onInterruption() throws SimControlException {

    	System.out.println("Koniec obslugi z smo");

		// TODO
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {


    	System.out.println("Gniazdo 1 ma zgloszenie: " + smoParent.getnum_zgl());
		System.out.println("Gniazdo 2 ma zgloszenie: " + smoParent.getnum_zgl2());
		System.out.println("Poakz numer zgloszenia na koniec : " + transitionParams.tenNr);

    	if ((!smoParent.isWolne()) && (smoParent.getnum_zgl() == transitionParams.tenNr))
			{
			smoParent.setWolne(true);
			System.out.println("Gniazdko 1 konczy obsluge "  + transitionParams.tenNr);


		}
		if ((!smoParent.isWolne2()) && (smoParent.getnum_zgl2() == transitionParams.tenNr))
		{
			smoParent.setWolne2(true);
			System.out.println("Gniazdko 2 konczy obsluge " + transitionParams.tenNr);



		}




        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Koniec obsługi zgl. nr: " + transitionParams.getTenNr());

      	// Zaplanuj dalsza obsługe w tym gnieździe


		Random rand = new Random();
		int n = rand.nextInt(50) + 1;
		if (n <25) {
			smoParent.dodaj_blak(transitionParams);
			transitionParams.startBlakania = new StartBlakania(transitionParams,smoParent,smoParent_2);
		}

      	   if (smoParent.liczbaZgl() > 1)
       	{

			if (smoParent.isWolne()) {
				System.out.println("NUMER ZGLOSZENIA PO ZAKONCZENIU KASA 1: " + transitionParams.tenNr + " liczba zgl" +  smoParent.liczbaZgl());
				System.out.println("NUMER OSTATNIEGO ZGLOSZENIA :"+ smoParent.getOstatni());

				smoParent.setNum_zgl(smoParent.getOstatni());
				smoParent.rozpocznijObsluge = new RozpocznijObsluge(smoParent,smoParent_2);
			}
			 else if (smoParent.isWolne2()) {

				System.out.println("NUMER ZGLOSZENIA PO ZAKONCZENIU KASA 2: " + transitionParams.tenNr + "  liczba zgl" +  smoParent.liczbaZgl());
				System.out.println("NUMER OSTATNIEGO ZGLOSZENIA :"+ smoParent.getOstatni());
				smoParent.setNum_zgl2(smoParent.getOstatni());
				smoParent.rozpocznijObsluge2 = new RozpocznijObsluge(smoParent,smoParent_2);
			} else {
				System.out.println("cos poszlo nie tak");
			}


       	}	
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}