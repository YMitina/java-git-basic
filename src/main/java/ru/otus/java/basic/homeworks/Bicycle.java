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

    public boolean ride(Human human, Application.TerrainType terrainType, float distance) {
        if (human == null) {
            System.out.println("Не возможно идентифицировать личность, поездка на вездеходе не возможна!");
            return false;
        }
        if (human.getCurrentTransport() == null) {
            System.out.println("Человек не сел на велосипед. Он идет пешком" + distance + " километров! ");
            human.setCurrentDistance(distance);
            human.setCurrentPower(distance * 5);
            return false;
        } else if (!(human.getCurrentTransport() instanceof Bicycle)) {
            System.out.println("Человек находится в другом транспорте, перемещение на велосипеде не возможно!");
            return false;
        }
        if (distance <= 0) {
            System.out.println("Дистанция должна иметь положительное значение");
            return false;
        }

        if (terrainType == Application.TerrainType.FOREST) {
            if ( human.getCurrentPower() < distance * 10 ) {
                System.out.println("Для поездки на велосипеде по лесу у Вас не достаточно сил!");
            }else {
                System.out.println(human.getName() + " проехал на велосипеде по лесу расстояние " + distance + " километров! ");
                human.setCurrentPower(distance * 10);
            }
        } else if (terrainType == Application.TerrainType.SWAMP) {
            System.out.println("Велосипед не может проехать по болоту! ");
            return false;
        } else if (terrainType == Application.TerrainType.PLAIN) {
            if ( human.getCurrentPower() < distance * 8 ) {
                System.out.println("Для поездки на велосипеде по равнине у Вас не достаточно сил!");
            }else {
                System.out.println(human.getName() + " проехал на велосипеде по равнине расстояние " + distance + " километров! ");
                human.setCurrentPower(distance * 8);
            }
        } else {
            System.out.println("Задан неизвестный тип местности, перемещение не возможно");
            return false;
        }
        mileage += distance;
        human.setCurrentDistance(distance);
        return true;
    }

}
