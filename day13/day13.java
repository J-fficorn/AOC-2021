import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static ArrayList<int[]> dotLocs = new ArrayList<int[]>();
    static ArrayList<int[]> folds = new ArrayList<int[]>();
    static int total = 12, left = total;
    
	public static void main(String[] args) throws FileNotFoundException {
	    Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
	    int dots = 0;
	    int dotS = 903, foldS = 12; //dotS = 903, foldS = 12
	    int maxX = 0, maxY = 0;
	    for (int i = 0; i < dotS; i++) {
	        String[] temp = new String[2];
	        String s = in.nextLine();
	        temp = s.split(",");
	        int[] tempI = new int[2];
	        int x = Integer.parseInt(temp[0]);
	        int y = Integer.parseInt(temp[1]);
	        maxX = (x > maxX) ? x : maxX;
	        maxY = (y > maxY) ? y : maxY;
	        tempI[1] = x;
	        tempI[0] = y;
	        dotLocs.add(tempI);
	    } //input paper
	    for (int i = 0; i < foldS; i++) {
	        String[] temp = new String[2];
	        in.next(); in.next();
	        String s = in.next();
	        temp = s.split("=");
	        int[] tempI = new int[2];
	        if (temp[0].equals("y")) {
	            tempI[0] = 0;
	        } else {
	            tempI[0] = 1;
	        }
	        tempI[1] = Integer.parseInt(temp[1]);
	        folds.add(tempI);
	    } //input folds 
	    maxX++; maxY++;
	    int[][] paper = new int[maxY][maxX];
	    for (int[] loc : dotLocs) {
	        int x = loc[1];
	        int y = loc[0];
	        paper[y][x] = 1;
	    }
	    int[][] paperT = xFold(folds.get(0)[1], paper);
	    for (int i = 0; i < paperT.length; i++) {
	        for (int j = 0; j < paperT[0].length; j++) {
	            out.print(paperT[i][j] + " ");
	            dots += paperT[i][j];
	        }
	        out.println();
	    } //simulate
	    out.println("\n" + dots);
        in.close();
        out.close();
	}
	
	public static int[][] xFold(int coord, int[][] refPaper) {
	    left--;
	    int ySize = refPaper.length;
	    int xSize = 2 * coord;
	    int[][] newPaper = new int[ySize][coord];
	    for (int[] loc : dotLocs) { //loc[0] is y, loc[1] is x
	        int x = 0, y = loc[0];
	        if (loc[1] > coord) {
	            x = xSize - loc[1];
	        } else {
	            x = loc[1];
	        }
	        newPaper[y][x] = 1;
	    }
	    dotLocs.clear();
	    for (int i = 0; i < newPaper.length; i++) {
	        for (int j = 0; j < newPaper[0].length; j++) {
	            if (newPaper[i][j] == 1) {
	                int[] temp = {i, j};
	                dotLocs.add(temp);
	            }
	        }
	    }
        if (left != 0) {
            int[] fold = folds.get(total - left);
            if (fold[0] == 0) {
                //y-fold
                newPaper = yFold(fold[1], newPaper);
            } else {
                //x-fold
                newPaper = xFold(fold[1], newPaper);
            }
        }
	    return newPaper;
	}
	
	public static int[][] yFold(int coord, int[][] refPaper) {
	    left--;
	    int ySize = 2 * coord;
	    int xSize = refPaper[0].length;
	    int[][] newPaper = new int[coord][xSize];
	    for (int i = 0; i < dotLocs.size(); i++) {
	        int[] loc = dotLocs.get(i);
	        int x = loc[1], y = 0;
	        if (loc[0] > coord) {
                y = ySize - loc[0];
	        } else {
	            y = loc[0];
	        }
	        newPaper[y][x] = 1;
	    }
	    dotLocs.clear();
	    for (int i = 0; i < newPaper.length; i++) {
	        for (int j = 0; j < newPaper[0].length; j++) {
	            if (newPaper[i][j] == 1) {
	                int[] temp = {i, j};
	                dotLocs.add(temp);
	            }
	        }
	    }
        if (left != 0) {
            int[] fold = folds.get(total - left);
            if (fold[0] == 0) {
                //y-fold
                newPaper = yFold(fold[1], newPaper);
            } else {
                //x-fold
                newPaper = xFold(fold[1], newPaper);
            }
        }
	    return newPaper;
	}
	
	public static void printPaper(int[][] nextRefPaper) {
	    for (int i = 0; i < nextRefPaper.length; i++) {
	        for (int j = 0; j < nextRefPaper[0].length; j++) {
	            out.print(nextRefPaper[i][j] + " ");
	        }
	        out.println();
	    }
	    out.print("\n\n");
	}
}
