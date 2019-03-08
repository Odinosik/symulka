package smo;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.Random;


public class KoniecPozar extends BasicSimEvent<Zgloszenie, Object>
{
   Otoczenie parent;
    public KoniecPozar(Otoczenie parent, double delay) throws SimControlException
    {
        super(delay);
        this.parent = parent;

    }

    public KoniecPozar(Zgloszenie parent) throws SimControlException
    {
        super(parent);

    }

    @Override
    protected void onInterruption() throws SimControlException {
    }

    @Override
    protected void onTermination() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void stateChange() throws SimControlException {
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Koniec pozaru ");
        parent.Wznow();



    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}