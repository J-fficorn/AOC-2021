import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
	    long sTime = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //i.txt
        PrintWriter out = new PrintWriter(System.out);
        List<String> watch = new ArrayList<String>();
        List<String> op = new ArrayList<String>();
        List<Integer> numbers = new ArrayList<Integer>();
        String[] alpha = {"a", "b", "c", "d", "e", "f", "g"};
        int w = 10, o = 4, n = 200;
        while (in.hasNext()) {
            for (int i = 0; i < w; i++) {
                watch.add(in.next());
            }
            in.next();
            for (int i = 0; i < o; i++) {
                op.add(in.next());
            }
        } //input works
        int low = 0, high = 10, lowO = 0, highO = 4;
        for (int i = 0; i < n; i++) {
            int[] current = new int[10];
            int[] fives = new int[3], sixes = new int[3];
            int curF = 0, curS = 0;
            String one = "", four = "", seven = "", eight = "";
            out.println("I: " + i);
            for (int j = low; j < high; j++) {
                int l = watch.get(j).length();
                if (l == 5) {
                    fives[curF] = j;
                    curF++;
                } else if (l == 6) {
                    sixes[curS] = j;
                    curS++;
                } else if (l == 2) {
                    current[j % 10] = 1;
                    one = watch.get(j);
                } else if (l == 3) {
                    current[j % 10] = 7;
                    seven = watch.get(j);
                } else if (l == 4) {
                    current[j % 10] = 4;
                    four = watch.get(j);
                } else if (l == 7) {
                    current[j % 10] = 8;
                    eight = watch.get(j);
                }
            }
            String[] oneC = one.split(""), fourC = four.split("");
            String[] sevenC = seven.split(""), eightC = eight.split("");
            String up = "";
            for (String c : sevenC) {
                if (!Arrays.asList(oneC).contains(c)) {
                    up = c;
                }
            } //find up
            String[] midDown = new String[2];
            String mDA = "", mDB = "";    
            String[] fiveA = watch.get(fives[0]).split(""); String[] fiveB = watch.get(fives[1]).split(""); String[] fiveC = watch.get(fives[2]).split("");
            String[] sixA = watch.get(sixes[0]).split(""); String[] sixB = watch.get(sixes[1]).split(""); String[] sixC = watch.get(sixes[2]).split("");
            for (String s : fiveA) {
                out.print(s + " ");
            }
            out.println();
            for (String s : fiveB) {
                out.print(s + " ");
            }
            out.println();
            for (String s : fiveC) {
                out.print(s + " ");
            }
            out.println();
            for (String c : alpha) {
                if ((Arrays.asList(fiveA).contains(c) && (Arrays.asList(fiveB).contains(c) && Arrays.asList(fiveC).contains(c)) ) && !c.equals(up)) {
                    if (mDA == "") {
                        mDA = c;
                    } else {
                        mDB = c;
                    }
                }
            } //mid/down
            String mid = "", down = "";
            if (Arrays.asList(fourC).contains(mDA) && !mDA.equals(up)) {
                mid = mDA;
                down = mDB;
            } else if (Arrays.asList(fourC).contains(mDB) && !mDB.equals(up)) {
                mid = mDB;
                down = mDA;
            } //mid & down
            String leftUp = "";
            for (String s : fourC) {
                if (!(Arrays.asList(oneC).contains(s)) && !s.equals(mid)) {
                    leftUp = s;
                }
            } //left_up
            if (Arrays.asList(fiveA).contains(oneC[0]) && Arrays.asList(fiveA).contains(oneC[1])) {
                current[fives[0] % 10] = 3;
            } else if (Arrays.asList(fiveA).contains(leftUp)) {
                current[fives[0] % 10] = 5;
            } else {
                current[fives[0] % 10] = 2;
            }
            if (Arrays.asList(fiveB).contains(oneC[0]) && Arrays.asList(fiveB).contains(oneC[1])) {
                current[fives[1] % 10] = 3;
            } else if (Arrays.asList(fiveB).contains(leftUp)) {
                current[fives[1] % 10] = 5;
            } else {
                current[fives[1] % 10] = 2;
            }
            if (Arrays.asList(fiveC).contains(oneC[0]) && Arrays.asList(fiveC).contains(oneC[1])) {
                current[fives[2] % 10] = 3;
            } else if (Arrays.asList(fiveC).contains(leftUp)) {
                current[fives[2] % 10] = 5;
            } else {
                current[fives[2] % 10] = 2;
            }
            String rightUp = "";
            for (int j = 0; j < 3; j++) {
                if (current[fives[j] % 10] == 5) {
                    String[] tempC = new String[5];
                    if (j == 0) {
                        tempC = fiveA;
                    } else if (j == 1) {
                        tempC = fiveB;
                    } else {
                        tempC = fiveC;
                    }
                    for (String c : sevenC) {
                        if (!Arrays.asList(tempC).contains(c)) {
                            rightUp = c;
                        }
                    }
                }
            } //right_up
            if (!Arrays.asList(sixA).contains(mid)) {
                current[sixes[0] % 10] = 0;
            } else if (!Arrays.asList(sixA).contains(rightUp)) {
                current[sixes[0] % 10] = 6;
            } else {
                current[sixes[0] % 10] = 9;
            }
            if (!Arrays.asList(sixB).contains(mid)) {
                current[sixes[1] % 10] = 0;
            } else if (!Arrays.asList(sixB).contains(rightUp)) {
                current[sixes[1] % 10] = 6;
            } else {
                current[sixes[1] % 10] = 9;
            }
            if (!Arrays.asList(sixC).contains(mid)) {
                current[sixes[2] % 10] = 0;
            } else if (!Arrays.asList(sixC).contains(rightUp)) {
                current[sixes[2] % 10] = 6;
            } else {
                current[sixes[2] % 10] = 9;
            }
            String leftDown = "", rightDown = "";
            for (int j = 0; j < 3; j++) {
                if (current[sixes[j] % 10] == 9) {
                    String[] tempC = new String[5];
                    if (j == 0) {
                        tempC = sixA;
                    } else if (j == 1) {
                        tempC = sixB;
                    } else {
                        tempC = sixC;
                    }
                    for (String s : tempC) {
                        out.print(s + " ");
                    }
                    out.println();
                    for (String s : eightC) {
                        out.print(s + " ");
                    }
                    out.println();
                    for (String c : eightC) {
                        boolean u = (c.equals(up)) ? true : false;
                        boolean m = (c.equals(mid)) ? true : false;
                        boolean d = (c.equals(down)) ? true : false;
                        boolean lu = (c.equals(leftUp)) ? true : false;
                        boolean ru = (c.equals(rightUp)) ? true : false;
                        boolean umd = ((u || m) || d) ? true : false;
                        boolean luru = (lu || ru) ? true : false;
                        boolean cont = (Arrays.asList(tempC).contains(c)) ? true : false;
                        out.println(c + " " + u + " " + m + " " + d + " " + lu + " " + ru + " " + umd + " " + luru + " " + cont);
                        if (!cont) {
                            leftDown = c;
                        } else if (Arrays.asList(tempC).contains(c) && (!umd && !luru)) {
                            rightDown = c;
                        }
                    }
                }
            } //left_down right_down
            out.println("U: " + up + " M: " + mid + " D:" + down + " LU: " + leftUp + " RU: " + rightUp + " LD: " + leftDown + " RD: " + rightDown);
            int[] numberArr = new int[4];
            for (int j = lowO; j < highO; j++) {
                String[] tempStr = op.get(j).split("");
                int curNum = 0;
                boolean u = (Arrays.asList(tempStr).contains(up)) ? true : false;
                boolean d = (Arrays.asList(tempStr).contains(down)) ? true : false;
                boolean m = (Arrays.asList(tempStr).contains(mid)) ? true : false;
                boolean lu = (Arrays.asList(tempStr).contains(leftUp)) ? true : false;
                boolean ru = (Arrays.asList(tempStr).contains(rightUp)) ? true : false;
                boolean ld = (Arrays.asList(tempStr).contains(leftDown)) ? true : false;
                boolean rd = (Arrays.asList(tempStr).contains(rightDown)) ? true : false;
                boolean ud = (u && d) ? true : false;
                boolean umd = (ud && m) ? true : false;
                boolean rurd = (ru && rd) ? true : false;
                if ((umd) && (lu && (ld && rurd))) {
                    curNum = 8;
                } else if ((umd) && (lu && (!ld && rurd))) {
                    curNum = 9;
                } else if ((ud && !m) && (lu && (ld && rurd))) {
                    curNum = 0;
                } else if (((!u && !d) && !m) && (!lu && (!ld && rurd))) {
                    curNum = 1;
                } else if (((umd) && (!lu && !rd)) && (ld && ru)) {
                    curNum = 2;
                } else if (((umd) && (!lu && !ld)) && (ru && rd)) {
                    curNum = 3;
                } else if ( ( ( (!u && !d) && m) && (lu && rd)) && (!ld && ru)) {
                    curNum = 4;
                } else if (((umd) && (lu && rd)) && (!ld && !ru)) {
                    curNum = 5;
                } else if (((umd) && (lu && rd)) && (ld && !ru)) {
                    curNum = 6;
                } else if (((u && !m) && (!lu && !ld)) && (rd && ru)) {
                    curNum = 7;
                }
                numberArr[j % 4] = curNum;
            }
            int number = (1000 * numberArr[0]) + (100 * numberArr[1]) + (10 * numberArr[2])+ numberArr[3];
            out.println(number);
            numbers.add(number);
            low += 10;
            high += 10;
            lowO += 4;
            highO += 4;
        }
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        out.println(sum);
        long stTime = System.currentTimeMillis();
        out.println(stTime - sTime);
        in.close();
        out.close();
	}
}
