package Java.Screens;

import java.util.ArrayList;

public class StoreSelected {
    static ArrayList<String[]> list = new ArrayList<>();

    public static void set(ArrayList<String[]> newList) {
        list = newList;
    }
    
    public static ArrayList<String[]> getList() {
    	return list;
    }
}
