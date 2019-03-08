package smo;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;


public class StartBlakania extends BasicSimEvent<Zgloszenie, Object>
{
    private SimGenerator generator;
    private Zgloszenie parent;
    private Smo smo;
    private Smo_2 smo_2;

    public StartBlakania(Zgloszenie parent, double delay) throws SimControlException
    {
        super(parent, delay);
        generator = new SimGenerator();
        this.parent = parent;
    }

    public StartBlakania(Zgloszenie parent,Smo smo1, Smo_2 smo_2) throws SimControlException
    {
        super(parent);
        smo=smo1;
        generator = new SimGenerator();
        this.parent = parent;
        this.smo_2 = smo_2;
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

        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Poczatek blakania zgl. nr: " + parent.getTenNr());
        double odstep = generator.normal(15.0, 1.0);

        parent.koniecBlakania = new KoniecBlakania(parent, odstep,smo , smo_2);

    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}