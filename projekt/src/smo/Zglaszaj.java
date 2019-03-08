package smo;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.Random;

/**
 * Description: Zdarzenie generatora zgłoszeń. Tworzy obiekt - zgłoszenie co losowy czas.
 * 
 * @author Dariusz Pierzchala

 */
public class Zglaszaj extends BasicSimEvent<Otoczenie, Object>
{
    private SimGenerator generator;
    private Otoczenie parent;

    public Zglaszaj(Otoczenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
    	System.out.println("Nowe nowe");
    }

    public Zglaszaj(Otoczenie parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
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

        parent = getSimObj();


        if (!parent.poz) {

        } else {

            Random rand = new Random();

            int n = rand.nextInt(50) + 1;




            if ((parent.smo.liczbaZgl() > parent.smo.size) || n<25) {
                Zgloszenie zgl = new Zgloszenie(simTime(), parent.smo,parent.smo_2,2);
                System.out.println(simTime() + " - " + simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - " + simDate(SimDateField.MILLISECOND) + ": Smo 2: " + zgl.tenNr);
                parent.smo_2.dodaj(zgl);
                if (parent.smo_2.liczbaZgl() == 1) {
                    parent.smo_2.rozpocznijObsluge = new RozpocznijObsluge_2(parent.smo, parent.smo_2);

                }
                parent.smo.usunWskazany(zgl);

            } else {
                Zgloszenie zgl = new Zgloszenie(simTime(), parent.smo,parent.smo_2,1);
                parent.smo.dodaj(zgl);


                System.out.println(simTime() + " - " + simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - " + simDate(SimDateField.MILLISECOND) + ": Otoczenie- Dodano nowe zgl. nr: " + zgl.getTenNr());


                // Aktywuj obsługę, jeżeli kolejka była pusta (gniazdo "spało")
                if (parent.smo.liczbaZgl() <= 2 && parent.smo.isWolne()) {
                    System.out.println("GNIAZKO 1 Powstaje: ");
                    parent.smo.setNum_zgl(zgl.tenNr);
                    parent.smo.rozpocznijObsluge = new RozpocznijObsluge(parent.smo, parent.smo_2);

                } else if (parent.smo.liczbaZgl() <= 2 && parent.smo.isWolne2()) {
                    parent.smo.rozpocznijObsluge2 = new RozpocznijObsluge(parent.smo, parent.smo_2);
                    parent.smo.setNum_zgl2(zgl.tenNr);

                    System.out.println("GNIAZKO 2 Powstaje ");

                }

                //alternatywnie: parent.zglaszaj = new Zglaszaj(parent, odstep);
            }
            double odstep = generator.normal(5.0, 1.0);

            setRepetitionPeriod(odstep);


        }
    }
	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}