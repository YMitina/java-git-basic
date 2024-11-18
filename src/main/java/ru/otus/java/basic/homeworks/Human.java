package ru.otus.java.basic.homeworks;

public class Human {
    private String name;
    private Transport currentTransport;
    private float currentDistance;
    private float currentPower;


    public Human(String name, Transport currentTransport, float currentDistance, float currentPower) {
        this.name = name;
        this.currentTransport = currentTransport;
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

    public void setCurrentDistance(float currentDistance) {
        this.currentDistance += currentDistance;
    }

    public float getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentPower(float currentPower) {
        this.currentPower -= currentPower;
    }

    public float getCurrentPower() {
        return currentPower;
    }

    public void currentInfo() {
        System.out.println(name + " с начала прогулки преодолел дистанцию " + currentDistance + " километров. Его сила составляет " + currentPower + " единиц.");
    }
}
