import java.util.*;
import java.io.*;

class aoc1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("input.in")));
        //Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        List<Integer> depths = new ArrayList<Integer>();
        int n = 0;
        while (in.hasNextInt()) {
            depths.add(in.nextInt());
            in.nextLine();
            n++;
        }
        int increasing = 0;
        for (int i = 0; i < n - 1; i++) {
            if (depths.get(i + 1) > depths.get(i)) {
                increasing++;
            }
        }
        out.print(increasing);
        in.close();
        out.close();
    }
}
