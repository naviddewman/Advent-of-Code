import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day03 {

    int total;

    public Day03() {
        total = 0;
    }

    public static void main(String[] args) {
        Day03 day03 = new Day03();
        day03.run();
    }

    public void run() {
        String data = getData();
        String regex = "(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        boolean execute = true;

        while(matcher.find()) {
            if (matcher.group().equals("do()")) { execute = true; }
            else if (matcher.group().equals("don't()")) { execute = false; }
            else if (execute) {
                int arg1 = 0, arg2 = 0;
                String subRegex = "\\d{1,3}";
                Pattern subPattern = Pattern.compile(subRegex);
                Matcher subMatcher = subPattern.matcher(matcher.group());
                
                if (subMatcher.find()) { arg1 = Integer.parseInt(subMatcher.group()); }
                if (subMatcher.find()) { arg2 = Integer.parseInt(subMatcher.group()); }

                executeMult(arg1, arg2);
            }
        }
        System.out.println(total);
    }

    public void executeMult(int x, int y) { total += (x * y); }

    public String getData() {
        String content = null;
        try { content = Files.readString(Path.of("./dat/aoc03.txt")); }
        catch (Exception e) { System.out.println("oopsie"); }
        return content;
    }
}
