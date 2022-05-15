package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class TreeMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Node<String> root = new Node<>("root");

        /*Node<String> root = createTree();
        printTree(root, "-");*/

        boolean flag = false;
        String gap = "    ";
        System.out.println("""
                1 - Generate tree
                3 - Output all tree
                0 - Exit
                """);
        /*Node<String> parent = null;
        Node<String> leaf = null;*/
        while (!flag) {
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();
            switch (OperationNumber) {
                case 1 -> {
                    System.out.print(gap + "Enter the tree length: ");
                    int length = scan.nextInt();
                    for (int i = 0; i < length; i++) {
                        Node<String> parent = root.addChild(new Node<>(getString(5)));
                        for (int j = 0; j < length; j++) {
                            Node<String> leaf = parent.addChild(new Node<>(getString(5)));
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Enter the parent value: ");
                    String par = scan.next();
                    Node<String> newParent = new Node<>(par);
                    root.setParent(newParent);
                }
                case 3 -> {
                    System.out.println();
                    printTree(root, "-");
                    System.out.println();
                }
                case 0 -> flag = true;
            }
        }

    }
    /*private static Node<String> createTree() {
        Node<String> root = new Node<>("root");
        Node<String> node1 = root.addChild(new Node<String>("node 1"));
        Node<String> node11 = node1.addChild(new Node<String>("node 11"));
        Node<String> node111 = node11.addChild(new Node<String>("node 111"));
        Node<String> node112 = node11.addChild(new Node<String>("node 112"));
        Node<String> node12 = node1.addChild(new Node<String>("node 12"));
        Node<String> node2 = root.addChild(new Node<String>("node 2"));
        Node<String> node21 = node2.addChild(new Node<String>("node 21"));
        Node<String> node211 = node2.addChild(new Node<String>("node 22"));
        return root;
    }*/

    public static String getString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder Sbuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);
            Sbuffer.append(str.charAt(number));
        }
        return Sbuffer.toString();
    }

    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }
    public static class Node<T> {

        private T data = null;

        private final List<Node<T>> children = new ArrayList<>();

        private Node<T> parent = null;

        public Node(T data) {
            this.data = data;
        }

        public Node<T> addChild(Node<T> child) {
            child.setParent(this);
            this.children.add(child);
            return child;
        }

        public void addChildren(List<Node<T>> children) {
            children.forEach(each -> each.setParent(this));
            this.children.addAll(children);
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        private void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getParent() {
            return parent;
        }

    }
}