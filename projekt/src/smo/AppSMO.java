package smo;

/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

import java.awt.*;
import java.math.BigDecimal;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();
			// Powołanie Smo 
			Smo smo = new Smo();
			Smo_2 smo_2= new Smo_2();

			// Utworzenie otoczenia
			Otoczenie generatorZgl = new Otoczenie(smo,smo_2);
			// Dwa sposoby zaplanowanego końca symulacji
			//model.setEndSimTime(10000);
			// lub
			SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "startSimulation" 
			model.startSimulation();



			//CZAS OCZEKIWANIA


			double wynik = new BigDecimal(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę:   "
							+ wynik);
			wynik = new BigDecimal(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi:       "
							+ wynik);
			wynik = new BigDecimal(Statistics.max(smo.MVczasy_oczekiwania))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę : "
					+ wynik);



			double wynik_2 = new BigDecimal(Statistics
					.arithmeticMean(smo_2.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę SMO 2:   "
							+ wynik_2);
			wynik_2 = new BigDecimal(Statistics
					.standardDeviation(smo_2.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi SMO 2:       "
							+ wynik_2);
			wynik_2 = new BigDecimal(Statistics.max(smo_2.MVczasy_oczekiwania))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę SMO 2: "
					+ wynik_2);









			Diagram d3 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,
					"Czasy oczekiwania na obsługę w smo ");
			d3.add(smo.MVczasy_oczekiwania, java.awt.Color.BLUE);
			d3.add(smo_2.MVczasy_oczekiwania, Color.GREEN);
			d3.show();









			//DLUGOSC KOLEJKI

			double wynik1 = new BigDecimal(Statistics
					.arithmeticMean(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia dlugosci kolejki:   "
							+ wynik1);
			wynik1 = new BigDecimal(Statistics
					.standardDeviation(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dlugosci kolejki :       "
							+ wynik1);
			wynik1 = new BigDecimal(Statistics.max(smo.MVdlKolejki))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna dlugosci kolejki: "
					+ wynik1);




			double wynik1_2 = new BigDecimal(Statistics
					.arithmeticMean(smo_2.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia dlugosci kolejki:   "
							+ wynik1_2);
			wynik1_2 = new BigDecimal(Statistics
					.standardDeviation(smo_2.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie  dlugosci kolejki obsługi:       "
							+ wynik1_2);
			wynik1_2 = new BigDecimal(Statistics.max(smo_2.MVdlKolejki))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna dlugosci kolejki: "
					+ wynik1_2);




			Diagram d4 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,
					"Dlugosc kolejki w smo");
			d4.add(smo.MVdlKolejki, java.awt.Color.BLUE);
			d4.add(smo_2.MVdlKolejki, Color.GREEN);
			d4.show();




			//CZAS OBSLUGI


			double wynik2 = new BigDecimal(Statistics
					.arithmeticMean(smo.MVczasy_obslugi)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu obslugi :   "
							+ wynik2);
			wynik2 = new BigDecimal(Statistics
					.standardDeviation(smo.MVczasy_obslugi)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe czasu obslugi :       "
							+ wynik2);
			wynik2 = new BigDecimal(Statistics.max(smo.MVczasy_obslugi))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu obslugi: "
					+ wynik2);


			double wynik2_2 = new BigDecimal(Statistics
					.arithmeticMean(smo_2.MVczasy_obslugi)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu obslugi :   "
							+ wynik2_2);
			wynik2_2 = new BigDecimal(Statistics
					.standardDeviation(smo_2.MVczasy_obslugi)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe czasu obslugi :       "
							+ wynik2_2);
			wynik2_2 = new BigDecimal(Statistics.max(smo_2.MVczasy_obslugi))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu obslugi: "
					+ wynik2_2);


			Diagram d5 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,
					"casz obslugi ");
			d5.add(smo.MVczasy_obslugi, java.awt.Color.BLUE);
			d5.add(smo_2.MVczasy_obslugi, Color.GREEN);
			d5.show();






		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
