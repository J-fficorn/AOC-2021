import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static String[] tech;
    static List<List<Integer>> field = new ArrayList<>();
    
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
        tech = in.nextLine().split(""); //input image enhancing tech
        in.nextLine();
        int n = 100; //100
        for (int i = 0; i < n; i++) {
            List<Integer> init = new ArrayList<Integer>();
            in.useDelimiter("");
            for (int j = 0; j < n; j++) {
                init.add((in.next().equals("#")) ? 1 : 0);
            }
            in.nextLine();
            field.add(init);
        } //input starting image
        int inf = 0; //1 after step 1
        int s = 50;
        List<List<Integer>> copy = growBorder(s);
        for (int i = 0; i < s; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            for (int r = 1; r < copy.size() - 1; r++) {
                List<Integer> row = new ArrayList<Integer>();
                for (int c = 1; c < copy.get(r).size() - 1; c++) {
                    int b = 0;
                    for (int u = -1; u <= 1; u++) {
                        for (int v = -1; v <= 1; v++) {
                            b = b * 2 + copy.get(r + u).get(c + v);
                        }
                    }
                    //out.println("VAL: " + b);
                    row.add(tech[b].equals("#") ? 1 : 0);
                }
                temp.add(row);
            }
            copy = temp;
            //print(copy);
            /*for (LinkedList<Integer> row : field) {
                for (int c : row) {
                    out.print((c == 1 ? "#" : "'" ) + " ");
                }
                out.println();
            }
            out.println();*/ //DEBUG FIELD
        }
        int count = 0;
        for (int i = 0; i < copy.size(); i++) {
            for (int j = 0; j < copy.get(i).size(); j++) {
                count += copy.get(i).get(j);
            }
        }
        out.println(count);
        in.close();
        out.close();
	}
	
	public static void print(List<List<Integer>> copy) {
	    StringBuilder sb = new StringBuilder();
	    for (List<Integer> row : copy) {
	        sb.append(row).append("\n");
	    }
	    out.println(sb.toString());
	}
	
	public static List<List<Integer>> growBorder(int times) { //where n is size of field w/o new borders
        int n = field.get(0).size();
        List<List<Integer>> newField = new ArrayList<List<Integer>>();
        int border = times * 2;
        for (int i = 0; i < border; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < border + n + border; j++) {
                row.add(0);
            }
            newField.add(row);
        }
        for (int i = 0; i < field.size(); i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < border; j++) {
                row.add(0);
            }
            row.addAll(field.get(i));
            for (int j = 0; j < border; j++) {
                row.add(0);
            }
            newField.add(row);
        }
        for (int i = 0; i < border; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < border + n + border; j++) {
                row.add(0);
            }
            newField.add(row);
        }
        return newField;
	} //expand field
}
