package ru.otus.java.basic.homeworks;

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

    public boolean ride(Human human, Application.TerrainType terrainType, float distance) {
        if (human == null) {
            System.out.println("Не возможно идентифицировать личность, поездка на вездеходе не возможна!");
            return false;
        }
        if (human.getCurrentTransport() == null) {
            System.out.println("Человек не сел на вездеход. Он идет пешком"+ distance + " километров! ");
            human.setCurrentDistance(distance);
            human.setCurrentPower(distance * 5);
            return false;
        } else if (!(human.getCurrentTransport() instanceof AllTerrainVehicle)) {
            System.out.println("Человек находится в другом транспорте, перемещение на вездеходе не возможно!");
            return false;
        }
        if (distance <= 0) {
            System.out.println("Дистанция должна иметь положительное значение");
            return false;
        }
        if (amountOfFuel < distance * FUEL_CONSUMPTION / 100) {
            System.out.println("В вездеходе не хватит топливо на " + distance + " километров! Вы можете проехать не более " + amountOfFuel * 100 / FUEL_CONSUMPTION);
            return false;
        }

        if (terrainType == Application.TerrainType.FOREST) {
            System.out.println(human.getName() + " проехал на вездеходе по лесу расстояние " + distance + " километров! ");
        } else if (terrainType == Application.TerrainType.SWAMP) {
            System.out.println(human.getName() + " проехал на вездеходе по болоту расстояние " + distance + " километров! ");
        } else if (terrainType == Application.TerrainType.PLAIN) {
            System.out.println(human.getName() + " проехал на вездеходе по равнине расстояние " + distance + " километров! ");
        } else {
            System.out.println("Задан неизвестный тип местности, перемещение не возможно");
            return false;
        }
        mileage += distance;
        amountOfFuel -= distance * FUEL_CONSUMPTION / 100;
        human.setCurrentDistance(distance);
        return true;
    }

}
