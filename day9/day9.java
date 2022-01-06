import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Main {
    static int h = 100, w = 100;

	public static void main(String[] args) throws FileNotFoundException {
		long sTime = System.currentTimeMillis();
		Scanner in = new Scanner(new BufferedReader(new FileReader("i.txt"))); //input.txt
        PrintWriter out = new PrintWriter(System.out);
        int[][] heights = new int[h][w];
        boolean[][] basined = new boolean[h][w];
        List<Integer> basins = new ArrayList<Integer>();
        int max3 = 1;
        in.useDelimiter("");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                heights[i][j] = in.nextInt();
                basined[i][j] = false;
            }
            if (i != h - 1) {
                in.nextLine();
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int cur = heights[i][j];
                int sumT = 0;
                if (i == 0) {
                    if (j == 0) {
                        if (heights[i + 1][j] > cur && heights[i][j + 1] > cur) {
                            basined[i][j] = true;
                            sumT += checkBCorner(heights, basined, i, j);
                        } //top-left
                    } else if (j == w - 1) {
                        if (heights[i + 1][j] > cur && heights[i][j - 1] > cur) {
                            basined[i][j] = true;
                            sumT += checkBCorner(heights, basined, i, j);
                        } //top-right
                    } else {
                        if ((heights[i][j + 1] > cur && heights[i][j - 1] > cur) && heights[i + 1][j] > cur) {
                            basined[i][j] = true;
                            sumT += checkBRowUD(heights, basined, i, j);
                        } //upper-row
                    }
        	    } else if (i == h - 1) {
        	        if (j == 0) {
                        if (heights[i - 1][j] > cur && heights[i][j + 1] > cur) {
                            basined[i][j] = true;
                            sumT += checkBCorner(heights, basined, i, j);
                        }
                    } else if (j == w - 1) {
                        if (heights[i - 1][j] > cur && heights[i][j - 1] > cur) {
                            basined[i][j] = true;
                            sumT += checkBCorner(heights, basined, i, j);
                        }
                    } else {
                        if ((heights[i][j - 1] > cur && heights[i][j + 1] > cur) && heights[i - 1][j] > cur) {
                            basined[i][j] = true;
                            sumT += checkBRowUD(heights, basined, i, j);
                        } //lower-row
                    }
        	    } else if (j == 0) {
        	        if ((heights[i - 1][j] > cur && heights[i + 1][j] > cur) && heights[i][j + 1] > cur) {
                        basined[i][j] = true;
                        sumT += checkBRowLR(heights, basined, i, j);
                    } //lower-row
        	    } else if (j == w - 1) {
        	        if ((heights[i - 1][j] > cur && heights[i + 1][j] > cur) && heights[i][j - 1] > cur) {
                        basined[i][j] = true;
                        sumT += checkBRowLR(heights, basined, i, j);
                    } //lower-row
        	    } else {
        	        if ((heights[i][j - 1] > cur && heights[i][j + 1] > cur) && (heights[i - 1][j] > cur && heights[i + 1][j] > cur)) {
                        basined[i][j] = true;
                        sumT += checkBMid(heights, basined, i, j);
                    } //mid
        	    }
                if (sumT > 0) {
                    out.println(heights[i][j] + ": " + i + " " + j);
                    out.println(sumT);
                    basins.add(sumT);
                }
            }
        }
        Collections.sort(basins);
        Collections.reverse(basins);
        int m = 0;
        for (int base : basins) {
            if (m < 3) {
                out.println(base);
                max3 *= base;
            }
            m++;
        }
        out.println(max3);
        long stTime = System.currentTimeMillis();
        out.println("TIME: " + (stTime - sTime));
        in.close();
        out.close();
	}
	
	public static int checkBCorner(int[][] heights, boolean[][] basined, int iS, int jS) {
	    int sum = 1;
	    int i = iS;
	    int j = jS;
	    int cur = heights[iS][jS];
	    if (i == 0) {
            if (j == 0) {
                if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                    basined[i + 1][j] = true;
                    sum += checkBRowLR(heights, basined, i + 1, j);
                }
                if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
                    basined[i][j + 1] = true;
                    sum += checkBRowUD(heights, basined, i, j + 1);
                }
            } else if (j == w - 1) {
                if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                    basined[i + 1][j] = true;
                    sum += checkBRowLR(heights, basined, i + 1, j);
                }
                if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                    basined[i][j - 1] = true;
                    sum += checkBRowUD(heights, basined, i, j - 1);
                }
            }
	    } else if (i == h - 1) {
	        if (j == 0) {
                if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                    basined[i - 1][j] = true;
                    sum += checkBRowLR(heights, basined, i - 1, j);
                }
                if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
                    basined[i][j + 1] = true;
                    sum += checkBRowUD(heights, basined, i, j + 1);
                }
            } else if (j == w - 1) {
                if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                    basined[i - 1][j] = true;
                    sum += checkBRowLR(heights, basined, i - 1, j);
                }
                if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                    basined[i][j - 1] = true;
                    sum += checkBRowUD(heights, basined, i, j - 1);
                }
            }
	    }
	    return sum;
	}
	
	public static int checkBRowUD(int[][] heights, boolean[][] basined, int iS, int jS) {
	    int i = iS, j = jS, sum = 1, cur = heights[iS][jS];
	    if (i == 0) {
            if (j != w - 1) {
                if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
	                basined[i][j + 1] = true;
                    sum += checkBRowUD(heights, basined, i, j + 1);
                }
            }
            if (j != 0) {
                if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                    basined[i][j - 1] = true;
                    sum += checkBRowUD(heights, basined, i, j - 1);
                }
            }
            if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                basined[i + 1][j] = true;
                sum += checkBMid(heights, basined, i + 1, j);
            }
        } else if (i == h - 1) {
            if (j != w - 1) {
                if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
	                basined[i][j + 1] = true;
                    sum += checkBRowUD(heights, basined, i, j + 1);
                }
            }
            if (j != 0) {
                if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                    basined[i][j - 1] = true;
                    sum += checkBRowUD(heights, basined, i, j - 1);
                }
            }
            if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                basined[i - 1][j] = true;
                sum += checkBMid(heights, basined, i - 1, j);
            }
        }
        return sum;
	}
	
    public static int checkBRowLR(int[][] heights, boolean[][] basined, int iS, int jS) {
	    int i = iS, j = jS, sum = 1, cur = heights[iS][jS];
	    if (j == 0) {
            if (i != h - 1) {
                if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                    basined[i + 1][j] = true;
                    sum += checkBRowLR(heights, basined, i, j + 1);
                }
            }
            if (i != 0) {
                if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                    basined[i - 1][j] = true;
                    sum += checkBRowLR(heights, basined, i - 1, j);
                }
            }
            if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
                basined[i][j + 1] = true;
                    sum += checkBMid(heights, basined, i, j + 1);
            }
        } else if (j == w - 1) {
            if (i != h - 1) {
                if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                    basined[i + 1][j] = true;
                    sum += checkBRowLR(heights, basined, i + 1, j);
                }
            }
            if (i != 0) {
                if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                    basined[i - 1][j] = true;
                    sum += checkBRowLR(heights, basined, i - 1, j);
                }
            }
            if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                basined[i][j - 1] = true;
                sum += checkBMid(heights, basined, i, j - 1);
            }
        }
        return sum;
	}
	
	public static int checkBMid(int[][] heights, boolean[][] basined, int iS, int jS) {
	    int i = iS, j = jS, sum = 1, cur = heights[iS][jS];
        if (i != 0) {
            if ((heights[i - 1][j] > cur && !basined[i - 1][j]) && heights[i - 1][j] != 9) {
                basined[i - 1][j] = true;
                sum += checkBMid(heights, basined, i - 1, j);
            }
        }
        if (i != h - 1) {
	        if ((heights[i + 1][j] > cur && !basined[i + 1][j]) && heights[i + 1][j] != 9) {
                basined[i + 1][j] = true;
                sum += checkBMid(heights, basined, i + 1, j);
            }
	    }
        if (j != w - 1) {
            if ((heights[i][j + 1] > cur && !basined[i][j + 1]) && heights[i][j + 1] != 9) {
                basined[i][j + 1] = true;
                sum += checkBMid(heights, basined, i, j + 1);
            }
        }
        if (j != 0) {
            if ((heights[i][j - 1] > cur && !basined[i][j - 1]) && heights[i][j - 1] != 9) {
                basined[i][j - 1] = true;
                sum += checkBMid(heights, basined, i, j - 1);
            }
        }
        return sum;
	}
}
