package com.example.interpreter.nodes;

public abstract class Node {
    private Node left;
    private Node right;
    public abstract double calc(double x);
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
