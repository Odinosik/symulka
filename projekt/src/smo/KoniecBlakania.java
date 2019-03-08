package smo;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.Random;


public class KoniecBlakania extends BasicSimEvent<Zgloszenie, Object>
{
    private Zgloszenie parent;
    private Smo smo;
    private Smo_2 smo2;
    public KoniecBlakania(Zgloszenie parent, double delay,Smo smo1, Smo_2 smo2) throws SimControlException
    {
        super(parent, delay);
        this.smo=smo1;
        this.smo2 = smo2;
        this.parent = parent;
    }

    public KoniecBlakania(Zgloszenie parent) throws SimControlException
    {
        super(parent);
        this.parent = parent;
    }

    @Override
    protected void onInterruption() throws SimControlException {
        System.out.println("przerwanie blakania");
    }

    @Override
    protected void onTermination() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void stateChange() throws SimControlException {
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Koniec blakania zgl. nr: " + parent.getTenNr());
        Random rand = new Random();

        int n = rand.nextInt(50) + 1;

        if(n < 25){
            parent.czasOdniesienia=simTime();

            smo.usun_blak(parent);

            int x = rand.nextInt(50) + 1;

            if (x<25) {
                System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Ponowne dodanie zgl. nr:  " + parent.getTenNr() + " Do SMO_2");
                parent.czasOdniesienia = simTime();
                smo2.dodaj(parent);
            } else {
                System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Ponowne dodanie zgl. nr:  " + parent.getTenNr() + "Do SMO ");
                parent.czasOdniesienia = simTime();
                smo.dodaj(parent);

            }

            parent.startNiecierpliwienia = new StartNiecierpliwienia(parent);
        } else {
            smo.usun_blak(parent);
            System.out.println(simTime() + " - " + simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - " + simDate(SimDateField.MILLISECOND) + ": Zgloszenie nie wraca do kolejki zgl. nr:  " + parent.getTenNr());
        }

    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}