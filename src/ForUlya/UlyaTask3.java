package ForUlya;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class UlyaTask3 {
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
                /*Генерация блока структуры заданных данных и добавление в начало узла*/
                case 1 -> {
                    float[] FloatArray = getFloatArray(3); /*Генерация массива float*/
                    int[] FirstIntArray = getIntArray(length); /*Генерация первого массива Int*/
                    int[] SecondIntArray = getIntArray(length); /*Генерация второго массива Int*/
                    char word = getRandomChar(); /*Генерация char элемента*/
                    blocks.add_index(new Data(FloatArray, FirstIntArray, SecondIntArray, word), 0); /*Непосредственно добавление по нулевому индексу*/
                    System.out.println("Generated!");
                }
                /*Генерация блока структуры заданных данных и добавление в конец узла*/
                case 2 -> {
                    float[] FloatArray = getFloatArray(3); /*Генерация массива float*/
                    int[] FirstIntArray = getIntArray(length); /*Генерация первого массива Int*/
                    int[] SecondIntArray = getIntArray(length); /*Генерация второго массива Int*/
                    char word = getRandomChar(); /*Генерация char элемента*/
                    blocks.add(new Data(FloatArray, FirstIntArray, SecondIntArray, word)); /*Непосредственно добавление*/
                    System.out.println("Generated!");
                }
                /*Генерация блока структуры заданных данных и добавление в заданную позицию узла*/
                case 3 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt(); /*Ввод индекса позиции*/
                    float[] FloatArray = getFloatArray(3);
                    int[] FirstIntArray = getIntArray(length);
                    int[] SecondIntArray = getIntArray(length);
                    char word = getRandomChar();
                    blocks.add_index(new Data(FloatArray, FirstIntArray, SecondIntArray, word), index);
                    System.out.println("Generated!");
                }
                /*Удаление записи по заданной позиции*/
                case 4 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt(); /*Ввод индекса позиции*/
                    blocks.remove_by_index(index); /*Удаление по индексу*/
                }
                /*Изменение записи из узла*/
                case 5 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();     /*Обьявление и ввод переменной index для последующего определения методов с помощью индексации*/
                    blocks.remove_by_index(index);    /*Удаление записи по заданной позиции*/

                    float[] FloatArray = getFloatArray(3);
                    int[] FirstIntArray = getIntArray(length);
                    int[] SecondIntArray = getIntArray(length);
                    char word = getRandomChar();

                    blocks.add_index(new Data(FloatArray, FirstIntArray, SecondIntArray, word), index);     /*Добаление блока в узел по нулевому индексу (необходимо для добавления по заданной позиции)*/
                    System.out.println("This record have been changed!");
                }
                /*Полная очистка узла*/
                case 6 -> {
                    for (int i = 0; i < 10; i++) blocks.clear(blocks);    /*Цикловая оцистка узла, почему цикловая, потому что это небольшой костыль, который помогает программе рабоать*/
                    System.out.println("All records have been deleted!");
                }
                /*Поиск по параметрам Массив Float -> Масссив Int -> Массив Int -> Элемент char*/
                case 7 -> {
                    System.out.println("""
                            1 - По FloatArray
                            2 - По FirstIntArray
                            3 - По SecondIntArray
                            4 - По word
                            Choose the type for search:""");
                    int number = scan.nextInt();    /*Обьявление и ввод переменной для выбора параметра поиска*/
                    /**
                     * В первом случае, как и во втором и в третьем мы пользуемся следующей логикой
                     * Не очень удобно сравнивать различные типы данных и не запутаться, получив кучу ошибок
                     * Следовательно нужно приводить все сравниваеммые элементы к одному типу данных
                     * В этих случаях у нас это String
                     * Нам гораздо легче перезаписать массивы в виде строк и сравнивать строки, чем делать непосредственное сравнение по элементам массивов*/
                    if (number == 1) {
                        System.out.print("Enter the float array elements: ");
                        String[] NewStrArray = new String[3];
                        for (int j = 0; j < 3; j++) {
                            NewStrArray[j] = scan.next();
                        }
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Arrays.toString(NewStrArray).equals(Arrays.toString(blocks.get(i).FloatArray)))
                                System.out.println("Found in " + (i + 1) + " position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).FirstIntArray) + " " + Arrays.toString(blocks.get(i).SecondIntArray) + " " + blocks.get(i).word);
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    if (number == 2) {
                        System.out.print("Enter the int array elements: ");
                        String[] NewStrArray = new String[length];
                        for (int j = 0; j < length; j++) {
                            NewStrArray[j] = scan.next();
                        }
                        System.out.println(Arrays.toString(NewStrArray) + " " + Arrays.toString(blocks.get(0).FirstIntArray));
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Arrays.toString(NewStrArray).equals(Arrays.toString(blocks.get(i).FirstIntArray)))
                                System.out.println("Found in " + (i + 1) + " position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).FirstIntArray) + " " + Arrays.toString(blocks.get(i).SecondIntArray) + " " + blocks.get(i).word);
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    if (number == 3) {
                        System.out.print("Enter the int array elements: ");
                        String[] NewStrArray = new String[length];
                        for (int j = 0; j < length; j++) {
                            NewStrArray[j] = scan.next();
                        }
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Arrays.toString(NewStrArray).equals(Arrays.toString(blocks.get(i).SecondIntArray)))
                                System.out.println("Found in " + (i + 1) + " position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).FirstIntArray) + " " + Arrays.toString(blocks.get(i).SecondIntArray) + " " + blocks.get(i).word);
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                        break;
                    }
                    /**
                     * Так как на вход мы получаем не числовое значение, а просто символ, но это очень сильно упрощает задачу
                     * Нам не нужно использывать какие-то буферные типы данных, как выше в случае с String,
                     * тут мы будем пользоваться обычным перебором всех значений по узлу*/
                    if (number == 4) {
                        System.out.print("Enter the char element: ");
                        char word = scan.next().charAt(0);
                        for (int i = 0; i < blocks.size(); i++) {
                            if (blocks.get(i).word == word) {
                                System.out.println("Found in " + (i + 1) + " position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).FirstIntArray) + " " + Arrays.toString(blocks.get(i).SecondIntArray) + " " + blocks.get(i).word);
                            }
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }
                    }
                }
                /*Вывод всего списка*/
                case 8 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        System.out.println(Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).FirstIntArray) + " " + Arrays.toString(blocks.get(i).SecondIntArray) + " " + blocks.get(i).word);
                    }
                }
                /*Вывод последнего блока структуры заданных данных*/
                case 9 -> {
                    System.out.println(Arrays.toString(blocks.get(blocks.size() - 1).FloatArray) + " " + Arrays.toString(blocks.get(blocks.size() - 1).FirstIntArray) + " " + Arrays.toString(blocks.get(blocks.size() - 1).SecondIntArray) + " " + blocks.get(blocks.size() - 1).word);
                }
                /*Выход из программы*/
                case 0 -> {
                    flag = true;
                }
            }
        }
    }

    /*Генерация случайного char символа*/
    public static char getRandomChar() {
        Random rand = new Random();
        return (char) (rand.nextInt(LETTERS_COUNT) + (rand.nextBoolean() ? 'a' : 'A'));
    }
    /*Генерация случайного Float массива*/
    static float[] getFloatArray(int number) {
        float[] array = new float[number];
        for (int i = 0; i < number; i++) {
            array[i] = (float) (Math.random() * 200) - 100;
        }
        return array;
    }
    /*Генерация Int массива*/
    static int[] getIntArray(int number) {
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = (int) (Math.random() * 200) - 100;
        }
        return array;
    }



}

/*Класс одного блока*/
class Data {
    public float[] FloatArray;    /*Инициализация Float массива*/
    public int[] FirstIntArray;    /*Инициализация первого int массива*/
    public int[] SecondIntArray;    /*Инициализация второго Int массива*/
    public char word;    /*Инициализация char символа*/

    /**Конструктор класса одного блока
     * Нужно для инициализации переменных по всей программе*/
    public Data(float[] FloatDigit, int[] FirstIntArray, int[] SecondIntArray, char word) {
        this.FloatArray = FloatDigit;
        this.FirstIntArray = FirstIntArray;
        this.SecondIntArray = SecondIntArray;
        this.word = word;
    }
}

/*Класс узла для блоков*/
class Node<T> {
    private final int INIT_SIZE = 8;    /*Инициализация количества блоков в узле*/
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

