import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day05 {

    Map<Integer, List<Integer>> map;
    List<List<Integer>> cmds;

    int total;

    public Day05() {
        map = new HashMap<>();
        cmds = new ArrayList<>();
        total = 0;
    }

    public static void main(String[] args) {
        Day05 day05 = new Day05();
        day05.run();
    }

    public void run() {
        getData();
        //checkCmds();  // part 1
        checkCmdsPt2(); // part 2

        System.out.println(total);
    }   

    public void getData() {
        try ( BufferedReader br = new BufferedReader(new FileReader("../dat/aoc05.txt")); ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(",")) {    // reading cmds
                    String[] arr = line.split(",");
                    List<Integer> temp = new ArrayList<>();
                    for (int i = 0; i < arr.length; i++) { temp.add(Integer.parseInt(arr[i])); }
                    cmds.add(temp);
                }
                else {  // reading ordering rules
                    String[] arr = line.split("\\|");
                    int key = Integer.parseInt(arr[0]);
                    if (map.containsKey(key)) {
                        List<Integer> val = map.get(key);
                        val.add(Integer.parseInt(arr[1]));
                        map.put(key, val);
                    }
                    else { 
                        List<Integer> newVal = new ArrayList<>();
                        newVal.add(Integer.parseInt(arr[1]));
                        map.put(key, newVal); 
                    }
                }
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void checkCmds() {
        for (List<Integer> list : cmds) {
            Boolean inOrder = true;
            if (!inOrder) { continue; }
            for (int i = 0; i < list.size(); i++) {
                int cmd = list.get(i);
                List<Integer> nextVals = map.get(cmd);
                if (nextVals != null) {
                    for (int val : nextVals) {
                        if (list.indexOf(val) != -1 && list.indexOf(val) < i) { inOrder = false; break; }
                    }
                }
            }
            if (inOrder) { total += list.get(list.size()/2); }
        }
    }

    public void checkCmdsPt2() {
        for (List<Integer> list : cmds) {
            Boolean inOrder = true;
            for (int i = 0; i < list.size(); i++) {
                int cmd = list.get(i);
                List<Integer> nextVals = map.get(cmd);
                if (nextVals != null) {
                    for (int val : nextVals) {
                        if (list.indexOf(val) != -1 && list.indexOf(val) < i) { inOrder = false; }
                    }
                }
            }
            if (!inOrder) {
                for (int i = 0; i < list.size(); i++) {
                    int cmd = list.get(i);
                    List<Integer> dependencies = map.get(cmd);
                    if (dependencies != null) {
                        for (int dep : dependencies) {
                            int depIndex = list.indexOf(dep);
                            if (depIndex != -1 && depIndex < i) {
                                Collections.swap(list, i, depIndex);
                                i = -1; 
                                break;
                            }
                        }
                    }
                }
                total += list.get(list.size()/2);
            }
        }
    }
}
