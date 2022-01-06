import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
	    long sTime = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
        PrintWriter out = new PrintWriter(System.out);
        List<String> watch = new ArrayList<String>();
        List<String> op = new ArrayList<String>();
        List<Integer> numbers = new ArrayList<Integer>();
        String[] alpha = {"a", "b", "c", "d", "e", "f", "g"};
        int w = 10, o = 4, n = 200;
        while (in.hasNext()) {
            for (int i = 0; i < w; i++) {
                watch.add(in.next());
            }
            in.next();
            for (int i = 0; i < o; i++) {
                op.add(in.next());
            }
        } //input works
        int part1 = 0;
        for (String v : op) {
            if (v.length() == 2 || v.length() == 3 || v.length() == 4 || v.length() == 7) {
                part1++;
            }
        } //part 1 works
        out.println(part1);
        long stTime = System.currentTimeMillis();
        out.println(stTime - sTime);
        in.close();
        out.close();
	}
}
