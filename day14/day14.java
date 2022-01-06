import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static int rulesN = 100, s = 40;
    static Map<String, String> rules = new HashMap<String, String>();
    static PrintWriter out = new PrintWriter(System.out);
    
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.currentTimeMillis();
        Map<String, Long> ticks = new HashMap<String, Long>();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
        String template = in.nextLine();
		//var letters = template.chars().boxed().map(s->Character.toString(s)).collect(Collectors.toMap(x->x, x->1l, Long::sum));
        in.nextLine();
        for (int i = 0; i < template.length() - 1; i++) {
            String temp = template.substring(i, i + 2);
            //long cur = (ticks.containsKey(temp)) ? ticks.get(temp) + 1: 1L;
            //ticks.put(temp, cur);
			ticks.merge(temp, 1L, Long::sum);
        } //template
        for (int i = 0; i < rulesN; i++) {
            String[] temp = in.nextLine().split(" -> ");
            rules.put(temp[0], temp[1]);
        } //rules
        for (int i = 0; i < s; i++) {
            Map<String, Long> temp = new HashMap<String, Long>();
            for (String p : ticks.keySet()) {
				String next = rules.get(p);
				String a = p.charAt(0) + next;
				String b = next + p.charAt(1);
				//long aS = (temp.containsKey(a)) ? ticks.get(p) + temp.get(a) : 1L;
				//long bS = (temp.containsKey(b)) ? ticks.get(p) + temp.get(b) : 1L;
				temp.merge(a, ticks.get(p), Long::sum);
				temp.merge(b, ticks.get(p), Long::sum);
				//temp.put(a, aS);
				//temp.put(b, bS);
				//letters.merge(next + )
			}
			ticks = temp;
        } //iteration
		Map<Character, Long> monomers = new HashMap<Character, Long>();
        for (String st : ticks.keySet()) {
			//out.println(st + ": " + ticks.get(st));
			char stC = st.charAt(0);
            long cS = (monomers.containsKey(stC)) ? monomers.get(stC) + ticks.get(st) : ticks.get(st);
			monomers.put(st.charAt(0), cS);
        }
		//out.println("-----");
		char tL = template.charAt(template.length() - 1);
		monomers.put(tL, monomers.get(tL) + 1);
		long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
		for (Map.Entry<Character, Long> c : monomers.entrySet()) {
			//out.println(c.getKey() + ": " + c.getValue());
			max = (max < c.getValue()) ? c.getValue() : max;
			min = (min > c.getValue()) ? c.getValue() : min;
		}
		out.println(max - min);
		long stop = System.currentTimeMillis();
		out.println("TIME: " + (stop - start));
        in.close();
        out.close();
	}
}
