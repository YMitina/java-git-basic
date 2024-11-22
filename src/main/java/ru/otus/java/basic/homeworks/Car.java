package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.Application.TerrainType;

public class Car implements Transport {
    private float amountOfFuel;
    private float mileage;
    private final static float FUEL_CONSUMPTION = 10;

    public Car(float amountOfFuel, float mileage) {
        this.amountOfFuel = amountOfFuel;
        this.mileage = mileage;
    }

    public void info() {
        System.out.println("Информация о машине:");
        System.out.println("Количество топлива в баке: " + amountOfFuel + " литров бензина.");
        System.out.println("Пробег машины состовляет  " + mileage + " километров.");
        System.out.println("Расход топлива составляет " + FUEL_CONSUMPTION + " литвов на 100 километров.");
    }

    public boolean ride(TerrainType terrainType, float distance) {
        TerrainType[] impassableAreas;
        impassableAreas = new TerrainType[]{TerrainType.SWAMP, TerrainType.FOREST};
        for (TerrainType impassableArea : impassableAreas) {
            if (terrainType == impassableArea) {
                System.out.println("Машина не может перемещаться по типу местности " + terrainType.getRusName() + " !");
                return false;
            }
        }
        if (amountOfFuel < distance * FUEL_CONSUMPTION / 100) {
            System.out.println("В машине не хватит топливо на " + distance + " километров! Вы можете проехать не более " + amountOfFuel * 100 / FUEL_CONSUMPTION + " километров! ");
            return false;
        } else {
            System.out.println("Машина переместилась по типу местности " + terrainType.getRusName() + " на расстояние " + distance + " километров!");
            mileage += distance;
            amountOfFuel -= distance * FUEL_CONSUMPTION / 100;
            return true;
        }
    }
}
