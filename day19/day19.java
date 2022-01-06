import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    
	public static void main(String[] args) throws FileNotFoundException {
	    long start = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
		int numScanners = 33; //33 for i.txt, 26
		Deque<Set<Coord>> beacons = new LinkedList<Set<Coord>>();
		int lines = 0, left = 916; //916
		for (int i = 0; i < numScanners; i++) {
		    in.nextLine();
		    lines++;
		    Set<Coord> temp = new HashSet<Coord>();
		    String s = in.nextLine();
		    lines++;
		    while (!s.equals("") && lines <= left) {
		        //out.println(s);
                String[] str = s.split(",");
                int[] toCoords = new int[3];
                for (int k = 0; k < 3; k++) { toCoords[k] = Integer.parseInt(str[k]); }
                Coord tempCoord = new Coord(toCoords[0], toCoords[1], toCoords[2]);
                temp.add(tempCoord);
                if (lines < left) { s = in.nextLine(); }
                lines++;
		    }
		    beacons.add(temp);
		}
		Set<Coord> scanners = new HashSet<Coord>();
		scanners.add(new Coord(0, 0, 0));
		Set<Coord> beacon = beacons.poll(); //0 0 0 scanner beacons
		w: while (!beacons.isEmpty()) {
		    Set<Coord> refBeacon = beacons.poll();
		    for (int r = 0; r < 6; r++) { //orientations
		        for (int u = 0; u < 4; u++) { //directions up
		            for (Coord c : refBeacon) { //beacon to be compared to 0 0 0 scanner
		                for (Coord b : beacon) { //base beacon to be compared
		                    Coord delta = new Coord(b.x - c.x, b.y - c.y, b.z - c.z);
		                    Set<Coord> shifted = refBeacon.stream().map(x -> x.change(delta)).collect(Collectors.toSet());
		                    int count = 0;
		                    for (Coord a : shifted) {
		                        if (isScanned(beacon, a)) { count++; }
		                        if (count >= 12) {
                                    for (Coord d : shifted) {
                                        if (!isScanned(beacon, d)) { beacon.add(d); }
                                    }
                                    out.println(delta);
                                    if (!isScanned(scanners, delta)) { scanners.add(delta); }
                                    continue w;
		                        }
		                    }
		                }
		            }
                    refBeacon = refBeacon.stream().map(x -> x.rotate("x")).collect(Collectors.toSet());
		        }
    		    if (r < 5) {
    		        if (r < 3)  { refBeacon = refBeacon.stream().map(x -> x.rotate("y")).collect(Collectors.toSet()); }
    		        if (r > 2)  { refBeacon = refBeacon.stream().map(x -> x.rotate("z")).collect(Collectors.toSet()); }
    		    }
		    }
		    beacons.add(refBeacon);
		}
		int maxMan = 0;
        for (Coord c : scanners) {
            out.println(c);
        }
        List<Coord> scannersList = scanners.stream().collect(Collectors.toList());
        for (int i = 0; i < scannersList.size(); i++) {
            for (int j = i + 1; j < scannersList.size(); j++) {
                int man = scannersList.get(i).manDist(scannersList.get(j));
                maxMan = (man > maxMan) ? man : maxMan;
            }
        }
        out.println(beacon.size());
        out.println(maxMan);
        out.println("TIME: " + (System.currentTimeMillis() - start));
        in.close();
        out.close();
	}
	
	public static boolean isScanned(Set<Coord> beacon, Coord c) {
        for (Coord coord : beacon) {
            if (coord.equals(c)) { return true; }
        }
        return false;
	}
}
