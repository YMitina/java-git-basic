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

    public boolean ride(Human human, Application.TerrainType terrainType, float distance) {
        if (human == null) {
            System.out.println("Не возможно идентифицировать личность, прогулка на лошадке не возможна!");
            return false;
        }
        if (human.getCurrentTransport() == null) {
            System.out.println("Человек не сел на лошадку. Он идет пешком " + distance + " километров! ");
            human.setCurrentDistance(distance);
            return false;
        } else if (!(human.getCurrentTransport() instanceof Horse)) {
            System.out.println("Человек находится в другом транспорте, перемещение на лошадке не возможно!");
            return false;
        }
        if (distance <= 0) {
            System.out.println("Дистанция должна иметь положительное значение!");
            return false;
        }
        if (power < distance) {
            System.out.println("У лощадке не хватает сил на " + distance + " километров! Вы можете проехать не более " + power + " километров!");
            return false;
        }

        if (terrainType == Application.TerrainType.FOREST) {
            System.out.println(human.getName() + " проскакал на лошадке по лесу расcтояние " + distance + " километров! ");
            power -= distance;

        } else if (terrainType == Application.TerrainType.SWAMP) {
            System.out.println("Лошадка не может катать по болоту! ");
            return false;

        } else if (terrainType == Application.TerrainType.PLAIN) {
            System.out.println(human.getName() + " проскакал на лошадке по равнине расстояние " + distance + " километров! ");
            power -= distance;

        } else {
            System.out.println("Задан неизвестный тип местности, перемещение на лошадки не возможно!");
            return false;
        }
        currentDistance += distance;
        human.setCurrentDistance(distance);
        return true;
    }

}
