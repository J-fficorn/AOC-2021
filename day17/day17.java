import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static int lowX, highX, lowY, highY;
    static int minX, maxX, minY, maxY;
    static boolean p1 = false;
    
	public static void main(String[] args) throws FileNotFoundException {
	    long start = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		lowX = in.nextInt(); highX = in.nextInt(); lowY = in.nextInt(); highY = in.nextInt();
		minX = findMin(); maxX = highX; minY = lowY; maxY = (-1 * lowY - 1);
		int part1 = (lowY + 1) * (lowY) / 2;
		int part2 = 0;
		for (int i = minX; i <= maxX; i++) {
		    for (int j = minY; j <= maxY; j++) {
		        int[] velo = {i, j};
		        if (sim(velo)) {
		            part2++;
		        }
		    }
		}
		out.println(p1 ? "PART 1: " + part1 : "PART 2: " + part2);
		if (!p1) { out.println(minX + " " + maxX + " | " + minY + " " + maxY); }
		out.println("TIME: " + (System.currentTimeMillis() - start));
        in.close();
        out.close();
	}
	
	///SIM METHODS///
	public static boolean sim(int[] velo) {
		int[] pos = {0, 0};
		while (pos[0] <= highX && pos[1] >= lowY) {
		    pos[0] += velo[0]; pos[1] += velo[1];
		    velo[0] = (velo[0] > 0 || velo[0] < 0) ? ((velo[0] > 0) ? --velo[0] : ++velo[0]) : 0; velo[1]--;
		    if (inTarget(pos)) { return true; }
		}
		return false;
	}
	
	/// HELPER METHODS ///
	public static int findMin() {
	    int min = 0;
	    for (int i = 0; i <= highX; i++) {
	        if ( (((i + 1) *(i)) / 2) >= lowX) {
	            min = i;
	            break;
	        }
        }
        return min;
	} //find lowest x-value
	
	public static boolean inTarget(int[] pos) {
	    return (lowX <= pos[0] && highX >= pos[0] && lowY <= pos[1] && highY >= pos[1]);
	} //check if in target
}
