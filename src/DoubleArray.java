import java.util.Scanner;
import java.util.Vector;

public class DoubleArray {
    public static void ShellSort(int number, Vector<Integer> array) {
        int i, j, step;
        int temp;
        for (step = number / 2; step > 0; step /= 2) {
            for (i = step; i < number; i++) {
                temp = array.get(i);
                for (j = i; j >= step; j -= step) {
                    if (temp < array.get(j - step)) array.set(j, array.get(j - step));
                    else break;
                }
                array.set(j, temp);
            }
        }
    }
    static int[][] getMatrix(int number_row, int number_column) {
        int[][] array = new int[number_row][number_column];
        for (int i = 0; i < number_row; i++) {
            for (int j = 0; j < number_column; j++) {
                array[i][j] = (int) (Math.random() * 200) - 100;
            }
        }
        return array;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns: ");
        int number_row, number_column; number_row = scan.nextInt(); number_column = scan.nextInt();
        int[][] array = getMatrix(number_row, number_column);
        Vector <Integer> new_array = new Vector<Integer>();
        for (int i = 0; i < number_row; i++) {
            for (int j = 0; j < number_column; j++) {
                new_array.add(array[i][j]);
            }
        }
        ShellSort(new_array.size(), new_array);
        for (int i = 0; i < new_array.size(); i++) {
            if (i % number_row == 0) {
                System.out.print('\n');
            }
            System.out.printf("%5d ", new_array.get(i));
        }
    }
}
