import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
	    long sTime = System.currentTimeMillis();
        Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
        //Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int len = 12; int rows = 1000;
        String[][] binary = new String[rows][len];
        String[] binMax = new String[len];
        String[] binMin = new String[len];
        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                binMax[i] = "0";
                binMin[i] = "0";
            } else {
                binMax[i] = "1";
                binMin[i] = "1";
            }
        }
        for (int i = 0; i < rows; i++) {
            String input = in.nextLine();
            binary[i] = input.split("");
        }
        List<Integer> CO2 = new ArrayList<Integer>();
        List<Integer> O2 = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            List<Integer> zerosJ = new ArrayList<Integer>();
            List<Integer> onesJ = new ArrayList<Integer>();
            int z = 0, o = 0;
            if (i == 0) {
                for (int j = 0; j < rows; j++) {
                    if (binary[j][i].equals("0")) {
                        zerosJ.add(j);
                        z ++;
                    } else {
                        onesJ.add(j);
                        o ++;
                    }
                }
                if (o >= z) {
                    O2 = onesJ;
                    CO2 = zerosJ;
                    binMax[i] = "1";
                    binMin[i] = "0";
                } else {
                    O2 = zerosJ;
                    CO2 = onesJ;
                    binMax[i] = "0";
                    binMin[i] = "1";
                }
            } else {
                for (int j : O2) {
                    if (binary[j][i].equals("0")) {
                        zerosJ.add(j);
                        z++;
                        out.println(j + " = " + "ZERO");
                    } else {
                        onesJ.add(j);
                        o++;
                        out.println(j + " = " + "ONE");
                    }
                }
                if ((o == 0 || o < z) && z != 0) {
                    O2 = zerosJ;;
                    binMax[i] = "0";
                    out.println("O2 ZERO");
                } else {
                    O2 = onesJ;
                    binMax[i] = "1";
                    out.println("O2 ONE");
                }
                List<Integer> zerosJCO2 = new ArrayList<Integer>();
                List<Integer> onesJCO2 = new ArrayList<Integer>();
                z = 0;
                o = 0;
                for (int j : CO2) {
                    if (binary[j][i].equals("0")) {
                        zerosJCO2.add(j);
                        z++;
                        out.println(j + " = ZERO");
                    } else {
                        onesJCO2.add(j);
                        o++;
                        out.println(j + " = ONE");
                    }
                }
                if ((z == 0 || z > o) && o != 0) {
                    CO2 = onesJCO2;
                    binMin[i] = "1";
                    out.println("CO2 ONE");
                } else {
                    CO2 = zerosJCO2;
                    binMin[i] = "0";
                    out.println("CO2 ZERO");
                }
            }
        }
        for (String s : binMax) {
            out.print(s);
        }
        out.println();
        for (String s : binMin) {
            out.print(s);
        }
        out.println();
        int max = 0;
        int min = 0;
        for (int i = 0; i < len; i++) {
            max = (int) (Math.pow(2, len - 1 - i) * Integer.parseInt(binMax[i])) + max;
            min = (int) (Math.pow(2, len - 1 - i) * Integer.parseInt(binMin[i])) + min;
        }
        out.println(max * min);
        long stTime = System.currentTimeMillis();
        out.println("TIME: " + (stTime - sTime));
        in.close();
        out.close();
	}
}
