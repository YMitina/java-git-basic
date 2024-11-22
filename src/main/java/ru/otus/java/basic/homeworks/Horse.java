package ru.otus.java.basic.homeworks;

public class Horse implements Transport {
    private float power;
    private float currentDistance;

    public Horse(float power, float currentDistance) {
        this.power = power;
        this.currentDistance = currentDistance;
    }

    public void info() {
        System.out.println("Информация о лошадке:");
        System.out.println("Сила лошадки: " + power + " единиц.");
        System.out.println("Пройденная дистанция  " + currentDistance + " километров.");
    }

    public boolean ride(Application.TerrainType terrainType, float distance) {
        Application.TerrainType[] impassableAreas;
        impassableAreas = new Application.TerrainType[]{Application.TerrainType.SWAMP};
        for (Application.TerrainType impassableArea : impassableAreas) {
            if (terrainType == impassableArea) {
                System.out.println("Лошадка не может перемещаться по типу местности " + terrainType.getRusName() + " !");
                return false;
            }
        }

        if (power < distance) {
            System.out.println("У лощадке не хватает сил на " + distance + " километров! Вы можете проехать не более " + power + " километров!");
            return false;
        }

        System.out.println("Лошадка проскакала по типу местности " + terrainType.getRusName() + " на растояние " + distance + " километров! ");
        power -= distance;
        currentDistance += distance;
        return true;
    }

}
