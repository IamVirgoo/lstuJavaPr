import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ThirdLab {
    public static void main(String[] args) {
        /*Инициализация узла, состоящего из определённого количества блоков*/
        Node<Data> blocks = new Node<>();
        Scanner scan = new Scanner(System.in);

        boolean flag = false;
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
        while (!flag) {
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();

            switch (OperationNumber) {
                /*Добавление блока в начало узла*/
                case 1 -> {
                    float[] array = getArray(3);
                    String first_word = getRandomString(5);
                    String second_word = getRandomString(5);
                    if (blocks.size() > 6) {
                        System.out.println("Record limit exceeded!");
                        blocks.remove_by_index(blocks.size() - 1);
                    }
                    blocks.add_index(new Data(array, first_word, second_word), 0);
                    System.out.println("New record added in start position!");
                    break;
                }
                /*Добавление блока в конец узла*/
                case 2 -> {
                    float[] array = getArray(3);
                    String first_word = getRandomString(5);
                    String second_word = getRandomString(5);
                    if (blocks.size() > 6) {
                        System.out.println("Record limit exceeded!");
                        blocks.remove_by_index(blocks.size() - 1);
                    }
                    blocks.add(new Data(array, first_word, second_word));
                    System.out.println("New record added in end position!");
                    break;
                }
                /*Добавление блока в заданную позицию узла*/
                case 3 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();
                    float[] array = getArray(3);
                    String first_word = getRandomString(5);
                    String second_word = getRandomString(5);
                    if (blocks.size() > 6) {
                        System.out.println("Record limit exceeded!");
                        blocks.remove_by_index(blocks.size() - 1);
                    }
                    blocks.add_index(new Data(array, first_word, second_word), index);
                    System.out.println("New record added in " + index + " position!");
                    break;
                }
                /*Удаление блока по заданному индексу*/
                case 4 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();

                    blocks.remove_by_index(index);
                    System.out.println("Record deleted!");
                    break;
                }
                /*Изменение блока узла по заданному индексу*/
                case 5 -> {
                    System.out.print("Enter the index of position: ");
                    int index = scan.nextInt();
                    blocks.remove_by_index(index);

                    System.out.println("Enter the new array elements: ");
                    float[] newArray = new float[3];
                    for (int i = 0; i < 3; i++) newArray[i] = scan.nextFloat();

                    System.out.print("Enter the new first word: ");
                    String newFirstWord = scan.next();

                    System.out.print("Enter the new second word: ");
                    String newSecondWord = scan.next();

                    blocks.add_index(new Data(newArray, newFirstWord, newSecondWord), index);

                    System.out.println("This record have been changed!");
                    break;
                }
                /*Полная очистка узла*/
                case 6 -> {
                    for (int i = 0; i < 10; i++) blocks.clear(blocks);
                    System.out.println("All records have been deleted!");
                }
                /*Поиск по узлу относительно Массивва-ПервогоСлова-ВторогоСлова*/
                case 7 -> {
                    System.out.println("""
                            1 - find by array
                            2 - find by first word
                            3 - fund by second word
                            Choose the type for search:""");
                    int number = scan.nextInt();
                    /*Поиск по массиву*/
                    if (number == 1) {
                        System.out.print("Enter the array elements: ");
                        String[] NewStrArray = new String[3];
                        for (int j = 0; j < 3; j++) {
                            NewStrArray[j] = scan.next();
                        }
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Arrays.toString(NewStrArray).equals(Arrays.toString(blocks.get(i).array)))
                                System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(blocks.get(i).array) + " " + blocks.get(i).first_word + " " + blocks.get(i).second_word);
                            else {
                                System.out.println("Not found in " + (i + 1) + " position");
                            }
                        }

                        break;
                    }
                    /*Поиск по первому слову*/
                    if (number == 2) {
                        System.out.print("Enter the first word: ");
                        String res = scan.next();
                        int counter = 0;
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Objects.equals(res, blocks.get(i).first_word)) {
                                System.out.println("Find in " + counter + " block");
                                System.out.println(Arrays.toString(blocks.get(i).array) + " " + blocks.get(i).first_word + " " + blocks.get(i).second_word);
                                break;
                            }
                            else {
                                System.out.println("Not found in " + (counter + 1)+ " block");
                            }
                            counter++;
                        }
                    }
                    /*Поиск по второму слову*/
                    if (number == 3) {
                        System.out.print("Enter the second word: ");
                        String res = scan.next();
                        int counter = 0;
                        for (int i = 0; i < blocks.size(); i++) {
                            if (Objects.equals(res, blocks.get(i).second_word)) {
                                System.out.println("Find in " + counter + " block");
                                System.out.println(Arrays.toString(blocks.get(i).array) + " " + blocks.get(i).first_word + " " + blocks.get(i).second_word);
                                break;
                            }
                            else {
                                System.out.println("Not found in " + (counter + 1) + " block");
                            }
                            counter++;
                        }
                    }
                    break;
                }
                /*Вывод всего узла*/
                case 8 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        System.out.println(Arrays.toString(blocks.get(i).array) + " " + blocks.get(i).first_word + " " + blocks.get(i).second_word);
                    }
                    break;
                }
                /*Вывод последнего блока узла*/
                case 9 -> {
                    System.out.println(Arrays.toString(blocks.get(blocks.size() - 1).array) + " " + blocks.get(blocks.size() - 1).first_word + " " + blocks.get(blocks.size() - 1).second_word);
                }
                /*Выход из программы*/
                case 10 -> {
                    flag = true;
                    break;
                }
            }
        }
    }
    /*Генерация массива определённой размерности с случайными елементами от -100 до 100*/
    static float[] getArray(int number) {
        float[] array = new float[number];
        for (int i = 0; i < number; i++) {
            array[i] = (float) (Math.random() * 200) - 100;
        }
        return array;
    }

    /*Генерация случайного String елемента*/
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
}

/*Класс одного блока*/
class Data {
    public float[] array;
    public String first_word;
    public String second_word;

    /*Конструктор класса одного блока*/
    public Data(float[] array, String first_word, String second_word) {
        this.array = array;
        this.first_word = first_word;
        this.second_word = second_word;
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