package ForVlad;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class VladTask3 {
    static final int LETTERS_COUNT = 'z' - 'a' + 1;    /*Переменная для функционирования метода генерации слуфайного массива char*/
    static Scanner scan = new Scanner(System.in);      /*Обьявление сканнера для возможности ввода чего-либо в программу*/


    public static void main(String[] args) {    /*Основной метод, который и запускается при старте программы*/
        System.out.print("Enter start and end numbers for random operators: ");
        int start = scan.nextInt(); int end = scan.nextInt();    /*Обьявление переменных для обозначения границ случайных чисел, соответсвенно start и end*/
        System.out.print("Enter the arrays size: ");
        int length = scan.nextInt();    /*Обьявление переменной для задания размерности массивов (По уполчанию в варианте задано число 20, но это методички Домашнева,
                                        так что я на всякий случай сделал возможность другого ввода)*/
        Node<Data> blocks = new Node<>();    /*Инициализация самого узла, состоящего из блоков данных*/

        boolean flag = false;    /*Флаг - задаётся для небессконечного и удобного функционирования интерфейса программы*/
        System.out.println("""
                
                1 - Add new record on start position
                2 - Add new record on end position
                3 - Add new record on index position
                4 - Remove the record on index position
                5 - Changing the record on index position
                6 - Clear all blocks
                7 - Find the record from block types
                8 - Output all list
                9 - Output last record
                """);
        while (!flag) {    /*Основной цикл для взаимодействия с методами*/
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();    /*Обьявление и ввод пользователем номера действия, которое необходимо сделать*/

            switch (OperationNumber) {
                /*Добавление новой записи в начало узла*/
                case 1 -> {
                    char[] FirstCharArray = getRandomCharset(length);    /*Обьявление первого массива char и его заполнение случайными значениями*/
                    char[] SecondCharArray = getRandomCharset(length);    /*Обьявление второго массива char и его заполнение случайными значениями*/
                    int IntDigit = (int) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Int и задание его значения случайными Int числом от start до end*/
                    float FloatDigit = (float) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Float и задание его значения случайными Float числом от start до end*/
                    blocks.add_index(new Data(FirstCharArray, SecondCharArray, IntDigit, FloatDigit), 0);    /*Добаление блока в узел по нулевому индексу (необходимо для добавления в начало)*/
                    System.out.println("New record added in start position!");
                }
                /*Добавление новой записи в конец узла*/
                case 2 -> {
                    char[] FirstCharArray = getRandomCharset(length);    /*Обьявление первого массива char и его заполнение случайными значениями*/
                    char[] SecondCharArray = getRandomCharset(length);    /*Обьявление второго массива char и его заполнение случайными значениями*/
                    int IntDigit = (int) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Int и задание его значения случайными Int числом от start до end*/
                    float FloatDigit = (float) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Float и задание его значения случайными Float числом от start до end*/
                    blocks.add(new Data(FirstCharArray, SecondCharArray, IntDigit, FloatDigit));    /*Добаление блока в узел (метод сразу добавляет запись в конец, поэтому добавление по индексу не нужно)*/
                    System.out.println("New record added in end position!");
                }
                /*Добавление новой записи в заданную позицию узла*/
                case 3 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();    /*Обьявление и ввод переменной index для последующего определения методов с помощью индексации*/
                    char[] FirstCharArray = getRandomCharset(length);    /*Обьявление первого массива char и его заполнение случайными значениями*/
                    char[] SecondCharArray = getRandomCharset(length);    /*Обьявление второго массива char и его заполнение случайными значениями*/
                    int IntDigit = (int) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Int и задание его значения случайными Int числом от start до end*/
                    float FloatDigit = (float) (Math.random() * (end - start)) + start;    /*Обьявление числа типа Float и задание его значения случайными Float числом от start до end*/
                    blocks.add_index(new Data(FirstCharArray, SecondCharArray, IntDigit, FloatDigit), index);    /*Добаление блока в узел по нулевому индексу (необходимо для добавления по заданной позиции)*/
                    System.out.println("New record added in" + index + "position!");
                }
                /*Удаление записи по заданному индексу*/
                case 4 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();    /*Обьявление и ввод переменной index для последующего определения методов с помощью индексации*/

                    blocks.remove_by_index(index);    /*Удаление записи по заданной позиции*/
                    System.out.println("Record deleted!");
                }
                /**Изменение блока узла по заданному индексу
                 *
                 * Здесь будем пользоваться классически рабочим методом для изменения чего-либо:
                 * удалить-сделать новый на месте старого*/
                case 5 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();     /*Обьявление и ввод переменной index для последующего определения методов с помощью индексации*/
                    blocks.remove_by_index(index);    /*Удаление записи по заданной позиции*/

                    char[] newFirstCharArray = getRandomCharset(length);     /*Обьявление первого массива char и его заполнение случайными значениями*/
                    char[] newSecondCharArray = getRandomCharset(length);    /*Обьявление второго массива char и его заполнение случайными значениями*/
                    int newIntDigit = (int) (Math.random() * (end - start)) + start;     /*Обьявление числа типа Int и задание его значения случайными Int числом от start до end*/
                    float newFloatDigit = (float) (Math.random() * (end - start)) + start;     /*Обьявление числа типа Float и задание его значения случайными Float числом от start до end*/

                    blocks.add_index(new Data(newFirstCharArray, newSecondCharArray, newIntDigit, newFloatDigit), index);     /*Добаление блока в узел по нулевому индексу (необходимо для добавления по заданной позиции)*/
                    System.out.println("This record have been changed!");
                }
                /*Полная очистка узла*/
                case 6 -> {
                    for (int i = 0; i < 10; i++) blocks.clear(blocks);    /*Цикловая оцистка узла, почему цикловая, потому что это небольшой костыль, который помогает программе рабоать*/
                    System.out.println("All records have been deleted!");
                }
                /*Поиск по узлу отсносительно Массивов-Числа(Int)-Числа(Float)*/
                case 7 -> {
                    System.out.println("""
                            1 - По FirstCharArray
                            2 - По SecondCharArray
                            3 - По Int Digit
                            4 - По Float Digit
                            Choose the type for search:""");
                    int number = scan.nextInt();    /*Обьявление и ввод переменной для выбора параметра поиска*/

                    if (number == 1)    /*В случае, если выбрали первый метод*/ {
                        System.out.print("Enter the FirstCharArray for search: ");
                        String FirstArray = scan.next();    /*Инициализация String строки для дальнейшего поиска по заданному значению*/
                        char[] temp = FirstArray.toCharArray();    /*Переводим выше введённый String в массив char, чтобы не словить конфликтов типизации*/
                        for (int i = 0; i < blocks.size(); i++) {    /*Проходим по всему нашему узлу*/
                            if (Arrays.equals(temp, blocks.get(i).FirstCharArray)) {    /*Сравниваем введённое нами значение и значения имеющиеся в блоках*/
                                /*Выводим блок, в котором нашёлся элемент*/
                                System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FirstCharArray) + " " + Arrays.toString(blocks.get(i).SecondCharArray) + " " + blocks.get(i).IntDigit + " " + blocks.get(i).FloatDigit);
                            }
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    if (number == 2)    /*В случае, если выбрали второй метод*/ {
                        System.out.print("Enter the SecondCharArray for search: ");
                        String SecondArray = scan.next();    /*Инициализация String строки для дальнейшего поиска по заданному значению*/
                        char[] temp = SecondArray.toCharArray();    /*Переводим выше введённый String в массив char, чтобы не словить конфликтов типизации*/
                        for (int i = 0; i < blocks.size(); i++) {    /*Проходим по всему нашему узлу*/
                            if (Arrays.equals(temp, blocks.get(i).SecondCharArray)) {    /*Сравниваем введённое нами значение и значения имеющиеся в блоках*/
                                /*Выводим блок, в котором нашёлся элемент*/
                                System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FirstCharArray) + " " + Arrays.toString(blocks.get(i).SecondCharArray) + " " + blocks.get(i).IntDigit + " " + blocks.get(i).FloatDigit);
                            }
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    if (number == 3)    /*В случае, если выбрали третий метод*/ {
                        System.out.print("Enter the Int Digit for search: ");
                        int IntDigit = scan.nextInt();    /*Инициализируем и вводим число Int для поиска*/
                        for (int i = 0; i < blocks.size(); i++)    /*Проходим по всему нашему узлу*/ {
                            if (IntDigit == blocks.get(i).IntDigit)    /*Сравнение введённого числа и числа имеющегося в блоках*/ {
                                /*Выводим блок, в котором нашёлся элемент*/
                                System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FirstCharArray) + " " + Arrays.toString(blocks.get(i).SecondCharArray) + " " + blocks.get(i).IntDigit + " " + blocks.get(i).FloatDigit);
                            }
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    if (number == 4)    /*В случае, если выбрали третий метод*/ {
                        System.out.print("Enter the Float Digit for search: ");
                        float FloatDigit = scan.nextFloat();    /*Инициализируем и вводим число Float для поиска*/
                        for (int i = 0; i < blocks.size(); i++)    /*Проходим по всему нашему узлу*/ {
                            if (FloatDigit == blocks.get(i).FloatDigit)    /*Сравнение введённого числа и числа имеющегося в блоках*/ {
                                /*Выводим блок, в котором нашёлся элемент*/
                                System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FirstCharArray) + " " + Arrays.toString(blocks.get(i).SecondCharArray) + " " + blocks.get(i).IntDigit + " " + blocks.get(i).FloatDigit);
                            }
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                }
                /*Вывод всего узла*/
                case 8 -> {
                    for (int i = 0; i < blocks.size(); i++)    /*Проходим по всему массиву*/ {
                        /*Выводим каждый блок узла*/
                        System.out.println(Arrays.toString(blocks.get(i).FirstCharArray) + " " + Arrays.toString(blocks.get(i).SecondCharArray) + " " + blocks.get(i).IntDigit + " " + blocks.get(i).FloatDigit);
                    }
                }
                /*Вывод последнего блока узла*/
                case 9 -> {
                    /*Выводим последний блок узла*/
                    System.out.println(Arrays.toString(blocks.get(blocks.size() - 1).FirstCharArray) + " " + Arrays.toString(blocks.get(blocks.size() - 1).SecondCharArray) + " " + blocks.get(blocks.size() - 1).IntDigit + " " + blocks.get(blocks.size() - 1).FloatDigit);
                }
                /*Выход из программы*/
                case 0 -> {
                    flag = true;
                }
            }
        }
    }

    /**Метод для получения случайного массива char
     * Передаётся размерность и строиться массив заданного количества элементов*/
    public static char[] getRandomCharset(int length) {
        char[] RandomChars = new char[length];
        Random rand = new Random();
        for (int i = 0; i < RandomChars.length; i++) {
            RandomChars[i] = (char) (rand.nextInt(LETTERS_COUNT) + (rand.nextBoolean() ? 'a' : 'A'));
        }
        return RandomChars;
    }
}

/*Класс одного блока*/
class Data {
    public char[] FirstCharArray;    /*Инициализация первого char массива*/
    public char[] SecondCharArray;    /*Инициализация второго char массива*/
    public int IntDigit;    /*Инициализация Int числа*/
    public float FloatDigit;    /*Инициализация Float числа*/

    /**Конструктор класса одного блока
     * Нужно для инициализации переменных по всей программе*/
    public Data(char[] FirstCharArray, char[] SecondCharArray, int IntDigit, float FloatDigit) {
        this.FirstCharArray = FirstCharArray;
        this.SecondCharArray = SecondCharArray;
        this.IntDigit = IntDigit;
        this.FloatDigit = FloatDigit;
    }
}

/*Класс узла для блоков*/
class Node<T> {
    private final int INIT_SIZE = 5;    /*Инициализация количества блоков в узле*/
    private Object[] class_array = new Object[INIT_SIZE];   /*Инициализация обьектного массива для узла*/
    private int pointer = 0;    /*Переменная для индексации узла*/

    /*Добавление елемента в конец узла*/
    public void add(T element) {
        if (pointer == class_array.length - 1) resize(class_array.length * 2);
        class_array[pointer++] = element;
    }

    /*Добавление елемента в узел по заданному индексу*/
    public void add_index(T element,int index){
        if(index>= class_array.length-1) resize(class_array.length+2);
        Object[] tmp = new Object[class_array.length+1];
        if (index >= 0) System.arraycopy(class_array, 0, tmp, 0, index);
        tmp[index]=element;
        if (class_array.length - index >= 0) {
            System.arraycopy(class_array, index, tmp, index + 1, class_array.length - index);
        }
        resize(tmp.length);
        class_array=tmp;
        pointer++;
    }

    /*Вызов елемента по индексу*/
    public T get(int index) {
        return (T) class_array[index];
    }

    /*Вывод размерности узла*/
    public int size() {
        return pointer;
    }

    /*Полная очистка узла*/
    public void clear(Node<Data> array) {
        for (int i = 0; i < array.size(); i++) {
            array.remove_by_index(i);
        }
    }

    /*Удаление блока из узла по индексу*/
    public void remove_by_index(int index) {
        if (pointer - index >= 0) System.arraycopy(class_array, index + 1, class_array, index, pointer - index);
        class_array[pointer] = null;
        pointer--;
    }

    /**Функция для мосштабирования
     * ДОПОЛНИТЕЛЬНО*/
    public void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(class_array, 0, newArray, 0, pointer);
        class_array = newArray;
    }
}