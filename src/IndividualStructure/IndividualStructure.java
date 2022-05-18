package IndividualStructure;

import java.util.*;

public class IndividualStructure {
    public static void main(String[] args) {
        ArrayList<Node> blocks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the random arguments (start/end): ");
        int start = scan.nextInt(); int end = scan.nextInt();
        boolean flag = false;
        System.out.println("""
                    1 - Generate the block
                    2 - Filling in the information array from keyboard
                    3 - Filling in the information array from file
                    4 - Building a hash-table
                    5 - Enter a new record
                    6 - Delete the record
                    7 - Search with hash-table
                    8 - Output the blocks
                    9 - Output the hash-table
                    0 - Exit
                    """);
        while (!flag) {
            System.out.print("Enter the operation number: ");
            int operationNumber = scan.nextInt();

            switch (operationNumber) {
                case 1 -> {
                    System.out.print('\t' + "Enter the word length: "); int lenght = scan.nextInt();
                    blocks.add(new Node(getRandomFloatArray(3, start, end), getRandomIntArray(3, start, end), getRandomString(lenght), getRandomFloat(start, end)));
                }
                case 2 -> {
                    System.out.print('\t' + "Enter the index: "); int index = scan.nextInt();
                    blocks.remove(index);

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

                    blocks.add(index, new Node(newFloatArray, newIntArray, newWord, newFloatDigit));
                    System.out.println('\t' + "This record have been changed!");
                }
                case 3 -> {

                }
                case 4 -> {
                    Hashtable<float[], int[]> ht = new Hashtable<float[], int[]>();
                    for (int i = 0; i < blocks.size(); i++) {
                        ht.put(blocks.get(i).FloatArray, blocks.get(i).IntArray);
                    }
                    System.out.println(ht);
                }
                case 8 -> {
                    for (int i = 0; i < blocks.size(); i++) {
                        System.out.println(Arrays.toString(blocks.get(i).FloatArray) + " " + Arrays.toString(blocks.get(i).IntArray) + " " + blocks.get(i).word + " " + blocks.get(i).digit);
                    }
                }
                case 0 -> {
                    flag = true;
                }
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

}

class Node {
    public float[] FloatArray;
    public int[] IntArray;
    public String word;
    public float digit;

    public Node(float[] FloatArray, int[] IntArray, String word, float digit) {
        this.FloatArray = FloatArray;
        this.IntArray = IntArray;
        this.word = word;
        this.digit = digit;
    }
}
