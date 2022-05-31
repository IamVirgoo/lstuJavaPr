package ForVlad;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class VladIDZ {
    public static void main(String[] args) {
        Hashtable<Integer, Data> hashtable = new Hashtable<>(); /*инициализация хэщ-таблицы с клюачми типа Integer и значением типа Data*/
        Node<Data> blocks = new Node<>(); /*Инициализация списка блоков*/
        Scanner scan = new Scanner(System.in); /*Инициализация сканнера, для ввода чего-либо в программе*/

        System.out.print("Enter the random arguments (start/end): ");
        int start = scan.nextInt(); int end = scan.nextInt(); /*ввод ограничений для случайных значений генерирующих методов*/
        System.out.print("Enter the word length: ");
        int len = scan.nextInt(); /*ввод размерности слова*/
        System.out.print("Enter the arrays size: ");
        int length = scan.nextInt(); /*ввод количества элементов в массивах*/
        boolean flag = false; /*инициализация флага, для задания не-рекурсивного интерфейса*/
        System.out.println("""
                    1 - Generate the information array
                    2 - Filling in the information array from keyboard
                    3 - Filling in the information array from file
                    4 - Building a hash-table
                    5 - Delete the information array from index
                    6 - Search with hash-table
                    7 - Output the blocks
                    8 - Output the hash-table
                    0 - Exit
                    """);
        while (!flag) /*Основной цикл для взаимодействия с методами*/ {
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt(); /*Обьявление и ввод пользователем номера действия, которое необходимо сделать*/

            switch (OperationNumber) {
                /*Добавление новой записи в конец узла*/
                case 1 -> {
                    blocks.add(new Data(getRandomFloat(start, end), getRandomFloatArray(length, start, end), getRandomString(len))); /*непосредственно добавлени методом генерации данных*/
                    System.out.println("Generated!");
                }
                /*Добавление новой записи на определенноё позиции*/
                case 2 -> {
                    System.out.print('\n' + "Enter the index of position: ");
                    int index = scan.nextInt(); /*Ввод индекса позиции для добавления*/
                    blocks.remove_by_index(index); /*Вызов метода для удаления*/

                    System.out.print('\t' + "Enter the new float digit: ");
                    float newFloatDigit = scan.nextFloat(); /*Ввод нового Float числа*/

                    System.out.print('\t' + "Enter the new float array elements: ");
                    float[] newFloatArray = new float[length]; /*Инициализация нового массива float*/
                    for (int i = 0; i < length; i++) newFloatArray[i] = scan.nextFloat(); /*Добавление элементов в новый массив*/

                    System.out.print('\t' + "Enter the new word: ");
                    String newWord = scan.next(); /*Ввод нового слова*/

                    blocks.add_index(new Data(newFloatDigit, newFloatArray, newWord), index); /*Непосредственно добавление по индексу*/
                    System.out.println("This record have been changed!");
                }
                /*Тут чтение из файла, я сам его не делал, но мне сказали, что необязательно*/
                case 3 -> {}
                /*Создание хэш-таблицы*/
                case 4 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        hashtable.put(i, new Data(blocks.get(i).FloatDigit, blocks.get(i).FloatArray, blocks.get(i).word)); /*Хеширование элементов*/
                    }
                    System.out.println("The Hash table created!");
                }
                /*Удаление блока по заданному индексу*/
                case 5 -> {
                    System.out.print('\n' + "Enter the index: ");
                    int index = scan.nextInt(); /*Ввод индекса нужной позиции*/
                    blocks.remove_by_index(index); /*Вызов метода по удалению блока*/
                    System.out.println("Deleted!");
                }
                /*Поиск по хэш-таблице по заданному ключу*/
                case 6 -> {
                    System.out.print("Enter the hash key: ");
                    int key = scan.nextInt();
                    System.out.println(hashtable.get(key).FloatDigit + " " + Arrays.toString(hashtable.get(key).FloatArray) + " " + hashtable.get(key).word);
                }
                /*Вывод всего списка*/
                case 7 -> {
                    for (int i = 0; i < blocks.size(); i++)
                        System.out.println(blocks.get(i).FloatDigit + " " + Arrays.toString(blocks.get(i).FloatArray) + " " + blocks.get(i).word);
                }
                /*Вывод хеш-таблицы*/
                case 8 -> {
                    System.out.println(hashtable);
                }
                /*Выход из программы*/
                case 0 -> {
                    flag = true;
                }
            }
        }

    }
    /**Генерация случайного Float массива
     * Передаётва количество элементов и ограничения по числам
     * на выходе получаем массив случайных float чисел*/
    public static float[] getRandomFloatArray(int length, int start, int end) {
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandomFloat(start, end);
        }
        return array;
    }

    /**Метод для получения случайного элемента String
     * Передаётся размерность и строиться массив заданного количества элементов*/
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

    /*Генерация случайного числа Float*/
    public static Float getRandomFloat(int start, int end) {
        return (float) (Math.random() * (end - start)) + start;
    }

    /*Класс одного блока*/
    public static class Data {
        public float FloatDigit; /*Инициализация числа float*/
        public float[] FloatArray; /*Инициализация массива float*/
        public String word; /*Инициализация String элементва == массива char*/

        /**Конструктор класса одного блока
         * Нужно для инициализации переменных по всей программе*/
        public Data(float FloatDigit, float[] FloatArray, String word) {
            this.FloatArray = FloatArray;
            this.word = word;
            this.FloatDigit = FloatDigit;
        }
    }
    /*Класс узла для блоков*/
    public static class Node<T> {
        private final int INIT_SIZE = 6;                        /*Инициализация количества блоков в узле*/
        private Object[] class_array = new Object[INIT_SIZE];   /*Инициализация обьектного массива для узла*/
        private int pointer = 0;                                /*Переменная для индексации узла*/

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
            for (int i = 0; i < array.size(); i++) array.remove_by_index(i);
        }

        /*Удаление блока из узла по индексу*/
        public void remove_by_index(int index) {
            if (pointer - index >= 0) System.arraycopy(class_array, index + 1, class_array, index, pointer - index);
            class_array[pointer] = null;
            pointer--;
        }

        /*Функция для мосштабирования*/
        public void resize(int newLength) {
            Object[] newArray = new Object[newLength];
            System.arraycopy(class_array, 0, newArray, 0, pointer);
            class_array = newArray;
        }
    }
}
