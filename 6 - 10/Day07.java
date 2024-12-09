import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    
    long total;
    List<String> data;
    
    public Day07() {
        total = 0;
        data = new ArrayList<>();
    }

    public static void main(String[] args) {
        Day07 day07 = new Day07();
        day07.run();
    }

    public void run() {
        getData();
        computeData();
        System.out.println(total);
    }

     public void getData() {
        try (
            BufferedReader br = new BufferedReader(new FileReader("./dat/aoc07.txt"));
        ) {
            String line;
            while ((line = br.readLine()) != null) { data.add(line); }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void computeData() {
        String targetRegex = "^\\d*";
        String numsRegex = "(?<=:\\s)(\\d+\\s)+\\d+";
        
        long target = 0;
        long[] nums = null;
        
        for (String str : data) {
            Pattern targetPattern = Pattern.compile(targetRegex);
            Matcher targetMatcher = targetPattern.matcher(str);
            Pattern numsPattern = Pattern.compile(numsRegex);
            Matcher numsMatcher = numsPattern.matcher(str);
            
            if (targetMatcher.find()) { target = Long.parseLong(targetMatcher.group()); }
            else { System.out.println("No target match found"); }
            if (numsMatcher.find()) { 
                String[] splitNums = numsMatcher.group().split(" ");
                nums = new long[splitNums.length];
                for (int i = 0; i < splitNums.length; i++) { nums[i] = Long.parseLong(splitNums[i]); }
            }
            else { System.out.println("No nums matches found"); }

            if (computeVals(target, nums, 1, nums[0])) { total += target; }
        }
    }

    public boolean computeVals(long target, long[] nums, int index, long currentResult) {
        if (index == nums.length) {
            return currentResult == target;
        }
        
        boolean add = computeVals(target, nums, index + 1, currentResult + nums[index]);
        boolean multiply = computeVals(target, nums, index + 1, currentResult * nums[index]);
        long concatenated = Long.parseLong(String.valueOf(currentResult) + nums[index]);
        boolean concat = computeVals(target, nums, index + 1, concatenated);

        return add || multiply || concat;
    }
}