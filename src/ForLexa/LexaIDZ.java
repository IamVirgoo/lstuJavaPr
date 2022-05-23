package ForLexa;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LexaIDZ {
    static final int LETTERS_COUNT = 'z' - 'a' + 1;
    public static void main(String[] args) {
        Node<Data> blocks = new Node<>();
        Scanner scan = new Scanner(System.in);

        boolean flag = false;
        System.out.println("""
                1 - Add new record
                2 - Add new file record
                3 - Add new record on index position
                4 - Remove the record on index position
                5 - Changing the record on index position
                6 - Sort the array on index position
                7 - Output all list
                0 - Exit
                """);
        while (!flag) {
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();

            switch (OperationNumber) {
                case 1 -> {
                    float[] FloatArray = getFloatArray(3);
                    int[] IntArray = getIntArray(3);
                    char[] Charset = getRandomCharset(5);
                    float Digit = (float) (Math.random() * 200) - 100;

                    blocks.add(new Data(FloatArray, IntArray, Charset, Digit));
                    System.out.println("New record added!");
                }
                case 2 -> {}
                case 3 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();

                    float[] FloatArray = getFloatArray(3);
                    int[] IntArray = getIntArray(3);
                    char[] Charset = getRandomCharset(5);
                    float Digit = (float) (Math.random() * 200) - 100;
                    blocks.add_index(new Data(FloatArray, IntArray, Charset, Digit), index);
                    System.out.println("New record have been added on " + index + " position");
                }
                case 4 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();

                    blocks.remove_by_index(index);
                }
                case 5 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();
                    blocks.remove_by_index(index);

                    float[] FloatArray = getFloatArray(3);
                    int[] IntArray = getIntArray(3);
                    char[] Charset = getRandomCharset(5);
                    float Digit = (float) (Math.random() * 200) - 100;

                    blocks.add_index(new Data(FloatArray, IntArray, Charset, Digit), index);
                    System.out.println("This record have been changed!");
                }
                case 6 -> {
                    System.out.print("Choose the index of array position: ");
                    int index = scan.nextInt();
                    float[] FloatArray = sort(blocks.get(index).FloatArray);
                    int[] IntArray = blocks.get(index).IntArray;
                    char[] Charset = blocks.get(index).Charset;
                    float Digit = blocks.get(index).Digit;
                    blocks.remove_by_index(index);
                    blocks.add_index(new Data(FloatArray, IntArray, Charset, Digit), index);
                }
                case 7 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        System.out.println(Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).IntArray) + " " + Arrays.toString(blocks.get(i).Charset) + " " + blocks.get(i).Digit);
                    }
                }
                case 0 -> {
                    flag = true;
                }
            }
        }
    }
    static float[] getFloatArray(int number) {
        float[] array = new float[number];
        for (int i = 0; i < number; i++) {
            array[i] = (float) (Math.random() * 200) - 100;
        }
        return array;
    }

    static int[] getIntArray(int number) {
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = (int) (Math.random() * 200) - 100;
        }
        return array;
    }

    public static char[] getRandomCharset(int length) {
        char[] RandomChars = new char[length];
        Random rand = new Random();
        for (int i = 0; i < RandomChars.length; i++) {
            RandomChars[i] = (char) (rand.nextInt(LETTERS_COUNT) + (rand.nextBoolean() ? 'a' : 'A'));
        }
        return RandomChars;
    }

    //рекурсивная функция сортировки частей массива
    public static float[] sort(float[] arr){
        if(arr.length < 2) return arr;
        int m = arr.length / 2;
        float[] arr1 = Arrays.copyOfRange(arr, 0, m);
        float[] arr2 = Arrays.copyOfRange(arr, m, arr.length);
        return merge(sort(arr1), sort(arr2));
    }
    //слияние двух массивов в один отсортированный
    public static float[] merge(float[] arr1, float[] arr2){
        int n = arr1.length + arr2.length;
        float[] arr = new float[n];
        int i1=0;
        int i2=0;
        for(int i=0;i<n;i++){
            if(i1 == arr1.length){
                arr[i] = arr2[i2++];
            }else if(i2 == arr2.length){
                arr[i] = arr1[i1++];
            }else{
                if(arr1[i1] < arr2[i2]){
                    arr[i] = arr1[i1++];
                }else{
                    arr[i] = arr2[i2++];
                }
            }
        }
        return arr;
    }
}

/*Класс одного блока*/
class Data {
    public float[] FloatArray;
    public int[] IntArray;
    public char[] Charset;
    public float Digit;

    /*Конструктор класса одного блока*/
    public Data(float[] FloatArray, int[] IntArray, char[] Charset, float Digit) {
        this.FloatArray = FloatArray;
        this.IntArray = IntArray;
        this.Charset = Charset;
        this.Digit = Digit;
    }
}

/*Класс узла для блоков*/
class Node<T> {
    private final int INIT_SIZE = 6;    /*Инициализация количества блоков в узле*/
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
        for (int i = index; i < pointer; i++) {
            class_array[i] = class_array[i + 1];
        }
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