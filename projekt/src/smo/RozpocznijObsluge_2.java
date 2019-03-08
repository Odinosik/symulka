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

public class RozpocznijObsluge_2 extends BasicSimEvent<Smo_2, Zgloszenie>

{
    private Smo_2 smoParent;
    private SimGenerator generator;
    private Smo smo_blak;


    public RozpocznijObsluge_2(Smo_2 parent, double delay) throws SimControlException
    {
        super(parent, delay);
        generator = new SimGenerator();
        this.smoParent = parent;


    }

    public RozpocznijObsluge_2(Smo parent, Smo_2 parent_2) throws SimControlException
    {
        super(parent_2);
        generator = new SimGenerator();
        this.smoParent = parent_2;
        this.smo_blak = parent;
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
            smoParent.setWolne(false);
            // Pobierz zgłoszenie

            smoParent.MVdlKolejki.setValue(smoParent.liczbaZgl(),simTime());

            Zgloszenie zgl = smoParent.usun();
            System.out.println("Liczba zgloszen w smo2 + " + smoParent.liczbaZgl());
            // Przerwanie niecierpliwosci

            zgl.koniecNiecierpliwosci.interrupt();
            // Wygeneruj czas obsługi
            double czasObslugi = generator.normal(9.0, 1.0);
            System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO_2 - Początek obsługi zgl. nr: " + zgl.getTenNr());
            // Zaplanuj koniec obsługi

            smoParent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia(), simTime());
            smoParent.MVczasy_obslugi.setValue(czasObslugi,simTime());

            smoParent.zakonczObsluge = new ZakonczObsluge_2(smoParent, czasObslugi, zgl,smo_blak);
        }

    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}