package Tree;

import java.util.*;

class TreeMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Node<String> root = new Node<>("root");
        Node<String> parent = null;

        boolean flag = false;
        String gap = "    ";
        System.out.println("""
                1 - Generate tree
                2 - Output all tree
                3 - Search for an input element
                0 - Exit
                """);

        while (!flag) {
            System.out.print("Enter the number of operation: ");
            int OperationNumber = scan.nextInt();
            switch (OperationNumber) {
                case 1 -> {
                    System.out.print(gap + "Enter the tree length: ");
                    int length = scan.nextInt();
                    for (int i = 0; i < length; i++) {
                        parent = root.addChild(new Node<>(getString(5)));
                        for (int j = 0; j < length; j++) {
                            Node<String> leaf = parent.addChild(new Node<>(getString(5)));
                        }
                    }
                }
                case 2 -> {
                    System.out.println();
                    printTree(root, "-");
                    System.out.println();
                }
                /*Need refactor*/
                case 3 -> {
                    System.out.print(gap + "Enter the search values: ");
                    String value = scan.next();
                    search(root, value);
                    search(parent, value);
                }
                case 0 -> flag = true;
            }
        }

    }

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
    /*Need refactor*/
    private static <T> void search(Node<T> node, String Data) {
        int counter = 0;
        for (Node<T> child : node.getChildren()) {
            counter++;
            if (Objects.equals(child.getData().toString(), Data)) {
                System.out.println("Found in " + counter + " line on tree");
            }
            else {
                System.out.println("Not Found in " + counter + " line on tree");
            }
        }
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

        private void remove(Node<T> child) {
            if (child.children.size() > 0) {
                Node<T> root = child.getChildren().get(child.getChildren().size() - 1);
                root.setParent(child.getParent());
                child.getChildren().remove(child.getChildren().size() - 1);
                root.children.addAll(child.getChildren());
                for (int i = 0; i < child.getParent().getChildren().size(); i++) {
                    if (child.getParent().getChildren().get(i) == child) {
                        child.getParent().getChildren().set(i, root);
                    }
                }
            } else {
                for (int i = 0; i < child.getParent().getChildren().size(); i++) {
                    if (child.getParent().getChildren().get(i) == child) {
                        child.getParent().getChildren().remove(i);
                    }
                }
            }
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