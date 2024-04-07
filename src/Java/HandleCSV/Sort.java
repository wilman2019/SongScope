package Java.HandleCSV;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {
	public static void sort(String[][] arr) {
		 Arrays.sort(arr, Comparator.comparing((String[] a) -> a[arr[0].length - 1]));
	}
}
