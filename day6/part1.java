import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
///NAIVE SOLUTION
///EXPONENTIAL TIME COMPLEXITY
public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
		long sTime = System.currentTimeMillis();
        PrintWriter out = new PrintWriter(System.out);
        List<Integer> fish = new ArrayList<Integer>();
        List<Boolean> bool = new ArrayList<Boolean>();
        while (in.hasNext()) {
            fish.add(in.nextInt());  
        }
        for (int i = 0; i <= days; i++) {
            for (int j = 0; j < fish.size(); j++) {
                //out.print(fish.get(j) + " ");
                if (fish.get(j) == 0) {
                    fish.set(j, 6);
                    bool.set(j, true);
                } else {
                    if (bool.get(j)) {
                        fish.add(8);
                        bool.add(false);
                    }
                    fish.set(j, fish.get(j) - 1);
                    bool.set(j, false);
                }
            }
            //out.println();
        }
        out.println(zero + one + two + three + four + five + six + seven + eight);
        long stTime = System.currentTimeMillis();
        out.print("TIME: " + (stTime - sTime));
        in.close();
        out.close();
	}
}
