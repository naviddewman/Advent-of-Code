import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Day08 {

    final int SIZE = 12;

    char[][] map;

    public Day08() {
        map = new char[SIZE][SIZE];
    }

    public static void main(String[] args) {
        Day08 day = new Day08();
        day.run();
    }
    
    public void run() {
        getData();
        findAntinodes();
    }   

    public void getData() {
        try ( BufferedReader br = new BufferedReader(new FileReader("./dat/test.txt")); ) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) { map[row][col] = line.charAt(col); }
                row++;
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void findAntinodes() {
        // loop through all elements in map
            // if element != '.'
                // find all occurences of element in map, get coords: function
                // calculate antinode positions based off indexes: function
    }

    public void findAll(char x) {
        // find all occurences of element x in map
    }

    public void getAntinodes(List<int[]> points) {
        // get all antinodes of the given list of identical antennas
    }

    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}