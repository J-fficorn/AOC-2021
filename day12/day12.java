import java.util.*;
import java.io.*;

public class Main {
    static int v = 11, e = 21; //p2 = 7  i = 11 //p2 = 10 i = 21
    static ArrayList<Integer>[] adj;
    static boolean[] large = new boolean[v];
    static List<String> nodeNames = new ArrayList<String>();
    static PrintWriter out = new PrintWriter(System.out);
    static int startNode = 0, endNode = 0;
    
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //p2, i
        int part1 = 0, part2 = 0;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
        //graph creation
        for (int i = 0; i < e; i++) {
            String[] inputs = new String[2];
            String next = in.nextLine();
            inputs = next.split("-");
            //out.println(inputs[0] + " " + inputs[1]); //DEBUG
            boolean containsA = false, containsB = false;
            int aIndex = 0, bIndex = 0;
            for (int j = 0; j < nodeNames.size(); j++) {
                String node = nodeNames.get(j);
                if (node.equals(inputs[0])) {
                    containsA = true;
                    aIndex = j;
                }
                if (node.equals(inputs[1])) {
                    containsB = true;
                    bIndex = j;
                }
            }
            boolean aStartEnd = (inputs[0].equals("start") || inputs[0].equals("end")) ? true : false;
            boolean bStartEnd = (inputs[1].equals("start") || inputs[1].equals("end")) ? true : false;
            if (!containsA) {
                nodeNames.add(inputs[0]);
                aIndex = nodeNames.size() - 1;
                if (!inputs[0].equals(inputs[0].toLowerCase())) {
                    large[aIndex] = true;
                }
            }
            if (!containsB) {
                nodeNames.add(inputs[1]);
                bIndex = nodeNames.size() - 1;
                if (!inputs[1].equals(inputs[1].toLowerCase())) {
                    large[bIndex] = true;
                } else if (bStartEnd) {
                    large[bIndex] = false;
                }
            }
            adj[aIndex].add(bIndex);
            adj[bIndex].add(aIndex);
        } //for
        //graph creation
        for (int i = 0; i < v; i++) {
            if (nodeNames.get(i).equals("start")) {
                startNode = i;
            } else if (nodeNames.get(i).equals("end")) {
                endNode = i;
            }
        } //find StartNode and endNode
        
        out.println("START NODE: "  + startNode);
        for (int i = 0; i < v; i++) {
            out.println(i + ": " + nodeNames.get(i));
            for (int next : adj[i]) {
                out.println(i + "-" + next + " | " + nodeNames.get(i) + "-" + nodeNames.get(next));
            }
            out.println();
        }
         //DEBUG MAP
        
        for (boolean b : large) {
            if (b) {
                out.print("1 ");
            } else {
                out.print("0 ");
            }
        }
         //DEBUG LARGE
        out.println();
	    boolean[] visited = new boolean[v];
        part1 += dfsP1(startNode, visited);
        out.println("\nPART 1: " + part1);
	    List<Integer> lVisited = new ArrayList<Integer>();
        part2 += dfsP2(startNode, lVisited);
        out.println("\nPART 2: " + part2);
        in.close();
        out.close();
	}
	
	public static int dfsP1(int n, boolean[] visited) {
        int paths = 0;
        boolean[] newV = Arrays.copyOf(visited, visited.length);
        newV[n] = true;
            if (nodeNames.get(n).equals("end")) {
                paths++;
            } else {
                for (int next : adj[n]) {
                    if (!newV[next] || large[next]) {
                        paths += dfsP1(next, newV);
                    }
                } 
            }
        return paths;
    }
    
    public static int dfsP2(int n, List<Integer> visited) {
        int paths = 0;
        List<Integer> newV = new ArrayList<Integer>(visited);
        newV.add(n);
        if (nodeNames.get(n).equals("end")) {
            paths++;
        } else {
            for (int next : adj[n]) {
                if (!visited.contains(n) || large[n]) {
                    paths += dfsP2(next, newV);
                } else if (visited.contains(n) && valid(n, visited)) {
                    paths += dfsP2(next, newV);
                }
            } 
        }
        return paths;
    }
    
    public static boolean valid(int n, List<Integer> visited) {
        List<Integer> smalls = new ArrayList<Integer>();
        Set<Integer> distinctSmalls = new HashSet<Integer>();
        if (n == startNode || n == endNode) {
            return false;
        }
        if (large[n]) {
            return false;
        }
        for (int i : visited) {
            if (!large[i]) {
                smalls.add(i);
            }
        }
        for (int i : smalls) {
            distinctSmalls.add(i);
        }
        int small = smalls.size();
        int distinct = distinctSmalls.size();
        return (small == distinct);
    }
}
