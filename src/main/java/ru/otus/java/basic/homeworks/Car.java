package ru.otus.java.basic.homeworks;

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

    public boolean ride(Human human, Application.TerrainType terrainType, float distance) {
        if (human == null) {
            System.out.println("Не возможно идентифицировать личность, поездка на машине не возможна!");
            return false;
        }
        if (human.getCurrentTransport() == null) {
            System.out.println("Человек не сел в машину. Он идет пешком " + distance + " километров! ");
            human.setCurrentDistance(distance);
            human.setCurrentPower(distance * 5);
            return false;
        } else if (!(human.getCurrentTransport() instanceof Car)) {
            System.out.println("Человек находится в другом транспорте, перемещение на машине не возможно!");
            return false;
        }
        if (distance <= 0) {
            System.out.println("Дистанция должна иметь положительное значение!");
            return false;
        }

        if (terrainType == Application.TerrainType.FOREST) {
            System.out.println("Машина не может перемещаться по густому лесу!");
            return false;
        } else if (terrainType == Application.TerrainType.SWAMP) {
            System.out.println("Машина не может перемещаться по болоту!");
            return false;
        } else if (terrainType == Application.TerrainType.PLAIN) {
            if (amountOfFuel < distance * FUEL_CONSUMPTION / 100) {
                System.out.println("В машине не хватит топливо на " + distance + " километров! Вы можете проехать не более " + amountOfFuel * 100 / FUEL_CONSUMPTION + " километров! ");
                return false;
            } else {
                mileage += distance;
                amountOfFuel -= distance * FUEL_CONSUMPTION / 100;
                System.out.println(human.getName() + " проехал на машина по равнине расстояние " + distance + " километров! ");
                human.setCurrentDistance(distance);
                return true;
            }
        } else {
            System.out.println("Задан неизвестный тип местности, перемещение не возможно");
            return false;
        }
    }
}
