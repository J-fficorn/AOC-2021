import java.util.*;
import java.io.*;

class aoc1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("input.in")));
        //Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        List<Integer> depths = new ArrayList<Integer>();
        List<Integer> windows = new ArrayList<Integer>();
        int n = 0;
        while (in.hasNextInt()) {
            depths.add(in.nextInt());
            in.nextLine();
            n++;
            if (n >= 3) {
              windows.add(depths.get(n - 1) + depths.get(n - 2) + depths.get(n - 3)); 
            }
        }
        int increasing = 0;
        for (int i = 0; i < windows.size() - 1; i++) {
            if (windows.get(i + 1) > windows.get(i)) {
                increasing++;
            }
        }
        out.print(increasing);
        in.close();
        out.close();
    }
}
