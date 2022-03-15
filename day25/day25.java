import java.util.*;
import java.io.*;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static char[][] field;
    static boolean[] movable;
    static int l, w;
    
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		List<String> input = new ArrayList<String>();
		long start = System.currentTimeMillis();
	    while (in.hasNextLine()) input.add(in.nextLine());
	    l = input.size(); w = input.get(0).length();
		field = new char[l][w];
		for (int i = 0; i < l; i++) {
		    for (int j = 0; j < w; j++) field[i][j] = input.get(i).charAt(j);
		}
		out.println(seaCucumberMove());
		out.println((System.currentTimeMillis() - start) + " ms");
		out.printf("%.1f mb\n", memoryUse());
        in.close(); out.close();
	}
	
	public static int seaCucumberMove() {
	    int ret = 1;
	    boolean h = true, v = true;
	    while (h || v) {
	        h = SCMoveHorizontal(); v = SCMoveVertical();
	        if (h || v) ret++;
	    }
	    return ret;
	}
	
	public static boolean SCMoveHorizontal() {
	    boolean moved = false;
	    for (int i = 0; i < l; i++) {
	        movable = new boolean[w];
	        for (int j = 0; j < w; j++) movable[j] = (field[i][j] == '>' && field[i][(j + 1) % w] == '.');
	        for (int j = 0; j < w; j++) {
	            if (movable[j]) {
	                field[i][j] = '.'; field[i][(j + 1) % w] = '>';
	                moved = true;
	            }   
	        }
	    }
	    return moved;
	}
	
	public static boolean SCMoveVertical() {
	    boolean moved = false;
	    for (int i = 0; i < w; i++) {
	        movable = new boolean[l];
	        for (int j = 0; j < l; j++) movable[j] = field[j][i] == 'v' && field[(j + 1) % l][i] == '.';
	        for (int j = 0; j < l; j++) {
	            if (movable[j]) {
	                field[j][i] = '.'; field[(j + 1) % l][i] ='v';
	                moved = true;
	            }
	        }
	    }
	    return moved;
	}
	
	public static String printField() {
	    StringBuilder sb = new StringBuilder("");
	    for (int i = 0; i < l; i++) {
	        for (int j = 0; j < w; j++) sb.append(field[i][j] + " ");
	        sb.append("\n");
	    }
	    return sb.toString();
	}
	
	public static double memoryUse() { return (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024); }
}
