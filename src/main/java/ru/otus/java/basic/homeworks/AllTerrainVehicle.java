package ru.otus.java.basic.homeworks;

import static ru.otus.java.basic.homeworks.Application.*;

public class AllTerrainVehicle implements Transport {
    private float amountOfFuel;
    private float mileage;
    private final static float FUEL_CONSUMPTION = 20;

    public AllTerrainVehicle(float amountOfFuel, float mileage) {
        this.amountOfFuel = amountOfFuel;
        this.mileage = mileage;
    }

    public void info() {
        System.out.println("Информация о вездеходе:");
        System.out.println("Количество топлива в баке: " + amountOfFuel + " литров бензина.");
        System.out.println("Пробег вездехода состовляет  " + mileage + " километров.");
        System.out.println("Расход вездехода составляет " + FUEL_CONSUMPTION + " литвов на 100 километров.");
    }

    public boolean ride(TerrainType terrainType, float distance) {
        if (amountOfFuel < distance * FUEL_CONSUMPTION / 100) {
            System.out.println("В вездеходе не хватит топливо на " + distance + " километров! Вы можете проехать не более " + amountOfFuel * 100 / FUEL_CONSUMPTION);
            return false;
        }
        System.out.println("Вездеход переместилась по типу местности " + terrainType.getRusName() + " на расстояние " + distance + " километров!");
        mileage += distance;
        amountOfFuel -= distance * FUEL_CONSUMPTION / 100;
        return true;
    }


}
