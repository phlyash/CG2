package com.example.interpreter.nodes;

public class NodeValue extends Node {
    private final double value;
    public NodeValue(double value) {
        this.value = value;
    }
    @Override
    public double calc(double x) {
        return this.value;
    }
}
