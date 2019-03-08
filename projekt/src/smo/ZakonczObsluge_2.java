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

public class ZakonczObsluge_2 extends BasicSimEvent<Smo_2, Zgloszenie>
{
    private Smo_2 smoParent;
    private Smo smo_blak;

    public ZakonczObsluge_2(Smo_2 parent, double delay, Zgloszenie zgl, Smo smo_blak) throws SimControlException
    {
        super(parent, delay, zgl);
        this.smoParent = parent;
        this.smo_blak = smo_blak;
    }

    public ZakonczObsluge_2(Smo_2 parent, SimEventSemaphore semafor, Zgloszenie zgl) throws SimControlException
    {
        super(parent, semafor, zgl);
        this.smoParent = parent;
    }

    @Override
    protected void onInterruption() throws SimControlException {
        System.out.println("Koniec obslugi z smo 2");
    }

    @Override
    protected void onTermination() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void stateChange() throws SimControlException {
        smoParent.setWolne(true);
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO_2- Koniec obsługi zgl. nr: " + transitionParams.getTenNr());

        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        if (n <25) {
            smo_blak.dodaj_blak(transitionParams);
            transitionParams.startBlakania = new StartBlakania(transitionParams,smo_blak, smoParent);
        }
        // Zaplanuj dalsza obsługe w tym gnieździe
        if (smoParent.liczbaZgl() > 0)
        {

            smoParent.rozpocznijObsluge = new RozpocznijObsluge_2(smo_blak, smoParent);
        }
    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}