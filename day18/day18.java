import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static LinkedList<Integer> hw = new LinkedList<Integer>();
    static List<String> hwList = new ArrayList<String>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		boolean first = true;
		while (in.hasNext()) {
		    String str = in.nextLine();
		    hwList.add(str);
            for (int i = 0; i < str.length(); i++) {
    	        switch (str.charAt(i)) {
                    case '[': hw.add(-1); break;
                    case ']': hw.add(-2); break;
                    case ',': break;
                    default: hw.add(Integer.parseInt(str.substring(i, (i == str.length() - 1) ? i : i + 1)));
                }
            } //create hw
    		if (!first) {
                hw.addFirst(-1);
                hw.addLast(-2);
    		} else {
    		    first = false;
    		}
    		reduce();
		    /*itr = hw.iterator();
		    String sR = "";
		    while (itr.hasNext()) {
		        Integer iBoxed = itr.next();
		        int i = iBoxed.intValue();
		        sR = sR.concat((i < 0) ? ((i == -1) ? "[ " : "] ") : Integer.toString(i) + " ");
		    }
		    System.out.println("REDUCED: " + sR); //DEBUG INPUT*/
		} //LOOP
		findMag();
		for (int i : hw) { out.println(i); }
		for (int i = 0; i < hwList.size(); i++) {
		    for (int j = i + 1; j < hwList.size(); j++) {
		        String a = hwList.get(i), b = hwList.get(j);
		        
		    }
		}
        in.close();
        out.close();
	}
	
	public static void reduce() {
	    while (explode() || split());
	}
	
	public static void findMag() {
	    while (mag());
	}
	
	public static boolean explode() {
	    int depth = 0;
        int i = 0;
        int size = hw.size();
        for (int j = 0; j < size; j++) { //loop through
            i = hw.get(j);
	        switch (i) {
	            case -1: ++depth; break;
	            case -2: --depth; break;
	        }
	        if (depth > 4) {
                int l = hw.get(j + 1), r = hw.get(j + 2);
	            for (int k = j - 1; k >= 0; k--) {
                    if (hw.get(k) >= 0) {
                        hw.set(k, hw.get(k) + l);
                        break;
                    }
	            } //find left
	            for (int k = j + 4; k < hw.size(); k++) {
	                if (hw.get(k) >= 0) {
	                    hw.set(k, hw.get(k) + r);
	                    break;
	                }
	            } //find right
	            hw.set(j, 0);
	            hw.remove(j + 1); hw.remove(j + 1); hw.remove(j + 1); //vals
	            size = hw.size();
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean split() {
	    int i = 0, size = hw.size();
	    for (int j = 0; j < size; j++) {
	        i = hw.get(j);
	        if (i > 9) {
	            int l = i / 2, r = ((i % 2 == 0) ? l : l + 1);
	            hw.remove(j);
	            hw.add(j, -2);
	            hw.add(j, r);
	            hw.add(j, l);
	            hw.add(j, -1);
	            size = hw.size();
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean mag() {
        int i = 0;
        int size = hw.size();
        for (int j = 0; j < size; j++) { //loop through
            i = hw.get(j);
            if (i == -2) {
                int l = hw.get(j - 2), r = hw.get(j - 1), mg = 3 * l + 2 * r;
                hw.set(j - 3, mg);
                hw.remove(j - 2); hw.remove(j - 2); hw.remove(j - 2);
    	        size = hw.size();
        	    return true;
            }
	    }
	    return false;
	}
}
