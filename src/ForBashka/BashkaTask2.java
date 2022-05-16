package ForBashka;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BashkaTask2 {
    /*Метод для поиска - Двоичный поиск*/
    public static int iterativeSearch(float[] arrayToSearch, float element) {
        int lowIndex = 0;
        int highIndex = arrayToSearch.length-1;

        int elementPos = -1;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            if (element == arrayToSearch[midIndex]) {
                elementPos = midIndex;
                break;
            } else if (element < arrayToSearch[midIndex]) {
                highIndex = midIndex-1;
            } else if (element > arrayToSearch[midIndex]) {
                lowIndex = midIndex+1;
            }
        }
        return elementPos;
    }
    /*Метод для сортировки Шелла*/
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
    /*Возвращение случайного String с заданной величиной*/
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

    /*Основной метод для запуска программы*/
    public static void main(String[] args) {
        /*Инициализация ввода для методов*/
        Scanner scan = new Scanner(System.in);
        /*Количество массивов, заданным пользователем*/
        System.out.print("""
                Enter the array size:\040""");
        int number = scan.nextInt();
        /*Величина для случайного String*/
        System.out.print("""
                Enter the size of words:\040""");
        int size = scan.nextInt();

        /*Инициалиация массивов*/
        float[] first_array = new float[number];
        float[] second_array = new float[number];
        float[] third_array = new float[number];

        /*Заполнение массивов случайными float числами*/
        for (int i = 0; i < first_array.length; i++) first_array[i] = (float) Math.round(Math.random() * 200) - 100;
        for (int i = 0; i < second_array.length; i++) second_array[i] = (float) Math.round(Math.random() * 200) - 100;
        for (int i = 0; i < third_array.length; i++) third_array[i] = (float) Math.round(Math.random() * 200) - 100;

        /*Сортировка массивов*/
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
        /*Парсинг элемента String в Float*/
        float elem = Float.parseFloat(element.replace(',', '.'));

        /*Поиск по заданному пользователем элементу*/
        System.out.print('\n' + "Searching in first array: ");
        System.out.println(iterativeSearch(first_array, elem));
        System.out.print('\n' + "Searching in second array: ");
        System.out.println(iterativeSearch(second_array, elem));
        System.out.print('\n' + "Searching in third array: ");
        System.out.println(iterativeSearch(third_array, elem));

        /**
         * Поиск по заданным интервалам.
         * Имеется костыль, тк необходимо реалзовать float-й интервал на многоаспектном поиске,
         * однако с float элементами это не возможно, приммер:
         * 1.023334, 1.023335, ....
         * Поиск будет работать максимально долго и неэфективно,
         * ссылаюсь на само задание.
         * Решение проблемы: Задание float - подобным образом: 1.0, 2.0, 2.0, ...
         * В этом случае элементы являются float-м и соответствуют заданию лабоаторной
         */
        System.out.print("""
                
                Enter the interval of numbers:\040""");
        float firs_number = scan.nextInt(); float second_number = scan.nextInt();
        System.out.println("""
                
                First Array
                """);
        /*Интервальный поиск отдельно по массивам*/
        for (float i = firs_number; i < second_number; i++) {
            if (iterativeSearch(first_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + ((float) Math.round(Math.random() * 200) - 100) + "} {" + getRandomString(size) + "} Index of number " + iterativeSearch(first_array, i));
            }
        }
        System.out.println("""
                
                Second Array
                """);
        for (float i = firs_number; i < second_number; i++) {
            if (iterativeSearch(second_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + ((float) Math.round(Math.random() * 200) - 100) + "} {" + getRandomString(size) + "} Index of number " + iterativeSearch(second_array, i));
            }
        }
        System.out.println("""
                
                Third Array
                """);
        for (float i = firs_number; i < second_number; i++) {
            if (iterativeSearch(third_array, i) != -1)  {
                System.out.println("Number: " + i + " {" + ((float) Math.round(Math.random() * 200) - 100) + "} {" + getRandomString(size) + "} Index of number " + iterativeSearch(third_array, i));
            }
        }
    }
}
