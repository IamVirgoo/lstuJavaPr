public class SortAlgorithm {
    public static int[] IntBubbleSort(int[] array) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return array;
    }
    public static float[] FloatBubbleSort(float[] array) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    float temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return array;
    }
    public static int[] IntInsertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[i]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }
    public static float[] FloatInsertionSort(float[] array) {
        for (int i = 0; i < array.length; i++) {
            float current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[i]) {
                array[j + 1] = array[j];
                j--;
            }
        }
        return array;
    }
    public static int[] IntSelectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minId = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[i];
                    minId = j;
                }
            }
            int temp = array[i];
            array[i] = min;
            array[minId] = temp;
        }
        return array;
    }
    public static float[] FloatSelectionSort(float[] array) {
        for (int i = 0; i < array.length; i++) {
            float min = array[i];
            int minId = i;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[i];
                    minId = j;
                }
            }
            float temp = array[i];
            array[i] = min;
            array[minId] = temp;
        }
        return array;
    }
    public static int[] IntShellSort(int[] array) {
        int i, j, step;
        for (step = array.length / 2; step > 0; step /= 2) {
            for (i = step; i < array.length; i++) {
                int temp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (temp < array[j - step])
                        array[i] = array[j - step];
                    else
                        break;
                }
                array[j] = temp;
            }
        }
        return array;
    }
    public static float[] FloatShellSort(float[] array) {
        int i, j, step;
        for (step = array.length / 2; step > 0; step /= 2) {
            for (i = step; i < array.length; i++) {
                float temp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (temp < array[j - step])
                        array[i] = array[i - step];
                    else
                        break;
                }
                array[j] = temp;
            }
        }
        return array;
    }
}
