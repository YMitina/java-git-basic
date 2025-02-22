package ru.otus.java.basic.homeworks;

public enum Symbols {
    SPACE (' '),
    QUESTION ('?'),
    AND('&'),
    EQUELS ('='),
    TWO_POINTS (':');

    private char value;

    Symbols(char value) {
        this.value =value;
    }

    public char getValue() {
        return value;
    }
}
