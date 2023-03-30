package no.hvl.dat102.Oppg2;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.time.Duration;


public class oppgave2 {

	public static void main(String[] args) {
		int gjIterasjoner = 5;
		int antallnummer   = 128000;
		int[] randomtall = new int[gjIterasjoner];
		// int[] randomseeds   = {54321, 74639, 26493, 38563, 92819};

		for (int i = 0; i < gjIterasjoner; i++) {
			Random r = new Random();
			randomtall[i] = r.nextInt(10000);
		}

		 sorterIntOgInteger(gjIterasjoner, antallnummer, randomtall, sortmetode.FLETTE);
		//sorterIntOgInteger(gjIterasjoner, antallnummer, randomtall, sortmetode.UTVALG);
		// sorterIntOgInteger(gjIterasjoner, antallnummer, randomtall, sortmetode.INNSETTING);
		//sorterIntOgInteger(gjIterasjoner, antallnummer, randomtall, sortmetode.QUICKSORT);

		// oppgave 2 b
		Integer[] sammetall = new Integer[antallnummer];
		Arrays.fill(sammetall, 24);
		long tid = 0;
		for (int i = 0; i < gjIterasjoner; i++) {
			Instant start = Instant.now();
			TabellSortering.quickSort(sammetall, sammetall.length);
			Instant slutt = Instant.now();
			tid += Duration.between(start, slutt)
			               .toMillis();
		}

		System.out.println(
				"Quicksort paa tabell med tall n = " + antallnummer + " tok " + (tid / gjIterasjoner) + "ms");
	}

	private static Integer[] settOppTabell(int antall, int randomtall) {
		Integer[] tabell    = new Integer[antall];
		Random    tilfeldig = new Random(randomtall);
		for (int j = 0; j < antall; j++) {
			tabell[j] = tilfeldig.nextInt();
		}
		return tabell;
	}

	private static int[] settOppTabellInt(int antall, int randomtall) {
		int[]  tabell    = new int[antall];
		Random tilfeldig = new Random(randomtall);
		for (int j = 0; j < antall; j++) {
			tabell[j] = tilfeldig.nextInt();
		}
		return tabell;
	}

	private static void sorterIntOgInteger(int iterasjoner, int antallnummer, int[] randomtall, oppgave2.sortmetode sm) {
		System.out.println("Metode for sortering: " + sm.toString());
		System.out.println("Sorterering av tabell med Integer wrapper class:");
		long tidInteger = sorterIntegerOgTaTid(iterasjoner, antallnummer, randomtall, sm);
		System.out.println("Gjennomsnitt tid: " + tidInteger + "ms.");
		System.out.println("\nSortering av tabell med primitiv int:");
		long tidInt = sorterIntOgTaTid(iterasjoner, antallnummer, randomtall, sm);
		System.out.println("Gjennomsnitt tid: " + tidInt + "ms.\n");

	}

	private static long sorterIntOgTaTid(int iterasjoner, int antallnummer, int[] randomtall, oppgave2.sortmetode sm) {
		long totalTid = 0;

		System.out.print("Tider: ");
		for (int i = 0; i < iterasjoner; i++) {
			int[]   tabell = settOppTabellInt(antallnummer, randomtall[i]);
			Instant start  = Instant.now();
			switch (sm) {
				case INNSETTING -> TabellSorteringInt.sorteringVedInnsetting(tabell, tabell.length);
				case UTVALG -> TabellSorteringInt.utvalgssortering(tabell, tabell.length);
				case QUICKSORT -> TabellSorteringInt.quickSort(tabell, tabell.length);
				case FLETTE -> TabellSorteringInt.flettesortering(tabell, tabell.length);
			}
			Instant finish = Instant.now();
			long tid = Duration.between(start, finish)
			                   .toMillis();

			totalTid += tid;
			System.out.print(tid + "ms, ");
		}
		System.out.println();
		return totalTid / iterasjoner;
	}

	private static long sorterIntegerOgTaTid(int iterasjoner, int antallnummer, int[] randomtall, oppgave2.sortmetode sm) {
		long totalTid = 0;
		System.out.print("Tider: ");
		for (int i = 0; i < iterasjoner; i++) {
			Integer[] tabell = settOppTabell(antallnummer, randomtall[i]);
			Instant   start  = Instant.now();
			switch (sm) {
				case INNSETTING -> TabellSortering.sorteringVedInnsetting(tabell, tabell.length);
				case UTVALG -> TabellSortering.utvalgssortering(tabell, tabell.length);
				case QUICKSORT -> TabellSortering.quickSort(tabell, tabell.length);
				case FLETTE -> TabellSortering.flettesortering(tabell, tabell.length);
			}
			Instant finish = Instant.now();
			long tid = Duration.between(start, finish)
			                   .toMillis();

			totalTid += tid;
			System.out.print(tid + "ms, ");
		}
		System.out.println();
		return totalTid / iterasjoner;
	}

	private enum sortmetode {
		INNSETTING, UTVALG, QUICKSORT, FLETTE,
	}

}