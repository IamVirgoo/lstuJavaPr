package ForUlya;

import java.util.Arrays;
import java.util.Scanner;

public class KruskalAlogrithm {
    private int numOfEdges;          /*количество ребер*/
    private final char[] vertex;     /*набор вершин*/
    private final int[][] matrix;    /*матрица смежности*/
    private static final int INF = 0;   /*Значение нуля для матрицы смежности*/

    private static final Scanner scan = new Scanner(System.in);    /*Инициализация глобального сканера для возможности ввода в программе*/

    /*Конструктор класса и инициализация*/
    public KruskalAlogrithm(char[] vertex, int[][] matrix) {
        this.vertex = vertex;    /*Инициализация множества вершин*/
        this.matrix = matrix;    /*Инициализация матрицы смежности*/
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    this.numOfEdges++;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the number of vertexes: ");
        int size = scan.nextInt();    /*Ввод количества вершин*/
        char[] vertex = new char[size];   /*Инициализация множества вершин*/
        int counter = 65;    /*Штука для случайной генерации буквы, так как в таблице ascii буквы начинаются с кода 65*/
        for (int i = 0; i < size; i++) {
            vertex[i] = ((char) counter);
            counter++;
        }
        System.out.print("Vertexes: "); for (int i = 0; i < vertex.length; i++) System.out.print(vertex[i] + " ");    /*Вывод множества вершин*/
        System.out.println();
        int[][] matrix = getSymmetricalMatrix(vertex.length);    /*Формирование симметрической */
        KruskalAlogrithm kruskalAlogrithm = new KruskalAlogrithm(vertex, matrix);    /*Инициализация работы самого алгоритма*/
        System.out.println('\n' + "Adjacency matrix:");
        kruskalAlogrithm.showGraph();    /*Вывод матрицы смежности*/
        System.out.println('\n' + "After sort: " + Arrays.toString(kruskalAlogrithm.getEdges()));
        Degree[] edges = kruskalAlogrithm.getEdges();    /*Вывод связей матрицы*/
        kruskalAlogrithm.sort(edges);    /*Сортировка связей матрицы*/
        System.out.println('\n' + "Before sort: " + Arrays.toString(edges));
        kruskalAlogrithm.Kruskal();    /*Вызов самого алгоритма выполнения*/
    }

    /**
     * @Title getSymmetricalMatrix
     * @Description Генерация симетричного дерева с случайными int значениями от 0 до 50
     * @param length является переменной, которая задаёт размерность матрицы
     * @return Симметричная матрица (Матрица смежности ориентированного графа)*/
    static int[][] getSymmetricalMatrix(int length) {
        int[][] source = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    source[i][j] = (int) (Math.random() * 50);
                } else if (i < j) {
                    int randomValue = (int) (Math.random() * 50);
                    source[i][j] = randomValue;
                    source[j][i] = randomValue;
                }
            }
        }
        return source;
    }

    /*Вывод матрицы смежности*/
    public void showGraph() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%11d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * @Title: sort
     * @Description: сортировка массивов рёбер методом пузырька
     * @param: edges
     * @return: */
    private void sort(Degree[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Degree temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @Title: getEdges
     * @Description: Метод возвращает массив рёбер нашего графа, представленного в виде матрицы
     * @return: Массив рёбер*/
    private Degree[] getEdges() {
        int index = 0;
        Degree[] edges = new Degree[numOfEdges];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Degree(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * @Title: getPosition
     * @Description: метод возвращает индекс позиции вершины V
     * @param: v узел
     * @return:
     */
    private int getPosition(char v) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == v) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @Title: getEnd
     * @Description: возвращает ключевую точку, соответствующую вершине с индексом i.
     * @param: еds Набор конечных точек, соответствующих каждой вершине, которые динамически добавляются при обходе ребер.
     * @param: i Вершина с индексом i, которую нужно найти
     * @return Конечная точка, соответствующая вершине с индексом i*/
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void Kruskal() {
        /*Инициализируем коллекцию конечных точек*/
        int index = 0;
        int[] ends = new int[numOfEdges];
        Degree[] edges = getEdges();
        Degree[] res = new Degree[vertex.length-1];
        sort(edges);
        for (int i = 0; i < numOfEdges; i++) {
            /*Получаем индекс первой вершины этого ребра*/
            int p1 = getPosition(edges[i].start);
            /*Получаем индекс второй вершины этого ребра*/
            int p2 = getPosition(edges[i].end);
            /*Получаем конечные точки p1 и p2*/
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if(m!=n) {
                res[index++] = edges[i];
                /*Устанавливаем конец m равным n*/
                ends[m] = n;
            }
        }
        System.out.println('\n' + "Minimum spanning tree:");
        for(int i = 0 ; i < index;i++) {
            System.out.println(res[i]);
        }
    }
}

/*Создаем новый класс Degree*/
class Degree {
    char start;    /*точка на краю*/
    char end;      /*еще одна точка на краю*/
    int weight;    /*Вес края*/

    /*Конструктор класса для связей вершин*/
    public Degree(char start, char end, int weight) {
        super();
        this.start = start;    /*Инициализация стартовой позиции связи*/
        this.end = end;    /*Инициализация конечной позиции связи*/
        this.weight = weight;    /*Инициализация веса ребра (одной связи)*/
    }

    /*Метод для вывода интерфейса*/
    @Override
    public String toString() {
        return "Degree {start = " + start + ", end = " + end + ", weight = " + weight + "}";
    }
}

