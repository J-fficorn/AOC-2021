import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		int p1 = in.nextInt(), p2 = in.nextInt();
		long[] output = {0, 0};
		int s1 = 0, s2 = 0;
		boolean first = false;
		long[] a = score(p1, p2, s1, s2, 3, !first, 1);
		long[] b = score(p1, p2, s1, s2, 4, !first, 3);
		long[] c = score(p1, p2, s1, s2, 5, !first, 6);
		long[] d = score(p1, p2, s1, s2, 6, !first, 7);
		long[] e = score(p1, p2, s1, s2, 7, !first, 6);
		long[] f = score(p1, p2, s1, s2, 8, !first, 3);
		long[] g = score(p1, p2, s1, s2, 9, !first, 1);
		output[0] = a[0] + b[0] + c[0] + d[0] + e[0] + f[0] + g[0];
		output[1] = a[1] + b[1] + c[1] + d[1] + e[1] + f[1] + g[1];
		long p1S = output[0], p2S = output[1];
		out.println(p1S + " " + p2S);
		out.println((p1S > p2S) ? p1S : p2S);
        in.close();
        out.close();
	}

    public static long[] score(int p1, int p2, int s1, int s2, int r, boolean first, long mult) {
        long[] output = {0, 0};
        if (first) {
            p1 += r; p1 %= 10;
            p1 = (p1 == 0) ? 10 : p1;
            s1 += p1;
        } else {
            p2 += r; p2 %= 10;
            p2 = (p2 == 0) ? 10 : p2;
            s2 += p2;
        }
        
        if (s1 >= 21) {
            output[0] = mult;
            return output;
        }
        if (s2 >= 21) {
            output[1] = mult;
            return output;
        }
        
		long[] a = score(p1, p2, s1, s2, 3, !first, mult);
		long[] b = score(p1, p2, s1, s2, 4, !first, mult * 3);
		long[] c = score(p1, p2, s1, s2, 5, !first, mult * 6);
		long[] d = score(p1, p2, s1, s2, 6, !first, mult * 7);
		long[] e = score(p1, p2, s1, s2, 7, !first, mult * 6);
		long[] f = score(p1, p2, s1, s2, 8, !first, mult * 3);
		long[] g = score(p1, p2, s1, s2, 9, !first, mult);
		
		output[0] = a[0] + b[0] + c[0] + d[0] + e[0] + f[0] + g[0];
		output[1] = a[1] + b[1] + c[1] + d[1] + e[1] + f[1] + g[1];
		return output;
    }
}
