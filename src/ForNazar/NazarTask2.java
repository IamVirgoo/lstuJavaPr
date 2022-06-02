package ForNazar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NazarTask2 {
    static final int LETTERS_COUNT = 'z' - 'a' + 1;    /*Переменная для функционирования метода генерации случайного массива char*/
    static Scanner scan = new Scanner(System.in);    /*Инициализация сканера для работы ввода в программе*/

    public static void main(String[] args) {
        System.out.print("Enter the random arguments (start/end): ");
        int start = scan.nextInt(); int end = scan.nextInt();    /*Инициализация и Ввод границ для генерации случайных чисел*/
        System.out.print("Enter the arrays size: ");
        int length = scan.nextInt();    /*Инициализация и Ввод числа - количества элементов в массивах*/
        ArrayList<Data> global = new ArrayList<>();    /*Инициализация двухсвязного списка для удобной работы с массивом структур данных*/
        boolean flag = false;    /*Инициализация флага для не бесконечной работы интерфейса*/
        System.out.println("""
                    1 - Generate
                    2 - Search
                    3 - Output all list
                    """);
        while (!flag) {    /*Инициализация основного цикла для интерфейса*/
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();    /*Инициализация и Ввод номера операции*/
            switch (OperationNumber) {
                /*Генерация одной записи структуры в списке*/
                case 1 -> {
                    float newFloat = (float) (Math.random() * (end - start)) + start;
                    int[] newIntArray = getIntArray(length, start, end);
                    char[] newCharset = getRandomCharset(length);
                    float newSecondFloat = (float) (Math.random() * (end - start)) + start;
                    global.add(new Data(newFloat, newIntArray, newCharset, newSecondFloat));
                    System.out.println('\t' + "Generated!");
                }
                /*Поиск по параметрам: Массив Int - Массив Char*/
                case 2 -> {
                    System.out.print("""
                                1 - int[]
                                2 - char[]
                                Choose the type:
                            """);
                    int choose = scan.nextInt();    /*Инициализация и ввод номера выбранного поиска*/
                    /*Поиск по Массиву Int*/
                    if (choose == 1) {
                        System.out.print('\t' + "Enter the element of search: "); int number = scan.nextInt();    /*Инициализация и Ввод элемента для поиска по Массиву Int*/
                        /**
                         * Циклом проходим по всему списку, просматривая каждую запись
                         * В каждой записи вызываем метод бинарного поиска вводимого числа
                         * Проверяем случаи где число нашлось и выводим соответсвующее сообщение пользователю*/
                        for (int i = 0; i < global.size(); i++) {
                            if (binarySearch(ShellSort(length, global.get(i).IntArray), number) != -1) {
                                System.out.println('\t' + "    " + "Found global in " + (i + 1) + " position on " + binarySearch(ShellSort(length, global.get(i).IntArray), number) + " index!");
                            }
                            else {
                                System.out.println('\t' + "    " + "Not Found in " + (i + 1) + " position!");
                            }
                        }
                        System.out.print('\t' + "Enter the start and end position of interval: ");
                        int startInterval = scan.nextInt(); int endInterval = scan.nextInt();    /*Вводим границы интервала по которому будет проходить дополнительный поиск*/
                        /**
                         * Всё также как и сверху, только сравнивается не с вводимым элементом,
                         * а с числом из интервала*/
                        for (int i = startInterval; i <= endInterval; i++) {
                            for (Data elem : global) {
                                if (binarySearch(ShellSort(length, elem.IntArray), i) != -1) {
                                    System.out.println('\t' + "    " + "The " + i + " number Found global in " + global.indexOf(elem) + " position on " + binarySearch(ShellSort(length, elem.IntArray), i) + " index!");
                                }
                            }
                        }
                    }
                    /*Поиск по массиву Char*/
                    if (choose == 2) {
                        System.out.print('\t' + "Enter the element of search: "); char word = scan.next().charAt(0);    /*Вводим символ, который будем искать*/
                        /**
                         * В цикле проходим по списку
                         * В каждой записи выделяем массив Char и вызываем метод бинарного поиска для вводимого символа
                         * После проверка выводим пользователю соответсвующее сообщение*/
                        for (int i = 0; i < global.size(); i++) {
                            if (binarySearchChar(global.get(i).Charset, word) != -1) {
                                System.out.println('\t' + "    " + "Found global in " + (i + 1) + " position on " + binarySearchChar(global.get(i).Charset, word) + " index!");
                            }
                            else {
                                System.out.println('\t' + "    " + "Not Found in " + (i + 1) + " position!");
                            }
                        }
                    }
                }
                /*Вывод всего списка (Каждой записи в нём)*/
                case 3 -> {
                    for (Data data : global) {
                        System.out.println(data.FirstFloatDigit + " " + Arrays.toString(ShellSort(length, data.IntArray)) + " " + Arrays.toString(data.Charset) + " " + data.SecondFloatDigit);
                    }
                }
                /*Выход из программы*/
                case 0 -> {
                    flag = true;
                }
            }
        }
    }

    /**Метод бинарного поиска
     * На вход подаются: Массив и элемент для поиска
     * На выходе получаем: -1 в случае, если элемент не нашёлся, Index элемента в массиве, в случае, если поиск произошёл успешно*/
    public static int binarySearch(int[] arr, int elementToSearch) {

        int firstIndex = 0;    /*Нулевой индекс, он нужен для вывода разницы и слежением за размерностью, чтобы не получить ошибку сегментации при вызове*/
        int lastIndex = arr.length - 1;    /*Конечный индекс, работает с нулевым для формирования разности*/

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    /*Метод бинарного поиска для массива элементов Char*/
    public static int binarySearchChar(char[] arr, char elementToSearch) {
        /*Формирование массива из преобразованных элементов Char в Int по таблице ASCII*/
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        /**
         * ВАЖНО!
         * Сортировка массива, без этого работать не будет, так метод бинарного поиска работает только с сортированным массивом
         * Именно из-за этого в самом выводе в этом случае мы замечаем разницу между табличным выводом списка и индексом найденного элемента
         * По сути это индекс элемента отсортированного массива
         * А не массива, указанного в списке*/
        ShellSort(array.length, array);

        int firstIndex = 0;    /*Нулевой индекс, он нужен для вывода разницы и слежением за размерностью, чтобы не получить ошибку сегментации при вызове*/
        int lastIndex = array.length - 1;    /*Конечный индекс, работает с нулевым для формирования разности*/

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (array[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (array[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (array[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    /*Метод сортировки Шеллом
    * Рассписывать его нету смысла, так вряд-ли можно понять
    * Лучше прочитать статью про этот тип сортировки*/
    public static int[] ShellSort(int number, int[] array) {
        int i, j, step;
        int temp;
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
        return array;
    }

    /**
     * Генерация случайного массива Int
     * На вход подаётся размер массива, начальное число и конечное, его мы указывали в самом начале, поэтому статически передаём его сюда*/
    public static int[] getIntArray(int length, int start, int end) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * (end - start)) + start;
        }
        return array;
    }

    /**
     * Генерация случайного массива Char
     * На вход подаётся размер массива, здесь также используется переменная LETTERS_COUNT, которая является основой генерации*/
    public static char[] getRandomCharset(int length) {
        char[] RandomChars = new char[length];
        Random rand = new Random();
        for (int i = 0; i < RandomChars.length; i++) {
            RandomChars[i] = (char) (rand.nextInt(LETTERS_COUNT) + (rand.nextBoolean() ? 'a' : 'A'));
        }
        return RandomChars;
    }
}
/*Создание и инициализация класса структуры данных*/
class Data {
    public float FirstFloatDigit;    /*Инициализация Первого числа Float*/
    public int[] IntArray;    /*Инициализация массива Int*/
    public char[] Charset;    /*Инициализация массива Char*/
    public float SecondFloatDigit;    /*Инициализация Второго числа Float*/

    /*Конструктор класса, эта штука просто необходима для работы программы и работы самой структуры*/
    public Data(float FirstFloatDigit, int[] IntArray, char[] Charset, float SecondFloatDigit) {
        this.FirstFloatDigit = FirstFloatDigit;
        this.IntArray = IntArray;
        this.Charset = Charset;
        this.SecondFloatDigit = SecondFloatDigit;
    }
}