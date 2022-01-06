import java.util.*;
import java.io.*;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static Map<String, String> conversion = new HashMap<String, String>(); //16 to decimal to binary
    
	public static void main(String[] args) throws FileNotFoundException {
	    long start = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		buildMap();
		Packet packet = parse(in.nextLine());
		out.println(packet.p1());
		out.println(packet.p2());
		out.println("TIME: " + (System.currentTimeMillis() - start));
        in.close();
        out.close();
	}
	
	public static Packet parse(String s) {
	    String[] tA = s.split("");
	    ArrayDeque<String> temp = new ArrayDeque<String>();
	    for (String t : tA) {
	        String[] tB = conversion.get(t).split("");
	        for (String tC : tB) {
	            temp.add(tC);
	        }
	    }
	    return parseBin(temp);
	}
	
	public static Packet parseBin(ArrayDeque<String> s) {
	    int ver = readBits(s, 3), type = readBits(s, 3);
	    if (type == 4) {
	        long val = literal(s);
	        return new Packet(ver, type, val);
	    } else {
	        int bit = Integer.parseInt(s.removeFirst());
	        List<Packet> subPackets = new ArrayList<Packet>();
	        switch (bit) {
	            case 1:
	                int pC = readBits(s, 11);
                for (int i = 0; i < pC; i++) {
                    subPackets.add(parseBin(s));
                }
	                break;
	            default:
	                int bC = readBits(s, 15), tarC = s.size() - bC;
	                while (s.size() != tarC) {
	                    subPackets.add(parseBin(s));
	                }
	                break;
	        }
	        return new Packet(ver, type, subPackets);
	    }
	}
	
	public static int readBits(ArrayDeque<String> s, int count) {
	    int val = 0;
	    for (int i = 0; i < count; i++) {
	        if (s.removeFirst().equals("1")) {
	            val = (val << 1) | 1;
	        } else {
	            val <<= 1;
	        }
	    }
	    return val;
	} 
	
	public static long literal(ArrayDeque<String> s) {
	    long lit = 0;
	    boolean zero = false;
	    while (!zero) {
	        int part = readBits(s, 5);
	        lit = (lit << 4) | (part & 15);
	        if ((part & 16) == 0) {
	            zero = true;
	        }
	    }
	    return lit;
	}
	
	public static void buildMap() {
        conversion.put("0", "0000"); conversion.put("1", "0001"); conversion.put("2", "0010"); conversion.put("3", "0011");
        conversion.put("4", "0100"); conversion.put("5", "0101"); conversion.put("6", "0110"); conversion.put("7", "0111");
        conversion.put("8", "1000"); conversion.put("9", "1001"); conversion.put("A", "1010"); conversion.put("B", "1011");
        conversion.put("C", "1100"); conversion.put("D", "1101"); conversion.put("E", "1110"); conversion.put("F", "1111");
	} //adds values to conversion map
}
