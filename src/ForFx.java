import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ForFx {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Matrix = scan.nextLine();
        ArrayList<Integer> Tmp_Array = new ArrayList<>();
        String[] Tmp_String = Matrix.split(" ");

        for (String el : Tmp_String) {
            Tmp_Array.add(Integer.parseInt(el));
        }

        System.out.println(Tmp_Array);

        int row = scan.nextInt(); int column = scan.nextInt();
        int[][] double_Array = new int[row][column];
        int counter = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                double_Array[i][j] = Tmp_Array.get(counter);
                counter++;
            }
        }
        for (int[] line : double_Array) {
            System.out.println(Arrays.toString(line));
        }
    }
}
