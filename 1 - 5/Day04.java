import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day04 {
    final int SIZE = 140;
    char[][] matrix;
    int itemsFound;
    
    public Day04() {
        matrix = new char[SIZE][SIZE];
        itemsFound = 0;
    }

    public static void main(String[] args) {
        Day04 day04 = new Day04();
        day04.run();
    }

    public void run() {
        populateMatrix();
        //search('X'); // part 1
        search('A');

        System.out.println(itemsFound);
    }

    public void populateMatrix() {
        try (
            BufferedReader br = new BufferedReader(new FileReader("../dat/aoc04.txt"));
        ) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) { matrix[row][col] = line.charAt(col); }
                row++;
            }
        }
        catch (Exception e) { System.out.println("something went oopsie..." + e); }
    }

    public void search(char letter) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (matrix[row][col] == letter) {
                    if (letter == 'X') {    // part 1
                        List<int[]> points = searchAdj(row, col);
                        if (points.size() > 0) { findXmas(points, row, col); }
                    }
                    else { findMas(row, col); } // part 2
                }
            }
        }
    }

    public void findMas(int row, int col) {
        int masFound = 0;
        int[] rowOffset = {-1, -1};
        int[] colOffset = {-1, 1};

        for (int i = 0; i < 2; i++) {
            String word = "";
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];

            if (isValid(newRow, newCol)) {
                if (i == 0 && isValid(row+1, col+1)) { word = String.valueOf(matrix[newRow][newCol]) + matrix[row][col] + matrix[row+1][col+1]; }
                else if (i == 1 && isValid(row+1, col-1)) { word = String.valueOf(matrix[newRow][newCol]) + matrix[row][col] + matrix[row+1][col-1]; }
                if (word.equals("MAS") || word.equals("SAM")) { masFound++; }
            }
        }
        if (masFound == 2) { itemsFound++; }
    }

    public void findXmas(List<int[]> points, int row, int col) {
        for (int[] point : points) {
            String word = "XM";
            int xVec = point[0] - row;
            int yVec = point[1] - col;

            for (int i = 1; i < 3; i++) {
                int newRow = point[0] + xVec * i;
                int newCol = point[1] + yVec * i;
                if (isValid(newRow, newCol)) { 
                    word += matrix[newRow][newCol]; 
                    if (word.equals("XMAS")) { itemsFound++; }
                }
            }
        }
    }

    public List<int[]> searchAdj(int x, int y) {
        List<int[]> points = new ArrayList<>();
        int[] rowOffset = {-1,-1,-1,0,0,1,1,1};
        int[] colOffset = {-1,0,1,-1,1,-1,0,1};

        for (int i = 0; i < 8; i++) {
            int row = x + rowOffset[i];
            int col = y + colOffset[i];

            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
                if (matrix[row][col] == 'M') { points.add(new int[]{row,col}); }
            }
        }
        return points;
    }

    public boolean isValid(int x, int y) { return x >= 0 && x < SIZE && y >= 0 && y < SIZE; }
}
