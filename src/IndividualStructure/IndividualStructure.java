package IndividualStructure;

import java.io.*;
import java.util.*;

public class IndividualStructure {
    public static void main(String[] args) {
        Hashtable<Integer, Node<Data>> hashtable = new Hashtable<>();
        Node<Data> blocks = new Node<>();
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the random arguments (start/end): ");
        int start = scan.nextInt(); int end = scan.nextInt();
        System.out.print("Enter the word length: ");
        int len = scan.nextInt();
        boolean flag = false;
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
        while (!flag) {
            System.out.print("Enter the operation number: ");
            int operationNumber = scan.nextInt();

            switch (operationNumber) {
                case 1 -> blocks.add(new Data(getRandomFloatArray(3, start, end), getRandomIntArray(3, start, end), getRandomString(len), getRandomFloat(start, end)));
                case 2 -> {
                    System.out.print('\t' + "Enter the index: "); int index = scan.nextInt();
                    blocks.remove_by_index(index);

                    System.out.print('\t' + "Enter the new float array elements: ");
                    float[] newFloatArray = new float[3];
                    for (int i = 0; i < 3; i++) newFloatArray[i] = scan.nextFloat();

                    System.out.print('\t' + "Enter the new int array elements: ");
                    int[] newIntArray = new int[3];
                    for (int i = 0; i < 3; i++) newIntArray[i] = scan.nextInt();

                    System.out.print('\t' + "Enter the new word: ");
                    String newWord = scan.next();

                    System.out.print('\t' + "Enter the new float digit");
                    float newFloatDigit = scan.nextFloat();

                    blocks.add_index(new Data(newFloatArray, newIntArray, newWord, newFloatDigit), index);
                    System.out.println('\t' + "This record have been changed!");
                }
                case 3 -> {
                    System.out.print("Enter the file name: ");
                    String fileName = scan.next();

                    try {
                        File file = new File(fileName);
                        //создаем объект FileReader для объекта File
                        FileReader fr = new FileReader(file);
                        //создаем BufferedReader с существующего FileReader для построчного считывания
                        BufferedReader reader = new BufferedReader(fr);
                        // считаем сначала первую строку
                        String line = reader.readLine();
                        String line2 = reader.readLine();

                        while (line != null) {
                            System.out.println(line);
                            // считываем остальные строки в цикле
                            line = reader.readLine();
                        }
                        System.out.println(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        hashtable.put(((hashtable.hashCode() + i) % blocks.size()), blocks);
                    }
                    System.out.println("The Hash table created!");
                }
                case 5 -> {
                    System.out.print("Enter the index: ");
                    int index = scan.nextInt();
                    blocks.remove_by_index(index);
                }
                case 6 -> {
                    System.out.print("Enter the hash key: ");
                    int key = scan.nextInt();
                    System.out.println(hashtable.get(key));
                }
                case 7 -> {
                    for (int i = 0; i < blocks.size(); i++)
                        System.out.println(Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).IntArray) + " " + blocks.get(i).word + " " + blocks.get(i).digit);
                }
                case 8 -> System.out.println(hashtable);
                case 0 -> flag = true;
                default -> throw new IllegalStateException("Unexpected value: " + operationNumber);
            }
        }
    }

    public static float[] getRandomFloatArray(int length, int start, int end) {
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandomFloat(start, end);
        }
        return array;
    }

    public static int[] getRandomIntArray(int length, int start, int end) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandomInt(start, end);
        }
        return array;
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

    public static Float getRandomFloat(int start, int end) {
        return (float) (Math.random() * (end - start)) + start;
    }

    public static int getRandomInt(int start, int end) {
        return (int) (Math.random() * (end - start)) + start;
    }

    public static class Data {
        public float[] FloatArray;
        public int[] IntArray;
        public String word;
        public float digit;

        public Data(float[] FloatArray, int[] IntArray, String word, float digit) {
            this.FloatArray = FloatArray;
            this.IntArray = IntArray;
            this.word = word;
            this.digit = digit;
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

