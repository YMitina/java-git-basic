package ru.otus.java.basic.homeworks;

public class Bicycle implements Transport {
    private float mileage;

    public Bicycle(float mileage) {
        this.mileage = mileage;
    }

    public void info() {
        System.out.println("Информация о велосипеде:");
        System.out.println("Пробег состовляет: " + mileage + " километров.");
    }

    public boolean ride(Application.TerrainType terrainType, float distance) {
        Application.TerrainType[] impassableAreas;
        impassableAreas = new Application.TerrainType[]{Application.TerrainType.SWAMP};
        for (Application.TerrainType impassableArea : impassableAreas) {
            if (terrainType == impassableArea) {
                System.out.println("Велосипед не может перемещаться по типу местности " + terrainType.getRusName() + " !");
                return false;
            }
        }
        System.out.println("Велосипед переместился по типу местности " + terrainType.getRusName() + " на растояние " + distance + " километров!");
        mileage += distance;
        return true;
    }
}
