package ForBashka;

import java.util.Arrays;
import java.util.Scanner;

public class BashkaTask1 {
    /*Метод сортировки Шелла*/
    public static void ShellSort(int number, float[] array) {
        int i, j, step;
        float temp;
        for (step = number / 2; step > 0; step /= 2) {
            for (i = step; i < number; i++) {
                temp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (temp <array[j - step]) array[j] = array[j - step];
                    else break;
                }
                array[j] = temp;
            }
        }
    }

    /*Сортировка двумерного массива*/
    public static void DoubleSort(float[][] matrix) {
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[r].length; c++) {
                int lastC = c + 1;
                for (int lastR = r; lastR < matrix.length; ++lastR) {
                    while (lastC < matrix[lastR].length) {
                        if (matrix[lastR][lastC] < matrix[r][c]) {
                            float tmp = matrix[r][c];
                            matrix[r][c] = matrix[lastR][lastC];
                            matrix[lastR][lastC] = tmp;
                        }
                        lastC++;
                    }
                    lastC = 0;
                }
            }
        }
    }

    /*Возвращение матрицы со случайными float элементами*/
    static float[][] getMatrix(int number) {
        float[][] array = new float[number][3];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = (float) (Math.random() * 200) - 100;
            }
        }
        return array;
    }

    /*Создание лент*/
    static StringBuilder createSlices(float[][] array, int slice_number, int number_sqrt) {
        StringBuilder tape = new StringBuilder();
        for (int i = slice_number; i < array.length; i += number_sqrt)
            tape.append(Arrays.toString(array[i])).append(' ');
        return tape;
    }

    public static void main(String[] args) { Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int number = Math.abs(scan.nextInt());
        int number_sqrt = (int) Math.ceil(Math.sqrt(number));
        float[][] array = getMatrix(number);
        DoubleSort(array);
        System.out.println("""
                
                The Generated Matrix:
                """);

        for (float[] lines : array) System.out.println(Arrays.toString(lines));
        System.out.println("""
                
                Sorted Matrix Lines:
                """);

        int counter = -1;
        for (float[] floats : array) {
            counter++;
            ShellSort(floats.length, floats);
            System.out.println("Line " + counter + ": " + Arrays.toString(floats));
        }
        counter = 0;
        System.out.println("""
                
                Sorted tapes:
                """);

        for (int i = counter; i < number_sqrt; i++) {
            System.out.println("Tape " + counter + ": " + createSlices(array, i, number_sqrt));
            counter++;
        }
        System.out.println("""
                
                Merged Tapes:
                """);
        for (float[] floats : array) {
            ShellSort(floats.length, floats);
            System.out.println(Arrays.toString(floats));
        }
    }
}
