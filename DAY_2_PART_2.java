import java.util.*;
import java.io.*;

class aoc2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
        //Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int h = 0; int v = 0; int a = 0;
        while (in.hasNext()) {
            String dir = in.next();
            int val = in.nextInt();
            out.println(dir + " " + val);
            if (dir.equals("down")) {
                a += val;
            } else if (dir.equals("up")) {
                a -= val;
            } else if (dir.equals("forward")) {
                h += val;
                v += (val * a);
            }
            out.println(h + " " + v + " " + a);
        }   
        out.println(h + " " + v);
        out.print(h * v);
        in.close();
        out.close();
    }
}
