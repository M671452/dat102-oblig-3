package no.hvl.dat102.Oppg2;

public class TabellSortering {
/*
 * jeg bytter om a[i] og a[j] ved å bruke temporary(h). 
 */
	public static void swap(Object[] a, int i, int j) {
		Object h = a[i];
		a[i] = a[j];
		a[j] = h;
	}
	/*
	 * Utvalgsortering 
	 * jeg sorerte de først n elementene i tabellen. 
	 */


	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a, int n) {
		for (int i = 0; i < n - 1; i++) {
			int smallPos = finnSmallPos(a, i, n - 1);
			swap(a, i, smallPos);
		}
	}

	private static <T extends Comparable<? super T>> int finnSmallPos(T[] a, int fra, int til) {
		int p = fra;
		for (int i = fra + 1; i <= til; i++) {
			if (a[i].compareTo(a[p]) < 0) {
				p = i;
			}
		}

		return p;
	}
	/*
	 * innsettingssortering
	 */


	public static <T extends Comparable<? super T>> void sorteringVedInnsetting(T[] a, int n) {
		sorteringVedInnsetting(a, 0, n - 1);
	}
	


	public static <T extends Comparable<? super T>> void sorteringVedInnsetting(T[] a, int start, int slutt) {
		for (int i = start + 1; i <= slutt; i++) {
			T       h   = a[i];
			int     j      = i - 1;
			boolean ferdig = false;
			while (!ferdig && j >= 0) {
				if (h.compareTo(a[j]) < 0) {
					a[j + 1] = a[j];
					j--;
				} else {
					ferdig = true;
				}
			}
			a[j + 1] = h;
		}
	}
	// flettesortering

	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int n) {
		flettesortering(a, 0, n - 1);
	}


	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int first, int last) {
		
		@SuppressWarnings("unchecked") T[] hArray = (T[]) new Comparable<?>[a.length]; 
		flettesortering(a, hArray, first, last);
	}


	private static <T extends Comparable<? super T>> void flettesortering(T[] a, T[] hTab, int forste,
            int siste) {
     if (forste >= siste) {

     int midtpkt = (forste + siste) / 2;
     flettesortering(a, hTab, forste, midtpkt);
     flettesortering(a, hTab, midtpkt + 1, siste);
     flette(a, hTab, forste, midtpkt, siste);

}
	}

	
	private static <T extends Comparable<? super T>> void flette(T[] a, T[] hTab, int forste, int middle,int siste) {
		
		int fV     = forste;
		int sluttV = middle;
		int fH     = middle + 1;
		int sluttH = siste;

		
		int index = fV;
		for (; (fV <= sluttV) && (fH <= sluttH); index++) {
			if (a[fV].compareTo(a[fH]) < 0) {
				hTab[index] = a[fV];
				fV++;
			} else {
				hTab[index] = a[fH];
				fH++;
			}
		}

	
		for (; fV <= sluttV; fV++, index++) {
			hTab[index] = a[fV];
		}

		for (; fH <= sluttH; fH++, index++) {
			hTab[index] = a[fH];
		}
		for (index = forste; index <= siste; index++) {
			a[index] = hTab[index];
		}
	}
/*
 * Quicksortering
 * Sorterer en matrise i stigende rekkefølge. Bruker rask sortering med median-av-tre
 * pivotvalg for matriser med minst MIN_SIZE oppføringer, og bruker innsetting
 * sorter for andre matriser.
 */


	public static final int MIN_SIZE = 5;

	public static <T extends Comparable<? super T>> void quickSort(T[] array, int n) {
		kvikksorter(array, 0, n - 1);


		sorteringVedInnsetting(array, n);
	}


	public static <T extends Comparable<? super T>> void kvikksorter(T[] a, int forste, int siste) {
		if (siste - forste + 1 > MIN_SIZE) {
			int divpoint = partition(a, forste, siste);
			kvikksorter(a, forste, divpoint - 1);
			kvikksorter(a, divpoint + 1, siste);
		}
	}


	private static <T extends Comparable<? super T>> int partition(T[] a, int forste, int siste) {
		int middle = (forste + siste) / 2;

		
		sortFirstMiddleLast(a, forste, middle, siste);



		swap(a, middle, siste - 1);
		int pivotIndex = siste - 1;
		T   pivotValue = a[pivotIndex];


		int fraVenstre = forste + 1;
		int fraHogre   = siste - 2;

		boolean ferdig = false;
		while (!ferdig) {

			while (a[fraVenstre].compareTo(pivotValue) < 0) {
				fraVenstre++;
			}

			while (a[fraHogre].compareTo(pivotValue) > 0) {
				fraHogre--;
			}

			if (fraVenstre < fraHogre) {
				swap(a, fraVenstre, fraHogre);
				fraVenstre++;
				fraHogre--;
			} else {
				ferdig = true;
			}
		}


		swap(a, pivotIndex, fraVenstre);
		pivotIndex = fraVenstre;

		return pivotIndex;
	}

	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
		order(a, first, mid); 
		order(a, mid, last); 
		order(a, first, mid); 
	}


	private static <T extends Comparable<? super T>> void order(T[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0) {
			swap(a, i, j);
		}
	}
}