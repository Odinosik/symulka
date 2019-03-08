package smo;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

/**
 *
 * @author Dariusz Pierzchala
 * Description: Zdarzenie początkowe niecierpliwości zgłoszenia. Rozpoczyna niecierpliwość przez losowy czas
 */
public class Pozar extends BasicSimEvent<Zgloszenie, Object>
{
    private SimGenerator generator;
    private Otoczenie parent;
    public Pozar(Otoczenie parent, double delay) throws SimControlException
    {
        super(delay);
        generator = new SimGenerator();
        this.parent = parent;
    }

    public Pozar(Otoczenie parent) throws SimControlException
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
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Początek pozaru" );
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Pozar zatrzymuje zglaszaj" );
        parent.Zatrzymaj();



        double odstep = generator.normal(15.0, 1.0);
        odstep = 50.0;
        parent.koniecpozar = new KoniecPozar(parent, odstep);

    }

    @Override
    public Object getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}