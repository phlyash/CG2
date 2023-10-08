package com.example.interpreter.nodes;

public class NodeSin extends Node {
    @Override
    public double calc(double x) {
        return Math.sin(getLeft().calc(x));
    }
}
