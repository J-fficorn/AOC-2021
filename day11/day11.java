import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    static int h = 10, w = 10, s = 100;
	public static void main(String[] args) throws FileNotFoundException {
	    long sTime = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("p.txt"))); //i.txt
        PrintWriter out = new PrintWriter(System.out);
        //List<Integer> a = new ArrayList<Integer>();
        long part1 = 0;
        int[][] grid = new int[h][w];
        in.useDelimiter("");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = in.nextInt();
            }
            if (i != h - 1) {
                in.nextLine();
            }
        }
        long firstAllFlash = 0;
        int step = 1;
        boolean allFlashed = false;
        while (!allFlashed) {
            allFlashed = false;
            boolean[][] flashed = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!flashed[i][j]) {
                        grid[i][j]++;
                    }
                    if (grid[i][j] == 10) {
                        flashed[i][j] = true;
                        grid[i][j] = 0;
                        allFlashed = true;
                        for (int he = 0; he < h; he++) {
                            for (int wi = 0; wi < w; wi++) {
                                if (!flashed[i][j]) {
                                    allFlashed = false;
                                }
                            }
                        }
                        allFlashed = ripple(grid, flashed, i, j);
                    }
                    if (allFlashed) {
                        firstAllFlash = step;
                    }
                }
            }
            step++;
        }
        out.println(firstAllFlash);
        long stop = System.currentTimeMillis();
        out.println("TIME: " + (stop - sTime));
        in.close();
        out.close();
	}
	
	public static boolean ripple(int[][] grid, boolean[][] flashed, int iS, int jS) {
	    boolean allFlashed = true;
	    int part1 = 1;
	    int i = iS;
	    int j = jS;
	    if (i != 0) {
            if (!flashed[i - 1][j]) {
                grid[i - 1][j]++;
            }
            if (grid[i - 1][j] == 10) {
                flashed[i - 1][j] = true;
                grid[i - 1][j] = 0;
                allFlashed = ripple(grid, flashed,i - 1, j);
            }
        } //up
        if (i != h - 1) {
            if (!flashed[i + 1][j]) {
                grid[i + 1][j]++;
            }
            if (grid[i + 1][j] == 10) {
                flashed[i + 1][j] = true;
                grid[i + 1][j] = 0;
                allFlashed = ripple(grid, flashed, i + 1, j);
            }
        } //down
        if (j != 0) {
            if (!flashed[i][j - 1]) {
                grid[i][j - 1]++;
            }
            if (grid[i][j - 1] == 10) {
                flashed[i][j - 1] = true;
                grid[i][j - 1] = 0;
                allFlashed = ripple(grid, flashed, i, j - 1);
            }
        } //left
        if (j != w - 1) {
            if (!flashed[i][j + 1]) {
                grid[i][j + 1]++;
            }
            if (grid[i][j + 1] == 10) {
                flashed[i][j + 1] = true;
                grid[i][j + 1] = 0;
                allFlashed = ripple(grid, flashed, i, j + 1);
            }
        } //right
        if (i != 0 && j != 0) {
            if (!flashed[i - 1][j - 1]) {
                grid[i - 1][j - 1]++;
            }
            if (grid[i - 1][j - 1] == 10) {
                flashed[i - 1][j - 1] = true;
                grid[i - 1][j - 1] = 0;
                allFlashed = ripple(grid, flashed, i - 1, j - 1);
            }
        } //up-left
        if (i != 0 && j != h - 1) {
            if (!flashed[i - 1][j + 1]) {
                grid[i - 1][j + 1]++;
            }
            if (grid[i - 1][j + 1] == 10) {
                flashed[i - 1][j + 1] = true;
                grid[i - 1][j + 1] = 0;
                allFlashed = ripple(grid, flashed, i - 1, j + 1);
            }
        } //up-right
        if (i != h - 1 && j != 0) {
            if (!flashed[i + 1][j - 1]) {
                grid[i + 1][j - 1]++;
            }
            if (grid[i + 1][j - 1] == 10) {
                flashed[i + 1][j - 1] = true;
                grid[i + 1][j - 1] = 0;
                allFlashed = ripple(grid, flashed, i + 1, j - 1);
            }
        } //down-left
        if (i != h - 1 && j != w - 1) {
            if (!flashed[i + 1][j + 1]) {
                grid[i + 1][j + 1]++;
            }
            if (grid[i + 1][j + 1] == 10) {
                flashed[i + 1][j + 1] = true;
                grid[i + 1][j + 1] = 0;
                allFlashed = ripple(grid, flashed, i + 1, j + 1);
            }
        } //down-right
        for (int he = 0; he < h; he++) {
            for (int wi = 0; wi < w; wi++) {
                if (!flashed[he][wi]) {
                    allFlashed = false;
                }
            }
        }
        return allFlashed;
	}
}
