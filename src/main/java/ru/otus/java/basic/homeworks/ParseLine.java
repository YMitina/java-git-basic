package ru.otus.java.basic.homeworks;

public class ParseLine{
    private int beginPosition;
    private int endPosition;
    private char currentSymbol;

    public char getCurrentSymbol() {
        return currentSymbol;
    }

    public void LineProcessor() {
        this.beginPosition = 0;
        this.endPosition = 0;
    }

    public String searchToSymbol(String inStr, char symbol, boolean newLine) {
        try {
            checkNewLine(newLine);
            endPosition = inStr.indexOf(symbol, beginPosition);
            return inStr.substring(beginPosition, endPosition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (symbol == Symbols.TWO_POINTS.getValue()) {
                beginPosition = endPosition + 2;
            } else {
                beginPosition = endPosition + 1;
            }
        }
    }

    public String searchToSymbolsOr(String inStr, char symbol1, char symbol2, boolean newLine) {
        try {
            checkNewLine(newLine);
            endPosition = inStr.indexOf(symbol1, beginPosition);
            currentSymbol = symbol1;
            if (endPosition == -1) {
                endPosition = inStr.indexOf(symbol2, beginPosition);
                currentSymbol = symbol2;
            }
            return inStr.substring(beginPosition, endPosition);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            beginPosition = endPosition + 1;
        }
    }

    public String searchToEnd(String inStr) {
        try {
            return inStr.substring(beginPosition, inStr.length());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            checkNewLine(true);
        }
    }

    public void checkNewLine(boolean newLine) {
        if (newLine) {
            this.beginPosition = 0;
            this.endPosition = 0;
        }
    }

}
