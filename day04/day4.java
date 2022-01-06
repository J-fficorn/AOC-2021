import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
        PrintWriter out = new PrintWriter(System.out);
        int b = 100; //100 for input.txt
        int s = 5; //still 5
        String ballStr = in.nextLine();
        String[] ballStrArr = ballStr.split(",");
        int[] balls = new int[ballStrArr.length];
        for (int i = 0; i < ballStrArr.length; i++) {
            balls[i] = Integer.parseInt(ballStrArr[i]);
        } //reading input (balls)
        //out.println(balls.length);
        List<int[][]> boards = new ArrayList<int[][]>();
        List<boolean[][]> drawn = new ArrayList<boolean[][]>();
        for (int i = 0; i < b; i++) {
            int[][] temp = new int[s][s];
            boolean[][] tempDrawn = new boolean[s][s];
            for (int j = 0; j < s; j++) {
                for (int k = 0; k < s; k++) {
                    temp[j][k] = in.nextInt();
                    tempDrawn[j][k] = false;
                }
            }
            boards.add(temp);
            drawn.add(tempDrawn);
        } //reading input (boards)
        /*for (int ball : balls) {
            out.print(ball + " ");
        }
        out.println();
        for (int[][] board : boards) {
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    out.print(board[i][j] + " ");
                }
                out.println();
            }
            out.println();
        }
        * DEBUGGING
        */
        int[][] ans = new int[b][3]; //winning index, winning #, uncalled sum
        for (int i = 0; i < b; i++) {
            boolean won = false;
            int winningBall = 0;
            int winningNum = 0;
            outer: for (int ball = 0; ball < balls.length; ball++) {
                for (int j = 0; j < s; j++) {
                    for (int k = 0; k < s; k++) {
                        if (boards.get(i)[j][k] == balls[ball]) {
                            drawn.get(i)[j][k] = true;
                            //out.println(i + ": " + balls[ball] + " drawn, matches " + j + "." + k + ": " + boards.get(i)[j][k]);
                            boolean hWin = true, vWin = true;
                            for (int l = 0; l < s; l++) {
                                if (!drawn.get(i)[j][l]) {
                                    hWin = false;
                                } //horizontal check
                                if (!drawn.get(i)[l][k]) {
                                    vWin = false;
                                } //vertical check
                            }
                            //out.println(i + ": " + hWin + " " + vWin);
                            if (hWin || vWin) {
                                won = true;
                                winningBall = ball;
                                winningNum = boards.get(i)[j][k];
                                //out.println(i + ": " + winningBall + "[" + balls[ball] + "] " + winningNum);
                                break outer;
                            } //win, set num & ball
                                //check for win
                        }
                    }
                }
            }
            if (won) {
                ans[i][0] = winningBall;
                ans[i][1] = winningNum;
            } else {
                ans[i][0] = balls.length + 1;
                ans[i][1] = 0;
            }
        } //simulation method
        for (int i = 0; i < b; i++) {
            int boardSum = 0;
            for (int j = 0; j < s; j++) {
                for (int k = 0; k < s; k++) {
                    if (!drawn.get(i)[j][k]) {
                        boardSum += boards.get(i)[j][k];
                    }
                }
            }
            ans[i][2] = boardSum;
        } //boardSum method
        int firstWin = 0;
        int firstWinVal = Integer.MIN_VALUE;
        for (int i = 0; i < b; i++) {
            //out.println(i + ": " + ans[i][0]);
            if (ans[i][0] > firstWinVal) {
                firstWinVal = ans[i][0];
                firstWin = i;
            }
        }
        out.println(firstWin);
        out.println(ans[firstWin][0] + " " + ans[firstWin][1] + " " + ans[firstWin][2]);
        out.println(ans[firstWin][1] * ans[firstWin][2]);
        in.close();
        out.close();
	}
}
