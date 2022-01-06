import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    static int h = 10, w = 10, mod = 5, dH = h * mod, dW = w * mod; //100, 100
    static int[][] field = new int[h][w]; //do not use w/o previous lines
    static int[] dY = {0, 0, -1, 1}, dX = {-1, 1, 0, 0};
    static PrintWriter out = new PrintWriter(System.out);
    
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("p.txt"))); //i.txt
        //List<Integer> a = new ArrayList<Integer>();
        int hT = 0;
        int[][] dist = new int[dH][dW];
        while (in.hasNext()) {
            String[] s = in.nextLine().split("");
            w = s.length;
            for (int i = 0; i < w; i++) {
                field[hT][i] = Integer.parseInt(s[i]);
            }
            hT++;
        } //create field
        path(dist);
        int shortest = dist[dH - 1][dW - 1];
        out.println(shortest);
        in.close();
        out.close();
	}
	
	public static void path(int[][] dist) {
        for (int i = 0; i < dH; i++) {
            for (int j = 0; j < dW; j++) {
                dist[i][j] = (i == 0 && j == 0) ? 0 : Integer.MAX_VALUE;
            }
        }
        PriorityQueue<int[]> q = new PriorityQueue<int[]>(Comparator.comparingInt(u -> u[2]));
        q.add(new int[] {0, 0, dist[0][0]});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int y = temp[0], x = temp[1], r = temp[2];
            for (int i = 0; i < dY.length; i++) {
                int nY = y + dY[i], nX = x + dX[i];
                if (nY < 0 || nY > h - 1 || nX < 0 || nX > w - 1) { //if out of bounds, skip
                    continue;
                }
                int nR = risk(nY, nX);
                if (r + nR < dist[nY][nX]) {
                    dist[nY][nX] = r + nR;
                    q.add(new int[] {nY, nX, dist[nY][nX]});
                }
            }
            if (q.isEmpty()) {
                out.println("EMPTY: " + y + " " + x + " " + r);
            }
        }
	}
	
	public static int risk(int i, int j) {
        int rows = field.length;
        int cols = field[0].length;
        int fR = field[i % rows][j % cols];
        return (fR - 1 + i / rows + j / cols) % 9 + 1; //val + manhattan distance
    }
}
