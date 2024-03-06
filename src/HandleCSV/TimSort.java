package HandleCSV;

public class TimSort {
	public static void sortCSV() {
		String[][] spotifyAndYoutubeData = CompleteStoreCSV.completeStoreSpotifyAndYoutubeCSV();
		String[][] topSpotifySongsData = CompleteStoreCSV.completeTopSpotifySongs();
		
		// Key is at index 28 for spotifyAndYoutubeData
		// Key is at index 25 for topSpotifySongsData
		
	}

    static int MIN_MERGE = 32; 
  
    public static int minRunLength(int n) 
    { 
        assert n >= 0; 
  
        int r = 0; 
        while (n >= MIN_MERGE) { 
            r |= (n & 1); 
            n >>= 1; 
        } 
        return n + r; 
    } 
  
    public static void insertionSort(String[][] arr, int left, 
                                     int right) 
    { 
        for (int i = left + 1; i <= right; i++) { 
            String temp = arr[arr.length - 1][i]; 
            int j = i - 1; 
            while (j >= left && arr[arr.length - 1][j].compareTo(temp) > 0) { 
                arr[arr.length - 1][j + 1] = arr[arr.length - 1][j]; 
                j--; 
            } 
            arr[arr.length - 1][j + 1] = temp; 
        } 
    } 
  
    public static void merge(String[][] arr, int l, int m, int r) 
    { 
        int len1 = m - l + 1, len2 = r - m; 
        String[] left = new String[len1]; 
        String[] right = new String[len2]; 
        for (int x = 0; x < len1; x++) { 
            left[x] = arr[arr.length - 1][l + x]; 
        } 
        for (int x = 0; x < len2; x++) { 
            right[x] = arr[arr.length - 1][m + 1 + x]; 
        } 
  
        int i = 0; 
        int j = 0; 
        int k = l; 
  
        while (i < len1 && j < len2) { 
            if (left[i].compareTo(right[j]) <= 0) { 
                arr[arr.length - 1][k] = left[i]; 
                i++; 
            } 
            else { 
                arr[arr.length - 1][k] = right[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < len1) { 
            arr[arr.length - 1][k] = left[i]; 
            k++; 
            i++; 
        } 
  
        while (j < len2) { 
            arr[arr.length - 1][k] = right[j]; 
            k++; 
            j++; 
        } 
    } 
  
    public static void timSort(String[][] arr, int n) 
    { 
        int minRun = minRunLength(MIN_MERGE); 
  
        for (int i = 0; i < n; i += minRun) { 
            insertionSort( 
                arr, i, 
                Math.min((i + MIN_MERGE - 1), (n - 1))); 
        } 
  
        for (int size = minRun; size < n; size = 2 * size) { 
  
            for (int left = 0; left < n; left += 2 * size) { 
  
                int mid = left + size - 1; 
                int right = Math.min((left + 2 * size - 1), 
                                     (n - 1)); 
  
                if (mid < right) 
                    merge(arr, left, mid, right); 
            } 
        } 
    } 
}
