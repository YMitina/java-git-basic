package ru.otus.java.basic.homeworks;

public class Plate {
    private int maxAmountFood;
    private int currentAmountFood;

    public Plate(int maxAmountFood) {
        this.maxAmountFood = maxAmountFood;
        this.currentAmountFood = maxAmountFood;
    }

    public int getCurrentAmountFood() {
        return currentAmountFood;
    }

    protected void addFood(int amountFood) {
        if (currentAmountFood + amountFood > maxAmountFood) {
            System.out.println("Привышен лимит размера тарелки, выможете добавить не более " + (maxAmountFood - currentAmountFood));
            return;
        } else {
            currentAmountFood += amountFood;
        }
    }

    protected boolean dropFood(int amountFood) {
        if (currentAmountFood >= amountFood) {
            currentAmountFood -= amountFood;
            return true;
        }
        return false;
    }
}
