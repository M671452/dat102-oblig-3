package no.hvl.dat102.Oppg2;

public class TabellSorteringInt {

	public static void  swap(int[] a, int i, int j) {
		int h = a[i];
		a[i] = a[j];
		a[j] = h;
	}

	
	 // Utvalgssortering / Plukksortering //
	  
	public static void utvalgssortering(int[] a, int n) {
		for (int i = 0; i < n - 1; i++) {
			int smallPos = finnSmallPos(a, i, n - 1);
			swap(a, i, smallPos);
		}
	}


	private static int finnSmallPos(int[] a, int fra, int til) {
		int p = fra;
		for (int i = fra + 1; i <= til; i++) {
			if (a[i] < a[p]) {
				p = i;
			}
		}

		return p;
	}

	/**
	 * innsetting-sortering
	 */
	public static void sorteringVedInnsetting(int[] a, int n) {
		sorteringVedInnsetting(a, 0, n - 1);
	}


	public static void sorteringVedInnsetting(int[] a, int start, int slutt) {
		for (int i = start + 1; i <= slutt; i++) {
			int     h   = a[i];
			int     j      = i - 1;
			boolean ferdig = false;
			while (!ferdig && j >= 0) {
				if (h < a[j]) {
					a[j + 1] = a[j];
					j--;
				} else {
					ferdig = true;
				}
			}
			a[j + 1] = h;
		}
	}

	/**
	 * Flettesortering
	 */
	public static void flettesortering(int[] a, int n) {
		flettesortering(a, 0, n - 1);
	}


	public static void flettesortering(int[] a, int first, int last) {
		
		int[] hArray = new int[a.length]; 
		flettesortering(a, hArray, first, last);
	}


	private static void flettesortering(int[] a, int[] hTab, int first, int last) {
		if (first >= last) {
			
		} else {
			int middelpoint = (first + last) / 2;
			flettesortering(a, hTab, first, middelpoint);
			flettesortering(a, hTab, middelpoint + 1, last);
			flette(a, hTab, first, middelpoint, last);
		}
	}

	private static void flette(int[] a, int[] hTab, int first, int middel, int last) {
		
		int fV     = first;
		int sluttV = middel;
		int fH     = middel + 1;
		int sluttH = last;

		
		int index = fV; 
		for (; (fV <= sluttV) && (fH <= sluttH); index++) {
			if (a[fV] < a[fH]) {
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


		for (index = first; index <= last; index++) {
			a[index] = hTab[index];
		}
	}

	
	public static final int MIN_SIZE = 5;

	public static void quickSort(int[] array, int n) {
		kvikksorter(array, 0, n - 1);

		
		sorteringVedInnsetting(array, n);
	}

	public static void kvikksorter(int[] a, int first, int last) {
		if (last - first + 1 > MIN_SIZE) {
			int divpoint = partition(a, first, last);
			kvikksorter(a, first, divpoint - 1);
			kvikksorter(a, divpoint + 1, last);
		}
	}


	private static int partition(int[] a, int first, int last) {
		int middel = (first + last) / 2;

	
		sortFirstMiddleLast(a, first, middel, last);


		swap(a, middel, last - 1);
		int pivotIndex = last - 1;
		int pivotValue = a[pivotIndex];

		

		int fraVenstre = first + 1;
		int fraHogre   = last - 2;

		boolean ferdig = false;
		while (!ferdig) {

			while (a[fraVenstre] < pivotValue) {
				fraVenstre++;
			}

			while (a[fraHogre] < pivotValue) {
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

	
	private static void sortFirstMiddleLast(int[] a, int forste, int mid, int siste) {
		order(a, forste, mid); 
		order(a, mid, siste); 
		order(a, forste, mid); 
	}


	private static void order(int[] a, int i, int j) {
		if (a[i] < a[j]) {
			swap(a, i, j);
		}
	}
}