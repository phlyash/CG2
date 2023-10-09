package com.example.interpreter;

import com.example.interpreter.nodes.*;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    private Node root;
    private static final Pattern expressionPattern = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+|\\+|-|\\*|/|x|\\)|\\(|\\^|sin\\(.*?\\)|cos\\(.*?\\)"); //
    public Expression(String expression) {
        buildTree(expression);
    }
    private void buildTree(String expression) {
        Stack<Node> nodeStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        Matcher matcher = expressionPattern.matcher(expression);

        while(matcher.find()) {
            String substring = expression.substring(matcher.start(), matcher.end());
            switch (substring) {
                case "x" -> nodeStack.push(new NodeX());
                case "(" -> operatorStack.push('(');
                case ")" -> {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        simplifyStack(nodeStack, operatorStack);
                    }
                    operatorStack.pop();
                }
                case "+", "-", "/", "*", "^" -> {
                    while (!operatorStack.isEmpty() && precedence(substring.charAt(0)) <= precedence(operatorStack.peek())) {
                        simplifyStack(nodeStack, operatorStack);
                    }
                    operatorStack.push(substring.charAt(0));
                }
                default -> {
                    if (substring.contains("sin")) nodeStack.push(parseFunction(new NodeSin(), substring));
                    else if (substring.contains("cos")) nodeStack.push(parseFunction(new NodeCos(), substring));
                    else nodeStack.push(new NodeValue(Double.parseDouble(substring)));
                }
            }
            }

        while (!operatorStack.isEmpty()) {
            simplifyStack(nodeStack, operatorStack);
        }
        root = nodeStack.pop();
        }
    private int precedence(char operation) {
        return switch (operation) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }
    private Node parseFunction(Node node, String expression) {
        node.setLeft(new Expression(expression.substring(4, expression.length() - 1)).getRoot());
        return node;
    }

    private void simplifyStack(Stack<Node> nodeStack, Stack<Character> operatorStack) {
        Node rightNode = nodeStack.pop();
        Node leftNode = nodeStack.pop();
        char operator = operatorStack.pop();

        Node operatorNode = nodeForOperation(operator);
        operatorNode.setLeft(rightNode);
        operatorNode.setRight(leftNode);

        nodeStack.push(operatorNode);
    }

    private Node nodeForOperation(char operation) {
        return switch (operation) {
            case '+' -> new NodePlus();
            case '-' -> new NodeMinus();
            case '*' -> new NodeMultiply();
            case '/' -> new NodeDivide();
            case '^' -> new NodePow();
            default -> null;
        };
    }
    public Node getRoot() {
        return root;
    }
}
