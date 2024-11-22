package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.Application.TerrainType;

public class Human {
    private String name;
    private Transport currentTransport;
    private float currentDistance;
    private float currentPower;


    public Human(String name, float currentDistance, float currentPower) {
        this.name = name;
        this.currentDistance = currentDistance;
        this.currentPower = currentPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrentTransport(Transport currentTransport) {
        this.currentTransport = currentTransport;
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }

    public void increaseCurrentDistance(float currentDistance) {
        this.currentDistance += currentDistance;
    }

    public float getCurrentDistance() {
        return currentDistance;
    }

    public void decreaseCurrentPower(float currentPower) {
        this.currentPower -= currentPower;
    }

    public float getCurrentPower() {
        return currentPower;
    }

    public void currentInfo() {
        System.out.println(name + " с начала прогулки преодолел дистанцию " + currentDistance + " километров. Его сила составляет " + currentPower + " единиц.");
    }

    public void ride(TerrainType terrainType, float distance) {
        if (distance <= 0) {
            System.out.println("Дистанция должна иметь положительное значение!");
            return;
        }
        if (currentTransport == null) {
            if (distance > currentPower) {
                System.out.println("Недостаточно сил для прогулки пешком! Он может пройти расстояние не более " + currentPower + " километров");
                return;
            } else {
                System.out.println(name + " идет пешком " + distance + " километров по типу местности " + terrainType.getRusName());
                decreaseCurrentPower(distance);
                increaseCurrentDistance(distance);
            }
        } else {
            if (currentTransport.getClass().getSimpleName().equals("Bicycle")) {
                if (distance > currentPower) {
                    System.out.println("Недостаточно сил для поездки на веслосипеде по типу местности " + terrainType.getRusName() + ", слишком большая дистанция в " + distance + " киллометров! Хватит только на растояние " + currentPower + " киломметров!");
                    return;
                }
            }
            if (currentTransport.ride(terrainType, distance)) {
                if (currentTransport.getClass().getSimpleName().equals("Bicycle")) {
                    decreaseCurrentPower(distance);
                }
                increaseCurrentDistance(distance);
            }
        }
    }
}
