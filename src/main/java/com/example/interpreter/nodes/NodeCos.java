package com.example.interpreter.nodes;

public class NodeCos extends Node {
    @Override
    public double calc(double x) {
        return Math.cos(getLeft().calc(x));
    }
}
