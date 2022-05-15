import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class FourthLab {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insertNode("A");
        tree.insertNode("Aba");
        tree.insertNode("Baba");
        tree.insertNode("Ababa");
        tree.insertNode("Bababa");
        tree.insertNode("Abababa");

        tree.printTree();
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
}

/*Класс дерева*/
class Tree {
    private TreeNode rootNode;    /*Корневой узел*/

    /*Инициализация пустого дерева*/
    public Tree() {
        rootNode = null;
    }

    /*Поиск узла по значению value*/
    public TreeNode findNodeByValue(String value) {
        TreeNode currentNode = rootNode;
        while (!Objects.equals(currentNode.getValue(), value)) {
            if (value.length() < currentNode.getValue().length()) {
                currentNode = currentNode.getLeftChild();
            }
            else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) return null;
        }
        return currentNode;
    }

    /*Добавление нового элемента*/
    public void insertNode(String value) {
        TreeNode newNode = new TreeNode();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        }
        else {
            TreeNode currentNode = rootNode;
            TreeNode parentNode;
            while (true) {
                parentNode = currentNode;
                if (Objects.equals(value, currentNode.getValue())) return;
                else if (value.length() < currentNode.getValue().length()) {
                    parentNode.setLeftChild(newNode);
                }
                else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    /*Удаление элемента по значению*/
    public boolean deleteNode(String value) {
        TreeNode currentNode = rootNode;
        TreeNode parentNode = rootNode;
        boolean isLeftChild = true;
        while (!Objects.equals(currentNode.getValue(), value)) {    /*начинаем поиск узла*/
            parentNode = currentNode;
            if (value.length() < currentNode.getValue().length()) {    /*Определяем, нужно ли движение влево?*/
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            }
            else {   /*или движение вправо?*/
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null)
                return false;
        }
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {    /*узел просто удаляется, если не имеет потомков*/
            if (currentNode == rootNode)    /*если узел - корень, то дерево очищается*/
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null);    /*если нет - узел отсоединяется, от родителя*/
            else
                parentNode.setRightChild(null);
        }
        else if (currentNode.getRightChild() == null) {    /*узел заменяется левым поддеревом, если правого потомка нет*/
            if (currentNode == rootNode)
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());
        }
        else if (currentNode.getLeftChild() == null) {    /*узел заменяется правым поддеревом, если левого потомка нет*/
            if (currentNode == rootNode)
                rootNode = currentNode.getRightChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getRightChild());
            else
                parentNode.setRightChild(currentNode.getRightChild());
        }
        else {    /*если есть два потомка, узел заменяется преемником*/
            TreeNode heir = receiveHeir(currentNode);    /*поиск преемника для удаляемого узла*/
            if (currentNode == rootNode)
                rootNode = heir;
            else if (isLeftChild)
                parentNode.setLeftChild(heir);
            else
                parentNode.setRightChild(heir);
        }
        return true;
    }

    /*Метод возвращает узел со следующим значением после передаваемого аргументом.
    для этого он сначала переходим к правому потомку, а затем
    отслеживаем цепочку левых потомков этого узла*/
    private TreeNode receiveHeir(TreeNode node) {
        TreeNode parentNode = node;
        TreeNode heirNode = node;
        TreeNode currentNode = node.getRightChild();    /*Переход к правому потомку*/
        while (currentNode != null)    /*Пока остаются левые потомки*/
        {
            parentNode = heirNode;    /*потомка задаём как текущий узел*/
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild();    /*переход к левому потомку*/
        }
        /*Если преемник не является*/
        if (heirNode != node.getRightChild()) {    /*правым потомком создать связи между узлами*/
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;    /*возвращаем приемника*/
    }

    /*Метод для вывода дерева в консоль*/
    public void printTree() {
        Stack<TreeNode> globalStack = new Stack<TreeNode>();    /*общий стек для значений дерева*/
        globalStack.push(rootNode);
        int gaps = 32;    /*начальное значение расстояния между элементами*/
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);    /*черта для указания начала нового дерева*/
        while (!isRowEmpty) {
            Stack<TreeNode> localStack = new Stack<>();    /*локальный стек для задания потомков элемента*/
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty()) {    /*покуда в общем стеке есть элементы*/
                TreeNode temp = globalStack.pop();    /*берем следующий, при этом удаляя его из стека*/
                if (temp != null) {
                    System.out.print(temp.getValue());    /*выводим его значение в консоли*/
                    localStack.push(temp.getLeftChild());    /*соохраняем в локальный стек, наследники текущего элемента*/
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;    /*при переходе на следующий уровень расстояние между элементами каждый раз уменьшается*/
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());    /*перемещаем все элементы из локального стека в глобальный*/
        }
        System.out.println(separator);    /*подводим черту*/
    }
}

/*Класс узла*/
class TreeNode {
    private String value;   /*Значение ключа узла*/
    private TreeNode leftChild;    /*Потомок левого узла*/
    private TreeNode rightChild;    /*Потомок правого узла*/

    /*Вывод узла дерева*/
    public void printNode() {
        System.out.println("The selected node has the value: " + value);
    }

    /*Получение значения узла дерева*/
    public String getValue() {
        return this.value;
    }

    /*Установка значения узла дерева*/
    public void setValue(final String value) {
        this.value = value;
    }

    /*Получение значения потомка левого узла*/
    public TreeNode getLeftChild() {
        return this.leftChild;
    }

    /*Получение значения потомка правого узла*/
    public TreeNode getRightChild() {
        return this.rightChild;
    }

    /*Установка значения потомка левого узла*/
    public void setLeftChild(final TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    /*Установка значения потомка правого узла*/
    public void setRightChild(final TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node {" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
