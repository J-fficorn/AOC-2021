import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.math.*;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("practice.txt")));
        //Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        List<Integer> x1 = new ArrayList<Integer>();
        List<Integer> x2 = new ArrayList<Integer>();
        List<Integer> y1 = new ArrayList<Integer>();
        List<Integer> y2 = new ArrayList<Integer>();
        int fieldW = 0, fieldL = 0;
        while (in.hasNext()) {
            int x1V = in.nextInt();
            int y1V = in.nextInt();
            x1.add(x1V);
            y1.add(y1V);
            int x2V = in.nextInt();
            int y2V = in.nextInt();
            x2.add(x2V);
            y2.add(y2V);
            fieldW = Math.max(fieldW, Math.max(x1V, x2V));
            fieldL = Math.max(fieldL, Math.max(y1V, y2V));
        }
        fieldL++;
        fieldW++;
        int[][] field = new int[fieldL][fieldW];
        for (int i = 0; i < x1.size(); i++) {
            if (x1.get(i).equals(x2.get(i))) {
                int firstY = 0;
                if (y2.get(i) > y1.get(i)) {
                    firstY = y1.get(i);
                } else {
                    firstY = y2.get(i);
                }
                for (int j = 0; j < Math.abs(y2.get(i) - y1.get(i)) + 1; j++) {
                    field[firstY + j][x1.get(i)]++;
                }
                //out.println("HOR: " + x1.get(i) + " " + x2.get(i) + " FROM: " + y1.get(i) + " TO " + y2.get(i)); 
            }
            if (y1.get(i).equals(y2.get(i))) {
                int firstX = 0;
                if (x2.get(i) > x1.get(i)) {
                    firstX = x1.get(i);
                } else {
                    firstX = x2.get(i);
                }
                for (int j = 0; j < Math.abs(x2.get(i) - x1.get(i)) + 1; j++) {
                    field[y1.get(i)][firstX + j]++;
                }
                //out.println("VER: " + y1.get(i) + " " + y2.get(i) + " FROM: " + x1.get(i) + " TO " + x2.get(i)); 
            }
            int xDiff = Math.abs(x2.get(i) - x1.get(i));
            int yDiff = Math.abs(y2.get(i) - y1.get(i));
            if (xDiff == yDiff) {
                int firstX = x1.get(i);
                int firstY = y1.get(i);
                boolean xInc = false;
                boolean yInc = false;
                if (firstX > x2.get(i)) {
                    xInc = false;
                } else {
                    xInc = true;
                }
                if (firstY > y2.get(i)) {
                    yInc = false;
                } else {
                    yInc = true;
                }
                for (int j = 0; j < xDiff + 1; j++) {
                    int newX = 0, newY = 0;
                    if (xInc) {
                        newX = firstX + j;
                    } else {
                        newX = firstX - j;
                    }
                    if (yInc) {
                        newY = firstY + j;
                    } else {
                        newY = firstY - j;
                    }
                    field[newX][newY]++;
                }
                //out.println("DIA: " + x1.get(i) + " " + y1.get(i) + " FROM: " + x2.get(i) + " TO " + y2.get(i)); 
            }
        }
        int dang = 0;
        for (int i = 0; i < fieldL; i++) {
            for (int j = 0; j < fieldW; j++) {
                //out.print(field[i][j]);
                if (field[i][j] >= 2) {
                    dang++;
                }
            }
            //out.println();
        }
        out.println(dang);
        in.close();
        out.close();
	}
}
