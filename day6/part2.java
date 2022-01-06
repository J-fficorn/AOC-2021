import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
///LINEAR SOLUTION (WORKS FOR PART 1)
public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
		long sTime = System.currentTimeMillis();
        PrintWriter out = new PrintWriter(System.out);
        long zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0;
        in.useDelimiter(",");
        int days = 256;
        while (in.hasNext()) {
            long next = in.nextInt();
            if (next == 0) {
                zero++;
            } else if (next == 1) {
                one++;
            } else if (next == 2) {
                two++;
            } else if (next == 3) {
                three++;
            } else if (next == 4) {
                four++;
            } else if (next == 5) {
                five++;
            }
        }
        long tempZero = 0;
        for (int i = 1; i <= days; i++) {
            //out.println(i + ": ");
            //out.println("BEFORE: " + zero + " " + one + " " + two + " " + three + " " + four + " " + five + " " + six + " " + seven + " " + eight);
            tempZero = zero;
            zero = one;
            one = two;
            two = three;
            three = four;
            four = five;
            five = six;
            six = seven;
            seven = eight;
            six += tempZero;
            eight = tempZero;
            //out.println("AFTER: " + zero + " " + one + " " + two + " " + three + " " + four + " " + five + " " + six + " " + seven + " " + eight);
            //out.println();
        }
        out.println(zero + one + two + three + four + five + six + seven + eight);
        long stTime = System.currentTimeMillis();
        out.print("TIME: " + (stTime - sTime));
        in.close();
        out.close();
	}
}
