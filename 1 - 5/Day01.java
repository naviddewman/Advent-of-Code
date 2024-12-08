import java.util.*;
import java.io.*;

public class Day01 {

    private LinkedList<Integer> list1, list2;
    private Map<Integer, Integer> map;
    private int totalDist, simScore;

    public Day01() {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
        map = new HashMap<>();
        totalDist = 0;
        simScore = 0;
    }

    public static void main(String[] args) {
        Day01 day01 = new Day01();
        day01.run();
    }

    public void run() {
        populate();

        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

        compare();

        System.out.println("Total Distance: " + totalDist + "\n Similarity: " + simScore);
    }

    public void populate() {
        try (
            BufferedReader br = new BufferedReader(new FileReader("../dat/aoc01.txt"));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLn = line.split("   ");
                int leftVal = Integer.parseInt(splitLn[0]);
                int rightVal = Integer.parseInt(splitLn[1]);

                list1.add(leftVal);
                list2.add(rightVal);

                if (map.containsKey(rightVal)) { map.put(rightVal, map.get(rightVal) + 1); }
                else { map.put(rightVal, 1); }
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void compare() {
        while (!list1.isEmpty() && !list2.isEmpty()) { 
            int leftVal = list1.pop();
            int rightVal = list2.pop();
            // calc distance
            totalDist += Math.abs(leftVal - rightVal); 
            // calc sim score
            int mult = 0;
            if (map.containsKey(leftVal)) { mult = map.get(leftVal); }
            simScore += leftVal * mult;
        }
    }
}