import java.io.BufferedReader;
import java.io.FileReader;

public class Day06 {
    
    final int SIZE = 130;
    final int[] UP = {-1,0};
    final int[] DOWN = {1,0};
    final int[] LEFT = {0,-1};
    final int[] RIGHT = {0,1};

    char[][] map;
    int distinctPositions;

    public Day06() {
        map = new char[SIZE][SIZE];
        distinctPositions = 0;
    }

    public static void main(String[] args) {
        Day06 day06 = new Day06();
        day06.run();
    }

    public void run() {
        populateMap();
        runSimulation();
        calculatePositions();
        System.out.println(distinctPositions);
    }

    public void populateMap() {
        try (
            BufferedReader br = new BufferedReader(new FileReader("./dat/aoc06.txt"));
        ) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) { map[row][col] = line.charAt(col); }
                row++;
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void runSimulation() {
        int[] direction = UP;
        int[] pos = findPos();
        markPosition(pos);
        
        while (positionValid(pos)) {
            int[] nextPos = {pos[0] + direction[0], pos[1] + direction[1]};
            if (!positionValid(nextPos)) { break; } // next position out of map bound, finish sim
            if (checkForObstruction(nextPos)) {
                // obstruction detected, change direction
                if      (direction == UP) { direction = RIGHT; }
                else if (direction == RIGHT) { direction = DOWN; }
                else if (direction == DOWN) { direction = LEFT; }
                else if (direction == LEFT) { direction = UP; }
            }
            else {  // move pos to nextPos, mark on map
                pos = nextPos;
                markPosition(pos);
            }
        }
    }

    public void markPosition(int[] pos) { map[pos[0]][pos[1]] = 'X'; }

    public boolean checkForObstruction(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return map[row][col] == '#';
    }

    public boolean positionValid(int[] pos) { return pos[0] < SIZE && pos[1] < SIZE; }

    public int[] findPos() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == '^') { return new int[]{i, j}; }
            }
        }
        return new int[]{-1,-1};
    }

    public void calculatePositions() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 'X') { distinctPositions++; }
            }
        }
    }
}
