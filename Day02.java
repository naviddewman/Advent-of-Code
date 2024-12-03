import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

    private int safeReports;
    private final int MAX_DIFF = 3;

    public Day02() {
        safeReports = 0;
    }

    public static void main(String[] args) {
        Day02 day02 = new Day02();
        day02.run();
    }

    public void run() {
        findSafeReports();
        System.out.println(safeReports);
    }

    public void findSafeReports() {
        try (
            BufferedReader br = new BufferedReader(new FileReader("./dat/aoc02.txt"));
        ) {
            String report;
            while ((report = br.readLine()) != null) {
                boolean safe = false;
                String[] splitArr = report.split(" ");
                List<Integer> levels = new ArrayList<>();
                for (String s : splitArr) { levels.add(Integer.parseInt(s)); }
                
                safe = checkLevels(levels);
                if (safe) { safeReports++; }
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public boolean checkLevels(List<Integer> list) {
        if (checkSafety(list)) { return true; }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> listCpy = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (j != i) { listCpy.add(list.get(j)); }
            }
            if (checkSafety(listCpy)) { return true; }
        }
        return false;
    }

    public boolean checkSafety(List<Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            int diff = Math.abs(list.get(i) - list.get(i+1));
            if (list.get(i) == list.get(i+1) || diff > MAX_DIFF) { return false; }
        }
        return checkOrdering(list);
    }

    public boolean checkOrdering(List<Integer> list) { return checkAsc(list) || checkDec(list); }

    public boolean checkAsc(List<Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i+1)) { return false; }
        }
        return true;
    }

    public boolean checkDec(List<Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) < list.get(i+1)) { return false; }
        }
        return true;
    }
}