package com.example.interpreter.nodes;

public class NodeMultiply extends Node {
    public double calc(double x) {
        return this.getRight().calc(x) * this.getLeft().calc(x);
    }
}
