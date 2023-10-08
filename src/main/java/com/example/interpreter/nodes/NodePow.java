package com.example.interpreter.nodes;

public class NodePow extends Node {
    @Override
    public double calc(double x) {
        return Math.pow(this.getRight().calc(x), this.getLeft().calc(x));
    }
}
