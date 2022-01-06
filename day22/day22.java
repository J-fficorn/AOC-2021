import java.util.*;
import java.io.*;

public class Main {
    
	public static void main(String[] args) throws FileNotFoundException {
	    long start = System.currentTimeMillis();
	    PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner(new BufferedReader(new FileReader("p.txt"))); //i.txt
		Map<int[], Integer> volume = new HashMap<int[], Integer>();
		while (in.hasNext()) {
            String[] temp = in.nextLine().split("[.,]+"); //split by whitespace to separate op?
            int[] dir = new int[6]; Map<int[], Integer> newV = new HashMap<int[], Integer>();
            boolean state = temp[0].substring(0, 2).equals("on");
            int sign = (state) ? 1 : -1;
            temp[0] = temp[0].substring((state) ? 5 : 6);
            temp[2] = temp[2].substring(2);
            temp[4] = temp[4].substring(2);
            for (int i = 0; i < temp.length; i++) {
                dir[i] = Integer.parseInt(temp[i]);
            } //create new directions
            for (int[] i : volume.keySet()) {
                int imaxx = max(dir[0], i[0]), iminx = min(dir[1], i[1]),
                    imaxy = max(dir[2], i[2]), iminy = min(dir[3], i[3]),
                    imaxz = max(dir[4], i[4]), iminz = min(dir[5], i[5]);
                int[] newI = {imaxx, iminx, imaxy, iminy, imaxz, iminz};
                if (imaxx <= iminx && imaxy <= iminy && imaxz <= iminz) {
                    newV.merge(newI, volume.get(i), Integer::sum);
                }
            }
            for (Map.Entry<int[], Integer> ent : newV.entrySet()) {
                volume.merge(ent.getKey(), -1 * ent.getValue(), Integer::sum);
            }
            if (sign == 1) {
                volume.merge(dir, sign, Integer::sum);
            }
		}
		long finish = System.currentTimeMillis();
		out.println(findSum(volume) + "\nTIME: " + (finish - start)); //ANS + TIME KEEPER
        in.close();
        out.close();
	}
	
	public static int max(int dir, int i) {
	    return (dir > i) ? dir : i;
	}
	
	public static int min(int dir, int i) {
	    return (dir < i) ? dir : i;
	}
	
	public static long findSum(Map<int[], Integer> volume) {
		long sum = 0;
		for (int[] i : volume.keySet()) {
            int xV = (i[1] - i[0] + 1), yV = (i[3] - i[2] + 1), zV = (i[5] - i[4] + 1), sign = volume.get(i);
            sum += (long) xV * yV * zV * sign;
		} //sum
		return sum;
	}
}
