import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static int jumpSearch(float[] integers, float elementToSearch) {
        int arrayLength = integers.length;
        int jumpStep = (int) Math.sqrt(integers.length);
        int previousStep = 0;
        while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
            previousStep = jumpStep;
            jumpStep += (int)(Math.sqrt(arrayLength));
            if (previousStep >= arrayLength)
                return -1;
        }
        while (integers[previousStep] < elementToSearch) {
            previousStep++;

            if (previousStep == Math.min(jumpStep, arrayLength))
                return -1 ;
        }
        if (integers[previousStep] == elementToSearch) {
            return previousStep;
        }
        return -1;
    }

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
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder Sbuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);
            Sbuffer.append(str.charAt(number));
        }
        return Sbuffer.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Enter the array size:\040""");
        int number = scan.nextInt();

        System.out.print("""
                Enter the size of words:\040""");
        int size = scan.nextInt();

        float[] first_array = new float[number];
        float[] second_array = new float[number];
        float[] third_array = new float[number];

        for (int i = 0; i < first_array.length; i++) first_array[i] = (float) Math.round(Math.random() * 200) - 100;
        for (int i = 0; i < second_array.length; i++) second_array[i] = (float) Math.round(Math.random() * 200) - 100;
        for (int i = 0; i < third_array.length; i++) third_array[i] = (float) Math.round(Math.random() * 200) - 100;

        ShellSort(number, first_array);
        ShellSort(number, second_array);
        ShellSort(number, third_array);

        System.out.println("""

                First Array:
                """);
        System.out.println(Arrays.toString(first_array));
        System.out.println("""

                Second Array:
                """);
        System.out.println(Arrays.toString(second_array));
        System.out.println("""

                Third Array:
                """);
        System.out.println(Arrays.toString(third_array));

        System.out.print("""
                
                Enter the searching element:\040""");
        String element = scan.next();
        float elem = Float.parseFloat(element.replace(',', '.'));

        System.out.print('\n' + "Searching in first array: ");
        System.out.println(jumpSearch(first_array, elem));
        System.out.print('\n' + "Searching in second array: ");
        System.out.println(jumpSearch(second_array, elem));
        System.out.print('\n' + "Searching in third array: ");
        System.out.println(jumpSearch(third_array, elem));

        System.out.print("""
                
                Enter the interval of numbers:\040""");
        float firs_number = scan.nextInt(); float second_number = scan.nextInt();
        System.out.println("""
                
                First Array
                """);
        for (float i = firs_number; i < second_number; i++) {
            if (jumpSearch(first_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + getRandomString(size) + "} {" + getRandomString(size) + "} Index of number " + jumpSearch(first_array, i));
            }
        }
        System.out.println("""
                
                Second Array
                """);
        for (float i = firs_number; i < second_number; i++) {
            if (jumpSearch(second_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + getRandomString(size) + "} {" + getRandomString(size) + "} Index of number " + jumpSearch(second_array, i));
            }
        }
        System.out.println("""
                
                Third Array
                """);
        for (float i = firs_number; i < second_number; i++) {
            if (jumpSearch(third_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + getRandomString(size) + "} {" + getRandomString(size) + "} Index of number " + jumpSearch(third_array, i));
            }
        }
    }
}
