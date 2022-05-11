import java.util.Arrays;

public class Gen {

    static int[][] getMatrix(int number_row, int number_column) {
        int[][] array = new int[number_row][number_column];
        for (int i = 0; i < number_row; i++) {
            for (int j = 0; j < number_column; j++) {
                array[i][j] = (int) (Math.random() * 2);
            }
        }
        return array;
    }
    public static void main(String[] args) {
        int[][] array = getMatrix(100, 100);
        for (int[] line : array) {
            System.out.println(Arrays.toString(line));
        }
    }
}
