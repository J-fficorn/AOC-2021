import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException {
	    long start = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //input.txt
        PrintWriter out = new PrintWriter(System.out);
        Deque<Character> que = new ArrayDeque<Character>();
        List<String> lines = new ArrayList<String>();
        while (in.hasNext()) {
            lines.add(in.nextLine());
        }
        int score = 0, middle = 0;
        List<Long> scores = new ArrayList<Long>();
        for (String s : lines) {
            boolean invalid = false;
            loop: for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '{' || c == '[' || c == '<') {
                    que.push(c);
                } else {
                    char h = que.pop();
                    boolean paren = (h == '(') ? true : false;
                    boolean bracket = (h == '[') ? true : false;
                    boolean curly = (h == '{') ? true : false;
                    boolean angle = (h == '<') ? true : false;
                    switch (c) {
                        case ')':
                            if (bracket || curly || angle) {
                                score += 3;
                                invalid = true;
                                que.clear();
                                break loop;
                            }
                            break;
                        case ']':
                            if (paren || curly || angle) {
                                score += 57;
                                invalid = true;
                                que.clear();
                                break loop;
                            }
                            break;
                        case '}':
                            if (paren || bracket || angle) {
                                score += 1197;
                                invalid = true;
                                que.clear();
                                break loop;
                            }
                            break;
                        case '>':
                            if (paren || bracket || curly) {
                                score += 25137;
                                invalid = true;
                                que.clear();
                                break loop;
                            }
                            break;
                    }
                }
            } //part 1, gets rid of invalid strings & pushes/pops queue
            while (!que.isEmpty()) {
                long cur = 0;
                loopB: while (!que.isEmpty()) {
                    char c = que.pop();
                    //out.print(c + " ");
                    cur *= 5;
                    switch (c) {
                        case ('('):
                            cur += 1;
                            break;
                        case ('['):
                            cur += 2;
                            break;
                        case ('{'):
                            cur += 3;
                            break;
                        case ('<'):
                            cur += 4;
                            break;
                    }
                    //out.print(cur + " "); //DEBUG
                }
                scores.add(cur);
            }
            //out.println(); //DEBUG
        }
        /*for (long s : scores) {
            out.print(s + " ");
        }*/ //DEBUG
        //out.println(); //DEBUG
        out.println(score);
        Collections.sort(scores);
        Collections.reverse(scores);
        out.println(scores.get(scores.size() / 2));
        long stop = System.currentTimeMillis();
        out.println("TIME: " + (stop - start));
        in.close();
        out.close();
	}
}
