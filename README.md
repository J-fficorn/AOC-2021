# Solutions to AOC 2021 (JAVA) <br />

## Some notes: <br />
Indenting may be weird because I'm new to using Github, sorry! <br />
Also, dayx/partx -.java files may need renaming of class names as they do not match the file names. <br />

## Favourite days: <br />
8:  I enjoyed this day because of the way I found each [segment's](https://adventofcode.com/2021/day/8) symbol and then reconstructed the outputs into meaningful digits. The code is messy and near-unreadable, but it's satisfying to run and watch the program figure out the appropriate symbols. <br />
<br />
11: Day 11 was satisfying as well because of the [ripple](https://adventofcode.com/2021/day/11) mechanic, which I was able to implement through an algorithm akin to DFS. By printing out the state of the board at each step, I was able to see the effect in real-time! How satisfying is that? <br />
<br />
13: This day was super fun because of the way it [folds](https://adventofcode.com/2021/day/13) and how satisfying it is (I'm seeing a trend here). By simulating each step, and cleverly using a "2y - x" trick to find the new location of a point after a fold, I was able to solve day 13 quite quickly.<br />
<br />
15: This [day](https://adventofcode.com/2021/day/15). Was. *Not.* Fun. However, it *was* super interesting and taught me [Dijkstra's algorithm](https://www.google.com/search?q=dijkstra%27s+algorithm) and forced me to adapt it to a 2D-grid rather than a graph of nodes.<br />
<br />
16: Day 16 was the first use of object-oriented-programming, where I created what was almost a tree of [packets](https://adventofcode.com/2021/day/16) and manipulated them using commands. It was fun converting the hex to binary, then to numbers and commands.<br />
<br />
18: I was stuck on day 18 for a whole day, where I tried to implement an over-complicated tree. In the end, I resorted to manipulating a [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html) after converting the [string](https://adventofcode.com/2021/day/18) into an integer list. It's incredibly satisfying to watch the numbers pop as the operations run.<br />
<br />
22: My notebook is filled with sketches of [3D cuboids](https://adventofcode.com/2021/day/22) now (thanks AOC). In the end, I remembered my lessons from discrete math about set intersection and union, so I set intersected cuboids as new cubes with volumes of -1. This way, having two overlapping cuboids with intersection would be calculated in this way: (3 * 3 * 3) + (3 * 3 * 3) - (2 * 2 * 2) = 27 + 27 - 8 = 46. <br />
<br />
<br />
## Conclusion <br/>
Enjoy my code, and good luck in next year's AOC or any programming adventures!
