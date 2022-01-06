import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt"))); //input.txt
        PrintWriter out = new PrintWriter(System.out);
        List<Integer> subs = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        in.useDelimiter(",");
        while (in.hasNext()) {
            int next = in.nextInt();
            if (next < min) {
                min = next;
            }
            if (next > max) {
                max = next;
            }
            subs.add(next);
        }
        int fuel = Integer.MAX_VALUE;
        for (int i = min; i < max; i++) {
            int sum = 0;
            for (int sub : subs) {
                int j = 1;
                int curSub = sub;
                while (curSub != i) {
                    if (curSub < i) {
                        sum += j;
                        curSub++;
                    } else {
                        sum += j;
                        curSub--;
                    }
                    j++;
                }
            }
            fuel = Math.min(sum, fuel);
        }
        out.println(fuel);
        in.close();
        out.close();
	}
}
