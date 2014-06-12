package serie3.structures;

public class Tree<E extends Comparable> {//TODO: TEST THIS !!!!

    public static class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E value;

        public Node(E pval) {
            value = pval;
            left = right = null;
        }
    }

    MyComparator cmp = new MyComparator();

    public static final int PRE_ORDER = 1;
    public static final int IN_ORDER = 2;
    public static final int POST_ORDER = 3;

    private Node<E> root;

    public Tree() {
        root = null;
    }

    public Tree(Node<E> root1) {
        root = root1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return getSize(root);
    }

    private int getSize(Node<E> pRoot) {
        if (pRoot == null) return 0;
        else return 1 + getSize(pRoot.left) + getSize(pRoot.right);
    }

    public int height() {
        return getHeight(root);
    }

    private int getHeight(Node<E> pRoot) {
        if (pRoot == null) return 0;
        else return 1 + Math.max(getHeight(pRoot.left), getHeight(pRoot.right));
    }

    public E search(E pValue) {
        Node<E> node = root;
        while (node != null && cmp.compare(node.value, pValue) != 0) { ///........
            if (cmp.compare(node.value, pValue) > 0) node = node.left;
            else node = node.right;
        }
        if (node != null) return node.value;
        else return null;
    }

    public E getMin() {
        if (root == null) return null;

        Node<E> node = root;
        while (node.left != null) node = node.left;

        return node.value;
    }


    public E getMax() {
        if (root == null) return null;
        Node<E> node = root;
        while (node.right != null) node = node.right;

        return node.value;

    }

    public void insert(E pValue) {

        if (root == null) {
            root = new Node<E>(pValue);
            return;
        }

        Node<E> node = root, prev = null;

        while (node != null) {
            prev = node;
            if (cmp.compare(node.value, pValue) > 0) node = node.left;

            else if (cmp.compare(node.value, pValue) < 0) node = node.right;

            else return; // repetidos na sao inseridos

        }
        if (cmp.compare(prev.value, pValue) > 0)
            prev.left = new Node<E>(pValue);
        else prev.right = new Node<E>(pValue);

    }

    public E delete(E pValue) {
        if (root == null) return null;

        Node<E> delnode = root, prev = null;

        while (delnode != null && cmp.compare(delnode.value, pValue) != 0) {
            // travessia descendente ate encontrar o elemento a remover
            prev = delnode;
            if (cmp.compare(delnode.value, pValue) > 0) delnode = delnode.left;
            else delnode = delnode.right;
        }

        if (delnode == null) return null; // elemento inexistente na arvore
        Node<E> node = delnode; // guardar a referencia do no do elemento
        E elem = delnode.value;  // guardar a referencia do elemento

        if (node.right == null) node = node.left; // ligar a esquerda
        else if (node.left == null) node = node.right; // ligar a direita
        else {
            // procurar menor elemento da subArvore direita
            Node<E> tmp = node.right, prevtmp = node;
            while (tmp.left != null) {
                prevtmp = tmp;
                tmp = tmp.left; // substituir pelo menor elemento
            }
            // desligar o menor elemento e ajustar as ligacoes
            if (prevtmp == root) prevtmp.right = tmp.right;
            else prevtmp.left = tmp.right;
            return elem;
        }

        // ajustar o pai do elemento removido que so tem um filho
        if (delnode == root) root = node; // caso tenha sido removido da raiz
        else if (prev.left == delnode) prev.left = node;
        else prev.right = node;

        return elem;

    }

    public String toString() {
        if (root == null) return "arvore vazia\n";
        else return getString(root, 1);

    }

    private String getString(Node<E> pRoot, int pHeight) {
        String str = "";
        if (pRoot == null) {
            for (int i = 0; i < pHeight; ++i) str += "\t";
            str += "*\n";
            return str;
        }
        str += getString(pRoot.right, pHeight + 1);
        for (int i = 0; i < pHeight; ++i) str += "\t";
        str += pRoot.value.toString() + "\n";
        str += getString(pRoot.left, pHeight + 1);
        return str;
    }

    @SuppressWarnings("unchecked")
    public void balance() {
        if (root == null) return;
        int n = size();
        E[] list = (E[]) new Comparable[n]; // criar a sequencia
        listInOrder(root, list, 0); // preenche-la com a travessia em ordem
        Tree<E> newTree = new Tree<E>(); // criar uma nova arvore;
        newTree.getBalance(list, 0, n - 1); // inserir elementos na nova arvore
        root = newTree.root; // actualizar a raiz


    }

    private void getBalance(E[] pList, int pBegin, int pEnd) {
        if (pBegin <= pEnd) {
            int med = (pBegin + pEnd) >>> 1; // (pBegin + pEnd )/2
            insert(pList[med]);            // inserir o elemento central
            getBalance(pList, pBegin, med - 1); // subarvore esquerda
            getBalance(pList, med + 1, pEnd); // subasvore direita

        }
    }

    private int listInOrder(Node<E> pRoot, E[] pList, int pInd) {
        int i = pInd;
        if (pRoot != null) {
            i = listInOrder(pRoot.left, pList, i);
            pList[i++] = pRoot.value;
            i = listInOrder(pRoot.right, pList, i);
        }
        return i;

    }

    public Node<E> getRoot() {
        return root;
    }

    public String traversal(int pTrav) {

        if (root == null) return "Arvore vazia";
        String str = "";

        switch (pTrav) {
            case PRE_ORDER:
                str = "pre order :";
                str = preOrderRec(root, str);
                break;
            case IN_ORDER:
                str = "em ordem :";
                str = inOrderRec(root, str);
                break;
            case POST_ORDER:
                str = "pre order :";
                str = postOrderRec(root, str);
                break;

        }
        return str + "\n";


    }

    private String postOrderRec(Node<E> pRoot, String pStr) {
        if (pRoot != null) {

            pStr = postOrderRec(pRoot.left, pStr);
            pStr = postOrderRec(pRoot.right, pStr);
            pStr += "\t" + pRoot.value.toString();
        }
        return pStr;
    }

    private String inOrderRec(Node<E> pRoot, String pStr) {
        if (pRoot != null) {

            pStr = inOrderRec(pRoot.left, pStr);
            pStr += "\t" + pRoot.value.toString();
            pStr = inOrderRec(pRoot.right, pStr);
        }
        return pStr;
    }

    private String preOrderRec(Node<E> pRoot, String pStr) {
        if (pRoot != null) {
            pStr += "\t" + pRoot.value.toString();
            pStr = preOrderRec(pRoot.left, pStr);
            pStr = preOrderRec(pRoot.right, pStr);
        }
        return pStr;
    }

}
